package com.mcr.lgss.questionresolved.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mcr.lgss.questionresolved.Adapters.DatabaseField;
import com.mcr.lgss.questionresolved.Adapters.DatabaseFieldAdapter;
import com.mcr.lgss.questionresolved.Entities.Person;
import com.mcr.lgss.questionresolved.R;
import com.mcr.lgss.questionresolved.Services.DatabaseHelper;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnViewUserFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewUserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_USERID = "param1";

    // TODO: Rename and change types of parameters
    private int userID;

    private OnViewUserFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ViewUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewUserFragment newInstance(String param1) {
        ViewUserFragment fragment = new ViewUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ViewUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
    }
    ImageButton btnEdit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_view_user, container, false);
         btnEdit= (ImageButton) v.findViewById(R.id.btn_edit);


        if (getArguments() != null) {
            userID = getArguments().getInt(ARG_USERID);

                final int id = (userID);
            Log.e("SDDSDSDS","SFASFSAFSA");
            btnEdit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
//                    goToEdit(v);

                    Log.e("SDDSDSDS","SFASFSAFSA");

                    onButtonPressed(id);
                }
            });

                DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
                final Person person = dbHelper.GetPerson(id);

                ImageView userimg = (ImageView) v.findViewById(R.id.img_userDetails);
                Bitmap workingImage;

                if(person != null) {
                    ArrayList<DatabaseField> dbfields = new ArrayList<>();
                    dbfields.add(new DatabaseField(R.drawable.user168, "Name", person.Name,null));
                    dbfields.add(new DatabaseField(0, "Description", person.Description,null));
                    dbfields.add(new DatabaseField(0, "Position", person.PosName,null));
                    dbfields.add(new DatabaseField(R.drawable.citation, "Quote", person.Quote,null));
                    dbfields.add(new DatabaseField(R.drawable.phone325, "Phone Number", person.PhoneNumber,new View.OnClickListener() {
                        @Override
                       public void onClick(View v) {
                           Intent callIntent = new Intent(Intent.ACTION_CALL);callIntent.setData(Uri.parse("tel:" +person.PhoneNumber));
                          startActivity(callIntent);
                       }
                    }));
                    dbfields.add(new DatabaseField(R.drawable.mail59, "Email", person.Email,new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                            sendIntent.setType("plain/text");
                            sendIntent.setData(Uri.parse("test@gmail.com"));
                            sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {person.Email });
                            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "We Have Emails In our app");
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "");
                            startActivity(sendIntent);
                        }
                    } ));
                    dbfields.add(new DatabaseField(R.drawable.navigation12, "Last Seen Coordinates", person.LastSeenCoordinates ,null));
                    ListView lv = (ListView)v.findViewById(R.id.lvInfo);
                    lv.setAdapter(new DatabaseFieldAdapter(getActivity(),dbfields.toArray()));
                    if (person.Image != null) {
                        workingImage = BitmapFactory.decodeByteArray(person.Image, 0, person.Image.length);

                        if(workingImage!=null&& userimg!=null)
                            userimg.setImageBitmap(workingImage);
                    }

                } else {
                    btnEdit.setVisibility(View.INVISIBLE);

                }
        }
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int uri) {
        Log.e("Scotts Debugs","1");
        if (mListener != null) {        Log.e("Scotts Debugs","2");

            mListener.onViewUserFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {        Log.e("Scotts Debugs","3");

            mListener = (OnViewUserFragmentInteractionListener) activity;
            Log.e("Scotts Debugs","4");

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAllUsersFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnViewUserFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onViewUserFragmentInteraction(int id);
    }

}
