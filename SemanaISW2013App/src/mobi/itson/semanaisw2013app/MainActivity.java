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
	
		//We put the image in the button
		//ALL IMAGES MUST HAVE WIDTH:150 AND HEIGHT: 150
		Bitmap bitmap = BitmapHack.decodeSampledBitmapFromResource(getResources(), R.drawable.conference_image,
				300, 150);
		setBackgroundImageButton(button_conferences, bitmap);
		
		button_conferences.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),ConferenceActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void setBackgroundImageButton(ImageButton imageButton, Bitmap bitmap){
		Drawable background_Drawable = new BitmapDrawable(getResources(), bitmap);
		ActivityUtils.setBackground(imageButton, background_Drawable);
	}
}
