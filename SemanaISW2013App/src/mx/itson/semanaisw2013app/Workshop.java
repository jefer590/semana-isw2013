package mx.itson.semanaisw2013app;

import android.os.Parcel;
import android.os.Parcelable;

public class Workshop implements Parcelable{

	private String name;
	private String instructor;
	private String summary;
	private String requirements;
	private String date;
	private String classroom;
	private int duration;
	private int image;
	
	public Workshop(){
		
	}
	
	public Workshop(String name, String instructor, String summary,
			String requirements, String date, String classroom, int duration, int image) {
		super();
		this.name = name;
		this.instructor = instructor;
		this.summary = summary;
		this.requirements = requirements;
		this.date = date;
		this.classroom = classroom;
		this.duration = duration;
		this.image = image;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClassroom(){
		return classroom;
	}
	
	public void setClassroom(String classroom){
		this.classroom = classroom;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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
		dest.writeString(instructor);
		dest.writeString(summary);
		dest.writeString(requirements);
		dest.writeString(date);
		dest.writeString(classroom);
		dest.writeInt(duration);
		dest.writeInt(image);
	}

}
