package ua.in.velopatrol.velopatrol.enums;

/**
 * Created by Anton on 3/31/2015.
 */
public enum ChallengeState {
	OPEN("open"),
	CLOSED("closed");

	private final String value;

	ChallengeState(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
