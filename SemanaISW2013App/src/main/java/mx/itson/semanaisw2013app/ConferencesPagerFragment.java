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

public class ConferencesPagerFragment extends SherlockFragment {
	
	private static final String[] PARCELABLE_KEYS = {"conferences", "conference"};
	
	public static ConferencesPagerFragment newInstance(ArrayList<Conference> conferences){
		ConferencesPagerFragment conferences_pager_fragment = new ConferencesPagerFragment();
		Bundle args = new Bundle();
		args.putParcelableArrayList(PARCELABLE_KEYS[0], conferences);
		conferences_pager_fragment.setArguments(args);
		
		return conferences_pager_fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewPager pager = new ViewPager(getActivity());
		pager.setId(0xFace);
		
		ArrayList<Conference> conferences = getArguments().getParcelableArrayList(PARCELABLE_KEYS[0]);
		pager.setAdapter(new ConferenceAdapter(getFragmentManager(), conferences));
		
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
			ConferenceFragment conferenceFragment = new ConferenceFragment();
			
			Bundle args = new Bundle();
			args.putParcelable(PARCELABLE_KEYS[1], conference);
			conferenceFragment.setArguments(args);
			
			return conferenceFragment;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.conference_view, container, false);
			
			mImage = (ImageView) view.findViewById(R.id.image);
			mName = (TextView) view.findViewById(R.id.name);
			mSpeaker = (TextView) view.findViewById(R.id.speaker);
			mLocation = (TextView) view.findViewById(R.id.location);
			mDate = (TextView) view.findViewById(R.id.date);
			mSummary = (TextView) view.findViewById(R.id.summary);
			
			if(android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.GINGERBREAD_MR1){
				view.findViewById(R.id.scroll).setBackgroundColor(Color.WHITE);
				mName.setTextColor(Color.BLACK);
				mSpeaker.setTextColor(Color.BLACK);
				mLocation.setTextColor(Color.BLACK);
				mDate.setTextColor(Color.BLACK);
				mSummary.setTextColor(Color.BLACK);
			}
			
			return view;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			mConference = (Conference) getArguments().getParcelable(PARCELABLE_KEYS[1]);
			
			mImage.setImageResource(mConference.getImage());
			mName.setText(mConference.getName());
			mSpeaker.setText(mConference.getSpeaker());
			mLocation.setText(mConference.getLocation());
			mDate.setText(mConference.getDate());
			mSummary.setText(Html.fromHtml(mConference.getSummary()));
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
