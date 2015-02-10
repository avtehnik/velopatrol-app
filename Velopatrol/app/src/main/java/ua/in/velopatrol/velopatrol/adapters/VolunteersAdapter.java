package ua.in.velopatrol.velopatrol.adapters;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.entities.Article;
import ua.in.velopatrol.velopatrol.entities.Volunteer;

/**
 * Created by Anton on 2/3/2015.
 */
public class VolunteersAdapter extends ArrayAdapter<Volunteer> {

	public VolunteersAdapter(Context context, List<Volunteer> objects) {
		super(context, R.layout.item_volunteer, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = View.inflate(getContext(), R.layout.item_volunteer, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Volunteer volunteer = getItem(position);
		viewHolder.name.setText(volunteer.getName());
//		viewHolder.date.setText(format.format(new Date(article.getDate()*1000)));
		viewHolder.description.setText(volunteer.getDescription());
		return convertView;
	}

	static class ViewHolder {
		TextView name;
		ImageView avatar;
		TextView description;

		public ViewHolder(View view) {
			name = (TextView) view.findViewById(R.id.txt_name);
			avatar = (ImageView) view.findViewById(R.id.img_avatar);
			description = (TextView) view.findViewById(R.id.txt_description);
		}
	}
}
