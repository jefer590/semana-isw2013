package mobi.itson.semanaisw2013app;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import mobi.itson.semanaisw2013app.utils.BitmapHack;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;

public class AboutUsActivity extends SherlockActivity{

	private final String[] mUsernames = new String[] {
			"Main devs:Fernando Alvarez;@jefer590",
			"CODIGO2013APP;SourceCode",
			"Colaborador:Jose Miguel Salcido;@jmsalcido"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us_activity);
		
		setUpImageView();
		
		setUpTextViews();
		
		//Set up the actionbar
		setUpActionBar();
		
		// If the device is in landscape orientation please add some margin to the left.
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			ScrollView scrollview = (ScrollView) findViewById(R.id.aboutus_scroll_layout);
			RelativeLayout.LayoutParams relativeLayoutParams = (RelativeLayout.LayoutParams) scrollview.getLayoutParams();
			relativeLayoutParams.setMargins(100, 0, 0, 0);
			scrollview.setLayoutParams(relativeLayoutParams);
		}
	}
	
	private void setUpImageView(){
		ImageView image_view_logo = (ImageView) findViewById(R.id.aboutus_logo);
		Bitmap bitmap = BitmapHack.decodeSampledBitmapFromResource(getResources(), 
				R.drawable.itsonmobi_logoimagen, 300, 300);
		image_view_logo.setImageBitmap(bitmap);
	} 
	
	private void setUpTextViews(){
		LinearLayout layout = (LinearLayout) findViewById(R.id.aboutus_usernames_layout);
		LinearLayout.LayoutParams linearLayoutParams;
		linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
															LinearLayout.LayoutParams.WRAP_CONTENT);
		
		TextView textview;
		int id = 0;
		
		for(String dev : mUsernames){
			id++;
			String[] developer = dev.split(";");
			String name = developer[0];
			String username = developer[1];
			textview = new TextView(getApplicationContext());
			textview.setTextSize(20.0f);
			textview.setPadding(10, 20, 0, 0);
			changeTextView(textview, name, id, Color.WHITE, linearLayoutParams);
			textview.setPadding(10, 0, 0, 0);
			layout.addView(textview);
		}
	}
	
	private void changeTextView(TextView textview, String text, int id,
			int textColor, LinearLayout.LayoutParams linearLayoutParams){
		textview.setText(text);
		textview.setId(id);
		textview.setTextColor(textColor);
		textview.setLayoutParams(linearLayoutParams);
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setUpActionBar(){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		} else {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.about_us_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
			
		case R.id.about_us_feedback:
			Intent intent = new Intent(Intent.ACTION_SENDTO);
			intent.setType("message/rfc822");
			String uriText = "mailto:" + Uri.encode("masterofer36@gmail.com") + 
			          "?subject=" + Uri.encode("[SEMANA ISW 2013] - Feedback") + 
			          "&body=" + Uri.encode("Escribe aqui tu feedback");
			intent.setData(Uri.parse(uriText));
			startActivity(Intent.createChooser(intent, "Mandanos un feedback..."));
		}
		return super.onOptionsItemSelected(item);
	}
}
