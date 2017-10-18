package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class CreateUserActivity extends AppCompatActivity implements AsyncResponse, View.OnClickListener {

    private EditText username, password, email, firstName, lastName;
    private Button newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_activity);

        username = (EditText) findViewById(R.id.create_username_id_textfield);
        password = (EditText) findViewById(R.id.create_user_password_id);
        firstName = (EditText) findViewById(R.id.first_name_id);
        lastName = (EditText) findViewById(R.id.last_name_id);
        email = (EditText) findViewById(R.id.create_email_id_textfield);
        newUser = (Button) findViewById(R.id.create_user_id_button);
        newUser.setOnClickListener(this);
    }

    public void onClick(View v) {

        HashMap postData = new HashMap();
        postData.put("btnLogin", "Login");
        postData.put("mobile", "android");
        postData.put("txtUsername", username.getText().toString());
        postData.put("txtPassword", password.getText().toString());
        postData.put("txtUsername", firstName.getText().toString());
        postData.put("txtPassword", lastName.getText().toString());

        PostResponseAsyncTask registerTask = new PostResponseAsyncTask(this, postData);
        registerTask.execute("http://10.0.2.2/EventApp/EventApp/register.php");
    }

    @Override
    public void processFinish(String result) {
        if (result.equals("success")) {
            Toast.makeText(this, "Register Successful!",
                    Toast.LENGTH_LONG).show();
            Intent next = new Intent(this, MainActivity.class);
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
