package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.asynctask.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterUserActivity extends AppCompatActivity implements AsyncResponse, View.OnClickListener {

//    private EditText username, password, email, firstName, lastName;
    private Button newUser;
    final EditText username = (EditText) findViewById(R.id.create_username_id_textfield);
    final EditText password = (EditText) findViewById(R.id.create_user_password_id);
    final EditText firstName = (EditText) findViewById(R.id.first_name_id);
    final EditText lastName = (EditText) findViewById(R.id.last_name_id);
    final EditText email = (EditText) findViewById(R.id.create_email_id_textfield);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        newUser = (Button) findViewById(R.id.create_user_id_button);
        newUser.setOnClickListener(this);
    }

    public void onClick(View v) {

//        HashMap postData = new HashMap();
//        postData.put("btnLogin", "Login");
//        postData.put("mobile", "android");
//        postData.put("txtUsername", username.getText().toString());
//        postData.put("txtPassword", password.getText().toString());
//        postData.put("txtUsername", firstName.getText().toString());
//        postData.put("txtPassword", lastName.getText().toString());
//
//        PostResponseAsyncTask registerTask = new PostResponseAsyncTask(this, postData);
//        registerTask.execute("http://10.0.2.2/EventApp/EventApp/register.php");

            final String fname = firstName.getText().toString();
            final String lname = lastName.getText().toString();
            final String uname = username.getText().toString();
            final String passw = password.getText().toString();
            final String emailString = password.getText().toString();

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.i("tagconvertstr", "["+response+"]");
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        System.out.println("boolean json"+success);

                        if(success){
                            Intent intent = new Intent(RegisterUserActivity.this, NavigationDrawer.class);
//                            intent.putExtra("username", uname);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(getApplicationContext(), "Username already taken ;(",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            RegisterRequest request = new RegisterRequest(fname, lname, uname, passw, emailString, responseListener);
            System.out.println("parameters "+request.getParams());
            Log.i("Register","["+ request.getParams()+"]");
            RequestQueue queue = Volley.newRequestQueue(RegisterUserActivity.this);
            queue.add(request);
    }

    @Override
    public void processFinish(String result) {
        if (result.equals("success")) {
            Toast.makeText(this, "Register Successful!",
                    Toast.LENGTH_LONG).show();
            Intent next = new Intent(this, NavigationDrawer.class);
            startActivity(next);
        }

    }
}

/**
 *
 * edit code to work
 *
 public class CreateActivity ext
 ends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {


super.onCreate(savedInstanceState);
setContentView(R.layout.create_user);

final EditText etfirst = (EditText) findViewById(R.id.createFName);
final EditText etlast = (EditText) findViewById(R.id.createLName);
final EditText etuser = (EditText) findViewById(R.id.createUser);
final EditText etpass = (EditText) findViewById(R.id.createPass);
final ImageButton btn = (ImageButton) findViewById(R.id.createCBtn);

btn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
final String fname = etfirst.getText().toString();
final String lname = etlast.getText().toString();
final String uname = etuser.getText().toString();
final String passw = etpass.getText().toString();

Response.Listener<String> responseListener = new Response.Listener<String>() {
@Override
public void onResponse(String response) {
try {
Log.i("tagconvertstr", "["+response+"]");
JSONObject jsonObject = new JSONObject(response);
boolean success = jsonObject.getBoolean("success");
System.out.println("boolean json"+success);

if(success){

Intent intent = new Intent(CreateActivity.this, MemberPage.class);
intent.putExtra("username", uname);

CreateActivity.this.startActivity(intent);
finish();

}else{
Toast.makeText(getApplicationContext(), "Username already taken ;(",
Toast.LENGTH_SHORT).show();
}

} catch (JSONException e) {
e.printStackTrace();
}
}
};

RegisterRequest request = new RegisterRequest(fname, lname, uname, passw, responseListener);
System.out.println("parameters "+request.getParams());
RequestQueue queue = Volley.newRequestQueue(CreateActivity.this);
queue.add(request);

}
});
}

}
 */
