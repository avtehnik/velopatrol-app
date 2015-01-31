package ua.in.velopatrol.velopatrol.entities;

/**
 * Created by Anton on 1/31/2015.
 */
public class UserPhotos {

	private String profile_thumb;
	private String profile_small_thumb;
	private String profile_chat_thumb;

	public UserPhotos() {
	}

	public UserPhotos(String profile_thumb, String profile_small_thumb, String profile_chat_thumb) {
		this.profile_thumb = profile_thumb;
		this.profile_small_thumb = profile_small_thumb;
		this.profile_chat_thumb = profile_chat_thumb;
	}

	@Override
	public String toString() {
		return "UserPhotos{" +
				"profile_thumb='" + profile_thumb + '\'' +
				", profile_small_thumb='" + profile_small_thumb + '\'' +
				", profile_chat_thumb='" + profile_chat_thumb + '\'' +
				'}';
	}

	public String getProfile_thumb() {
		return profile_thumb;
	}

	public void setProfile_thumb(String profile_thumb) {
		this.profile_thumb = profile_thumb;
	}

	public String getProfile_small_thumb() {
		return profile_small_thumb;
	}

	public void setProfile_small_thumb(String profile_small_thumb) {
		this.profile_small_thumb = profile_small_thumb;
	}

	public String getProfile_chat_thumb() {
		return profile_chat_thumb;
	}

	public void setProfile_chat_thumb(String profile_chat_thumb) {
		this.profile_chat_thumb = profile_chat_thumb;
	}
}
