package org.mvp.labs.android.data.sqlite;

import org.mvp.labs.android.data.sqlite.util.DatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUpdateValues extends Activity implements OnClickListener {
	private Button btn_updaterecord;
	private EditText txtpname, txtpprice;
	DatabaseHelper db;
	ProductBean pm;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addupdatevalues);

		i = getIntent();

		txtpname = (EditText) findViewById(R.id.txt_udatepname);
		txtpprice = (EditText) findViewById(R.id.txt_udatepprice);

		txtpname.setText(i.getExtras().getString("name"));
		txtpprice.setText(String.valueOf(i.getExtras().getFloat("price")));
		btn_updaterecord = (Button) findViewById(R.id.btn_updaterecord);
		btn_updaterecord.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_updaterecord:
			if (txtpname.getText().toString().equals("")
					|| txtpprice.getText().toString().equals("")) {
				Toast.makeText(AddUpdateValues.this, "por favor llena los campos",
						Toast.LENGTH_LONG).show();
			} else {

				db = new DatabaseHelper(getApplicationContext());
				db.getWritableDatabase();
				pm = new ProductBean();
				pm.productname = txtpname.getText().toString();
				pm.productprice = Float.parseFloat(txtpprice.getText().toString());
				pm.id = i.getExtras().getInt("id");

				Log.i(">>>>>productid<<<<<", "" + i.getExtras().getString("id"));
				db.updateProduct(pm);
				Toast.makeText(AddUpdateValues.this,
						"actualización exitosa.", Toast.LENGTH_LONG)
						.show();

				db.close();
				super.onResume();

			}
			break;
		}

	}

}
