package ua.in.velopatrol.velopatrol.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.entities.Volunteer;
import ua.in.velopatrol.velopatrol.utils.CircleTransform;

/**
 * Created by Anton on 2/3/2015.
 */
public class VolunteersAdapter extends ArrayAdapter<Volunteer> {

	public VolunteersAdapter(Context context, List<Volunteer> objects) {
		super(context, R.layout.item_volunteer, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {
			convertView = View.inflate(getContext(), R.layout.item_volunteer, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Volunteer volunteer = getItem(position);
		viewHolder.name.setText(volunteer.getName());
		viewHolder.description.setText(volunteer.getDescription());
		viewHolder.avatar.setImageResource(0);
		if (volunteer.getPhotos() != null) {
			Picasso.with(getContext())
					.load(volunteer.getPhotos().getProfileSmallThumb())
					.transform(new CircleTransform())
					.placeholder(R.drawable.ic_launcher)
					.into(viewHolder.avatar);
		}
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
