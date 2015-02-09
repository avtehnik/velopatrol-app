package ua.in.velopatrol.velopatrol.dao;
import com.rightutils.rightutils.collections.RightList;

import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

import ua.in.velopatrol.velopatrol.entities.UserPhotos;
import ua.in.velopatrol.velopatrol.entities.Volunteer;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

/**
 * Created by Anton on 1/30/2015.
 */
public class VolunteerDAO {
	private long id;
	private String name;
	private String date;
	private String phone;
	private String socialLink;
	private long updated;
	private String description;
	private String areas;
	private String photos;

	public static VolunteerDAO newInstance(Volunteer volunteer) throws IOException {
		VolunteerDAO volunteerDAO = new VolunteerDAO();
		volunteerDAO.setId(volunteer.getId());
		volunteerDAO.setName(volunteer.getName());
		volunteerDAO.setDate(volunteer.getDate());
		volunteerDAO.setPhone(volunteer.getPhone());
		volunteerDAO.setSocialLink(volunteer.getSocialLink());
		volunteerDAO.setUpdated(volunteer.getUpdated());
		volunteerDAO.setDescription(volunteer.getDescription());
		volunteerDAO.setAreas(SystemUtils.MAPPER.writeValueAsString(volunteer.getAreas()));
		volunteerDAO.setPhotos(SystemUtils.MAPPER.writeValueAsString(volunteer.getPhotos()));
		return volunteerDAO;
	}

	public Volunteer toVolunteer() throws IOException {
		Volunteer volunteer = new Volunteer();
		volunteer.setId(id);
		volunteer.setName(name);
		volunteer.setDate(date);
		volunteer.setPhone(phone);
		volunteer.setSocialLink(socialLink);
		volunteer.setUpdated(updated);
		volunteer.setDescription(description);
		volunteer.setAreas((RightList<String>) SystemUtils.MAPPER.readValue(areas, new TypeReference<RightList<String>>() {}));
		volunteer.setPhotos(SystemUtils.MAPPER.readValue(photos, UserPhotos.class));
		return volunteer;
	}

	public VolunteerDAO() {
	}

	public VolunteerDAO(long id, String name, String date, String phone, String socialLink, long updated, String description, String areas, String photos) {
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

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
}
