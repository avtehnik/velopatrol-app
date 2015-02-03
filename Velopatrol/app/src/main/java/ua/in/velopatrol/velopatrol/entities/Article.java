package ua.in.velopatrol.velopatrol.entities;

/**
 * Created by Anton on 2/1/2015.
 */
public class Article {

	private long id;
	private boolean published;
	private long date;
	private long updated;
	private String title;
	private String text;

	public Article() {
	}

	public Article(long id, boolean published, long date, long updated, String title, String text) {
		this.id = id;
		this.published = published;
		this.date = date;
		this.updated = updated;
		this.title = title;
		this.text = text;
	}

	@Override
	public String toString() {
		return "Article{" +
				"id=" + id +
				", published=" + published +
				", date=" + date +
				", updated=" + updated +
				", title='" + title + '\'' +
				", text='" + text + '\'' +
				'}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getUpdated() {
		return updated;
	}

	public void setUpdated(long updated) {
		this.updated = updated;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
