package ua.in.velopatrol.velopatrol.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.rightutils.rightutils.collections.RightList;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.applications.VelopatlorApp;
import ua.in.velopatrol.velopatrol.entities.Challenge;
import ua.in.velopatrol.velopatrol.enums.ChallengeState;

/**
 * Created by Anton Maniskevich on 12/5/14.
 */
public class UserChallengeAdapter extends RecyclerView.Adapter<UserChallengeAdapter.ViewHolder> implements Filterable {

	private static final String TAG = UserChallengeAdapter.class.getSimpleName();
	private Context context;
	private ChallengeFilter challengeFilter;
	private RightList<Challenge> challenges = new RightList<>();

	public static class ViewHolder extends RecyclerView.ViewHolder {
		TextView title;
		TextView date;
		TextView address;

		public ViewHolder(View v) {
			super(v);
			title = (TextView) v.findViewById(R.id.txt_title);
			date = (TextView) v.findViewById(R.id.txt_date);
			address = (TextView) v.findViewById(R.id.txt_address);
		}
	}

	public UserChallengeAdapter(Context context) {
		this.context = context;
	}

	@Override
	public UserChallengeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_challenge, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Challenge item = challenges.get(position);
		holder.title.setText("Lorem ipsum");
		holder.address.setText(item.getAddress());
		holder.date.setText(item.getDate());
//		holder.description.setText(job.getDescription());
//		holder.more.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				context.startActivity(new Intent(context, JobDetailActivity.class).putExtra(Job.class.getSimpleName(), job));
//				((Activity) context).overridePendingTransition(R.anim.next_enter, R.anim.next_leave);
//			}
//		});
	}

	@Override
	public int getItemCount() {
		return challenges.size();
	}

	@Override
	public Filter getFilter() {
		if (challengeFilter == null) {
			challengeFilter = new ChallengeFilter();
		}
		return challengeFilter;
	}

	private class ChallengeFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			RightList<Challenge> challenges = VelopatlorApp.dbUtils.getChallenge(constraint.equals(ChallengeState.OPEN.getValue()) ? ChallengeState.OPEN : ChallengeState.CLOSED);
			results.values = challenges;
			results.count = challenges.size();
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			if (results.values != null) {
				challenges.clear();
				challenges.addAll((RightList<Challenge>) results.values);
				notifyDataSetChanged();
			}
		}
	}

//	public void updateList(RightList<Job> newJobs) {
//		if (!jobs.containsAll(newJobs)) {
//			jobs.clear();
//			jobs.addAll(newJobs);
//			notifyDataSetChanged();
//		}
//	}


}
