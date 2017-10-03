package e_food_additive.zenolbs.com.efoodadditive.view.settings;


import android.app.Fragment;
import android.os.Bundle;

import android.preference.PreferenceFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import e_food_additive.zenolbs.com.efoodadditive.R;

/**
 * A simple {@link Fragment} subclass.
 */

/*
Сам фрагмент SettingsFragment наследуется от класса PreferenceFragment.

В его методе onCreate() вызывается метод addPreferencesFromResource(),
 в который передается id ресурса xml с настройками (в данном
 случае ранее определенный ресурс R.xml.settings).

 */
public class SettingsFragment extends PreferenceFragment {


    public SettingsFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.my_preference);

    }

}
