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
import android.widget.TextView;

import com.mcr.lgss.questionresolved.Entities.Person;
import com.mcr.lgss.questionresolved.R;
import com.mcr.lgss.questionresolved.Services.DatabaseHelper;

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
 TextView txt_id;
    TextView txt_name;
    TextView txt_desc;
    TextView txt_position;
    TextView txt_quote;
    TextView txt_phone;
    TextView txt_email;
    TextView txt_coord;
    TextView txt_missing;
    ImageButton btnEdit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_view_user, container, false);
         btnEdit= (ImageButton) v.findViewById(R.id.btn_edit);


        txt_id = (TextView) v.findViewById(R.id.lbl_id_txt);
        txt_name = (TextView)  v.findViewById(R.id.lbl_name_txt);
        txt_desc = (TextView) v.findViewById(R.id.lbl_description_txt);
        txt_position = (TextView) v.findViewById(R.id.lbl_position_txt);
        txt_quote = (TextView) v.findViewById(R.id.lbl_quote_txt);
        txt_phone = (TextView) v.findViewById(R.id.lbl_phone_txt);
        txt_email = (TextView) v.findViewById(R.id.lbl_email_txt);
        txt_coord = (TextView) v.findViewById(R.id.lbl_coord_txt);

        txt_missing= (TextView) v.findViewById(R.id.lbl_missing);
        if (getArguments() != null) {
            userID = getArguments().getInt(ARG_USERID);

                final int id = (userID);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
//                    goToEdit(v);
                    onButtonPressed(id);
                }
            });
                txt_id.append(" "+id);

                DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
                Person person = dbHelper.GetPerson(id);

                ImageView userimg = (ImageView) v.findViewById(R.id.img_userDetails);
                Bitmap workingImage;

                if(person != null) {
                    txt_name.setText("" + person.Name);
                    txt_desc.setText("" + person.Description);
                    txt_position.setText("" + person.PosName);
                    txt_quote.setText("" + person.Quote);
                    txt_phone.setText("" + person.PhoneNumber);
                    txt_email.setText("" + person.Email);
                    txt_coord.setText("" +person.LastSeenCoordinates);

                    txt_phone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse(txt_phone.getText()+""));
                            startActivity(callIntent);
                        }
                    });

                    txt_email.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                            sendIntent.setType("plain/text");
                            sendIntent.setData(Uri.parse("test@gmail.com"));
                            sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { txt_email.getText()+"" });
                            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "test");
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "hello. this is a message sent from my demo app :-)");
                            startActivity(sendIntent);
                        }
                    });

                    if (person.Image != null) {
                        workingImage = BitmapFactory.decodeByteArray(person.Image, 0, person.Image.length);

                        if(workingImage!=null&& userimg!=null)
                            userimg.setImageBitmap(workingImage);
                    }

                } else {
                    btnEdit.setVisibility(View.INVISIBLE);
                    txt_missing.setVisibility(View.VISIBLE);
                    txt_name.setVisibility(View.INVISIBLE);
                    txt_desc.setVisibility(View.INVISIBLE);
                }
        }
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int uri) {
        if (mListener != null) {
            mListener.onViewUserFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            Log.e("LISGTERNER ATTACHING","SADSADAS");
            mListener = (OnViewUserFragmentInteractionListener) activity;
            Log.e("LISGTERNER ATTACHED","SADSADAS");

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
