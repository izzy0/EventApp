package com.event.app.izhar.eventappbeta.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.event.app.izhar.eventappbeta.ImageObject;
import com.event.app.izhar.eventappbeta.R;
import com.event.app.izhar.eventappbeta.Service.Adapter.GridViewAdapter;
import com.event.app.izhar.eventappbeta.Service.DBConnection.Downloader;
import com.squareup.picasso.Picasso;

public class ImageListActivity extends Fragment {

    final static String IMAGE_LIST_URL = "http://cq7243tk.000webhostapp.com/image_list.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void loadImages(GridView gridView) {
        new Downloader(getContext(), IMAGE_LIST_URL, gridView).execute();
    }
}
