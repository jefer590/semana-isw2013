package mx.itson.semanaisw2013app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.View;

@SuppressLint("NewApi")
@SuppressWarnings("deprecation")
public class ActivityUtils {
	
	/**
	 * Method that get the Screen Size
	 * @param activity Actual Activity
	 * @return array type int 
	 */
	public static int[] getScreenSize(Activity activity){
		
		//Obtain default display
		Display display = activity.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		
		//Set Size in case of ICS
		if(android.os.Build.VERSION.SDK_INT >= 13){
			display.getSize(size);
			return new int[] {size.x, size.y};
		} else {
			return new int[] {display.getWidth(), display.getHeight()};
		}
	}
	
	
	public static void setBackground(View view, Drawable drawable){
		//In case of Newer Version
		if(android.os.Build.VERSION.SDK_INT >= 16){
			view.setBackground(drawable);
		} else {
			view.setBackgroundDrawable(drawable);
		}
	}
}
