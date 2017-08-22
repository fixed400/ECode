package e_food_additive.zenolbs.com.efoodadditive.view;


import android.app.Fragment;
import android.os.Bundle;

import android.preference.PreferenceFragment;

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
/*
    SharedPreferences mPrefs;

    @Override
    public void onStart(){
        super.onStart();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.my_preference);
        // getActivity().setTheme(android.R.style.Theme_Holo);
/*
        CheckBoxPreference disableInternet = (CheckBoxPreference)findPreference("chb3");
        if(disableInternet != null)
        {
            disableInternet.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object checkState) {

                    boolean isNetToBeDisabled = ((Boolean) checkState).booleanValue();

                    SharedPreferences.Editor e = mPrefs.edit();
                    //storing preference values & access anywhere in programme
                    e.putBoolean("chb3", isNetToBeDisabled);
                    e.commit();

                    return true;
                }
            });

            disableInternet.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference arg0) {

                    getActivity().setTheme(android.R.style.Theme_Holo);

                    Toast.makeText(getActivity(), "Preference listChooserItem is clicked", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        */
    } //------onCreate()

/*
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view != null){
           // view.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.background_dark));
           // view.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.darker_gray));
            view.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.background_light));
        }

    }
    */


}
