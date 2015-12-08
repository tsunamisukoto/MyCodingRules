package com.mcr.lgss.questionresolved.Views;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mcr.lgss.questionresolved.Entities.Person;
import com.mcr.lgss.questionresolved.R;
import com.mcr.lgss.questionresolved.Services.DatabaseHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Daniel on 19/11/2015.
 */
public class EditUserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_USERID = "param1";

    // TODO: Rename and change types of parameters
    private int userID;
    int TAKE_PHOTO_CODE = 1232;
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
        setHasOptionsMenu(true);
    }
    @Override
     public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.adduseractionmenu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_favorite){
            DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if (workingImage != null)
                workingImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Person tempPerson = new Person(argID, edittxt_name.getText().toString(), edittxt_desc.getText().toString(), byteArray, edittxt_posno.getText().toString(),
                    edittxt_quote.getText().toString(), edittxt_phsno.getText().toString(), edittxt_email.getText().toString(), null);

            if (argID == -1) {
                dbHelper.InsertPerson(tempPerson, null);
            } else {
                dbHelper.UpdatePerson(tempPerson, null);

            }
            onSaveEditPressed(getArguments().getInt(ARG_USERID));
            return true;
        }
        if(id == R.id.action_cam){
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "New Picture");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
            imageUri = getActivity().getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    EditText edittxt_name;
    EditText edittxt_desc;
    EditText edittxt_posno;
    EditText edittxt_phsno;
    EditText edittxt_quote;
    EditText edittxt_email;
    Uri imageUri;
    int argID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_user, container, false);


         edittxt_name= (EditText) v.findViewById(R.id.tb_nameedit);
         edittxt_desc = (EditText) v.findViewById(R.id.tb_descedit);
         edittxt_posno = (EditText) v.findViewById(R.id.tb_posnoedit);
         edittxt_phsno = (EditText) v.findViewById(R.id.tb_phnoedit);
         edittxt_quote = (EditText) v.findViewById(R.id.tb_quoteedit);
         edittxt_email = (EditText) v.findViewById(R.id.tb_emailedit);
        final ImageView a = (ImageView) v.findViewById(R.id.imgUserImage);
        argID  = getArguments().getInt(ARG_USERID);

        if (argID != -1) {
            DatabaseHelper dbHelper = new DatabaseHelper(getActivity().getApplicationContext());
            Person person = dbHelper.GetPerson(argID);
            edittxt_name.setText(person.Name);
            edittxt_desc.setText(person.Description);
            edittxt_posno.setText(person.PosName);
            edittxt_phsno.setText(person.PhoneNumber);
            edittxt_quote.setText(person.Quote);
            edittxt_email.setText(person.Email);

            if (person.Image != null) {
                workingImage = BitmapFactory.decodeByteArray(person.Image, 0, person.Image.length);

                if (workingImage != null && a != null)

                    a.setImageBitmap(workingImage);

            }
        }

//       Intent callingIntent = getIntent();
//
//        edittxt_name.setText(callingIntent.getStringExtra("name"));
//        edittxt_desc.setText(callingIntent.getStringExtra("description"));

        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onSaveEditPressed(int id) {
        if (mListener != null) {
            mListener.onEditUserFragmentInteraction(id);
        }
    }

    //    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnEditUserFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAllUsersFragmentInteractionListener");
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

    Bitmap workingImage;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == getActivity().RESULT_OK) {


            Bitmap thumbnail = null;
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(
                         getActivity().getContentResolver(), imageUri);
                ImageView a = (ImageView) getActivity().findViewById(R.id.imgUserImage);

                 workingImage=BitmapFactory.decodeFile(getRealPathFromURI(imageUri));

                if(workingImage!=null)
                {
                    workingImage = Bitmap.createScaledBitmap(workingImage, 400, 400, false);
                    a.setImageBitmap(workingImage);
                }
                else
                    a.setImageResource(R.drawable.user168);
                a.setImageBitmap(workingImage);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
