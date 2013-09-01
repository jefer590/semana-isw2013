package mx.itson.semanaisw2013app;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;



public class ConferencesPagerFragment extends SherlockFragment{
	
	public static ConferencesPagerFragment newInstance(ArrayList<Conference> conferences){
		ConferencesPagerFragment c = new ConferencesPagerFragment();
		Bundle args = new Bundle();
		args.putParcelableArrayList("conferences", conferences);
		c.setArguments(args);
		
		return c;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewPager pager = new ViewPager(getActivity());
		pager.setId(0xFace);
		
		ArrayList<Conference> conferences = getArguments().getParcelableArrayList("coferences");
		pager.setAdapter(new ConferenceAdapter(getChildFragmentManager(), conferences));
		
		return pager;
	}
	
	public static class ConferenceFragment extends SherlockFragment{
		
		private ImageView mImage;
		private TextView mName;
		private TextView mSpeaker;
		private TextView mLocation;
		private TextView mDate;
		private TextView mSummary;
		private Conference mConference;
		
		public static ConferenceFragment newInstance(Conference conference){
			ConferenceFragment z = new ConferenceFragment();
			
			Bundle args = new Bundle();
			args.putParcelable("conferences", conference);
			z.setArguments(args);
			
			return z;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.conference_view, container, false);
			
			mImage = (ImageView) v.findViewById(R.id.image);
			mName = (TextView) v.findViewById(R.id.name);
			mSpeaker = (TextView) v.findViewById(R.id.speaker);
			mLocation = (TextView) v.findViewById(R.id.location);
			mDate = (TextView) v.findViewById(R.id.date);
			mSummary = (TextView) v.findViewById(R.id.summary);
			
			if(android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.GINGERBREAD_MR1){
				v.findViewById(R.id.scroll).setBackgroundColor(Color.WHITE);
				mName.setTextColor(Color.BLACK);
				mSpeaker.setTextColor(Color.BLACK);
				mLocation.setTextColor(Color.BLACK);
				mDate.setTextColor(Color.BLACK);
				mSummary.setTextColor(Color.BLACK);
			} else {
				//HOLO THEME DO THE WORK :)
			}
			
			return v;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			mConference = (Conference) getArguments().getParcelable("conference");
			
			mImage.setImageResource(mConference.getImage());
			mName.setText(mConference.getName());
			mSpeaker.setText(mConference.getSpeaker());
			mLocation.setText(mConference.getLocation());
			mDate.setText(mConference.getDate());
			mSummary.setText(mConference.getSummary());
		}
	}//Class Fragment Finished
	
	public static class ConferenceAdapter extends FragmentPagerAdapter{

		private List<ConferenceFragment> fragments = new ArrayList<ConferenceFragment>();

		public ConferenceAdapter(FragmentManager fm, List<Conference> conferences){
			super(fm);
			init(conferences);
		}
		
		private void init(List<Conference> conferences){
			for(Conference conference : conferences){
				ConferenceFragment cf = ConferenceFragment.newInstance(conference);
				fragments.add(cf);
			}
		}
		
		@Override
		public Fragment getItem(int index) {
			return fragments.get(index);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}
		
		
	}
	
}//OP Class finished
