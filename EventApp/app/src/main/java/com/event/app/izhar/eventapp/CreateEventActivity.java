package com.event.app.izhar.eventapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CreateEventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_activity);
    }
}


/**
 *
 * edit code to work
 *
 public class CreateActivity extends AppCompatActivity {

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