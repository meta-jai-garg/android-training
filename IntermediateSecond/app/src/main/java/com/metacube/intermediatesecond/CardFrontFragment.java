package com.metacube.intermediatesecond;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CardFrontFragment extends Fragment {

    public CardFrontFragment() {
    }

    public static CardFrontFragment getInstance() {
        return new CardFrontFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_front, container, false);
    }

}
