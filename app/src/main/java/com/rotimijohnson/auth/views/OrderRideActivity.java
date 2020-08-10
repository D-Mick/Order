package com.rotimijohnson.auth.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;
import com.rotimijohnson.auth.R;
import com.rotimijohnson.auth.model.Destinations;
import com.rotimijohnson.auth.model.Locations;
import com.rotimijohnson.auth.views.adapters.DestinationAdapter;
import com.rotimijohnson.auth.views.adapters.LocationAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class OrderRideActivity extends AppCompatActivity {
    TextInputLayout location, destination, seat;
    AutoCompleteTextView locationAuto,destinationAuto;
    private String jsonString = "{\n" +
            "  \"user\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"Leanne Graham\",\n" +
            "      \"username\": \"Bret\",\n" +
            "      \"email\": \"Sincere@april.biz\",\n" +
            "      \"address\":  \"Kulas Light\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"name\": \"Rotimi bolu\",\n" +
            "      \"username\": \"Bret\",\n" +
            "      \"email\": \"Sincere@april.biz\",\n" +
            "      \"address\":  \"Kulas abk\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 3,\n" +
            "      \"name\": \"Ify Graham\",\n" +
            "      \"username\": \"Bret\",\n" +
            "      \"email\": \"Sincere@april.biz\",\n" +
            "      \"address\":  \"Lagos Light\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 4,\n" +
            "      \"name\": \"Micheal Johnson\",\n" +
            "      \"username\": \"Bret\",\n" +
            "      \"email\": \"Sincere@april.biz\",\n" +
            "      \"address\":  \"Ikeja Oyo\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 5,\n" +
            "      \"name\": \"Ola Emma\",\n" +
            "      \"username\": \"Bret\",\n" +
            "      \"email\": \"Sincere@april.biz\",\n" +
            "      \"address\":  \"Ogun Hub\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private String jsonStringTwo = "{\n" +
            "  \"user\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"Leanne Graham\",\n" +
            "      \"username\": \"Bret\",\n" +
            "      \"email\": \"Sincere@april.biz\",\n" +
            "      \"address\":  \"Kulas Light\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"name\": \"Rotimi bolu\",\n" +
            "      \"username\": \"Bret\",\n" +
            "      \"email\": \"Sincere@april.biz\",\n" +
            "      \"address\":  \"Kulas abk\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 3,\n" +
            "      \"name\": \"Ify Graham\",\n" +
            "      \"username\": \"Bret\",\n" +
            "      \"email\": \"Sincere@april.biz\",\n" +
            "      \"address\":  \"Lagos Light\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 4,\n" +
            "      \"name\": \"Micheal Johnson\",\n" +
            "      \"username\": \"Bret\",\n" +
            "      \"email\": \"Sincere@april.biz\",\n" +
            "      \"address\":  \"Ikeja Oyo\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 5,\n" +
            "      \"name\": \"Ola Emma\",\n" +
            "      \"username\": \"Bret\",\n" +
            "      \"email\": \"Sincere@april.biz\",\n" +
            "      \"address\":  \"Ogun Hub\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private ArrayList<Locations> locationsList;
    private ArrayList<Destinations> destinationsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ride);
        location = findViewById(R.id.location);
        destination = findViewById(R.id.destination);
        seat = findViewById(R.id.seat);
        locationAuto = findViewById(R.id.location_auto);
        destinationAuto = findViewById(R.id.destination_auto);

        //Location
        locationsList = new ArrayList<>();
        try {
            locationsList =  extractUser(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LocationAdapter locationAdapter = new LocationAdapter(this,R.layout.user_raw_layout, locationsList);
        locationAuto.setAdapter(locationAdapter);
        locationAuto.setThreshold(1);

        //Destination
        destinationsList = new ArrayList<>();
        try {
            destinationsList =  extractDestinationUser(jsonStringTwo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        DestinationAdapter destinationAdapter = new DestinationAdapter(this,R.layout.user_raw_layout, destinationsList);
        destinationAuto.setAdapter(destinationAdapter);
        destinationAuto.setThreshold(1);


    }

    //Location extractUser Method
    private ArrayList<Locations> extractUser(String jsonString) throws JSONException {
        ArrayList<Locations> list = new ArrayList<>();

        JSONObject rootJO = new JSONObject(jsonString);
        JSONArray userJA = rootJO.getJSONArray("user");

        for (int i = 0; i<userJA.length(); i++){
            JSONObject jo = userJA.getJSONObject(i);

            int id = jo.getInt("id");
            String name = jo.getString("name");
            String username = jo.getString("username");
            String email = jo.getString("email");
            String address = jo.getString("address");

            Locations locations = new Locations(id,name,username,email,address);
            list.add(locations);
        }

        return list;
    }

    //Destination extractUser Method
    private ArrayList<Destinations> extractDestinationUser(String jsonStringTwo) throws JSONException {
        ArrayList<Destinations> list = new ArrayList<>();

        JSONObject rootJO = new JSONObject(jsonStringTwo);
        JSONArray userJA = rootJO.getJSONArray("user");

        for (int i = 0; i<userJA.length(); i++){
            JSONObject jo = userJA.getJSONObject(i);

            int id = jo.getInt("id");
            String name = jo.getString("name");
            String username = jo.getString("username");
            String email = jo.getString("email");
            String address = jo.getString("address");

            Destinations destinations = new Destinations(id,name,username,email,address);
            list.add(destinations);
        }

        return list;
    }

    private boolean validateLocation() {
        String checkLocation = location.getEditText().getText().toString().trim();
        if (checkLocation.isEmpty()) {
            location.setError("Field can't be empty");
            return false;
        } else {
            location.setError(null);
            location.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateDestination() {
        String checkDestination = destination.getEditText().getText().toString().trim();
        if (checkDestination.isEmpty()) {
            destination.setError("Field can't be empty");
            return false;
        } else {
            destination.setError(null);
            destination.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateSeat() {
        String checkSeat = seat.getEditText().getText().toString().trim();
        if (checkSeat.isEmpty()) {
            seat.setError("Field can't be empty");
            return false;
        } else {
            seat.setError(null);
            seat.setErrorEnabled(false);
            return true;
        }
    }

    public void nextScreen(View view) {
        if (!validateLocation() | !validateDestination()| !validateSeat()) {
            return;
        }

        String _location = location.getEditText().getText().toString().trim();
        String _destination = destination.getEditText().getText().toString().trim();
        String _seat = seat.getEditText().getText().toString().trim();
        Intent intent = new Intent(OrderRideActivity.this, WaitingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("location", _location);
        intent.putExtra("destination", _destination);
        intent.putExtra("seat", _seat);
        startActivity(intent);
    }
}