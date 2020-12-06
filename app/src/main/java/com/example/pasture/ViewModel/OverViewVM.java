package com.example.pasture.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pasture.Model.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class OverViewVM extends ViewModel {

    private MutableLiveData<List<Event>> events;
    private DatabaseReference myRef;

    public OverViewVM(){
        events = new MutableLiveData<List<Event>>();
        List<Event> eventList = new ArrayList<Event>();
        events.setValue(eventList);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        getEventsFromDatabase();
    }


    public LiveData<List<Event>> getEventList(){
        return events;
    }


    private void getEventsFromDatabase(){
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH)+1);
        String day = String.valueOf(c.get(Calendar.DATE));

        myRef.child(year).child(month).child(day).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Event event = dsp.getValue(Event.class);

                    System.out.println("nmsl#################################");
                    if(event !=null){
                        //System.out.println(title);
                        System.out.println("schedule: "+ event.getTitle());
                        List<Event> eventList = events.getValue();
                        eventList.add(event);
                        events.setValue(eventList);
                    }else {
                        System.out.println("nmsl");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("发生什么事了");
            }

        });

    }



}
