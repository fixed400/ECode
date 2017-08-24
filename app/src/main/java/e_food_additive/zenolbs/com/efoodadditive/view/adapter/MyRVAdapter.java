package e_food_additive.zenolbs.com.efoodadditive.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import e_food_additive.zenolbs.com.efoodadditive.R;
import e_food_additive.zenolbs.com.efoodadditive.model.E;
import e_food_additive.zenolbs.com.efoodadditive.view.EFragment;

/**
 * Created by fixoid on 8/24/17.
 */

//public class MyRVAdapter extends RecyclerView.Adapter<EFragment.MyRVAdapter.ViewHolder> {
public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.ViewHolder> {

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

            MyRVAdapter.ViewHolder viewHolder = new MyRVAdapter.ViewHolder(itemLayoutView);

            return viewHolder;
        }

        // Replace the contents of a view (invoked by the layout manager)
        // Заменяет контент отдельного view (вызывается layout manager-ом)
        @Override
        public void onBindViewHolder(MyRVAdapter.ViewHolder viewHolder, int position) {

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

        private EFragment.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final EFragment.ClickListener clicklistener){

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
*/
    //------------------------------------------------------

    /**
     * RecyclerView: Implementing single listChooserItem click and long press (Part-II)
     *
     * - creating an Interface for single tap and long press
     * - Parameters are its respective view and its position
     * */

    /*
    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }
    */

    /*
    interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }
    */


    //==============================================================================

