package com.example.pasture.View;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pasture.R;
import com.example.pasture.ViewModel.NewEventActivityVM;

import java.util.Calendar;

public class NewEventActivity extends AppCompatActivity {

    private NewEventActivityVM viewModel;

    private TextView dateTV;
    private EditText titleET;
    private TextView startTimeTV;
    private TextView endTimeTV;
    private EditText descriptionET;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event_activity);

        dateTV = findViewById(R.id.new_event_date);
        titleET = findViewById(R.id.title);
        startTimeTV = findViewById(R.id.startTime);
        endTimeTV = findViewById(R.id.endTime);
        descriptionET = findViewById(R.id.description);
        submit = findViewById(R.id.submitNewEvent);

        startTimeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStartTime();
            }
        });
        endTimeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEndTime();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewEvent();
            }
        });

        String overview_date = getIntent().getStringExtra("date");
        dateTV.setText(overview_date);

        viewModel = new ViewModelProvider(this).get(NewEventActivityVM.class);
    }




    private void setStartTime(){
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        new TimePickerDialog(NewEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                startTimeTV.setText( selectedHour + ":" + selectedMinute);

            }
        }, hour, minute, true).show();
    }

    private void setEndTime(){
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        new TimePickerDialog(NewEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                endTimeTV.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true).show();
    }

    private void createNewEvent() {
        System.out.println("createNewEvents+++++++++++++++++++++++++++++++++++++++++++");

        viewModel.createNewEvent(dateTV.getText().toString(), titleET.getText().toString(), startTimeTV.getText().toString(), endTimeTV.getText().toString(), descriptionET.getText().toString());
    }


}
