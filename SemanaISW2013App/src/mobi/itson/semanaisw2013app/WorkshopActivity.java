package mobi.itson.semanaisw2013app;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

@SuppressLint("ShowToast")
public class WorkshopActivity extends SherlockFragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		
		setUpActionBar();
	}
	
	@SuppressWarnings("static-access")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setUpActionBar(){
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//Tabs
		actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);
		
		actionBar.addTab(actionBar.newTab().setText("Talleres")
				.setTabListener(new TabListener(getWorkshops(R.array.workshops), "Talleres")));
		
	}
	
	private ArrayList<Workshop> getWorkshops(int dayResource){
		ArrayList<Workshop> workshops = new ArrayList<Workshop>();
		Resources res = getResources();
		
		TypedArray dayResources = res.obtainTypedArray(dayResource);
		
		String[] names = res.getStringArray(dayResources.getResourceId(0, 0));
		String[] instructors = res.getStringArray(dayResources.getResourceId(1, 0));
		String[] requirements = res.getStringArray(dayResources.getResourceId(2, 0));
		String[] dates = res.getStringArray(dayResources.getResourceId(3, 0));
		String[] classrooms = res.getStringArray(dayResources.getResourceId(4, 0)); 
		String[] summaries = res.getStringArray(dayResources.getResourceId(5, 0));
		String[] durations = res.getStringArray(dayResources.getResourceId(6, 0));
		TypedArray images = res.obtainTypedArray(dayResources.getResourceId(7, 0));
		
		//Array length
		int arrayLength = names.length;
		
		for(int i = 0; i<arrayLength; i++){
			workshops.add(new Workshop(names[i], instructors[i], summaries[i], 
					requirements[i], dates[i], classrooms[i], durations[i], images.getResourceId(i, 0)));
		}
		
		dayResources.recycle();
		images.recycle();
		
		return workshops;
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
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static class TabListener implements ActionBar.TabListener{

		private WorkshopPagerFragment mFragment;
			
		private final ArrayList<Workshop> mWorkshops;
		private final String mTag;
		
		public TabListener(ArrayList<Workshop> mWorkshops, String mTag){
			this.mWorkshops = mWorkshops;
			this.mTag = mTag;
		}
		
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if(mFragment == null){
				mFragment = WorkshopPagerFragment.newInstance(mWorkshops);
				ft.add(android.R.id.content,mFragment, mTag);
			} else {
				ft.attach(mFragment);
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if(mFragment != null){
				ft.detach(mFragment);
			}
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) throws UnsupportedOperationException{
			
		}
		
		
	}
	
	
}
