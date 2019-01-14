package com.example.administrator.systemuidemo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.view.View;

/**
 * @author zy on 2018/12/11.
 */
public class SystemUiTool {

    public static void setSystemUiVisible(boolean statusVisible, boolean navigationVisible, FullScreenTypeEnum fullScreenType, Activity activity) {
        if (activity == null || activity.isDestroyed()) {
            return;
        }
        int uiOptions;
        if (!statusVisible && !navigationVisible) {
            // 全隐藏
            uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        } else if (statusVisible && navigationVisible) {
            // 全显示
            uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        } else if (!statusVisible) {
            // 只隐藏状态栏
            uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN;
        } else {
            // 只隐藏导航栏
            uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            switch (fullScreenType) {
                case FULL_SCREEN_TYPE_IMMERSIVE:
                    uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE;
                    break;
                case FULL_SCREEN_TYPE_IMMERSIVE_STICKY:
                    uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                    break;
                case FULL_SCREEN_TYPE_LEAN_BACK:
                    break;
            }
        }
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
        if (!statusVisible) {
            // 隐藏标题栏
            ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        }
    }

    public enum FullScreenTypeEnum {
        // 点击屏幕唤起系统ui，有系统回调
        FULL_SCREEN_TYPE_LEAN_BACK,
        // 滑动屏幕边缘唤起系统ui，有系统回调
        FULL_SCREEN_TYPE_IMMERSIVE,
        // 滑动屏幕边缘唤起系统ui，但ui是半透明的，唤起的滑动操作会传到应用中，无系统回调，ui几秒后或点击ui之外区域会自动消失
        FULL_SCREEN_TYPE_IMMERSIVE_STICKY
    }

}
