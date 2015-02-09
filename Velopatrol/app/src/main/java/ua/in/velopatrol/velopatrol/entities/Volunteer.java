package ua.in.velopatrol.velopatrol.entities;

import com.rightutils.rightutils.collections.RightList;

/**
 * Created by Anton on 1/30/2015.
 */
public class Volunteer {
	private long id;
	private String name;
	private String date;
	private String phone;
	private String socialLink;
	private long updated;
	private String description;
	private RightList<String> areas;
	private UserPhotos photos;

	public Volunteer() {
	}

	public Volunteer(long id, String name, String date, String phone, String socialLink, long updated, String description, RightList<String> areas, UserPhotos photos) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.phone = phone;
		this.socialLink = socialLink;
		this.updated = updated;
		this.description = description;
		this.areas = areas;
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "Volunteer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", date='" + date + '\'' +
				", phone='" + phone + '\'' +
				", socialLink='" + socialLink + '\'' +
				", updated=" + updated +
				", description='" + description + '\'' +
				", areas=" + areas +
				", photos=" + photos +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSocialLink() {
		return socialLink;
	}

	public void setSocialLink(String socialLink) {
		this.socialLink = socialLink;
	}

	public long getUpdated() {
		return updated;
	}

	public void setUpdated(long updated) {
		this.updated = updated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RightList<String> getAreas() {
		return areas;
	}

	public void setAreas(RightList<String> areas) {
		this.areas = areas;
	}

	public UserPhotos getPhotos() {
		return photos;
	}

	public void setPhotos(UserPhotos photos) {
		this.photos = photos;
	}
}
