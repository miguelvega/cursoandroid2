package org.mvp.labs.android.data.sqlite;

import org.mvp.labs.android.data.sqlite.util.DatabaseHelper;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecord extends Activity implements OnClickListener {

	private Button btn_addrecord;
	private EditText txtpname, txtpprice, txtpid;
	DatabaseHelper db;
	ProductBean pm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addrecord);

		txtpname = (EditText) findViewById(R.id.txtpname);
		txtpprice = (EditText) findViewById(R.id.txtpprice);
		btn_addrecord = (Button) findViewById(R.id.btn_addrecord);

		txtpid = (EditText) findViewById(R.id.txtpid);
		btn_addrecord.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_addrecord:

			if (txtpname.getText().toString().equals("")
					|| txtpprice.getText().toString().equals("")) {
				Toast.makeText(AddRecord.this, "debes completar los campos",
						Toast.LENGTH_LONG).show();
			} else {

				db = new DatabaseHelper(getApplicationContext());
				db.getWritableDatabase();
				pm = new ProductBean();
				pm.id = Integer.parseInt(txtpid.getText().toString());
				pm.productname = txtpname.getText().toString();
				pm.productprice = Float.parseFloat(txtpprice.getText().toString());

				Log.i("idno,productname,productprice", "" + pm.id + ""
						+ pm.productname + "" + pm.productprice);
				db.addProduct(pm);
				Toast.makeText(AddRecord.this, "Registro agregado exitosamente.",
						Toast.LENGTH_LONG).show();
				
				finish();
			}
			break;

		default:
			break;
		}

	}
}
