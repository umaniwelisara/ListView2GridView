package com.umaniwelisara.listview2gridview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Umani Welisara on 3/13/2018.
 */

public class ListViewAdapter extends ArrayAdapter<Product> {
    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //return super.getView(position, convertView, parent);
        View v=convertView;

        if (null == v){
            LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.list_item,null);
        }
        Product product = getItem(position);
        ImageView img= (ImageView)v.findViewById(R.id.imageView);
        TextView txtTitle = (TextView)v.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView)v.findViewById(R.id.txtDescription);

        img.setImageResource(product.getImageId());
        txtTitle.setText(product.getTitle());
        txtDescription.setText(product.getDescription());

        return v;
    }
}
