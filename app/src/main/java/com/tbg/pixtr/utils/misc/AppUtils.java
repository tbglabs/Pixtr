package com.tbg.pixtr.utils.misc;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.tbg.pixtr.R;
import com.tbg.pixtr.db.preferences.SharedPreferencesUtil;
import com.tbg.pixtr.model.pojo.common.Urls;
import com.tbg.pixtr.model.pojo.settings.SettingsPojo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by kausthubhadhikari on 25/12/17.
 */

public class AppUtils {

    /**
     * Convert PX to DP.
     *
     * @param size
     * @return
     */
    public int pxToDp(int size) {
        return (int) (size * Resources.getSystem().getDisplayMetrics().density);
    }


    /**
     * Get URI from a downloaded in Glide Image.
     *
     * @param context
     * @param bitmap
     * @return
     */
    public Uri getImageURI(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, UUID.randomUUID().toString() + ".png", "Pixtr Images");
        return Uri.parse(path);
    }


    /**
     * Settings arraylist.
     *
     * @return
     */
    public ArrayList<SettingsPojo> getSettingsList(Context context) {
        ArrayList<SettingsPojo> settings = new ArrayList<>();

        SettingsPojo settingsPojo = new SettingsPojo();
        settingsPojo.headerName = context.getString(R.string.other);
        settingsPojo.descriptionName = context.getString(R.string.na);
        settingsPojo.type = 1;
        settings.add(settingsPojo);

        settingsPojo = new SettingsPojo();
        settingsPojo.headerName = context.getString(R.string.clear_cache);
        settingsPojo.descriptionName = context.getString(R.string.clear_the_memory);
        settingsPojo.type = 2;
        settings.add(settingsPojo);

        settingsPojo = new SettingsPojo();
        settingsPojo.headerName = context.getString(R.string.go_to_unsplash);
        settingsPojo.descriptionName = context.getString(R.string.view_in_browser);
        settingsPojo.type = 2;
        settings.add(settingsPojo);

        settingsPojo = new SettingsPojo();
        settingsPojo.type = 1;
        settingsPojo.headerName = context.getString(R.string.quality);
        settingsPojo.descriptionName = context.getString(R.string.na);
        settings.add(settingsPojo);

        settingsPojo = new SettingsPojo();
        settingsPojo.headerName = context.getString(R.string.load_quality);
        settingsPojo.descriptionName = context.getString(R.string.choose_quality_loaded);
        settingsPojo.type = 2;
        settings.add(settingsPojo);

        settingsPojo = new SettingsPojo();
        settingsPojo.headerName = context.getString(R.string.download_quality);
        settingsPojo.descriptionName = context.getString(R.string.choose_quality_downloaded);
        settingsPojo.type = 2;
        settings.add(settingsPojo);

        settingsPojo = new SettingsPojo();
        settingsPojo.headerName = context.getString(R.string.wallpaper_quality);
        settingsPojo.descriptionName = context.getString(R.string.choose_quality_wallpaper);
        settingsPojo.type = 2;
        settings.add(settingsPojo);

        return settings;
    }

    /**
     * Edit Quality method.
     *
     * @param quality
     * @param flags
     * @param preferencesUtil
     */
    public void editQualityData(int quality, AppConstants.QUALITY_FLAGS flags, SharedPreferencesUtil preferencesUtil) {
        if (flags == AppConstants.QUALITY_FLAGS.DOWNLOAD) {
            preferencesUtil.setDownloadQuality(quality);
        } else if (flags == AppConstants.QUALITY_FLAGS.LOAD) {
            preferencesUtil.setLoadQuality(quality);
        } else if (flags == AppConstants.QUALITY_FLAGS.WALLPAPER) {
            preferencesUtil.setWallpaperQuality(quality);
        }
    }

    /**
     * Retrive the URL according to config.
     *
     * @param url
     * @return
     */
    public String retrieveLoadURLConfig(Urls url, SharedPreferencesUtil preferencesUtil, AppConstants.QUALITY_FLAGS flags) {
        int resConfig = -1;
        if (flags == AppConstants.QUALITY_FLAGS.WALLPAPER) {
            resConfig = preferencesUtil.getWallpaperQuality();
        } else if (flags == AppConstants.QUALITY_FLAGS.DOWNLOAD) {
            resConfig = preferencesUtil.getDownloadQuality();
        } else if (flags == AppConstants.QUALITY_FLAGS.LOAD) {
            resConfig = preferencesUtil.getLoadQuality();
        }

        if (resConfig == AppConstants.QUALITY_KEY_RAW) {
            return url.raw;
        } else if (resConfig == AppConstants.QUALITY_KEY_FULL) {
            return url.full;
        } else if (resConfig == AppConstants.QUALITY_KEY_REG) {
            return url.regular;
        } else if (resConfig == AppConstants.QUALITY_KEY_SMALL) {
            return url.small;
        } else if (resConfig == AppConstants.QUALITY_KEY_THUMB) {
            return url.thumb;
        } else {
            return null;
        }
    }

}
