package e_food_additive.zenolbs.com.efoodadditive.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import e_food_additive.zenolbs.com.efoodadditive.R;
import e_food_additive.zenolbs.com.efoodadditive.model.E;

/**
 * Created by grd on 9/17/17.
 */


/*
public class MyRVAdapter{

}
*/

 public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.ViewHolder> {

    private List<E> eList = null;
    private ArrayList<E> arraylist;

    public MyRVAdapter(List<E> elist) {

        MyRVAdapter.this.eList = elist;
        MyRVAdapter.this.arraylist = new ArrayList<E>();//init
        MyRVAdapter.this.arraylist.addAll(elist);//set values
    }

    // Create new views (invoked by the layout manager)
    // Создает новые views (вызывается layout manager-ом)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
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

        public TextView txtViewTitle, tvCode, tvName, tvDanger;
        public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.tickerSymbol);

            tvCode = (TextView) itemLayoutView.findViewById(R.id.e_code);
            tvName = (TextView) itemLayoutView.findViewById(R.id.e_name);
            tvDanger = (TextView) itemLayoutView.findViewById(R.id.e_danger);

        }
    }

    // Return the size of your itemsData (invoked by the layout manager)
    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return eList.size();
    }

    //==============================FILTER=========================================
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







