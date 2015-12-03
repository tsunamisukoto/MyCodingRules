package com.mcr.lgss.questionresolved.Adapters;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcr.lgss.questionresolved.Entities.Person;
import com.mcr.lgss.questionresolved.R;

public class PersonArrayAdapter extends ArrayAdapter<Object> {
    private final Context context;
    private final Object[] values;

    public PersonArrayAdapter(Context context, Object[] values) {
        super(context, R.layout.listviewitems, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listviewitems, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        TextView textView2 = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        Person p = (Person)values[position];
        textView.setText(p.Name);
        textView2.setText(p.Description);
        // Change the icon for Windows and iPhone
        if(p.Image!=null)
        {
            Bitmap workingImage= BitmapFactory.decodeByteArray(p.Image, 0,p.Image.length);

if(workingImage!=null)
            imageView.setImageBitmap(workingImage);

        }


        return rowView;
    }
}