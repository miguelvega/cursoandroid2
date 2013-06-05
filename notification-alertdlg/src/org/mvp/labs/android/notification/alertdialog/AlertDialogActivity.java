package org.mvp.labs.android.notification.alertdialog;

import com.androidhive.alertdialog.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btnAlert = (Button) findViewById(R.id.btnAlert);
		Button btnAlertTwoBtns = (Button) findViewById(R.id.btnAlertWithTwoBtns);
		Button btnAlertThreeBtns = (Button) findViewById(R.id.btnAlertWithThreeBtns);

		btnAlert.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// Creating alert Dialog with one Button

				AlertDialog alertDialog = new AlertDialog.Builder(
						AlertDialogActivity.this).create();

				// Setting Dialog Title
				alertDialog.setTitle("AlertDialog");

				// Setting Dialog Message
				alertDialog.setMessage("Bienvenido  la unidad 4");

				// Setting Icon to Dialog
				alertDialog.setIcon(R.drawable.tick);

				// Setting OK Button
				alertDialog.setButton("Aceptar",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// Write your code here to execute after dialog
								// closed
								Toast.makeText(getApplicationContext(),
										"Ten un buen día", Toast.LENGTH_SHORT)
										.show();
							}
						});

				// Showing Alert Message
				alertDialog.show();

			}
		});


		/**
		 * AlertDialog con dos botones, un Positive Button con la etiqueta 
		 * "Si" otro Negative Button con etiqueta "No"
		 */
		btnAlertTwoBtns.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// Creating alert Dialog with two Buttons

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlertDialogActivity.this);

				// Setting Dialog Title
				alertDialog.setTitle("Confirme eliminación...");

				// Setting Dialog Message
				alertDialog.setMessage("Estas seguro de borrarlo?");

				// Setting Icon to Dialog
				alertDialog.setIcon(R.drawable.delete);

				// Setting Positive "Yes" Button
				alertDialog.setPositiveButton("Si",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which) {
								// Write your code here to execute after dialog
								Toast.makeText(getApplicationContext(), "Has confirmado la eliminación", Toast.LENGTH_SHORT).show();
							}
						});
				// Setting Negative "NO" Button
				alertDialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int which) {
								// Write your code here to execute after dialog
								Toast.makeText(getApplicationContext(), "Has cancelado la eliminación", Toast.LENGTH_SHORT).show();
								dialog.cancel();
							}
						});

				// Showing Alert Message
				alertDialog.show();

			}
		});

		
		/**
		 * AlertDialog con dos botones un Positive Button con etiqueta 
		 * "Si" un Neutral Button con etiqueta "No" un Negative Button con
		 * etiqueta "Cancelar"
		 */
		btnAlertThreeBtns.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// Creating alert Dialog with three Buttons

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						AlertDialogActivity.this);

				// Setting Dialog Title
				alertDialog.setTitle("Guardar cambios...");

				// Setting Dialog Message
				alertDialog.setMessage("Estas seguro de aceptar los cambios?");

				// Setting Icon to Dialog
				alertDialog.setIcon(R.drawable.save);

				// Setting Positive Yes Button
				alertDialog.setPositiveButton("Si",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// User pressed Cancel button. Write Logic Here
								Toast.makeText(getApplicationContext(),
										"El archivo se ha guardado",
										Toast.LENGTH_SHORT).show();
							}
						});
				// Setting Positive Yes Button
				alertDialog.setNeutralButton("No",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// User pressed No button. Write Logic Here
								Toast.makeText(getApplicationContext(),
										"El archivo no se ha guardado", Toast.LENGTH_SHORT)
										.show();
							}
						});
				// Setting Positive "Cancel" Button
				alertDialog.setNegativeButton("Cancelar",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// User pressed Cancel button. Write Logic Here
								Toast.makeText(getApplicationContext(),
										"Sigue pensando que hacer...",
										Toast.LENGTH_SHORT).show();
							}
						});
				// Showing Alert Message
				alertDialog.show();

			}
		});
	}
}