package org.mvp.labs.android.data.complexcp;

import org.mvp.labs.android.data.complexcp.data.TodoObj;
import org.mvp.labs.android.data.complexcp.provider.TodoContentProvider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TodoDetailActivity extends Activity{
	private Spinner categorySpinner;
	private EditText titleText;
	private EditText bodyText;

	private Uri todoUri;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.todo_edit);

		categorySpinner = (Spinner) findViewById(R.id.category);
		titleText = (EditText) findViewById(R.id.todo_edit_summary);
		bodyText = (EditText) findViewById(R.id.todo_edit_description);
		Button confirmButton = (Button) findViewById(R.id.todo_edit_button);

		Bundle extras = getIntent().getExtras();

		// Check from the saved Instance
		todoUri = (bundle == null) ? null : (Uri) bundle
				.getParcelable(TodoContentProvider.CONTENT_ITEM_TYPE);

		// Or passed from the other activity
		if (extras != null) {
			todoUri = extras
					.getParcelable(TodoContentProvider.CONTENT_ITEM_TYPE);

			fillData(todoUri);
		}

		confirmButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (TextUtils.isEmpty(titleText.getText().toString())) {
					makeToast();
				} else {
					setResult(RESULT_OK);
					finish();
				}
			}

		});
	}

	private void fillData(Uri uri) {
		String[] projection = { TodoObj.COLUMN_SUMMARY,
				TodoObj.COLUMN_DESCRIPTION, TodoObj.COLUMN_CATEGORY };
		Cursor cursor = getContentResolver().query(uri, projection, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();
			String category = cursor.getString(cursor
					.getColumnIndexOrThrow(TodoObj.COLUMN_CATEGORY));

			for (int i = 0; i < categorySpinner.getCount(); i++) {
				String s = (String) categorySpinner.getItemAtPosition(i);
				if (s.equalsIgnoreCase(category)) {
					categorySpinner.setSelection(i);
				}
			}

			titleText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(TodoObj.COLUMN_SUMMARY)));
			bodyText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(TodoObj.COLUMN_DESCRIPTION)));

			// Always close the cursor
			cursor.close();
		}
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putParcelable(TodoContentProvider.CONTENT_ITEM_TYPE, todoUri);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	private void saveState() {
		String category = (String) categorySpinner.getSelectedItem();
		String summary = titleText.getText().toString();
		String description = bodyText.getText().toString();

		// Only save if either summary or description
		// is available

		if (description.length() == 0 && summary.length() == 0) {
			return;
		}

		ContentValues values = new ContentValues();
		values.put(TodoObj.COLUMN_CATEGORY, category);
		values.put(TodoObj.COLUMN_SUMMARY, summary);
		values.put(TodoObj.COLUMN_DESCRIPTION, description);

		if (todoUri == null) {
			// New todo
			todoUri = getContentResolver().insert(TodoContentProvider.CONTENT_URI, values);
		} else {
			// Update todo
			getContentResolver().update(todoUri, values, null, null);
		}
	}

	private void makeToast() {
		Toast.makeText(TodoDetailActivity.this, "Please maintain a summary",
				Toast.LENGTH_LONG).show();
	}
}
