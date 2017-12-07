package com.event.app.izhar.eventappbeta;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.event.app.izhar.eventappbeta.Adapter.GridViewAdapter;
import com.event.app.izhar.eventappbeta.DBConnection.Downloader;
import com.squareup.picasso.Picasso;

import javax.xml.parsers.FactoryConfigurationError;

public class ImageListActivity extends Fragment{

//    final static String IMAGE_LIST_URL = "http://10.15.21.74/Eventapp/image_list.php";
//    final static String IMAGE_LIST_URL = "http://localhost/Eventapp/image_list.php";
//    final static String IMAGE_LIST_URL = "http://192.168.1.135/Eventapp/image_list.php";
    final static String IMAGE_LIST_URL = "http://cq7243tk.000webhostapp.com/image_list.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image_list);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_image_list, container, false);

        final GridView gridView = (GridView) view.findViewById(R.id.gallery_image_view);
        final ImageView thumbView = (ImageView) view.findViewById(R.id.expanded_image);
        final FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.image_list_fab_refresh);

        thumbView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //minimize image
                thumbView.setVisibility(view.GONE);
                gridView.setVisibility(view.VISIBLE);
                button.show();
            }
        });

        loadImages(gridView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImages(gridView);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageObject io = GridViewAdapter.imageObjectArrayList.get(position);
                Toast.makeText(getContext(), io.getImageURL(), Toast.LENGTH_SHORT).show();
                Picasso.with(getContext()).load(io.getImageURL()).into(thumbView);
                thumbView.setVisibility(view.VISIBLE);
                gridView.setVisibility(view.GONE);
                button.hide();
            }
        });

        return view;
    }

    private void loadImages(GridView gridView){
        new Downloader(getContext(), IMAGE_LIST_URL, gridView).execute();
    }
}
