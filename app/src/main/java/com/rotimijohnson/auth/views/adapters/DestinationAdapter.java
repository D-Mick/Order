package com.rotimijohnson.auth.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.rotimijohnson.auth.model.Destinations;
import com.rotimijohnson.auth.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DestinationAdapter extends ArrayAdapter<Destinations> {
    private List<Destinations> locationsList;
    private List<Destinations> tempList;
    private List<Destinations> suggestionList;

    public DestinationAdapter(@NonNull Context context, int resource, @NonNull List<Destinations> objects) {
        super(context, resource, objects);
        locationsList = objects;
        tempList = new ArrayList<>(locationsList);
        suggestionList = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_raw_layout,parent, false);
        }
        TextView textView = convertView.findViewById(R.id.simple_text);

        Destinations destinations =  locationsList.get(position);
        textView.setText(destinations.getName() + " " + destinations.getEmail() + " " + destinations.getAddress());
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return userFilter;
    }

    Filter userFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Destinations destinations = (Destinations) resultValue;

            return destinations.getName() + " " + destinations.getEmail() + " " + destinations.getAddress();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (charSequence != null && charSequence.length() > 0){
                suggestionList.clear();
                charSequence = charSequence.toString().trim().toLowerCase();

                for (Destinations destinations : tempList){
                    if (destinations.getName().toLowerCase().contains(charSequence)
                            || destinations.getEmail().toLowerCase().contains(charSequence)
                            || destinations.getAddress().toLowerCase().contains(charSequence)){
                        suggestionList.add(destinations);
                    }
                }

                filterResults.count = suggestionList.size();
                filterResults.values = suggestionList;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<Destinations> uList = (ArrayList<Destinations>) filterResults.values;

            if (filterResults != null && filterResults.count > 0){
                clear();
                for (Destinations u: uList){
                    add(u);
                    notifyDataSetChanged();

                }
            }
        }
    };
}
