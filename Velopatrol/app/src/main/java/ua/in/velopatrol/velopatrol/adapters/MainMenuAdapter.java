package ua.in.velopatrol.velopatrol.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.entities.MenuEntity;

/**
 * Created by Anton on 1/30/2015.
 */
public class MainMenuAdapter extends ArrayAdapter<MenuEntity> {

	public MainMenuAdapter(Context context, List<MenuEntity> objects) {
		super(context, R.layout.item_menu, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(getContext(), R.layout.item_menu, null);
		MenuEntity menuEntity = getItem(position);

		TextView title = (TextView) view.findViewById(R.id.txt_item_name);
		title.setText(menuEntity.getTitle());
//		title.setCompoundDrawablesWithIntrinsicBounds(0, menuEntity.getDrawable(), 0, 0);
		return view;
	}
}
