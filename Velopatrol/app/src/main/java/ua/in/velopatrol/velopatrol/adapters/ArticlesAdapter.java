package ua.in.velopatrol.velopatrol.adapters;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.entities.Article;

/**
 * Created by Anton on 2/3/2015.
 */
public class ArticlesAdapter extends ArrayAdapter<Article> {

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
		viewHolder.description.setText(Html.fromHtml(article.getText()));
		return convertView;
	}

	static class ViewHolder {
		TextView title;
		TextView description;

		public ViewHolder(View view) {
			title = (TextView) view.findViewById(R.id.txt_title);
			description = (TextView) view.findViewById(R.id.txt_description);
		}
	}
}
