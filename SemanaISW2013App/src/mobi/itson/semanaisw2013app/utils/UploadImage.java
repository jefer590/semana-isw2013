package mobi.itson.semanaisw2013app.utils;

import java.util.Date;

public class UploadImage {

	private Long id;
	private String imageBlobKey;
	private String thumbBlobKey;
	private Date creationDate;
	
	public UploadImage(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageBlobKey() {
		return imageBlobKey;
	}

	public void setImageBlobKey(String imageBlobKey) {
		this.imageBlobKey = imageBlobKey;
	}

	public String getThumbBlobKey() {
		return thumbBlobKey;
	}

	public void setThumbBlobKey(String thumbBlobKey) {
		this.thumbBlobKey = thumbBlobKey;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
}
