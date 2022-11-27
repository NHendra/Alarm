package com.nanda.alarm;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TimePicker myTimePicker;     
	Button buttonstartSetDialog;     
	TextView textAlarmPrompt;        
	TimePickerDialog timePickerDialog;        
	final static int RQS_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textAlarmPrompt = (TextView) findViewById(R.id.alarmprompt);            
        buttonstartSetDialog = (Button)findViewById(R.id.startSetDialog);        
        buttonstartSetDialog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				textAlarmPrompt.setText("");              
				openTimePickerDialog(false);
				
			}
		});
    }

    private void openTimePickerDialog(boolean is24r) {        
    	Calendar calendar = Calendar.getInstance();           
    	timePickerDialog = new TimePickerDialog(MainActivity.this,              
    	onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),              
    	calendar.get(Calendar.MINUTE), true);        
    	timePickerDialog.setTitle("Set Alarm Time");           
    	timePickerDialog.show();	 
    	
    	} 
    OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {           
		@Override 
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {              
		Calendar calNow = Calendar.getInstance();           
		Calendar calSet = (Calendar) calNow.clone();              
		calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);           
		calSet.set(Calendar.MINUTE, minute);           
		calSet.set(Calendar.SECOND, 0);           
		calSet.set(Calendar.MILLISECOND, 0);              
		if (calSet.compareTo(calNow) <= 0) {             
		 // Today Set time passed, count to tomorrow              
		 calSet.add(Calendar.DATE, 1);              
		 Log.i("hasil", " =<0");           
		 } else if (calSet.compareTo(calNow) > 0) {              
		 Log.i("hasil", " > 0");           
		 } else {              
		 Log.i("hasil", " else ");  }              
		 setAlarm(calSet);        
		 }    
	};
    
    private void setAlarm(Calendar targetCal) {    
    	textAlarmPrompt.setText("***\n" + "Alarm set on " + targetCal.getTime()              
    			+ "\n***");           
    	Intent intent = new Intent(getBaseContext(),AlarmReceiver.class);        
    	PendingIntent pendingIntent = PendingIntent.getBroadcast(              
    			getBaseContext(), RQS_1, intent, 0); 
    
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE); 
     
    alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),              
    		pendingIntent);        }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
