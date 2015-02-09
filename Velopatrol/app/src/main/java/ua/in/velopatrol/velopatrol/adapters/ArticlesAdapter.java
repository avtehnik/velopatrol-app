package ua.in.velopatrol.velopatrol.adapters;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.entities.Article;

/**
 * Created by Anton on 2/3/2015.
 */
public class ArticlesAdapter extends ArrayAdapter<Article> {

	private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm yyyy-MM-dd");

	public ArticlesAdapter(Context context, List<Article> objects) {
		super(context, R.layout.item_article, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = View.inflate(getContext(), R.layout.item_article, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Article article = getItem(position);
		viewHolder.title.setText(article.getTitle());
		viewHolder.date.setText(format.format(new Date(article.getDate()*1000)));
		viewHolder.description.setText(Html.fromHtml(article.getDescription()));
		return convertView;
	}

	static class ViewHolder {
		TextView title;
		TextView date;
		TextView description;

		public ViewHolder(View view) {
			title = (TextView) view.findViewById(R.id.txt_title);
			date = (TextView) view.findViewById(R.id.txt_date);
			description = (TextView) view.findViewById(R.id.txt_description);
		}
	}
}
