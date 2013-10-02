package mobi.itson.semanaisw2013app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpHelper {

	public String DoPost(String url, List<NameValuePair> params) throws IOException{
		//Create a new httpclient and post header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(params));
		//Execute HTTP Post Request
		HttpResponse response = httpclient.execute(httpPost);
		String result = convertStreamToString(response.getEntity().getContent());
		
		return result;
	}
	
	public String DoGet(String url, List<NameValuePair> params) throws IOException{
		//create a new HttpClient and get Header
		HttpClient httpClient = new DefaultHttpClient();
		if(params!=null){
			url.concat("?").concat(URLEncodedUtils.format(params, "UTF-8"));
		}
		HttpGet httpGet = new HttpGet(url);
		//Execute HTTP Post Request
		HttpResponse response = httpClient.execute(httpGet);
		String result = convertStreamToString(response.getEntity().getContent());
		
		return result;
	}
	
	private static String convertStreamToString(InputStream inputStream){
		java.util.Scanner scanner = new java.util.Scanner(inputStream).useDelimiter("\\A");
		return scanner.hasNext() ? scanner.next() : "";
	}
}
