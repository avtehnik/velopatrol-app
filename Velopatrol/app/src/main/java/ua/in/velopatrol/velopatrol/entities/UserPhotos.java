package ua.in.velopatrol.velopatrol.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Anton on 1/31/2015.
 */
public class UserPhotos {

	@JsonProperty("profile_thumb")
	private String profileThumb;
	@JsonProperty("profile_small_thumb")
	private String profileSmallThumb;
	@JsonProperty("profile_chat_thumb")
	private String profileChatThumb;

	public UserPhotos() {
	}

	public UserPhotos(String profileThumb, String profileSmallThumb, String profileChatThumb) {
		this.profileThumb = profileThumb;
		this.profileSmallThumb = profileSmallThumb;
		this.profileChatThumb = profileChatThumb;
	}

	@Override
	public String toString() {
		return "UserPhotos{" +
				"profileThumb='" + profileThumb + '\'' +
				", profileSmallThumb='" + profileSmallThumb + '\'' +
				", profileChatThumb='" + profileChatThumb + '\'' +
				'}';
	}

	public String getProfileThumb() {
		return profileThumb;
	}

	public void setProfileThumb(String profileThumb) {
		this.profileThumb = profileThumb;
	}

	public String getProfileSmallThumb() {
		return profileSmallThumb;
	}

	public void setProfileSmallThumb(String profileSmallThumb) {
		this.profileSmallThumb = profileSmallThumb;
	}

	public String getProfileChatThumb() {
		return profileChatThumb;
	}

	public void setProfileChatThumb(String profileChatThumb) {
		this.profileChatThumb = profileChatThumb;
	}
}
