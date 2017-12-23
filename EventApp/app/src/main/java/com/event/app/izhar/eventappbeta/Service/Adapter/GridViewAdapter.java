package com.event.app.izhar.eventappbeta.Service.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.event.app.izhar.eventappbeta.ImageObject;
import com.event.app.izhar.eventappbeta.R;

import java.util.ArrayList;

/**
 * Created by Izhar on 12/4/2017.
 */

public class GridViewAdapter extends BaseAdapter{

    Context context;
    public static ArrayList<ImageObject> imageObjectArrayList;

    LayoutInflater inflater;

    public GridViewAdapter(Context context, ArrayList<ImageObject> imageObjectArrayList) {
        this.context = context;
        this.imageObjectArrayList = imageObjectArrayList;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return imageObjectArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageObjectArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = inflater.inflate(R.layout.module_image, parent,false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_image_module);

        //Bind data
        ImageObject imageObject = imageObjectArrayList.get(position);
        //Bing image
//        Toast.makeText(context, imageObject.getImageURL(), Toast.LENGTH_SHORT).show();
        PicassoClient.downloadImage(context,imageObject.getImageURL(),imageView);

        return convertView;
    }
}
