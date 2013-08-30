package mx.itson.semanaisw2013app;

import mx.itson.semanaisw2013app.utils.ActivityUtils;
import mx.itson.semanaisw2013app.utils.BitmapHack;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar;

public class SplashActivity extends SherlockActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		
		//Get the image view reference
		ImageView imageView = (ImageView) findViewById(R.id.splash_imageview);
		
		/**Size with [0]= width
		[1] = height**/
		int[] size = ActivityUtils.getScreenSize(this);
		
		//Hack the bitmap
		Bitmap bitmap = BitmapHack.decodeSampledBitmapFromResource(getResources(), 
				R.drawable.semana_isw_logo, size[0], size[1]);
		
		//Set the source for the imageview
		imageView.setImageBitmap(bitmap);
		
		//Hide the action bar
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		//Handler
		Handler handler = new Handler();
		
		//run an thread after x seconds to start the home screen
		int x = 2;
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				//Finishing the Splash Screen and preventing to come back to menu
				finish();
				
				//Starting the Main Screen
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				SplashActivity.this.startActivity(intent);
			}
		}, x * 1000); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called
	}
	
}
