package mobi.itson.semanaisw2013app.utils;

import java.io.File;
import java.io.FileNotFoundException;

import mobi.itson.semanaisw2013app.GalleryActivity;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SemanaUpload {
	
	private final static String GAPE_UPLOAD_URL = "http://codigo-itson.appspot.com/upload";
	private final static String GAPE_UPLOAD_IMGFILE_PARAM = "imgFile";
	
	public static void upload(final Context context, final File f, final View content, final View loadingScreen, final GalleryActivity ga) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(GAPE_UPLOAD_URL, new AsyncHttpResponseHandler() {
		    @Override
		    public void onSuccess(String response) {
		    	response = response.trim();
		        startUpload(context, response, f, content, loadingScreen, ga);
		    }
		});
	}
	
	private static void startUpload(final Context context, final String url, final File f, final View content, final View loadingScreen, final GalleryActivity ga) {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		try {
			params.put(GAPE_UPLOAD_IMGFILE_PARAM, f);
		} catch(FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		}
		client.post(url, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				System.out.println(arg1);
				System.out.println("ERROR: " + arg0.getMessage());
				Toast.makeText(context, "FAIL!", Toast.LENGTH_LONG).show();
				content.setVisibility(View.VISIBLE);
				loadingScreen.setVisibility(View.GONE);
			}
			
			@Override
			public void onFinish() {
				Toast.makeText(context, "FINISH!", Toast.LENGTH_LONG).show();
				content.setVisibility(View.VISIBLE);
				loadingScreen.setVisibility(View.GONE);
				ga.loadImages();
			}
		});
	}
}
