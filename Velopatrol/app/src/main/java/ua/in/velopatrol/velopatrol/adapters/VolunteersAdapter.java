package ua.in.velopatrol.velopatrol.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.FailReason;

import java.util.List;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.entities.Volunteer;
import ua.in.velopatrol.velopatrol.utils.BitmapUtils;
import ua.in.velopatrol.velopatrol.utils.ImageLoadingListenerImpl;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

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
			viewHolder.avatarProgressBar.setVisibility(View.VISIBLE);
			SystemUtils.IMAGELOADER.displayImage(volunteer.getPhotos().getProfileSmallThumb(), viewHolder.avatar, new ImageLoadingListenerImpl() {

				@Override
				public void onLoadingComplete(String imageUri, View view, final Bitmap loadedImage) {
					if (loadedImage != null) {
						viewHolder.avatar.setImageBitmap(BitmapUtils.getScaledRoundBitmap(loadedImage));
					} else {
						viewHolder.avatar.setImageResource(R.drawable.ic_launcher);
					}
					viewHolder.avatarProgressBar.setVisibility(View.GONE);
				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
					viewHolder.avatarProgressBar.setVisibility(View.GONE);
				}

				@Override
				public void onLoadingCancelled(String imageUri, View view) {
					viewHolder.avatarProgressBar.setVisibility(View.GONE);
				}
			});
		}
		return convertView;
	}

	static class ViewHolder {
		TextView name;
		ImageView avatar;
		TextView description;
		View avatarProgressBar;

		public ViewHolder(View view) {
			name = (TextView) view.findViewById(R.id.txt_name);
			avatar = (ImageView) view.findViewById(R.id.img_avatar);
			description = (TextView) view.findViewById(R.id.txt_description);
			avatarProgressBar = view.findViewById(R.id.avatar_progress_bar);
		}
	}
}
