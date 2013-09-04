package mobi.itson.semanaisw2013app;

import android.os.Parcel;
import android.os.Parcelable;

public class Conference implements Parcelable{

	private String name;
	private String speaker;
	private String location;
	private String date;
	private String summary;
	private int image;
	
	public Conference(){
	}
	
	public Conference(String name, String speaker, String location, String date, String summary, int image){
		this.name = name;
		this.speaker = speaker;
		this.location = location;
		this.date = date;
		this.summary = summary;
		this.image = image;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(speaker);
		dest.writeString(location);
		dest.writeString(date);
		dest.writeString(summary);
		dest.writeInt(image);
	}

}
