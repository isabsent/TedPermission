package com.gun0912.tedpermissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import java.util.ArrayList;

/**
 * Created by TedPark on 16. 2. 21..
 */
public class NormalActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    PermissionListener permissionlistener = new PermissionListener() {
      @Override
      public void onPermissionGranted() {
        Toast.makeText(NormalActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        Toast.makeText(NormalActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT)
            .show();
      }


    };


    TedPermission.with(this)
        .setPermissionListener(permissionlistener)
        .setRationaleTitle(R.string.rationale_title)
        .setRationaleMessage(R.string.rationale_message)
        .setDeniedTitle("Permission denied")
        .setDeniedMessage(
            "If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
        .setGotoSettingButtonText("bla bla")
        .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION)
        .check();


  }
}
