package com.example.administrator.systemuidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SystemUiActivity extends AppCompatActivity implements View.OnSystemUiVisibilityChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(this);
        setContentView(R.layout.system_ui_activity);
    }

    public void openAndOpen(View view) {
        SystemUiTool.setSystemUiVisible(true, true, SystemUiTool.FullScreenTypeEnum.FULL_SCREEN_TYPE_IMMERSIVE, this);
    }

    public void openAndClose(View view) {
        SystemUiTool.setSystemUiVisible(true, false, SystemUiTool.FullScreenTypeEnum.FULL_SCREEN_TYPE_IMMERSIVE, this);
    }

    public void closeAndOpen(View view) {
        SystemUiTool.setSystemUiVisible(false, true, SystemUiTool.FullScreenTypeEnum.FULL_SCREEN_TYPE_IMMERSIVE, this);
    }

    public void closeAndClose(View view) {
        SystemUiTool.setSystemUiVisible(false, false, SystemUiTool.FullScreenTypeEnum.FULL_SCREEN_TYPE_IMMERSIVE, this);
    }

    @Override
    public void onSystemUiVisibilityChange(int visibility) {
        boolean statusVisible = (visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0;
        boolean navigationVisible = (visibility & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;
        if (statusVisible && navigationVisible) {
            SystemUiTool.setSystemUiVisible(true, true, SystemUiTool.FullScreenTypeEnum.FULL_SCREEN_TYPE_IMMERSIVE, this);
        }
    }
}
