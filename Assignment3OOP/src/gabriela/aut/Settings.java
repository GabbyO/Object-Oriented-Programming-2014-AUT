package gabriela.aut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings.SettingNotFoundException;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ToggleButton;

//Gabriela Orellana 1244821

public class Settings extends Activity implements View.OnClickListener {

	private SeekBar seekBar;
	private Button backButton;
	public Intent i;
	public Vibrator vib;
	public ToggleButton toggleButton,toggleButton1;
	public MediaPlayer mp = null;
	public boolean isVibOn;
	long pattern;
	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_settings);
	    
	    // TODO Auto-generated method stub

	    //VIBRATE
	    pattern = 150;
	    toggleButton = (ToggleButton)findViewById(R.id.toggleButton1); 
	    toggleButton.setOnCheckedChangeListener( new OnCheckedChangeListener()
	    {
	        @Override
	        public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked)
	        {
	        	if(isChecked == true)
	        	{
		        	toggleButton.setChecked(isChecked);
		            //TODO
		      		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		      		vib.vibrate(pattern);
	        	} 
	        		else 
	        	{
	        		if(isChecked == false)
		        	{
			        	toggleButton.setChecked(isChecked);
			      		vib.cancel();
		        	}
	        	}
	        }
	    });
	    
	    //AUDIO
	    setVolumeControlStream(AudioManager.STREAM_MUSIC);
	    toggleButton1 = (ToggleButton)findViewById(R.id.ToggleButton01); 
	    toggleButton1.setOnCheckedChangeListener( new OnCheckedChangeListener()
	    {
	        @Override
	        public void onCheckedChanged(CompoundButton toggleButton1, boolean isChecked)
	        {
	        	if(isChecked)
	        	{
	        		mp = MediaPlayer.create(getApplicationContext(), 
	        				R.raw.dramaticinto); 
	        		mp.start();
	        	}
	            else
	            {
	            	mp.release();	   
	            }
	        }
	    });
	    
	    //BRIGHTNESS
	    seekBar = (SeekBar)findViewById(R.id.seekBar1);	  
	    seekBar.setMax(255);

	    float curBrightnessValue = 0;
	    try 
	    {
	    	curBrightnessValue = android.provider.Settings.System.getInt(
	    	getContentResolver(),
	        android.provider.Settings.System.SCREEN_BRIGHTNESS);
	    } 
	    	catch (SettingNotFoundException e) 
	    {
	    	e.printStackTrace();
	    }

	     int screen_brightness = (int) curBrightnessValue;
	     seekBar.setProgress(screen_brightness);
	    seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
	     int progress = 0;

	      @Override
	     public void onProgressChanged(SeekBar seekBar, int progresValue,
	       boolean fromUser) {
	      progress = progresValue;
	     }

	      @Override
	     public void onStartTrackingTouch(SeekBar seekBar) {}

	      @Override
	     public void onStopTrackingTouch(SeekBar seekBar) {
	      android.provider.Settings.System.putInt(getContentResolver(),
	        android.provider.Settings.System.SCREEN_BRIGHTNESS,
	        	progress);
	     }
	    });
	    
	    //BACK BUTTON
	    backButton = (Button)findViewById(R.id.BACKbutton);	
	    backButton.setOnClickListener(this);

	}
	
	//BACK BUTTON
	@Override
	public void onClick(View v) 
	{
		if(v == backButton)
		{
			i = new Intent(this, MainActivity.class);	
			startActivity(i); 
			finish();
		}
	}

}
