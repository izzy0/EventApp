package com.event.app.izhar.eventappbeta.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.event.app.izhar.eventappbeta.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Izhar on 11/8/2017.
 */

public class Camera extends AppCompatActivity {

    Button cameraBtn;
    ImageView cameraImageView;

    String folderName = "/EventApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        cameraBtn = (Button) findViewById(R.id.cameraButton);
        cameraImageView = (ImageView) findViewById(R.id.cameraImageView);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,0);
            }
        });
    }

    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = sdf.format(new Date());
        return "EventApp" + timeStamp +".jpg";
    }
    public void savePhoto(Bitmap bitmap){

        cameraImageView.setImageBitmap(bitmap);

        String pitureDirectory = Environment.getExternalStorageDirectory().getAbsoluteFile() + folderName;
        String pictureName = getPictureName();
        File dir = new File(pitureDirectory);

        if(!dir.exists()){
            dir.mkdirs();
        }

        File file = new File(dir, pictureName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            MakeSureFileWasCreated(file);

            //make into method call
            Toast.makeText(this, "Picture was saved to Gallery", Toast.LENGTH_SHORT).show();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        savePhoto(bitmap);

    }

    private  void MakeSureFileWasCreated(File file){
        MediaScannerConnection.scanFile(
                this,
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
