package com.rotimijohnson.auth.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.rotimijohnson.auth.R;
import com.rotimijohnson.auth.model.Locations;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LocationAdapter extends ArrayAdapter<Locations> {
    private List<Locations> locationsList;
    private List<Locations> tempList;
    private List<Locations> suggestionList;

    public LocationAdapter(@NonNull Context context, int resource, @NonNull List<Locations> objects) {
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

        Locations locations =  locationsList.get(position);
        textView.setText(locations.getName() + " " + locations.getEmail() + " " + locations.getAddress());
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
            Locations locations = (Locations) resultValue;

            return locations.getName() + " " + locations.getEmail() + " " + locations.getAddress();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (charSequence != null && charSequence.length() > 0){
                suggestionList.clear();
                charSequence = charSequence.toString().trim().toLowerCase();

                for (Locations locations : tempList){
                    if (locations.getName().toLowerCase().contains(charSequence)
                            || locations.getEmail().toLowerCase().contains(charSequence)
                            || locations.getAddress().toLowerCase().contains(charSequence)){
                        suggestionList.add(locations);
                    }
                }

                filterResults.count = suggestionList.size();
                filterResults.values = suggestionList;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<Locations> uList = (ArrayList<Locations>) filterResults.values;

            if (filterResults != null && filterResults.count > 0){
                clear();
                for (Locations u: uList){
                    add(u);
                    notifyDataSetChanged();

                }
            }
        }
    };
}
