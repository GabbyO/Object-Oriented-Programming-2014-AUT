package gabriela.aut;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Gabriela Orellana 1244821

public class MainActivity extends Activity 
{
	private Button play, settings, exit;
	private Intent launchactivity1, launchactivity2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setTextSize(60);
        textView.setTextColor(Color.WHITE);
		
        //PLAY GAME BUTTON AND HEADING TO GAME ACTIVITY
        play = (Button)findViewById(R.id.buttonPLAY);
        play.setOnClickListener(new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if(v == play)
				{
					launchactivity1 = new Intent(MainActivity.this, ActivityTwo.class);                               
					startActivity(launchactivity1); 

					Toast.makeText(getApplicationContext(), "...Starting...",
							   Toast.LENGTH_SHORT).show();
					finish();
				}
			}
		});
        
        //SETTINGS BUTTON AND HEADING TO SETTINGS ACTIVITY
        settings = (Button)findViewById(R.id.buttonSETTING);
        settings.setOnClickListener(new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				if(v == settings)
				{
					launchactivity2 = new Intent(MainActivity.this, Settings.class);                               
					startActivity(launchactivity2); 
					finish();
				}
			}
		});
        
        //EXIT BUTTON AND EXITING THIS APP
        exit = (Button)findViewById(R.id.buttonEXIT);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
            	android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                finish();
            }
        });
	}
}