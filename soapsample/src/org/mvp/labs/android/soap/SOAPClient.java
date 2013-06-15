package org.mvp.labs.android.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;


public class SOAPClient extends AsyncTask<String, Void, String>{

	public static String SOAP_ACTION="logonAction";
	public static String METHOD_NAME="logon";
	public static String NAMESPACE="http://kdd.sartawi.org/queryws";
	public static String DOT_NAME="";
	public static String URI="http://192.168.10.11:8080/kddws?wsdl";
	
	public static String PARAM_USERNAME="username";
	public static String PARAM_PASSWORD="password";
	public static String USERNAME="mvega";
	public static String PASSWORD="mvega";

	Receiver receiver;
	public SOAPClient(Receiver receiver){
		this.receiver = receiver;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
		try {
			 
		    // Modelo el request
		    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); //namespace, Metodo
		    request.addProperty(PARAM_USERNAME, USERNAME); // Paso parametros al WS
		    request.addProperty(PARAM_PASSWORD, PASSWORD);
		 
		    // Model el Sobre
		    SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    sobre.dotNet = true;
		    sobre.setOutputSoapObject(request);
		 
		    // Modelo el transporte
		    HttpTransportSE transporte = new HttpTransportSE(URI);//url
		    transporte.debug= true;
		    
		    // Llamada
		    transporte.call(SOAP_ACTION, sobre);//Constants.SOAP_ACTION
		    // Resultado
		    SoapObject resultado = (SoapObject) sobre.getResponse();
		    
		    sb.append(resultado.getPrimitivePropertyAsString("fullName"));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		receiver.onLoad(result);
	}
	
	public interface Receiver{
		void onLoad(String input);
	}
}