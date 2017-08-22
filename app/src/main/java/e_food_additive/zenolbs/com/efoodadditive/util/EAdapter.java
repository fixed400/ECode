package e_food_additive.zenolbs.com.efoodadditive.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.widget.BaseAdapter;
import android.widget.TextView;

import e_food_additive.zenolbs.com.efoodadditive.R;
import e_food_additive.zenolbs.com.efoodadditive.controller.AssetLoad;
import e_food_additive.zenolbs.com.efoodadditive.model.E;

/**
 SimpleAdapter – это расширенный ArrayAdapter.
 Если вы делаете ListView и у вас каждый пункт списка содержит не один TextView,
 а несколько, то вы используете SimpleAdapter.
 Кроме наследуемых методов SimpleAdapter содержит методы по наполнению View-элементов значениями из
 Map – setViewImage, setViewText, setViewBinder.
 Т.е. видим, что он умеет работать не только с текстом, но и с изображениями.
 Метод setViewBinder – отличная штука, позволяет вам написать свой парсер значений из
 Map в View-элементы и адаптер будет использовать его.
 */

public class EAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<E> eList = null;
    private ArrayList<E> arraylist;
    private static final int bgColor = 0xAAAAFFFF;//Default

    public EAdapter(Context context, List<E> elist) {
        mContext = context;
        this.eList = elist;

        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<E>();
        this.arraylist.addAll(elist);
    }

    public class ViewHolder {

        TextView hCode;
        TextView hName;
        TextView hDanger; //int
    }

    @Override
    public int getCount() {
        return eList.size();
    }

    @Override
    public E getItem(int position) {
        return eList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public View getView(final int position, View convertView, ViewGroup parent) {

        E item = getItem(position);

        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();


            convertView= inflater.inflate(R.layout.e_list_item, null);

            convertView.setBackgroundColor(bgColor);

            TextView tv2 = (TextView) convertView.findViewById(R.id.e_code);
            TextView tv = (TextView) convertView.findViewById(R.id.e_name);
            TextView tv3 = (TextView) convertView.findViewById(R.id.e_danger);

            holder.hCode = tv2;
            holder.hName = tv;
            holder.hDanger = tv3;

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

         holder.hName.setText(eList.get(position).getName());

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
            convertView.setBackgroundColor(colorRes);

        //---------------------------------------------

        // Listen for ListView Item Click
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String myFile = eList.get(position).getPath();

                // Cделать динамичное заменение фрагмента вместо intent activity
              // Intent intent = new Intent(mContext, AssetLoad.class);
                Intent intent = new Intent(mContext, AssetLoad.class);
                // intent не видет нужного context (скорее класса с которого запускается) - одно из решений использовать флаг
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// не рекомендуется проблемы task стеком activity

                intent.putExtra("fname", myFile);

                mContext.startActivity(intent);


            }
        });

        //--------color---------

        int red = mContext.getResources().getColor(R.color.Red);
        int orange = mContext.getResources().getColor(R.color.Orange);
        int green = mContext.getResources().getColor(R.color.Green);


            int i = 0;
            switch (convertView.getId()) {

                case R.id.e_danger:

                    if (i == 1) convertView.setBackgroundColor(green);
                    else if (i == 0) convertView.setBackgroundColor(orange);
                    else
                        convertView.setBackgroundColor(red);
            }



        return convertView;
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
