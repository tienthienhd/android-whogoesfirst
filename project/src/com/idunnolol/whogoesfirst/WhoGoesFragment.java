package com.idunnolol.whogoesfirst;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WhoGoesFragment extends Fragment {

	public static final String TAG = WhoGoesFragment.class.getName();

	private static final String ARG_NUM_PLAYERS = "ARG_NUM_PLAYERS";
	private static final String ARG_PLAYER_PICKED = "ARG_PLAYER_PICKED";

	public static WhoGoesFragment newInstance(int numPlayers, int playerPicked) {
		WhoGoesFragment fragment = new WhoGoesFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_NUM_PLAYERS, numPlayers);
		args.putInt(ARG_PLAYER_PICKED, playerPicked);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_who_goes, container, false);

		TextView whoTextView = Ui.findView(v, R.id.who_text_view);
		whoTextView.setText(createText());

		return v;
	}

	// Determine the text based on the # of players 
	private CharSequence createText() {
		Bundle args = getArguments();
		int numPlayers = args.getInt(ARG_NUM_PLAYERS);
		int playerPicked = args.getInt(ARG_PLAYER_PICKED);

		String text = null;
		String highlight = null;
		int highlightColor = getResources().getColor(android.R.color.holo_blue_light);

		if (playerPicked == 0) {
			// You go first
			text = getString((numPlayers == 1) ? R.string.you_go_first_duh : R.string.you_go_first);
			highlight = getString(R.string.you_go_first_highlight);
		}
		else if (numPlayers == 2) {
			// Special case; if there are two players but you don't first
			text = getString(R.string.other_player_goes_first);
			highlight = getString(R.string.other_player_goes_first_highlight);
		}
		else if (playerPicked == 1) {
			// Player clockwise (aka, one left) goes first
			text = getString(R.string.player_directly_left);
			highlight = getString(R.string.player_directly_left_highlight);
		}
		else if (playerPicked == numPlayers - 1) {
			// Player counterclockwise (aka, one right) goes first
			text = getString(R.string.player_directly_right);
			highlight = getString(R.string.player_directly_right_highlight);
		}
		else {
			int textResId;
			int highlightResId;
			int val;

			// Figure out quickest way to get to the player (left used if equidistance)
			if (playerPicked <= numPlayers / 2) {
				val = playerPicked;
				textResId = R.string.player_to_left_TEMPLATE;
				highlightResId = R.string.player_to_left_highlight_TEMPLATE;
			}
			else {
				val = numPlayers - playerPicked;
				textResId = R.string.player_to_right_TEMPLATE;
				highlightResId = R.string.player_to_right_highlight_TEMPLATE;
			}

			String textVal;
			switch (val) {
			case 2:
				textVal = getString(R.string.two_full);
				break;
			case 3:
				textVal = getString(R.string.three_full);
				break;
			case 4:
				textVal = getString(R.string.four_full);
				break;
			case 5:
				textVal = getString(R.string.five_full);
				break;
			case 6:
				textVal = getString(R.string.six_full);
				break;
			default:
				textVal = Integer.toString(val);
				break;
			}

			text = getString(textResId, textVal);
			highlight = getString(highlightResId, textVal);
		}

		return Ui.createHighlightedText(text, highlight, highlightColor);
	}
}