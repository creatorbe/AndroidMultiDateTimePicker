package com.creatorbe.multidatetimepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private EditText mStartDate,mEndDate,mStartTime,mEndTime;

    private DatePickerDialogFragment mDatePickerDialogFragment;
    private TimePicker mTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //date
        mStartDate = (EditText) findViewById(R.id.start_date);
        mEndDate = (EditText) findViewById(R.id.end_date);
        mDatePickerDialogFragment = new DatePickerDialogFragment();

        //time
        mStartTime = (EditText) findViewById(R.id.start_time);
        mEndTime = (EditText) findViewById(R.id.end_time);
        mTimePicker = new TimePicker();

        mStartDate.setOnClickListener(this);
        mEndDate.setOnClickListener(this);
        mStartTime.setOnClickListener(this);
        mEndTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start_date) {
            mDatePickerDialogFragment.setFlag(DatePickerDialogFragment.FLAG_START_DATE);
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "datePicker");
        } else if (id == R.id.end_date) {
            mDatePickerDialogFragment.setFlag(DatePickerDialogFragment.FLAG_END_DATE);
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "datePicker");
        }else if (id == R.id.start_time) {
            mTimePicker.setFlag(TimePicker.FLAG_START_DATE);
            mTimePicker.show(getSupportFragmentManager(), "datePicker");
        }else if (id == R.id.end_time) {
            mTimePicker.setFlag(TimePicker.FLAG_END_DATE);
            mTimePicker.show(getSupportFragmentManager(), "datePicker");
        }
    }

    public class DatePickerDialogFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        public static final int FLAG_START_DATE = 0;
        public static final int FLAG_END_DATE = 1;

        private int flag = 0;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void setFlag(int i) {
            flag = i;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (flag == FLAG_START_DATE) {
                mStartDate.setText(format.format(calendar.getTime()));
            } else if (flag == FLAG_END_DATE) {
                mEndDate.setText(format.format(calendar.getTime()));
            }
        }
    }

    public class TimePicker extends DialogFragment implements
            android.app.TimePickerDialog.OnTimeSetListener {
        public static final int FLAG_START_DATE = 0;
        public static final int FLAG_END_DATE = 1;

        private int flag = 0;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new android.app.TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void setFlag(int i) {
            flag = i;
        }

        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            if (flag == FLAG_START_DATE) {
                mStartTime.setText(Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
            } else if (flag == FLAG_END_DATE) {
                mEndTime.setText(Integer.toString(hourOfDay) + ":" + Integer.toString(minute));

            }
        }
    }
}
