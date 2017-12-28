package com.event.app.izhar.eventappbeta.Service.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.event.app.izhar.eventappbeta.R;
import com.squareup.picasso.Picasso;

public class PicassoClient {

    public static void downloadImage(Context context, String imageUrl, ImageView imageView) {

        if (imageUrl != null && imageUrl.length() > 0) {
            Picasso.with(context).load(imageUrl).placeholder(R.drawable.placeholder).into(imageView);
        } else {
            Picasso.with(context).load(R.drawable.placeholder).into(imageView);
        }
    }
}
