package mx.itson.semanaisw2013app;

import mx.itson.semanaisw2013app.utils.BitmapHack;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
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
	
		button_conferences.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),ConferenceActivity.class);
				startActivity(intent);
			}
		});
	}
}
