package ua.in.velopatrol.velopatrol.entities;

/**
 * Created by Anton on 1/30/2015.
 */
public class Cache {

	private String pushId;
	private String deviceId;
	private User user;

	public Cache() {
	}

	public Cache(String pushId, String deviceId, User user) {
		this.pushId = pushId;
		this.deviceId = deviceId;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Cache{" +
				"pushId='" + pushId + '\'' +
				", deviceId='" + deviceId + '\'' +
				", user=" + user +
				'}';
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
