package ua.in.velopatrol.velopatrol.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rightutils.rightutils.collections.RightList;

import ua.in.velopatrol.velopatrol.R;
import ua.in.velopatrol.velopatrol.entities.Challenge;

/**
 * Created by Anton Maniskevich on 12/5/14.
 */
public class UserChallengeAdapter extends RecyclerView.Adapter<UserChallengeAdapter.ViewHolder> {

	private RightList<Challenge> challenges;
	private Context context;

	public static class ViewHolder extends RecyclerView.ViewHolder {
		TextView jobName;
		TextView address;
		TextView date;
		TextView description;
		Button more;

		public ViewHolder(View v) {
			super(v);
//			jobName = (TextView) v.findViewById(R.id.txt_job_name);
//			address = (TextView) v.findViewById(R.id.txt_address);
//			date = (TextView) v.findViewById(R.id.txt_date);
//			description = (TextView) v.findViewById(R.id.txt_description);
//			more = (Button) v.findViewById(R.id.btn_more);
		}
	}

	public UserChallengeAdapter(Context context, RightList<Challenge> challenges) {
		this.context = context;
		this.challenges = challenges;
	}

	@Override
	public UserChallengeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_challenge, parent, false);
		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
//		final Job job = jobs.get(position);
//		holder.jobName.setText(job.getTitle());
//		holder.address.setText(job.getAddress());
//		holder.date.setText(SystemUtils.DATE_FORMAT.format(new Date(job.getStartDate()*1000)));
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

//	public void updateList(RightList<Job> newJobs) {
//		if (!jobs.containsAll(newJobs)) {
//			jobs.clear();
//			jobs.addAll(newJobs);
//			notifyDataSetChanged();
//		}
//	}
}
