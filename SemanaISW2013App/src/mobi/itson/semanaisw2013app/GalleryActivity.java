package mobi.itson.semanaisw2013app;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mobi.itson.semanaisw2013app.utils.HttpHelper;
import mobi.itson.semanaisw2013app.utils.SemanaUpload;
import mobi.itson.semanaisw2013app.utils.UploadImage;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;

public class GalleryActivity extends SherlockActivity {
    
	private static final String SERVER_URL = "http://codigo-itson.appspot.com/";
	
	public static final String IMAGES = "com.nostra13.example.universalimageloader.IMAGES";
	public static final String IMAGE_POSITION = "com.nostra13.example.universalimageloader.IMAGE_POSITION";
	
	private ImageLoader imageLoader = ImageLoader.getInstance();
	
    private static final int ACTION_TAKE_PICTURE = 1;
    private static final int ACTION_SELECT_IMAGE = 2;
    private static final String TAG = "GalleryActivity";    
    private static final String ALBUM_NAME = "SEMANAISW2013";
    
    private static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
	private static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";

	private boolean pauseOnScroll = false;
	private boolean pauseOnFling = true;
	
    private String mCurrentPhotoPath;
    private AbsListView listView;
    
    String[] imageUrls;

	DisplayImageOptions options;
    
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        if(android.os.Build.VERSION.SDK_INT >= 9) {
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    		StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_gallery);
        setupActionBar();
        loadImages();
    }
    
    public void loadImages() {
    	if(!imageLoader.isInited()){
			imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		}
		imageUrls = getImgUrlsFromServer();

		options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.semana_isw_logoo)
			.showImageForEmptyUri(R.drawable.ic_launcher)
			.showImageOnFail(R.drawable.ic_launcher)
			.cacheInMemory()
			.cacheOnDisc()
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();
		
		listView = (GridView) findViewById(R.id.gridView);
		((GridView) listView).setAdapter(new ImageAdapter());
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImagePagerActivity(position);
			}
		});
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
		applyScrollListener();
    }
    
    private void applyScrollListener() {
		listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling));
	}
    
    @Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(STATE_PAUSE_ON_SCROLL, pauseOnScroll);
		outState.putBoolean(STATE_PAUSE_ON_FLING, pauseOnFling);
	}
    
    private void startImagePagerActivity(int position) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(IMAGES, imageUrls);
		intent.putExtra(IMAGE_POSITION, position);
		startActivity(intent);
	}
    
    public String[] getImgUrlsFromServer(){
		HttpHelper httpHelper = new HttpHelper();
		List<String> images = new ArrayList<String>();
		try {
			String response = httpHelper.DoGet(SERVER_URL+"/images?size=100", null);
			Gson gson = new Gson();
			List<UploadImage> uploadedImages = gson.fromJson(response, new TypeToken<List<UploadImage>>(){}.getType());
			for(UploadImage img: uploadedImages){
				images.add(img.getImageBlobKey());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return images.toArray(new String[images.size()]);
	}
    
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		} else {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.menu.gallery_menu, menu);
    	return true;
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
		case R.id.gallery_add_picture:
			try
		    {
		        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		        intent.setType("image/*");
		        startActivityForResult(intent, ACTION_SELECT_IMAGE);
		    }
		    catch ( ActivityNotFoundException e )
		    {
		        Log.e( TAG, "No gallery: " + e );
		    }
			return true;
		case R.id.camera_take_picture:
			if (isCameraIntentAvailable()) {
                startCameraIntent();
            }
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	View content = findViewById(R.id.gridView);
    	View loadingScreen = findViewById(R.id.loading);
    	switch(requestCode) {
    	case ACTION_TAKE_PICTURE:
    		if (resultCode == RESULT_OK) {
                if (mCurrentPhotoPath != null) {
                	content.setVisibility(View.GONE);
                	loadingScreen.setVisibility(View.VISIBLE);
                    saveImage();
                    File file = new File(mCurrentPhotoPath);
                    SemanaUpload.upload(getApplicationContext(), file, content, loadingScreen, this);
                    mCurrentPhotoPath = null;
                }
            } else if(resultCode == RESULT_CANCELED) {
            	Toast.makeText(getApplicationContext(), "Entonces Haz un twit con el hashtag #SEMANAISW2013", Toast.LENGTH_SHORT).show();
            }
    		break;
    	case ACTION_SELECT_IMAGE:
    		if(resultCode == RESULT_OK) {
    			content.setVisibility(View.GONE);
    	    	loadingScreen.setVisibility(View.VISIBLE);
    			Uri selectedImageUri = data.getData();
    			mCurrentPhotoPath = getPath(selectedImageUri);
    			File file = new File(mCurrentPhotoPath);
    			SemanaUpload.upload(getApplicationContext(), file, content, loadingScreen, this);
                mCurrentPhotoPath = null;
    		}
    		break;
    	}
    }
    
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor;
        cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    
    private File getAlbumDirectory() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), ALBUM_NAME);
            } else {
                storageDir = new File(Environment.getExternalStorageDirectory() + "/dcim/" + ALBUM_NAME);
            }            
            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if (!storageDir.exists()){
                        Log.d(TAG, "failed to create directory");
                        return null;
                    }
                }
            }            
        } else {
            Log.v(TAG, "External storage is not mounted READ/WRITE.");
        }
        
        return storageDir;
    }
    
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File albumF = getAlbumDirectory();
        File imageF = File.createTempFile(imageFileName, ".jpg", albumF);
        return imageF;
    }

    private File setupImageFile() throws IOException {
        File file = createImageFile();
        mCurrentPhotoPath = file.getAbsolutePath();        
        return file;
    }

    private void saveImage() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File file = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }
    
    private boolean isCameraIntentAvailable() {
        final PackageManager packageManager = getPackageManager();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> list =
            packageManager.queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
    
    private void startCameraIntent() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        
        File file = null;
        
        try {
            file = setupImageFile();
            mCurrentPhotoPath = file.getAbsolutePath();
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        } catch (IOException e) {
            e.printStackTrace();
            file = null;
            mCurrentPhotoPath = null;
        }
        
        startActivityForResult(cameraIntent, ACTION_TAKE_PICTURE);
    }
    
    public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ImageView imageView;
			if (convertView == null) {
				imageView = (ImageView) getLayoutInflater().inflate(R.layout.item_grid_image, parent, false);
			} else {
				imageView = (ImageView) convertView;
			}

			imageLoader.displayImage(imageUrls[position], imageView, options);

			return imageView;
		}
		
	}

}
