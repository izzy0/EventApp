package com.event.app.izhar.eventappbeta.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.event.app.izhar.eventappbeta.R;


//TODO add account info
public class AccountFrag extends Fragment implements CreateEvent.OnFragmentInteractionListener {

    private Switch isAdminSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

//        isAdminSwitch = (Switch) view.findViewById(R.id.account_sadmin);

//        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Fragment fragment = new CreateEvent();
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.commit();
//            }
//        });

        return view;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
