package gabriela.aut;

import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//Gabriela Orellana 1244821

public class ActivityTwo extends  Activity implements View.OnClickListener  {
	
	private Random generator;
	private Button LEFT, RIGHT, BACK;
	private Intent ient;
	public ImageView plane, metor01, metor02, metor03;
	private boolean right, centre, left;
	protected boolean alive;
	private MediaPlayer mp = null;

	//Scoring and start timing
	private Handler myHandler = new Handler();
	private long startTime = 0L;
	private long timeInMillies = 0L;
	private long timeSwap = 0L;
	private long finalTime = 0L;
	
	protected TextView time, gameOver;
	protected int planePosition;
	protected int planePositionX, planePositionY;
	
	protected int metor02PositionXCENTRE, metor01PositionXLEFT, metor03PositionXRIGHT, 
		metor02PositionYCENTRE, metor01PositionYLEFT, metor03PositionYRIGHT;
	
	protected TranslateAnimation metorAnimation1,metorAnimation2,metorAnimation3,animation;
	
	protected float x1,x2;
	protected float y1, y2;
	protected int width,height;
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_activity_two);
	    // TODO Auto-generated method stub	    
	    
	    //ID OF OBJECTS
	    plane = (ImageView) findViewById(R.id.imageView1);
	    metor01 = (ImageView) findViewById(R.id.metor1);
	    metor02 = (ImageView) findViewById(R.id.metor2);
	    metor03 = (ImageView) findViewById(R.id.metor3);
	    
	    //TIMERS' TEXT VIEW AND COUNT UP
	    time = (TextView) findViewById(R.id.timer);
	    Runnable updateTimerMethod = new Runnable() 
	    {
	    	  public void run() 
	    	  {
		    	   timeInMillies = SystemClock.uptimeMillis() - startTime;
		    	   finalTime = timeSwap + timeInMillies;
	
		    	   int seconds = (int) (finalTime / 1000);
		    	   int minutes = seconds / 60;
		    	   seconds = seconds % 60;
		    	   int milliseconds = (int) (finalTime % 1000);
		    	   int hours = minutes/60;
		    	   
		    	   time.setText("Time: " + hours + ":" + minutes + ":"
		    	     + String.format("%02d", seconds) + ":"
		    	     + String.format("%03d", milliseconds));
		    	   myHandler.postDelayed(this, 0);
	    	  }
	    };
	    	 
	    startTime = SystemClock.uptimeMillis();
	    myHandler.postDelayed(updateTimerMethod, 0);

	    //PLANE and METORS BUTTONS
	    RIGHT = (Button)findViewById(R.id.buttonRIGHT);
	    RIGHT.setOnClickListener(this);
			
        LEFT = (Button)findViewById(R.id.buttonLEFT);
        LEFT.setOnClickListener(this);
        
        BACK = (Button)findViewById(R.id.buttonBACK);
        BACK.setOnClickListener(this);
        
        //START POSITION OF PLANE CENTER
        right = false;
    	centre = true;
    	left = false;    	
    	
    	planePosition = (int) plane.getX();
		planePosition = 0;
		
		//Get Screen Size of PHONE
		Display display = getWindowManager().getDefaultDisplay(); 
		width = display.getWidth();
		height = display.getHeight();
		
		Log.d("HEIGHT && WIDTH: ", String.valueOf(height+ " " + width));
		
		generator = new Random();
		
		//METORS and PLANES' X AND Y	
		metor01PositionYLEFT = (int) metor01.getY();
		metor01PositionXLEFT = (int) metor01.getX();	
		metor01PositionXLEFT = 2;
		
		metor02PositionYCENTRE = (int) metor02.getY();
		metor02PositionXCENTRE = (int) metor02.getX();
		metor02PositionXCENTRE = 0;
		
		metor03PositionYRIGHT = (int) metor03.getY();
		metor03PositionXRIGHT = (int) metor03.getX();
		metor03PositionXRIGHT = 1;
		
		planePositionY = (int) plane.getY();
		planePositionX = (int) plane.getX();
		
		//AT START, PLANE IS ALIVE
		alive = true;

		if(this.alive == true)
		{
			metorAnimationOne();
			metorAnimationTwo();
			metorAnimationThree();
		} else {
			if(this.alive == false)
			{
				mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound); 
		    	mp.start();
		    	gameOver.setText("GAME OVER!");
		    	gameOver.setText(100);
		    	gameOver.getText();
			}
		}
	}	
	
	//If plane do not get hit, alive will be true if yes, will be false
	boolean isAlive()
	{
		if( this.planePosition == 2 && metor01PositionXLEFT == 2 && planePositionY == metor01PositionYLEFT
				|| this.planePosition == 0 && metor02PositionXCENTRE == 0 && planePositionY == metor02PositionYCENTRE 
				|| this.planePosition == 1 && metor03PositionXRIGHT == 1 && planePositionY ==  metor03PositionYRIGHT )
		{
			this.alive = false;
			return this.alive;
		} else {
			this.alive = true;
			return this.alive;
		}
	}
	
	//PLANE BUTTONS LEFT AND RIGHT
	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		if(this.centre == true)
		{
			if(v == RIGHT && this.planePosition == 0)
			{
				animation = new TranslateAnimation(0.0f, 170.0f, 0.0f, 0.0f);
				animation.setDuration(150);
				animation.setRepeatCount(0);
				animation.setFillAfter(true);
				plane.startAnimation(animation);	
				this.right = true;
				this.centre = false;
				this.left = false;  
				this.planePosition = 1;
			}
		}
		
		if(this.centre == true)
		{
			if(v == LEFT && this.planePosition == 0)
			{
				animation = new TranslateAnimation(0.0f, -170.0f, 0.0f, 0.0f);
				animation.setDuration(150);
				animation.setRepeatCount(0);
				animation.setFillAfter(true);
				plane.startAnimation(animation);
				this.right = false;
				this.centre = false;
				this.left = true;  
				this.planePosition = 2;  
			}
		}
				
		if(this.left == true)
		{
			if(v == RIGHT && this.planePosition == 2)
			{
				animation = new TranslateAnimation(-170.0f, 0.0f, 0.0f, 0.0f);
				animation.setDuration(150);
				animation.setRepeatCount(0);
				animation.setFillAfter(true);
				plane.startAnimation(animation);	
				this.right = false;
				this.centre = true;
				this.left = false;  
				this.planePosition = 0;
			}
		} 
			
		if(this.right == true)
		{
			if(v == LEFT && this.planePosition == 1)
			{
				animation = new TranslateAnimation(170.0f, 0.0f, 0.0f, 0.0f);
				animation.setDuration(150);
				animation.setRepeatCount(0);
				animation.setFillAfter(true);
				plane.startAnimation(animation);
				this.right = false;
				this.centre = true;
				this.left = false;  
				this.planePosition = 0;
			}
		}
		
		//BACK BUTTON TO MENU
		if(v == BACK)
		{
			ient = new Intent(this, MainActivity.class);	
			startActivity(ient); 
			finish();
		}
	}

	//METOR ANIMATION ONE
	public void metorAnimationOne()
	{
		metorAnimation1 = new TranslateAnimation(0.0f, 0.0f, -100.0f, 1500.0f);
		metorAnimation1.setDuration(5500);
		//Delay
		float easeInFactor = (float)generator.nextInt(7);
		metorAnimation1.setInterpolator(new AccelerateInterpolator(easeInFactor+1));
		metorAnimation1.setRepeatCount(100);
		metor01.startAnimation(metorAnimation1);
	}
	
	//METOR ANIMATION TWO
	public void metorAnimationTwo()
	{
		metorAnimation2 = new TranslateAnimation(0.0f, 0.0f, -100.0f, 1500.0f);
		metorAnimation2.setDuration(5000);
		//Delay
		float easeInFactor = (float)generator.nextInt(6);
		metorAnimation2.setInterpolator(new AccelerateInterpolator(easeInFactor+1));
		metorAnimation2.setRepeatCount(100);
		metor02.startAnimation(metorAnimation2);
	}
	
	//METOR ANIMATION THREE
	public void metorAnimationThree()
	{
		metorAnimation3 = new TranslateAnimation(0.0f, 0.0f, -100.0f, 1500.0f);
		metorAnimation3.setDuration(4500);
		//Delay
		float easeInFactor = (float)generator.nextInt(5);
		metorAnimation3.setInterpolator(new AccelerateInterpolator(easeInFactor+1));
		metorAnimation3.setRepeatCount(120);
		metor03.startAnimation(metorAnimation3);
	}
	
	//PLANE SWIPES
	public boolean onTouchEvent(MotionEvent touchevent) 
    {
        switch (touchevent.getAction())
        {
	        case MotionEvent.ACTION_DOWN: 
	        {
                x1 = touchevent.getX();             	 
                break;
            }
                case MotionEvent.ACTION_UP: 
                {
                 	x2 = touchevent.getX();

                 	//if left to right sweep event on screen
                 	if (x1 < x2) 
                 	{
                 		if(this.centre == true)
                		{
                				animation = new TranslateAnimation(0.0f, 170.0f, 0.0f, 0.0f);
                				animation.setDuration(150);
                				animation.setRepeatCount(0);
                				animation.setFillAfter(true);
                				plane.startAnimation(animation);
                				this.right = true;
                				this.centre = false;
                				this.left = false;  
                		}
                 	}
                 		
                 	if (x1 < x2) 
                    {
                 		if(this.left == true)
                		{
                			animation = new TranslateAnimation(-170.0f, 0.0f, 0.0f, 0.0f);
                			animation.setDuration(150);
                			animation.setRepeatCount(0);
                			animation.setFillAfter(true);
                			plane.startAnimation(animation);
                			this.right = false;
                			this.centre = true;
                			this.left = false;  
                		}
                	}
                 		
                 	// if right to left sweep event on screen
                 	if (x1 > x2)
                 	{
                		if(this.centre == true)
                		{
                			animation = new TranslateAnimation(0.0f, -170.0f, 0.0f, 0.0f);
                			animation.setDuration(150);
                			animation.setRepeatCount(0);
                			animation.setFillAfter(true);
                			plane.startAnimation(animation);
                			this.right = false;
                			this.centre = false;
                			this.left = true;  
                		}
                 	}
                	
                 	if (x1 > x2)
                    {
                 		if(this.right == true)
                		{
                 			animation = new TranslateAnimation(170.0f, 0.0f, 0.0f, 0.0f);
                			animation.setDuration(150);
                			animation.setRepeatCount(0);
                			animation.setFillAfter(true);
                			plane.startAnimation(animation);
                			this.right = false;
                			this.centre = true;
                			this.left = false;  
                		}
                 	}
                 	}
                 }
        return false;
    }	
}
