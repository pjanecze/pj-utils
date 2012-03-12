package com.pj.lib.widgets;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

public class DateTimeButton extends Button{

	public final static int TYPE_DATE = 1;
	
	public final static int TYPE_TIME = 2;
	
	
	private int mType;
	
	private Calendar mCalendar;
	
	Context mContext;
	public DateTimeButton(Context context) {
		super(context);
		init(context);
	}

	public DateTimeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public DateTimeButton(Context context, AttributeSet attrs, int defStyle) {
		super(context,attrs, defStyle);
		init(context);
	}
	


	private void init(Context context) {
		mType = TYPE_DATE;
		mCalendar = Calendar.getInstance();
		mContext = context;
		this.setOnClickListener(onClickListener);
		
		updateButtonValue();
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			showDateTimeDialog();
		}
	};
	
	
	private void showDateTimeDialog() {
		if(mType == TYPE_DATE) {
			DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, onDateSetListener, 
					mCalendar.get(Calendar.YEAR), 
					mCalendar.get(Calendar.MONTH), 
					mCalendar.get(Calendar.DAY_OF_MONTH));
			datePickerDialog.show();
		} else if(mType == TYPE_TIME) {
			TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, onTimeSetListener, 
					mCalendar.get(Calendar.HOUR_OF_DAY),
					mCalendar.get(Calendar.MINUTE),
					true);
			timePickerDialog.show();
		}
	}
	
	
	private void updateButtonValue() {
		Date date = mCalendar.getTime();
		DateFormat dateFormat;
		if(mType == TYPE_DATE) {
			dateFormat = android.text.format.DateFormat.getDateFormat(mContext);
		} else {
			dateFormat = android.text.format.DateFormat.getTimeFormat(mContext);
		}
		Log.i("test", "prompt: " + dateFormat.format(date));
		this.setText(dateFormat.format(date));
	}
	
	private OnDateSetListener onDateSetListener = new OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mCalendar.set(Calendar.YEAR, year);
			mCalendar.set(Calendar.MONTH, monthOfYear);
			mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateButtonValue();
		}
	};
	
	private OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
			mCalendar.set(Calendar.MINUTE, minute);
			updateButtonValue();
		}
	};

	public int getType() {
		return mType;
	}

	public void setType(int type) {
		this.mType = type;
		updateButtonValue();
	}
	
	public Calendar getDateTime() {
		return mCalendar;
	}
	
	public void setDateTime(Calendar dateTime) {
		mCalendar = dateTime;
		updateButtonValue();
	}
	
}
