package com.metacube.firstapp;

import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class StoreDetailFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private SupportMapFragment supportMapFragment;
    private Toolbar toolbar;
    private TextView backTv;
    private AppCompatActivity activity;
    private MaterialButton storeDirectionBtn, callStoreBtn, orderFoodBtn;
    private StoreHoursAdapter storeHoursAdapter;
    private ViewPager viewPager;
    private List<StoreHoursModel> models;

    public StoreDetailFragment() {
    }

    public static StoreDetailFragment newInstance() {
        return new StoreDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_detail, container, false);
        models = new ArrayList<>();
        models.add(new StoreHoursModel("Sunday", "9:30 AM to 10:30 Pm"));
        models.add(new StoreHoursModel("Monday", "9:30 AM to 10:30 Pm"));
        models.add(new StoreHoursModel("Tuesday", "9:30 AM to 10:30 Pm"));
        models.add(new StoreHoursModel("Wednesday", "9:30 AM to 10:30 Pm"));
        models.add(new StoreHoursModel("Thursday", "9:30 AM to 10:30 Pm"));
        models.add(new StoreHoursModel("Friday", "9:30 AM to 10:30 Pm"));
        models.add(new StoreHoursModel("Saturday", "9:30 AM to 10:30 Pm"));
        init(view);
        methodListener();
        return view;
    }

    private void init(View view) {
        supportMapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }
        toolbar = view.findViewById(R.id.toolbar);
        backTv = view.findViewById(R.id.back_tv);
        storeDirectionBtn = view.findViewById(R.id.store_direction_btn);
        activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        callStoreBtn = view.findViewById(R.id.call_store_btn);
        orderFoodBtn = view.findViewById(R.id.order_food_btn);
        viewPager = view.findViewById(R.id.view_pager);
        storeHoursAdapter = new StoreHoursAdapter(models, getContext());
        viewPager.setAdapter(storeHoursAdapter);
        viewPager.setPadding(120, 0, 120, 0);
    }

    private void methodListener() {

        backTv.setOnClickListener(v -> {
        });

        storeDirectionBtn.setOnClickListener(v -> {
        });

        callStoreBtn.setOnClickListener(v -> {
        });

        orderFoodBtn.setOnClickListener(v -> {
        });

        viewPager.setPageTransformer(false, pageTransformer);
    }

    private ViewPager.PageTransformer pageTransformer = (view, position) -> {
        if (position >= 1) {
            view.setAlpha(0.5f);
            changeViewColor(view, false);
        } else if (position < 1 && position >= 0) {
            view.setAlpha(1);
            changeViewColor(view, true);
        } else {
            view.setAlpha(0.5f);
            changeViewColor(view, false);
        }
    };

    private void changeViewColor(View view, boolean isVisible) {
        final int color = isVisible ? getResources().getColor(R.color.rouge) :
                getResources().getColor(R.color.brown_grey);
        ((TextView) view.findViewById(R.id.day_tv)).setTextColor(color);
        view.findViewById(R.id.line_view).setBackgroundColor(color);
        ((TextView) view.findViewById(R.id.store_timing_tv)).setTextColor(color);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng jaipur = new LatLng(26.9124, 75.7873);
        googleMap.addMarker(
                new MarkerOptions()
                        .position(jaipur)
                        .title("Jaipur")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_star))
        );
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(jaipur));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
