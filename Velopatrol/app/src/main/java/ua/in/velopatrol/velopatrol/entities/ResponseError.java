package ua.in.velopatrol.velopatrol.entities;

/**
 * Created by Anton on 1/31/2015.
 */
public class ResponseError {

	private String message;

	public ResponseError() {
	}

	public ResponseError(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseError{" +
				"message='" + message + '\'' +
				'}';
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
