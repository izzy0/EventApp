package com.event.app.izhar.eventappbeta;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.event.app.izhar.eventappbeta.DBConnection.Downloader;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;


public class Gallery extends Fragment {

    private OnFragmentInteractionListener mListener;

    //    private static final String UPLOAD_REQUEST_URL = "http://10.15.21.137/eventapp/upload.php";
    private static final String UPLOAD_REQUEST_URL = "https://cq7243tk.000webhostapp.com/upload.php";
    public static final int GALLERY_REQUEST = 100;
    public static final int REQUEST_CAMERA = 200;

    Uri imageUri;
    ImageView imageView;
    String photoPath;
    Bitmap bitmap = null;
    GalleryPhoto galleryPhoto;
    CameraPhoto cameraPhoto;
    String folderName = "/EventApp";
    FloatingActionButton uploadFab;

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

        permissions();

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
//                Intent shareImageIntent = new Intent(getContext(), ImageListActivity.class);
//                startActivity(shareImageIntent);
                Fragment imageList = new ImageListActivity();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, imageList);
                fragmentTransaction.commit();
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

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CAMERA);
            }
        });

        uploadFab = (FloatingActionButton) view.findViewById(R.id.gallery_upload_fab_left);
        uploadFab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (imageView.getDrawable() != null){
                    uploadImage();
                    uploadFab.hide();
                }else {
                    Toast.makeText(getContext(), "Must have a photo selected to upload", Toast.LENGTH_SHORT).show();
                }
            }
        });

        uploadFab.hide();
        return view;
    }

    private void uploadImage() {
        String user = "admim";
        String path = photoPath;

        try {
            String uploadID = UUID.randomUUID().toString();

            new MultipartUploadRequest(getContext(), uploadID, UPLOAD_REQUEST_URL)
                    .addParameter("name", user)
                    .addFileToUpload(path, "image")
//                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(3)
                    .startUpload();

//            Toast.makeText(getContext(), "Image Uploaded: " + path, Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(null);
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void permissions() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.CAMERA}, 120);
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Toast.makeText(getContext(), "To Results resultCode: " + resultCode +
//                "requestCode: " + requestCode, Toast.LENGTH_SHORT).show();
        if (resultCode == RESULT_OK) {
            uploadFab.show();
            if (requestCode == GALLERY_REQUEST) {
//                Toast.makeText(getContext(), "From Gallery", Toast.LENGTH_SHORT).show();

                imageUri = data.getData();
                galleryPhoto.setPhotoUri(imageUri);
                photoPath = galleryPhoto.getPath();
//                Toast.makeText(getContext(), photoPath, Toast.LENGTH_LONG).show();

                try {
                    bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }


            } else if (requestCode == REQUEST_CAMERA) {
//                Toast.makeText(getContext(), "From Camera", Toast.LENGTH_SHORT).show();

                try {
                    Bitmap bitmapSave = (Bitmap) data.getExtras().get("data");
                    savePhoto(bitmapSave);

//                    bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
//                    imageView.setImageBitmap(bitmap);


                } catch (Exception e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
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


    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = sdf.format(new Date());
        return "EventApp" + timeStamp + ".jpg";
    }

    public void savePhoto(Bitmap bitmap) {

        imageView.setImageBitmap(bitmap);

        String pitureDirectory = Environment.getExternalStorageDirectory().getAbsoluteFile() + folderName;
        String pictureName = getPictureName();

        File dir = new File(pitureDirectory);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, pictureName);
        photoPath = file.getAbsolutePath();
//        Toast.makeText(getContext(), photoPath, Toast.LENGTH_LONG).show();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            MakeSureFileWasCreated(file);

            //make into method call
            Toast.makeText(getContext(), "Picture saved to Gallery", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void MakeSureFileWasCreated(File file) {
        MediaScannerConnection.scanFile(
                getContext(),
                new String[]{file.toString()},
                null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.e("ExternalStorage", "Scanned" + path + ":");
                        Log.e("ExternalStorage", "-> uri=" + uri);
                    }
                }
        );
    }

}
