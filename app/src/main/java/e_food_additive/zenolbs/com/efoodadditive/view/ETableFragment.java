package e_food_additive.zenolbs.com.efoodadditive.view;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import e_food_additive.zenolbs.com.efoodadditive.App;
import e_food_additive.zenolbs.com.efoodadditive.controller.AssetLoader;
import e_food_additive.zenolbs.com.efoodadditive.model.E;
import e_food_additive.zenolbs.com.efoodadditive.controller.MainActivity;
import e_food_additive.zenolbs.com.efoodadditive.R;
import e_food_additive.zenolbs.com.efoodadditive.model.References;
import e_food_additive.zenolbs.com.efoodadditive.view.adapter.ClickListener;
import e_food_additive.zenolbs.com.efoodadditive.view.adapter.DividerItemDecoration;
import e_food_additive.zenolbs.com.efoodadditive.view.adapter.MyRVAdapter;
import e_food_additive.zenolbs.com.efoodadditive.view.adapter.RecyclerTouchListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ETableFragment extends Fragment implements SearchView.OnQueryTextListener  {


    public ETableFragment() {
        // Required empty public constructor
    }

    SearchView search;
    MyRVAdapter rvAdapter;
    ArrayList<E> arraylist = new ArrayList<E>();
    RecyclerView recyclerView;


    private final String KEY_RECYCLER_STATE = "recycler_state";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);

        search=(SearchView) rootView.findViewById(R.id.search_view);
        search.setQueryHint("Введите номер...");


        for (int i = 0; i < References.eCode1.length; i++)
        {
            E wp = new E(References.ePath[i], References.eCode1[i],References.eCodeName[i], References.danger[i]);
            arraylist.add(wp);
        }

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAdapter = new MyRVAdapter(arraylist);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        //--------------- Animation --------------------------
        MainActivity.loadSettingsAnimation();

           if(App.allowAnimation) {
               Animation animation = AnimationUtils
                       .loadAnimation(getActivity(), App.animId);

               LayoutAnimationController controller = new LayoutAnimationController(animation);
               controller.setOrder(LayoutAnimationController.ORDER_NORMAL);

               controller.setDelay((float) 0.5);
               recyclerView.setLayoutAnimation(controller);
               recyclerView.startLayoutAnimation();
           }
        //--------------------------------------------------------------------


         // RecyclerView: Implementing single listChooserItem click and long press (Part-II)
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                String myFile = arraylist.get(position).getPath();
                Intent intent = new Intent(getActivity().getApplicationContext(), AssetLoader.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// не рекомендуется проблемы task с стеком activity
                intent.putExtra("fname", myFile);
                getActivity().getApplicationContext().startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
               // Toast.makeText(getActivity(), "Long press on position :"+position,
                //        Toast.LENGTH_LONG).show();
                 Toast.makeText(getActivity(), "Информация о пищевых добавках может меняться в зависимости от законодальства определенной страны",
                        Toast.LENGTH_LONG).show();
            }
        }));

        recyclerView.setAdapter(rvAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        search.setOnQueryTextListener(this);


       // AdView adView = (AdView) rootView.findViewById(R.id.adView);
       // AdRequest adRequest = new AdRequest.Builder().build();
      //  adView.loadAd(adRequest);

        return rootView;

    }

    //---------------------------searchView
    //Вызывается, когда текст запроса изменяется пользователем.
    @Override
    public boolean onQueryTextChange(String newText) {

       // adapterRV.filter(newText);
        rvAdapter.filter(newText);
        return false;
    }

    //Вызывается, когда пользователь отправляет запрос.
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void onPause()
    {
        super.onPause();

        // save RecyclerView state
        App.mBundleRecyclerViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        App.mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        // restore RecyclerView state
        if (App.mBundleRecyclerViewState != null) {
            Parcelable listState = App.mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }



}
