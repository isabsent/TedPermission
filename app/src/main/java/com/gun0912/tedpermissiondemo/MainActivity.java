package com.gun0912.tedpermissiondemo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_normal).setOnClickListener(this);
        findViewById(R.id.btn_windowPermission).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        Intent intent=null;

        switch (id){
            case R.id.btn_normal:
                intent = new Intent(this,NormalActivity.class);
                break;

            case R.id.btn_windowPermission:
                intent = new Intent(this,WindowPermissionActivity.class);
                break;

        }

        if(intent!=null){
            startActivity(intent);
        }

    }
}
