package com.gun0912.tedpermission;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;

import java.util.ArrayList;

/**
 * Created by TedPark on 2017. 9. 26..
 */

public abstract class TedPermissionBase {
    public static final int REQ_CODE_REQUEST_SETTING = 2000;
    private static final String PREFS_NAME_PERMISSION = "PREFS_NAME_PERMISSION";
    private static final String PREFS_IS_FIRST_REQUEST = "IS_FIRST_REQUEST";

    public static boolean isGranted(Context context, String... permissions) {
        for (String permission : permissions) {
            if (isDenied(context, permission)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDenied(Context context, String permission) {
        return !isGranted(context, permission);
    }

    private static boolean isGranted(Context context, String permission) {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static ArrayList<String> getDeniedPermissions(Context context, String... permissions) {
        ArrayList<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (isDenied(context, permission)) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }

    public static boolean canRequestPermission(Activity activity, String... permissions) {

        if (isFirstRequest(activity, permissions)) {
            return true;
        }

        for (String permission : permissions) {
            boolean showRationale = activity.shouldShowRequestPermissionRationale(permission);
            if (isDenied(activity, permission) && !showRationale) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFirstRequest(Context context, String[] permissions) {
        for (String permission : permissions) {
            if (!isFirstRequest(context, permission)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFirstRequest(Context context, String permission) {
        return getSharedPreferences(context).getBoolean(getPrefsNamePermission(permission), true);
    }

    private static String getPrefsNamePermission(String permission) {
        return PREFS_IS_FIRST_REQUEST + "_" + permission;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME_PERMISSION, Context.MODE_PRIVATE);
    }

    public static void startSettingActivityForResult(Activity activity) {
        startSettingActivityForResult(activity, REQ_CODE_REQUEST_SETTING);
    }

    public static void startSettingActivityForResult(Activity activity, int requestCode) {
        activity.startActivityForResult(getSettingIntent(activity), requestCode);
    }

    private static Intent getSettingIntent(Context context) {
        return new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:" + context.getPackageName()));
    }

    public static void startSettingActivityForResult(Fragment fragment) {
        startSettingActivityForResult(fragment, REQ_CODE_REQUEST_SETTING);
    }

    public static void startSettingActivityForResult(Fragment fragment, int requestCode) {
        fragment.startActivityForResult(getSettingIntent(fragment.getActivity()), requestCode);
    }

    static void setFirstRequest(Context context, String[] permissions) {
        for (String permission : permissions) {
            setFirstRequest(context, permission);
        }
    }

    private static void setFirstRequest(Context context, String permission) {
        getSharedPreferences(context).edit().putBoolean(getPrefsNamePermission(permission), false).apply();
    }


}
