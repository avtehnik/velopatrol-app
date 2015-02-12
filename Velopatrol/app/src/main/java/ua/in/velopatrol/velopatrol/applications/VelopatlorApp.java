package ua.in.velopatrol.velopatrol.applications;

import android.app.Application;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import ua.in.velopatrol.velopatrol.db.DBUtils;
import ua.in.velopatrol.velopatrol.utils.SystemUtils;

/**
 * Created by Anton on 1/30/2015.
 */
public class VelopatlorApp extends Application {

	public static DBUtils dbUtils;

	@Override
	public void onCreate() {
		super.onCreate();
		dbUtils = DBUtils.newInstance(getApplicationContext(), "velopatrol.db", 1);
		if (SystemUtils.IMAGELOADER == null) {
			initImageLoader();
		}
	}

	private void initImageLoader() {
		SystemUtils.IMAGELOADER = ImageLoader.getInstance();
		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
				.displayer(new FadeInBitmapDisplayer(500))
				.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
				.diskCacheExtraOptions(720, 1280, null)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(10 * 1024 * 1024))
				.memoryCacheSize(10 * 1024 * 1024)
				.diskCacheSize(100 * 1024 * 1024)
				.diskCacheFileCount(200)
				.defaultDisplayImageOptions(options).build();
		SystemUtils.IMAGELOADER.init(config);
	}
}
