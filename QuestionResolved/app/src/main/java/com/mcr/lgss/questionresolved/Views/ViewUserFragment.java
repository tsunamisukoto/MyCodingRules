package com.mcr.lgss.questionresolved.Views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    TextView txt_missing;
    ImageButton btnEdit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_view_user, container, false);
         btnEdit= (ImageButton) v.findViewById(R.id.btn_edit);


        txt_id = (TextView) v.findViewById(R.id.lbl_id);

        txt_name= (TextView)  v.findViewById(R.id.lbl_name);
        txt_desc= (TextView) v.findViewById(R.id.lbl_description);
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

                if(person != null) {
                    txt_name.append(" " +person.Name);
                    txt_desc.append(" " + person.Description);

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
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (OnViewUserFragmentInteractionListener) activity;
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
