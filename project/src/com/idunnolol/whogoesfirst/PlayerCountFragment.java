package com.idunnolol.whogoesfirst;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlayerCountFragment extends Fragment {

	public static final String TAG = PlayerCountFragment.class.getName();

	private PlayerCountListener mListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (activity instanceof PlayerCountListener) {
			mListener = (PlayerCountListener) activity;
		}
		else {
			throw new RuntimeException("PlayerCountFragment Activity needs to implement listener!");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_player_count, container, false);

		// Rig up the button click listener
		Ui.findView(v, R.id.one_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.two_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.three_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.four_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.five_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.six_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.seven_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.eight_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.nine_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.ten_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.eleven_button).setOnClickListener(mButtonClickListener);
		Ui.findView(v, R.id.twelve_button).setOnClickListener(mButtonClickListener);

		// Highlight part of the inquiry text
		TextView inquiryTextView = Ui.findView(v, R.id.inquiry_text_view);
		String text = getString(R.string.player_count_inquiry);
		String highlight = getString(R.string.player_count_inquiry_highlight);
		int color = getResources().getColor(R.color.holo_blue_light);
		inquiryTextView.setText(Ui.createHighlightedText(text, highlight, color));

		return v;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_more:
			MorePlayersDialogFragment df = new MorePlayersDialogFragment();
			df.show(getFragmentManager(), MorePlayersDialogFragment.TAG);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private OnClickListener mButtonClickListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.one_button:
				mListener.onPlayerCountSelected(1);
				break;
			case R.id.two_button:
				mListener.onPlayerCountSelected(2);
				break;
			case R.id.three_button:
				mListener.onPlayerCountSelected(3);
				break;
			case R.id.four_button:
				mListener.onPlayerCountSelected(4);
				break;
			case R.id.five_button:
				mListener.onPlayerCountSelected(5);
				break;
			case R.id.six_button:
				mListener.onPlayerCountSelected(6);
				break;
			case R.id.seven_button:
				mListener.onPlayerCountSelected(7);
				break;
			case R.id.eight_button:
				mListener.onPlayerCountSelected(8);
				break;
			case R.id.nine_button:
				mListener.onPlayerCountSelected(9);
				break;
			case R.id.ten_button:
				mListener.onPlayerCountSelected(10);
				break;
			case R.id.eleven_button:
				mListener.onPlayerCountSelected(11);
				break;
			case R.id.twelve_button:
				mListener.onPlayerCountSelected(12);
				break;
			}
		}
	};
}
