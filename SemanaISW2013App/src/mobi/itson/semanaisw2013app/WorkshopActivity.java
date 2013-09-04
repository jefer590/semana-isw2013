package mobi.itson.semanaisw2013app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

@SuppressLint("ShowToast")
public class WorkshopActivity extends SherlockFragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
	}
	
	private void setUpActionBar(){
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//Tabs
	}
	
	@Override
	public void onBackPressed() {
		 Toast.makeText(getApplicationContext(), getString(R.string.back_button_danger), Toast.LENGTH_SHORT);
	}
}
