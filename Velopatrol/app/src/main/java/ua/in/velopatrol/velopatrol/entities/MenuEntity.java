package ua.in.velopatrol.velopatrol.entities;

import java.io.Serializable;

/**
 * Created by Anton Maniskevich on 10/2/14.
 */
public class MenuEntity implements Serializable {

	private Class<?> fragmentClass;
	private String title;
	private int drawable;

	public MenuEntity(Class<?> fragmentClass) {
		this.fragmentClass = fragmentClass;
	}

	public MenuEntity(Class<?> fragmentClass, String title) {
		this.fragmentClass = fragmentClass;
		this.title = title;
	}

	public MenuEntity(Class<?> fragmentClass, String title, int drawable) {
		this.fragmentClass = fragmentClass;
		this.title = title;
		this.drawable = drawable;
	}

	@Override
	public String toString() {
		return "MenuEntity{" +
				"fragmentClass=" + fragmentClass +
				", title='" + title + '\'' +
				", drawable=" + drawable +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MenuEntity)) return false;

		MenuEntity that = (MenuEntity) o;

		if (fragmentClass != null ? !fragmentClass.equals(that.fragmentClass) : that.fragmentClass != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return fragmentClass.hashCode();
	}

	public Class<?> getFragmentClass() {
		return fragmentClass;
	}

	public void setFragmentClass(Class<?> fragmentClass) {
		this.fragmentClass = fragmentClass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDrawable() {
		return drawable;
	}

	public void setDrawable(int drawable) {
		this.drawable = drawable;
	}
}
