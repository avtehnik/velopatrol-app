package ua.in.velopatrol.velopatrol.entities;

/**
 * Created by Anton on 2/12/2015.
 */
public class Challenge {

	private long id;
	private long volunteer;
	private String address;
	private double lat;
	private double lng;
	private String state;
	private long user;
	private long updated;
	private String date;

	public Challenge() {
	}

	public Challenge(long id, long volunteer, String address, double lat, double lng, String state, long user, long updated, String date) {
		this.id = id;
		this.volunteer = volunteer;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
		this.state = state;
		this.user = user;
		this.updated = updated;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Challenge{" +
				"id=" + id +
				", volunteer=" + volunteer +
				", address='" + address + '\'' +
				", lat=" + lat +
				", lng=" + lng +
				", state='" + state + '\'' +
				", user=" + user +
				", updated=" + updated +
				", date='" + date + '\'' +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(long volunteer) {
		this.volunteer = volunteer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getUpdated() {
		return updated;
	}

	public void setUpdated(long updated) {
		this.updated = updated;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
