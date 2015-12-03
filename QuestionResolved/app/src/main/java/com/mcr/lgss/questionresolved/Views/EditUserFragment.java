package com.mcr.lgss.questionresolved.Views;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mcr.lgss.questionresolved.Entities.Person;
import com.mcr.lgss.questionresolved.R;
import com.mcr.lgss.questionresolved.Services.DatabaseHelper;

/**
 * Created by Daniel on 19/11/2015.
 */
public class EditUserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_USERID = "param1";

    // TODO: Rename and change types of parameters
    private int userID;

    private OnEditUserFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment EditUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditUserFragment newInstance(String param1) {
        EditUserFragment fragment = new EditUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public EditUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_user, container, false);

        EditText edittxt_name = (EditText)  v.findViewById(R.id.tb_nameedit);
        EditText edittxt_desc = (EditText) v.findViewById(R.id.tb_descedit);

        int argID = getArguments().getInt(ARG_USERID);

        if (argID != -1) {
            DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
            Person person = dbHelper.GetPerson(argID);
            edittxt_name.setText(person.Name);
            edittxt_desc.setText(person.Description);
        }

        Button btnEdit = (Button) v.findViewById(R.id.btn_save);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int argID = getArguments().getInt(ARG_USERID);

                EditText edittxt_name = (EditText)  v.findViewById(R.id.tb_nameedit);
                EditText edittxt_desc = (EditText) v.findViewById(R.id.tb_descedit);

                DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
                Person tempPerson = new Person(argID, edittxt_name.getText().toString(), edittxt_desc.getText().toString(), null);

                if (argID == -1) {
                    dbHelper.InsertPerson(tempPerson, null);
                } else {
                    dbHelper.UpdatePerson(tempPerson, null);
                }

                onSaveEditPressed(getArguments().getInt(ARG_USERID));
            }
        });

        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onSaveEditPressed(int id) {
        if (mListener != null) {
            mListener.onEditUserFragmentInteraction(id);
        }
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (OnEditUserFragmentInteractionListener) activity;
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
    public interface OnEditUserFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onEditUserFragmentInteraction(int id);
    }
}
