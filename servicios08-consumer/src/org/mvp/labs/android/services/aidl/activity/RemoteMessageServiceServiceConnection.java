package org.mvp.labs.android.services.aidl.activity;

import org.mvp.labs.android.services.aidl.IRemoteMessageService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class RemoteMessageServiceServiceConnection implements ServiceConnection {

	private static final String AIDL_MESSAGE_SERVICE_CLASS = ".AIDLMessageService";
	private static final String AIDL_MESSAGE_SERVICE_PACKAGE = "org.mvp.labs.android.services.aidl";
	private static final String APPSOLUT_INTENT_ACTION_BIND_MESSAGE_SERVICE = "org.mvp.labs.action.TimeService";
	private final DisplayRemoteMessage parent;
	private IRemoteMessageService service;

	public RemoteMessageServiceServiceConnection(
			DisplayRemoteMessage parent) {
		this.parent = parent;
	}

	private final static String LOG_TAG = RemoteMessageServiceServiceConnection.class.getCanonicalName();
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		Log.d(LOG_TAG, "Se ha conectado al servicio!");
		this.service = IRemoteMessageService.Stub.asInterface(service);
		Log.d(LOG_TAG, "Consultando la hora...");
		try {
			/*
			 * This call is required because the connect is an asynchronous call
			 * so the activity has to be notified that the connection is now 
			 * established and that the message was queried.
			 */
			parent.theMessageWasReceivedAsynchronously(this.service.getMessage());
		} catch (RemoteException e) {
			Log.e(LOG_TAG, "Ha ocurrido un error al conectarse.");
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.d(LOG_TAG, "Se ha perdido la conexion con el servicio inesperadamente!");
		service = null;
	}

	/**
	 * Method to disconnect the Service.
	 * This method is required because the onServiceDisconnected
	 * is only called when the connection got closed unexpectedly
	 * and not if the user requests to disconnect the service.
	 */
	public void safelyDisconnectTheService() {
		if(service != null) {
			service = null;
			parent.unbindService(this);
			Log.d(LOG_TAG, "La conexion al servicio ha sido cerrada.!");
		}
	}

	/**
	 * Method to connect the Service.
	 */
	public void safelyConnectTheService() {
		if(service == null) {
			Intent bindIntent = new Intent(APPSOLUT_INTENT_ACTION_BIND_MESSAGE_SERVICE);
			bindIntent.setClassName(AIDL_MESSAGE_SERVICE_PACKAGE, AIDL_MESSAGE_SERVICE_PACKAGE + AIDL_MESSAGE_SERVICE_CLASS);
			parent.bindService(bindIntent, this, Context.BIND_AUTO_CREATE);
			Log.d(LOG_TAG, "A punto de conectarse al servicio (llamada asincrona)!");
		}
	}

	/**
	 * Method to safely query the message from the remote service
	 */
	public void safelyQueryMessage() {
		Log.d(LOG_TAG, "Iniciando la consulta al servicio.");
		if(service == null) {	// if the service is null the connection is not established.
			Log.d(LOG_TAG, "El servicio no ha sido conectado > conectando.");
			safelyConnectTheService();
		} else {
			Log.d(LOG_TAG, "Se ha conectado al servicio -> consultando la hora.");
			try {
				parent.theMessageWasReceivedAsynchronously(service.getMessage());
			} catch (RemoteException e) {
				Log.e(LOG_TAG, "Ha ocurrido un error inesperado en la llamada.");
			}
		}
	}



}
