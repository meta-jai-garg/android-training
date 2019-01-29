package com.metacube.firstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VerificationFragment extends Fragment {

    private TextView resendCodeTv;

    public VerificationFragment() {
    }


    public static VerificationFragment newInstance() {
        return new VerificationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verification, container, false);
//        resendCodeTv = view.findViewById(R.id.resend_code_tv);
//        resendCodeTv.setText(Html.fromHtml("<u><font color='red'>Underline Text</font></u>"));
        return view;
    }

}
