package com.change.kranti.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.change.kranti.R;
import model.Issue;

import java.util.List;

public class ViewIssuesAdapter extends ArrayAdapter<Issue>{


    private final Context context;

    public ViewIssuesAdapter(Context context, int resource, int textViewResourceId, List<Issue> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        if (convertView == null) {
            rowView = layoutInflater.inflate(R.layout.issue_item, parent, false);
        } else {
            rowView = convertView;
        }
        setTextToResource(rowView, R.id.title, getItem(position).getTitle());
        setTextToResource(rowView, R.id.description, getItem(position).getDescription());
//        ImageView issueImageView = (ImageView) rowView.findViewById(R.id.issue_thumbnail);
//        issueImageView.setImageResource(getItem(position).getImagePath());
        return rowView;
    }

    private void setTextToResource(View rowView, int id, String title) {
        TextView textViewForTitle = (TextView) rowView.findViewById(id);
        textViewForTitle.setText(title);
    }
}
