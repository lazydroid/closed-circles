package com.closedcircles.reloaded.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.closedcircles.reloaded.R;
import com.closedcircles.reloaded.WebConnectionManager;
import com.closedcircles.reloaded.model.Account;


public class FragmentCircles extends android.support.v4.app.ListFragment {
	private Typeface typeface;

	public static final FragmentCircles newInstance() {
		FragmentCircles f = new FragmentCircles();
		return f;
	}

	public FragmentCircles() {
		super();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		CirclesActivity activity = (CirclesActivity) getActivity();
		setListAdapter(activity.getCirclesAdapter());
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		CirclesActivity activity = (CirclesActivity) getActivity();
		Account account = WebConnectionManager.get().account();
		account.setSelectedCircle(account.getCircle(position));
		activity.getPager().setCurrentItem(1, true);
		account.setSelectedThreadId(-1); // clear thread selection
		account.setSelectedMsgId(-1); // clear msg selection
		FragmentMessages fragmentMessages = (FragmentMessages) activity.getSupportFragmentManager().findFragmentByTag(
				"android:switcher:" + R.id.pager + ":" + CirclesActivity.FRAGMENT_MESSAGES);
		if (fragmentMessages != null) {
			fragmentMessages.updateList(false, false);
		}
		FragmentThreads fragmentThreads = (FragmentThreads) activity.getSupportFragmentManager().findFragmentByTag(
				"android:switcher:" + R.id.pager + ":" + CirclesActivity.FRAGMENT_THREADS);
		if (fragmentThreads != null)
			fragmentThreads.updateList();
		l.setItemChecked(position, true);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_circles, container, false);
	}
}

