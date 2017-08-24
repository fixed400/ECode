package e_food_additive.zenolbs.com.efoodadditive.view;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import e_food_additive.zenolbs.com.efoodadditive.controller.AssetLoad;
import e_food_additive.zenolbs.com.efoodadditive.model.E;
import e_food_additive.zenolbs.com.efoodadditive.controller.MainActivity;
import e_food_additive.zenolbs.com.efoodadditive.R;
import e_food_additive.zenolbs.com.efoodadditive.model.References;
import e_food_additive.zenolbs.com.efoodadditive.view.adapter.MyRVAdapter;
import e_food_additive.zenolbs.com.efoodadditive.view.adapter.RecyclerTouchListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class EFragment extends Fragment implements SearchView.OnQueryTextListener  {


    public EFragment() {
        // Required empty public constructor
    }


    //----------------------------------------


    // SearchView searchView;
    SearchView search;
    MyRVAdapter mAdapter;
    ArrayList<E> arraylist = new ArrayList<E>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);

        search=(SearchView) rootView.findViewById(R.id.search_view);
        search.setQueryHint("Введите номер...");


        for (int i = 0; i < References.eCode1.length; i++)
        {

            //GREATE OVVERLOAD CONSTRUCTOR VARIANT for FIND RUS CHAR E

            //  IF CHAR RUS
            //  IF CHAR ENGL

            //  http://ru.stackoverflow.com/questions/161270/%D0%9E%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5-%D1%8F%D0%B7%D1%8B%D0%BA%D0%B0-%D0%BA%D0%BB%D0%B0%D0%B2%D0%B8%D0%B0%D1%82%D1%83%D1%80%D1%8B-%D0%B2-android

            E wp = new E(References.ePath[i], References.eCode1[i],References.eCodeName[i], References.danger[i]);
            arraylist.add(wp);
        }

        //-------------------------set RV-------------------------------
        // 1. get a reference to recyclerView
        //RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_list_rv);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 3. create an adapter
         mAdapter = new MyRVAdapter(arraylist);

        // 4. set adapter
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        //--------------- Animation --------------------------

        MainActivity.loadSettingsAnimation();

           if(MainActivity.allowAnimation) {

               Log.d("EfRAGMENT","ITEM IS "+MainActivity.animId);
               Log.d("EfRAGMENT","list_chooser --- "+MainActivity.listChooserItem);//NULL

               Animation  animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), MainActivity.animId);
              // Animation  animation = AnimationUtils.loadSettingsAnimation(getActivity().getApplicationContext(), R.anim.scaling);

               LayoutAnimationController controller = new LayoutAnimationController(animation);
               controller.setOrder(LayoutAnimationController.ORDER_NORMAL);

               controller.setDelay((float) 0.5);
               recyclerView.setLayoutAnimation(controller);
               recyclerView.startLayoutAnimation();
           }
        //--------------------------------------------------------------------

        /**
         * RecyclerView: Implementing single listChooserItem click and long press (Part-II)
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                String myFile = arraylist.get(position).getPath();
                Intent intent = new Intent(getActivity().getApplicationContext(), AssetLoad.class);
                // intent не видет нужного context (скорее класса с которого запускается) - одно из решений использовать флаг
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// не рекомендуется проблемы task стеком activity
                intent.putExtra("fname", myFile);
                getActivity().getApplicationContext().startActivity(intent);
                //-------------------------------------------------------

            }

            @Override
            public void onLongClick(View view, int position) {
               // Toast.makeText(getActivity(), "Long press on position :"+position,
              //          Toast.LENGTH_LONG).show();
            }
        }));

                recyclerView.setAdapter(mAdapter);
        // 5. set listChooserItem animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        search.setOnQueryTextListener(this);
        return rootView;

    }


    //---------------------------searchView
    //Вызывается, когда текст запроса изменяется пользователем.
    @Override
    public boolean onQueryTextChange(String newText) {

       // adapterRV.filter(newText);
        mAdapter.filter(newText);
        return false;
    }


    //Вызывается, когда пользователь отправляет запрос.
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    //---------------------------------------

    /*

    private static class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.ViewHolder> {

        private List<E> eList = null;
        private ArrayList<E> arraylist;

        public MyRVAdapter(List<E> elist) {

            MyRVAdapter.this.eList = elist;
            MyRVAdapter.this.arraylist = new ArrayList<E>();
            MyRVAdapter.this.arraylist.addAll(elist);
        }

        // Create new views (invoked by the layout manager)
        // Создает новые views (вызывается layout manager-ом)
        @Override
        public MyRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            View itemLayoutView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_fragment_row, null);

            ViewHolder viewHolder = new ViewHolder(itemLayoutView);

            return viewHolder;
        }

        // Replace the contents of a view (invoked by the layout manager)
        // Заменяет контент отдельного view (вызывается layout manager-ом)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {

            viewHolder.txtViewTitle.setText(eList.get(position).getName());
            E item = eList.get(position);
            //---------------------------------------------
            Integer colorRes;
            switch (item.getDanger())
            {

                default:
                case 0:
                    // colorRes = mContext.getResources().getColor(R.id.red);
                    // colorRes = 0xFFff0000;
                    colorRes = 0xAAF0BD00;
                    break;
                case 1:
                    //colorRes = mContext.getResources().getColor(R.id.green);
                    colorRes = 0xFF71D123;
                    break;
                case -1:
                    //colorRes = mContext.getResources().getColor(R.id.blue);
                    colorRes =  0xAAF0000F;

                    break;

            }
            viewHolder.itemView.setBackgroundColor(colorRes);


        }

        // inner class to hold a reference to each listChooserItem of RecyclerView
        // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
        // отдельного пункта списка
        public static class ViewHolder extends RecyclerView.ViewHolder {

            public TextView txtViewTitle,tv2,tv,tv3;
            public ImageView imgViewIcon;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);

                txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.tickerSymbol);
                // imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.imageView);

                 tv2 = (TextView) itemLayoutView.findViewById(R.id.e_code);
                 tv = (TextView) itemLayoutView.findViewById(R.id.e_name);
                 tv3 = (TextView) itemLayoutView.findViewById(R.id.e_danger);

            }
        }



        // Return the size of your itemsData (invoked by the layout manager)
        // Возвращает размер данных (вызывается layout manager-ом)
        @Override
        public int getItemCount() {
            return eList.size();
        }


        //=======================================================================
        // Filter Class
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            eList.clear();
            if (charText.length() == 0) {
                eList.addAll(arraylist);
            }
            else
            {
                for (E wp : arraylist)
                {
                    if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText))
                    {
                        eList.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

*/
    //=================================Cлушатель нажатий RecyclerVIEW ============

    /**
     * RecyclerView: Implementing single listChooserItem click and long press (Part-II)
     *
     * - creating an innerclass implementing RevyvlerView.OnItemTouchListener
     * - Pass clickListener interface as parameter
     * */
    //public static interface OnItemTouchListener
        /*
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    } //------------END----CLASS--RecyclerTouchListener

    //------------------------------------------------------
*/
    /**
     * RecyclerView: Implementing single listChooserItem click and long press (Part-II)
     *
     * - creating an Interface for single tap and long press
     * - Parameters are its respective view and its position
     * */

    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }


    //==============================================================================


}
