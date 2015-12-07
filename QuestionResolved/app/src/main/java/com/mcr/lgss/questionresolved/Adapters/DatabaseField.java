package com.mcr.lgss.questionresolved.Adapters;

import android.view.View;

/**
 * Created by Scott on 5/12/2015.
 */
public class DatabaseField {
    public int resource = 0;
    public String FieldName="";
    public String FieldValue;
    public View.OnClickListener OnClickListener;

    public DatabaseField(int _res, String _fname, String _fvalue, View.OnClickListener onclick)
    {
        resource= _res;
        FieldName=_fname;
        FieldValue=_fvalue;
        OnClickListener= onclick;
    }
}
