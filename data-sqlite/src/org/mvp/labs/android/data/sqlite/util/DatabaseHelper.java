package org.mvp.labs.android.data.sqlite.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.mvp.labs.android.data.sqlite.ProductBean;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

	public static String DATABASENAME = "simpledatabase";
	public static String PRODUCTTABLE = "products";
	public static String colProductId = "prod_id";
	//public static String _colProductid = "productidno";
	public static String colProductName = "prod_name";
	public static String colProductPrice = "prod_price";
	private ArrayList<ProductBean> cartList = new ArrayList<ProductBean>();
	Context c;

	public DatabaseHelper(Context context) {
		super(context, DATABASENAME, null, 33);
		c = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// db.execSQL("CREATE TABLE if not exists " + PRODUCTTABLE + "("
		// + colProductId + " INTEGER PRIMARY KEY AUTOINCREMENT , "
		// + "productidno" + "TEXT," + colProductName + " TEXT ,"
		// + colProductPrice + " TEXT)");

		db.execSQL("CREATE TABLE if not exists "+PRODUCTTABLE+" (prod_id INTEGER PRIMARY KEY  NOT NULL  UNIQUE , prod_name VARCHAR, prod_price NUMERIC)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + PRODUCTTABLE);
		onCreate(db);
	}

	public void addProduct(ProductBean product) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(colProductId, product.id);
		contentValues.put(colProductName, product.productname);
		contentValues.put(colProductPrice, product.productprice);
		db.insert(PRODUCTTABLE, null, contentValues);
		db.close();
	}

	// update

	public void updateProduct(ProductBean product) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(colProductName, product.productname);
		contentValues.put(colProductPrice, product.productprice);
		
		db.update(PRODUCTTABLE, contentValues, colProductId+"="
				+ product.id, null);
		
		db.close();
	}

	public void emptyProduct() {
					SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("delete from "+PRODUCTTABLE);
			db.close();
		
	}

	public void removeProduct(int productid) {
		try {
			String[] args = { productid+"" };
			getWritableDatabase().delete(PRODUCTTABLE, colProductId+"=?", args);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ProductBean> getProudcts() {

		cartList.clear();

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from "+PRODUCTTABLE, null);
		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					ProductBean item = new ProductBean();

					item.id = cursor.getInt(cursor
							.getColumnIndex(colProductId));

					item.productname = cursor.getString(cursor
							.getColumnIndex(colProductName));

					item.productprice = cursor.getFloat(cursor
							.getColumnIndex(colProductPrice));

					cartList.add(item);

				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return cartList;
	}
}
