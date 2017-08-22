package e_food_additive.zenolbs.com.efoodadditive.view;

/**
 * Created by adex on 13.04.16.
 */

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import e_food_additive.zenolbs.com.efoodadditive.R;


//import android.support.v4.app.Fragment;
public class SecondFragment extends Fragment  {
    private static final String TAG = "myLogs";

    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

      //  MobileAds.initialize(getActivity().getApplicationContext(), "ca-app-pub-9704805163544705~6590519476");

        AdView adView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        return rootView;
    }



}