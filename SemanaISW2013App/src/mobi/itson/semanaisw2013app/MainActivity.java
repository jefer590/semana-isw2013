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

	private static final int[] MAIN_BUTTONS_SIZE = {300,150};
	
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
		ImageButton button_about_us = (ImageButton) findViewById(R.id.main_button_itson);
		ImageButton button_schedule = (ImageButton) findViewById(R.id.main_button_horario);
		ImageButton button_semanaisw = (ImageButton) findViewById(R.id.main_button_semanaisw2013);
		
		//We put the image in the button
		//ALL IMAGES MUST HAVE WIDTH:300 AND HEIGHT: 150
		//CONFERENCES
		Bitmap bitmap_conference_button = BitmapHack.decodeSampledBitmapFromResource(getResources(),
				R.drawable.conference_image, MAIN_BUTTONS_SIZE[0], MAIN_BUTTONS_SIZE[1]);
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
				R.drawable.workshop_image, MAIN_BUTTONS_SIZE[0], MAIN_BUTTONS_SIZE[1]);
		setBackgroundImageButton(button_workshops, bitmap_workshop_button);
		
		button_workshops.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), WorkshopActivity.class);
				startActivity(intent);
			}
		});
		
		//#SEMANAISW2013
		Bitmap bitmap_semana_button = BitmapHack.decodeSampledBitmapFromResource(getResources(),
				R.drawable.semanaisw_register, MAIN_BUTTONS_SIZE[0], MAIN_BUTTONS_SIZE[1]);
		setBackgroundImageButton(button_semanaisw, bitmap_semana_button);
		button_semanaisw.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
				startActivity(intent);
			}
		});
		
		
		//SCHEDULE
		Bitmap bitmap_horario_button = BitmapHack.decodeSampledBitmapFromResource(getResources(), 
				R.drawable.horario, MAIN_BUTTONS_SIZE[0], MAIN_BUTTONS_SIZE[1]);
		setBackgroundImageButton(button_schedule, bitmap_horario_button);
		button_schedule.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
				startActivity(intent);
			}
		});
		
		//REGISTER
		Bitmap bitmap_register_button = BitmapHack.decodeSampledBitmapFromResource(getResources(),
				R.drawable.eventbrite, MAIN_BUTTONS_SIZE[0], MAIN_BUTTONS_SIZE[1]);
		setBackgroundImageButton(button_register, bitmap_register_button);
		button_register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), WebView_Register_Actvity.class);
				startActivity(intent);
			}
		});
		
		//ABOUT_US
		Bitmap bitmap_about_us_button = BitmapHack.decodeSampledBitmapFromResource(getResources(),
				R.drawable.itsonmobi_dark_logo,MAIN_BUTTONS_SIZE[0], MAIN_BUTTONS_SIZE[1]);
		setBackgroundImageButton(button_about_us, bitmap_about_us_button);
		button_about_us.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
	private void setBackgroundImageButton(ImageButton imageButton, Bitmap bitmap){
		Drawable background_Drawable = new BitmapDrawable(getResources(), bitmap);
		ActivityUtils.setBackground(imageButton, background_Drawable);
	}
}
