package com.event.app.izhar.eventapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.*;
import com.android.volley.toolbox.ImageRequest;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

// TODO change gallery to activity NOT FRAGMENT?

public class Gallery extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static final String UPLOAD_REQUEST_URL = "http://10.15.21.137/eventapp/upload.php";
    public static final int GALLERY_REQUEST = 100;
    public static final int REQUEST_CAMERA = 200;

    Uri imageUri;
    ImageView imageView;
    String photoPath;
    Bitmap bitmap = null;
    GalleryPhoto galleryPhoto;
    CameraPhoto cameraPhoto;
    String selectedPhoto;

    public Gallery() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        permissionsForGallery();
        permissionsForCamera();

        cameraPhoto = new CameraPhoto(getContext());
        galleryPhoto = new GalleryPhoto(getContext());

        imageView = (ImageView) view.findViewById(R.id.gallery_iv);

        Button yourPicBtn = (Button) view.findViewById(R.id.button3);
        yourPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
            }
        });

        Button sharedBtn = (Button) view.findViewById(R.id.gal_shared_btn);
        sharedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "View Shared Event Pictures!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button photoGBtn = (Button) view.findViewById(R.id.gal_photog_btn);
        photoGBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "View photos of Event Photographer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.gallery_upload_fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), Camera.class);
//                getActivity().startActivity(intent);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,REQUEST_CAMERA);



//                try {
//
////TODO FIX CAMERA CRASH ---
//                    startActivityForResult(cameraPhoto.takePhotoIntent(), REQUEST_CAMERA);
//                    imageUri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider",createImageFile());
//
//                    cameraPhoto.addToGallery();
//                } catch (IOException e) {
//                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
//                }
            }
        });

        FloatingActionButton uploadFab = (FloatingActionButton) view.findViewById(R.id.gallery_upload_fab_left);
        uploadFab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });

        return view;
    }

    private void uploadImage() {
        String user = "admim";
//        String name;
        String path = photoPath;

        try{
            String uploadID = UUID.randomUUID().toString();

            new MultipartUploadRequest(getContext(), uploadID, UPLOAD_REQUEST_URL)
                    .addParameter("name",user)
                    .addFileToUpload(path,"image")
//                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(3)
                    .startUpload();

            Toast.makeText(getContext(), "Image Uploaded: " + path, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
/**used by camera*/
    private File createImageFile() throws IOException {
        String timeStamp = (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        this.photoPath = image.getAbsolutePath();
        Toast.makeText(getContext(), photoPath, Toast.LENGTH_SHORT).show();
        Log.d("EVNET_CREATE_IMAGE_FILE", photoPath);
        return image;
    }

    public void permissionsForGallery() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 120);
            }
        }
    }

    public void permissionsForCamera() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA}, 250);
            }
        }
    }

    //not used// but opens native gallery
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GALLERY_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(getContext(), "To Results resultCode: " + resultCode +
                "requestCode: " + requestCode, Toast.LENGTH_SHORT).show();
        if (resultCode == RESULT_OK) {

            if (requestCode == GALLERY_REQUEST) {
                Toast.makeText(getContext(), "From Gallery", Toast.LENGTH_SHORT).show();

                imageUri = data.getData();
                galleryPhoto.setPhotoUri(imageUri);
                photoPath = galleryPhoto.getPath();
                Toast.makeText(getContext(), photoPath, Toast.LENGTH_LONG).show();

                try {
                    bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == REQUEST_CAMERA) {
            Toast.makeText(getContext(), "From Camera", Toast.LENGTH_SHORT).show();

            String photoPath = cameraPhoto.getPhotoPath();
            try {
            Bitmap bitmapSave = (Bitmap) data.getExtras().get("data");
                bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                imageView.setImageBitmap(bitmap);

                Camera camera = new Camera();
                camera.savePhoto(bitmapSave);

            } catch (Exception e) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
