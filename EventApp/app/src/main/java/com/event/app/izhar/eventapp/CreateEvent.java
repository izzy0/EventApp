package com.event.app.izhar.eventapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.sql.Time;
import java.util.Date;
/**
 *
 *
 private Date eventDate;
 private String eventHost;
 private String eventName;
 private Time startTime;
 private Time endTime;

 public Event(Date eventDate, String eventHost, String eventName){
 this.eventDate = eventDate;
 this.eventHost = eventHost;
 this.eventName = eventName;
 }

 public Date getEventDate(){
 return eventDate;
 }

 public String getEventHost(){
 return eventHost;
 }

 public String getEventName(){
 return eventName;
 }


 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateEvent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateEvent#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class CreateEvent extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Date eventDate;
    private String eventHost;
    private String eventName;
    private Time startTime;
    private Time endTime;



    @SuppressLint("ValidFragment")
    public CreateEvent() {
        // Required empty public constructor
//        this.eventDate = eventDate;
//        this.eventHost = eventHost;
//        this.eventName = eventName;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateEvent.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEvent newInstance(String sr, String str) {
        CreateEvent fragment = new CreateEvent();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);

        final Fragment fragment = this;

        Button addButton = (Button)view.findViewById(R.id.add_event_button);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                getFragmentManager().popBackStack();
//                getActivity().getSupportFragmentManager().beginTransaction().remove().commit();

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                trans.remove(fragment);
                trans.commit();
                manager.popBackStack();
            }
        });

        return view;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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