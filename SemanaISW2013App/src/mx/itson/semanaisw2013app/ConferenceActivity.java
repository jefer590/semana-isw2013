package mx.itson.semanaisw2013app;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class ConferenceActivity extends SherlockFragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setUpActionBar();
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setUpActionBar() {
		ActionBar actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		
		//Tabs
		actionbar.setNavigationMode(actionbar.NAVIGATION_MODE_TABS);
		
		actionbar.addTab(actionbar.newTab().setText("L 07/10")
				.setTabListener(new TabListener(getConferences(R.array.monday), "Lunes")));
		
		//actionbar.addTab(actionbar.newTab().setText("L 08/10")
		//		.setTabListener(new TabListener(getConferences(R.array.tuesday), "Martes")));
		
	}
	
	public ArrayList<Conference> getConferences(int dayResource){
		ArrayList<Conference> c = new ArrayList<Conference>();
		Resources res = getResources();
		
		TypedArray dayResources = res.obtainTypedArray(dayResource);
		
		String[] names = res.getStringArray(dayResources.getResourceId(0, 0));
		String[] speakers = res.getStringArray(dayResources.getResourceId(1, 0));
		String[] locations = res.getStringArray(dayResources.getResourceId(2, 0));
		String[] dates = res.getStringArray(dayResources.getResourceId(3, 0));
		String[] summaries = res.getStringArray(dayResources.getResourceId(4, 0));
		TypedArray images = res.obtainTypedArray(dayResources.getResourceId(5, 0));
		
		//Everyone must have the same length
		for(int i = 0; i<names.length; i++){
			c.add(new Conference(names[i], speakers[i], locations[i], dates[i], summaries[i], images.getResourceId(i, 0)));
		}
		
		images.recycle();
		dayResources.recycle();
		
		return c;
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

		private ConferencesPagerFragment mFragment;
		
		private final ArrayList<Conference> mConferences;
		private final String mTag;
		
		public TabListener(ArrayList<Conference> conferences, String tag){
			mConferences = conferences;
			mTag = tag;
		}
		
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if(mFragment == null){
				mFragment = ConferencesPagerFragment.newInstance(mConferences);
				ft.add(android.R.id.content, mFragment, mTag);
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
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			//Nothing, shut up
		}
		
		
	}
}
 	 	  	