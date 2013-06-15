package org.mvp.labs.android.fragments.complex;

import java.util.Arrays;
import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * This is a top levefragment, showing a list of items that the
 * user can pick.  Upon picking an item, it takes care of displaying the
 * data to the user as appropriate, based on the current screen size
 * and orientation.
 */

public class TitlesFragment extends ListFragment {
	boolean mDualPane;
	int mCurCheckPosition = 0;

	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Populate list with our static array of titles.
		
		// Use a custom adapter so we can have something more than the just the text view filled in.
		setListAdapter (new CustomAdapter (getActivity (), R.layout.complex_list_item, 
				R.id.text1, Arrays.asList (Shakespeare.TITLES)));

		// Check to see if we have a frame in which to embed the details
		// fragment directly in the containing UI.
		View detailsFrame = getActivity().findViewById(R.id.details);
		mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			// Restore last state for checked position.
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
		}

		if (mDualPane) {
			// In dual-pane mode, the list view highlights the selected item.
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			// Make sure our UI is in the correct state.
			showDetails(mCurCheckPosition);
		}
	}

	/**
	 */

	@Override 
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}

	/**
	 * Helper function to show the details of a selected item, either by
	 * displaying a fragment in-place in the current UI, or starting a
	 * whole new activity in which it is displayed.
	 */
	void showDetails(int index) {
		mCurCheckPosition = index;

		if (mDualPane) {
			// We can display everything in-place with fragments, so update
			// the list to highlight the selected item and show the data.
			getListView().setItemChecked(index, true);

			// Check what fragment is currently shown, replace if needed.
			DetailsFragment details = (DetailsFragment)
					getFragmentManager().findFragmentById(R.id.details);
			if (details == null || details.getShownIndex() != index) {
				// Make new fragment to show this selection.
				details = DetailsFragment.newInstance(index);

				// Execute a transaction, replacing any existing fragment
				// with this one inside the frame.
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.details, details);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}

		} else {
			// Otherwise we need to launch a new activity to display
			// the dialog fragment with selected text.
			Intent intent = new Intent();
			intent.setClass(getActivity(), DetailsActivity.class);
			intent.putExtra("index", index);
			startActivity(intent);
		}
	}

	/**
	 */
	// CustomAdapter

	/**
	 * CustomAdapter
	 *
	 */

	private class CustomAdapter extends ArrayAdapter<String> {

		private Context mContext;
		private int mLayoutId;

		/**
		 * Constructor
		 */

		public CustomAdapter(Context context, int layoutId, int textViewResourceId, List<String> items) {
			super(context, textViewResourceId, items);
			mContext = context;
			mLayoutId = layoutId;
		}

		/**
		 * getView
		 *
		 * Return a view that displays an item in the array.
		 *
		 */

		public View getView (int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate (mLayoutId, null);
			}

			View itemView = v;

			TextView tv1 = (TextView) itemView.findViewById (R.id.text1);
			if (tv1 != null) tv1.setText (new Integer (position+1).toString ());

			String text2 = getItem (position);
			TextView tv2 = (TextView) itemView.findViewById (R.id.text2);
			if (tv2 != null) tv2.setText (text2);

			return itemView;
		}

	} // end class CustomAdapter

} // end class TitlesFragment
