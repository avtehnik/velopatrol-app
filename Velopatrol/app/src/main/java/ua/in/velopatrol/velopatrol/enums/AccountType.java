package ua.in.velopatrol.velopatrol.enums;

/**
 * Created by Anton on 1/31/2015.
 */
public enum AccountType {
	USER("user"),
	VOLUNTEER("volunteer");
	private final String value;

	AccountType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
