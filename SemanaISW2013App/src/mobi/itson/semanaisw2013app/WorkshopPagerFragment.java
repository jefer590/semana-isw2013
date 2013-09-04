package mobi.itson.semanaisw2013app;

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


public class WorkshopPagerFragment extends SherlockFragment{

	private static final String[] PARCELABLE_KEYS = {"conferences","conference"};
	
	public static WorkshopPagerFragment newInstance(ArrayList<Workshop> workshops){
		WorkshopPagerFragment workshop_pager_fragment = new WorkshopPagerFragment();
		Bundle args = new Bundle();
		args.putParcelableArrayList(PARCELABLE_KEYS[0], workshops);
		workshop_pager_fragment.setArguments(args);
		
		return workshop_pager_fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		ViewPager pager = new ViewPager(getActivity());
		pager.setId(0xFaca);
		
		ArrayList<Workshop> workshops = getArguments().getParcelableArrayList(PARCELABLE_KEYS[0]);
		pager.setAdapter(new WorkshopAdapter(getChildFragmentManager(), workshops));
		
		return pager;
	}
	
	public static class WorkshopFragment extends SherlockFragment{
		
		private ImageView mImageW;
		private TextView mNameW;
		private TextView mInstructorW;
		private TextView mRequirementsW;
		private TextView mDateW;
		private TextView mClassroomW; 
		private TextView mSummaryW;
		private TextView mDurationW;
		private Workshop mWorkshop;

		public static WorkshopFragment newInstance(Workshop workshop){
			WorkshopFragment workshop_fragment = new WorkshopFragment();
			Bundle args = new Bundle();
			args.putParcelable(PARCELABLE_KEYS[1], workshop);
			workshop_fragment.setArguments(args);
			
			return workshop_fragment;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.workshop_view, container, false);
			
			mImageW = (ImageView) view.findViewById(R.id.imageW);
			mNameW = (TextView) view.findViewById(R.id.nameW);
			mInstructorW = (TextView) view.findViewById(R.id.instructorW);
			mRequirementsW = (TextView) view.findViewById(R.id.requerimentsW);
			mDateW = (TextView) view.findViewById(R.id.dateW);
			mClassroomW = (TextView) view.findViewById(R.id.classroomW);
			mSummaryW = (TextView) view.findViewById(R.id.summaryW);
			mDurationW = (TextView) view.findViewById(R.id.durationW);
			
			if(android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.GINGERBREAD_MR1){
				view.findViewById(R.id.scrollWorkshop).setBackgroundColor(Color.BLACK);
				mNameW.setTextColor(Color.BLACK);
				mInstructorW.setTextColor(Color.BLACK);
				mRequirementsW.setTextColor(Color.BLACK);
				mDateW.setTextColor(Color.BLACK);
				mClassroomW.setTextColor(Color.BLACK);
				mSummaryW.setTextColor(Color.BLACK);
				mDurationW.setTextColor(Color.BLACK);
			}
			
			return view;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			mWorkshop = getArguments().getParcelable(PARCELABLE_KEYS[1]);
			
			mImageW.setImageResource(mWorkshop.getImage());
			mNameW.setText(mWorkshop.getName());
			mInstructorW.setText(mWorkshop.getInstructor());
			mRequirementsW.setText(mWorkshop.getRequirements());
			mDateW.setText(mWorkshop.getDate());
			mClassroomW.setText(mWorkshop.getClassroom());
			mSummaryW.setText(Html.fromHtml(mWorkshop.getSummary()));
			mDurationW.setText(mWorkshop.getDuration());
		}
		
	}//Class Fragment Finished :)
	
	public static class WorkshopAdapter extends FragmentPagerAdapter{
		
		private List<WorkshopFragment> fragments = new ArrayList<WorkshopFragment>();

		public WorkshopAdapter(FragmentManager fragmentManager, List<Workshop> workshops){
			super(fragmentManager);
			init(workshops);
		}
		
		private void init(List<Workshop> workshops){
			for(Workshop workshop : workshops){
				WorkshopFragment workshop_fragment = WorkshopFragment.newInstance(workshop);
				fragments.add(workshop_fragment);
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
	}//Adapter class Finished
}//OP class finished
