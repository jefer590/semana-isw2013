package mx.itson.semanaisw2013app.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapHack {

	
	/**
	 * Method that calculates the In SampleSize
	 * @param options Options from BitmapFactory
	 * @param reqWidth The require Width
	 * @param reqHeight The Require Height
	 * @param samplesize The sample size
	 * @return inSampleSize The In sample size
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight, int samplesize){
		//Define here the Width and Height by outwidth and outheight
		//also the insamplesize
		options.inJustDecodeBounds = true;
		final int width = options.outWidth;
		final int height = options.outHeight;
		int inSampleSize = samplesize;
		
		//if Width or Height are bigger than their reqs, will get the ratios
		if(width > reqWidth || height > reqHeight){
			final int widthRatio = Math.round( (float) width / (float) reqWidth);
			final int heightRatio = Math.round( (float) height / (float) reqHeight);
			
			//the lowest ratio have to be the insamplesize (in this case de height)
			return inSampleSize = (heightRatio < widthRatio) ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
	
	
	/**
	 * Method that decode a bitmap from Resource calculating a insamplesize
	 * @param res Resources
	 * @param resId id's Resources
	 * @param reqWidth require width
	 * @param reqHeight requiere height
	 * @param inSampleSize InsampleSize
	 * @return Decoded resource
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight, int inSampleSize){
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight, inSampleSize);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}
	
	/**
	 * Method that decode a bitmap from Resource
	 * @param res Resources
	 * @param resId id's Resources
	 * @param reqWidth require width
	 * @param reqHeight requiere height
	 * @return Decoded resource
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight, 2);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);	
	}
}
