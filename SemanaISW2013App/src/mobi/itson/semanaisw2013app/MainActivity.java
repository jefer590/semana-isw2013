package mobi.itson.semanaisw2013app;

import mobi.itson.semanaisw2013app.utils.ActivityUtils;
import mobi.itson.semanaisw2013app.utils.BitmapHack;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Primary buttons
		setImageViews();
		
		//ActionBarSherlock
		ActionBar actionbar = getSupportActionBar();
		actionbar.setTitle(R.string.app_name);
	}
	
	private void setImageViews(){
		ImageButton button_conferences = (ImageButton) findViewById(R.id.main_button_conferencias);
		ImageButton button_workshops = (ImageButton) findViewById(R.id.main_button_talleres);
		ImageButton button_register = (ImageButton) findViewById(R.id.main_button_register);
		
		//We put the image in the button
		//ALL IMAGES MUST HAVE WIDTH:300 AND HEIGHT: 150
		//CONFERENCES
		Bitmap bitmap_conference_button = BitmapHack.decodeSampledBitmapFromResource(getResources(),
				R.drawable.conference_image, 300, 150);
		setBackgroundImageButton(button_conferences, bitmap_conference_button);
		
		button_conferences.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),ConferenceActivity.class);
				startActivity(intent);
			}
		});
		
		//WORKSHOPS
		Bitmap bitmap_workshop_button = BitmapHack.decodeSampledBitmapFromResource(getResources(),
				R.drawable.workshop_image, 300, 150);
		setBackgroundImageButton(button_workshops, bitmap_workshop_button);
		
		button_workshops.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), WorkshopActivity.class);
				startActivity(intent);
			}
		});
		
		//REGISTER
		
		button_register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), WebView_Register_Actvity.class);
				startActivity(intent);
			}
		});
	}
	
	private void setBackgroundImageButton(ImageButton imageButton, Bitmap bitmap){
		Drawable background_Drawable = new BitmapDrawable(getResources(), bitmap);
		ActivityUtils.setBackground(imageButton, background_Drawable);
	}
}
