package ua.in.velopatrol.velopatrol.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

/**
 * Created by Anton on 3/23/2015.
 */
public abstract class BaseTaskMaterial extends AsyncTask<String, Integer, Boolean> {

	public interface Callback {
		public void successful();
		public void failed();
	}

	protected MaterialDialog.Builder progressBuilder;
	protected MaterialDialog progressDialog;
	protected boolean showProgress;
	protected Context context;
	protected Callback callback;

	public BaseTaskMaterial setCallback(Callback callback) {
		this.callback = callback;
		return this;
	}


	public BaseTaskMaterial(Context context, boolean showProgress) {
		this.context = context;
		this.showProgress = showProgress;
		if (showProgress) {
			this.progressBuilder = new MaterialDialog.Builder(context)
					.theme(Theme.DARK)
					.cancelable(false)
					.content("Будь-ласка зачекайте...")
					.progress(true, 0);
		}
	}

	@Override
	protected void onPreExecute() {
		if (showProgress) {
			progressDialog = progressBuilder.show();
		}
		super.onPreExecute();
	}


	@Override
	protected void onPostExecute(Boolean result) {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
		super.onPostExecute(result);

		if (callback != null) {
			if (result) {
				callback.successful();
			} else {
				callback.failed();
			}
		}
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
