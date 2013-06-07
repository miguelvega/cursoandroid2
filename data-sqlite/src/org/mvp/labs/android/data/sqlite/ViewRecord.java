package org.mvp.labs.android.data.sqlite;

import java.util.ArrayList;

import org.mvp.labs.android.data.sqlite.util.DatabaseHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRecord extends Activity {

	private ListView listview;

	TextView totalrecords;
	DatabaseHelper db;
	public ArrayList<ProductBean> products = new ArrayList<ProductBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewrecord);
		totalrecords = (TextView) findViewById(R.id.totalrecords);
		listview = (ListView) findViewById(R.id.listview);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		products.clear();

		db = new DatabaseHelper(getApplicationContext());
		db.getWritableDatabase();
		ArrayList<ProductBean> product_list = db.getProudcts();

		for (int i = 0; i < product_list.size(); i++) {

			int tid = product_list.get(i).getId();

			String tname = product_list.get(i).getProductname();
			float tprice = product_list.get(i).getProductprice();

			ProductBean product = new ProductBean();

			product.setId(tid);
			product.setProductname(tname);
			product.setProductprice(tprice);

			products.add(product);
			
			Log.v("aaaaaaaaaaaaaaaaaaaa", product.getProductname()+" -- "+product.getProductprice());
		}
		totalrecords.setText("Nro. registros : " + products.size());
		listview.setAdapter(new ListAdapter(this));
		db.close();

	}

	private class ListAdapter extends BaseAdapter {
		LayoutInflater inflater;
		ViewHolder viewHolder;

		public ListAdapter(Context context) {
			// TODO Auto-generated constructor stub
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return products.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {

				convertView = inflater.inflate(R.layout.listview_row, null);
				viewHolder = new ViewHolder();

				viewHolder.txt_pname = (TextView) convertView
						.findViewById(R.id.txtdisplaypname);
				viewHolder.txt_pprice = (TextView) convertView
						.findViewById(R.id.txtdisplaypprice);

				viewHolder.txtid = (TextView) convertView
						.findViewById(R.id.txtdisplaypid);
				convertView.setTag(viewHolder);

			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.txt_pname.setText(products.get(position)
					.getProductname().trim());
			viewHolder.txt_pprice.setText(String.valueOf(products.get(position)
					.getProductprice()));

			viewHolder.txtid.setText(products.get(position).getId()+"");

			final int temp = position;
			(convertView.findViewById(R.id.btn_update))
			.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {

					int _productid = products.get(temp).getId();
					String _productname = products.get(temp)
							.getProductname();
					float price = products.get(temp)
							.getProductprice();
					
					Log.v("aaaaaaaaaaaaaaaaa", "prcio..."+price);

					Intent intent = new Intent(ViewRecord.this,
							AddUpdateValues.class);

					Bundle bundle = new Bundle();
					bundle.putInt("id", _productid);
					bundle.putString("name", _productname);
					bundle.putFloat("price", price);
					intent.putExtras(bundle);
					startActivity(intent);

				}
			});

			(convertView.findViewById(R.id.btn_delete))
			.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {

					AlertDialog.Builder alertbox = new AlertDialog.Builder(
							ViewRecord.this);
					alertbox.setCancelable(true);
					alertbox.setMessage("estas seguro de eliminarlo?");
					alertbox.setPositiveButton("Si",
							new DialogInterface.OnClickListener() {

						public void onClick(
								DialogInterface arg0, int arg1) {

							Log.i(">>>TEMP>>>", temp + "");
							Log.i(">>>getIdno>>>>>>",
									products.get(temp)
									.getId()
									+ "");
							System.out
							.println(">>>getIdno>>>>>>"
									+ products
									.get(temp)
									.getId());
							db.removeProduct(products.get(temp).getId());

							ViewRecord.this.onResume();

							Toast.makeText(
									getApplicationContext(),
									"Registro eliminado...",
									Toast.LENGTH_SHORT).show();

						}

					});
					alertbox.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
						public void onClick(
								DialogInterface arg0, int arg1) {

						}
					});
					alertbox.show();
				}
			});
			return convertView;

		}
	}

	private class ViewHolder {
		TextView txt_pname;
		TextView txt_pprice;
		TextView txtid;
	}

}
