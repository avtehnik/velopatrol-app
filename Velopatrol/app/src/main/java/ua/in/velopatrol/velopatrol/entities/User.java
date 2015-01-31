package ua.in.velopatrol.velopatrol.entities;

import com.rightutils.rightutils.collections.RightList;

/**
 * Created by Anton on 1/30/2015.
 */
public class User {
	private long id;
	private String name;
	private String date;
	private String phone;
	private String authToken;
	private String socialLink;
	private String description;
	private String state;
	private RightList<String> areas;
	private String userType;
	private UserPhotos photos;

	public User() {
	}

	public User(long id, String name, String date, String phone, String authToken, String socialLink, String description, String state, RightList<String> areas, String userType, UserPhotos photos) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.phone = phone;
		this.authToken = authToken;
		this.socialLink = socialLink;
		this.description = description;
		this.state = state;
		this.areas = areas;
		this.userType = userType;
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", date='" + date + '\'' +
				", phone='" + phone + '\'' +
				", authToken='" + authToken + '\'' +
				", socialLink='" + socialLink + '\'' +
				", description='" + description + '\'' +
				", state='" + state + '\'' +
				", areas=" + areas +
				", userType='" + userType + '\'' +
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

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getSocialLink() {
		return socialLink;
	}

	public void setSocialLink(String socialLink) {
		this.socialLink = socialLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public RightList<String> getAreas() {
		return areas;
	}

	public void setAreas(RightList<String> areas) {
		this.areas = areas;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public UserPhotos getPhotos() {
		return photos;
	}

	public void setPhotos(UserPhotos photos) {
		this.photos = photos;
	}
}
