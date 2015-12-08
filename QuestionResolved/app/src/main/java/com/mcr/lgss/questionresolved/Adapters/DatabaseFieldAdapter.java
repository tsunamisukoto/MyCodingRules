package com.mcr.lgss.questionresolved.Adapters;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcr.lgss.questionresolved.Entities.Person;
import com.mcr.lgss.questionresolved.R;

public class DatabaseFieldAdapter extends ArrayAdapter<Object> {
    private final Context context;
    private final Object[] values;

    public DatabaseFieldAdapter(Context context, Object[] values) {
        super(context, R.layout.listitem_databasefields, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listitem_databasefields, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        TextView textView2 = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        DatabaseField p = (DatabaseField)values[position];
        textView.setText(p.FieldValue);
        textView2.setText(p.FieldName);
          if(p.resource!=0)
          {
              imageView.setImageResource(p.resource);
          }
        if(p.OnClickListener!=null)
        rowView.setOnClickListener(p.OnClickListener);


        return rowView;
    }
}