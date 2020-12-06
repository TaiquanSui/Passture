package com.example.pasture.ViewModel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pasture.Model.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewEventActivityVM extends ViewModel  {

    private Event event;

    public NewEventActivityVM(){
        event = new Event();
    }


    public void createNewEvent(String date, String title, String startTime, String endTime, String description){
        event.setTitle(title);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setDescription(description);

        saveNewEventInDatabase(date);
    }

    private void saveNewEventInDatabase(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));

            String year = String.valueOf(cal.get(Calendar.YEAR));
            String month = String.valueOf(cal.get(Calendar.MONTH)+1);
            String day = String.valueOf(cal.get(Calendar.DATE));

            System.out.println("pushing data to database+++++++++++++++++++++++++++++++++++++++++++");

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            myRef.child(year).child(month).child(day).push().setValue(event);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
