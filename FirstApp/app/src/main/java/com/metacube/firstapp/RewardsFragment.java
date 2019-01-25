package com.metacube.firstapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RewardsFragment extends Fragment {

    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout linearLayoutBSheet;
    private ToggleButton tbUpDown;
    private ListView listView;
    private TextView txtCantante, txtCancion;
    private ContentLoadingProgressBar progbar;

    public RewardsFragment() {
    }

    public static RewardsFragment newInstance() {
        return new RewardsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);
        init(view);
        fillListView();
        tbUpDown.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View view, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    tbUpDown.setChecked(true);
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    tbUpDown.setChecked(false);
                }
            }

            @Override
            public void onSlide(View view, float v) {
            }
        });
        return view;
    }

    private void fillListView() {
        String[] firstName = {"50 Cent", "50 Cent", "50 Cent", "50 Cent", "50 Cent", "50 Cent",
                "50 Cent", "50 Cent"};
        String[] mail = {"Many Men", "Window Shopper",
                "Candy Shop", "Just a lil bit", "I'm the man", "P.I.M.P", "Wanksta",
                "Ayo technology"};


        ArrayList<Map<String, Object>> list = new ArrayList<>();


        for (int i = 0; i < firstName.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("Singer", firstName[i]);
            listItem.put("Title", mail[i]);

            list.add(listItem);
        }

        this.listView.setAdapter(getAdapterListViewCT(list));

        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            TextView txtSingerLV = view.findViewById(android.R.id.text1);
            TextView txtSingLV = view.findViewById(android.R.id.text2);
            txtCantante.setText(txtSingerLV.getText());
            txtCancion.setText(txtSingLV.getText());
            progbar.setProgress(getRandom());
        });
    }

    private SimpleAdapter getAdapterListViewCT(ArrayList<Map<String, Object>> list) {
        return new SimpleAdapter(getActivity().getApplicationContext(), list,
                android.R.layout.simple_list_item_2, new String[]{"Singer", "Title"},
                new int[]{android.R.id.text1, android.R.id.text2}) {
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView txtFirstName = view.findViewById(android.R.id.text1);
                txtFirstName.setTypeface(Typeface.DEFAULT_BOLD);
                TextView txtMail = view.findViewById(android.R.id.text2);
                txtMail.setTextColor(Color.DKGRAY);
                return view;
            }
        };
    }

    private int getRandom() {
        return (int) Math.floor(Math.random() * 100);
    }

    private void init(View view) {
        linearLayoutBSheet = view.findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBSheet);
        tbUpDown = view.findViewById(R.id.toggleButton);
        listView = view.findViewById(R.id.listView);
        txtCantante = view.findViewById(R.id.txtCantante);
        txtCancion = view.findViewById(R.id.txtCancion);
        progbar = view.findViewById(R.id.progbar);
    }

}
