package com.nanda.alarm;


import android.content.BroadcastReceiver; 
import android.content.Context; 
import android.content.Intent; 
import android.media.MediaPlayer; 
import android.view.View; 
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver { 
	 MediaPlayer player; 
	 @Override 
	 public void onReceive(Context context, Intent intent) { 
		  Toast.makeText(context, "Alarm aktif!", Toast.LENGTH_LONG).show(); 
		  player = MediaPlayer.create(context, R.raw.melodi);
		  player.start();
	 }
}
