package byc.by.com.threeplayer.base;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import byc.by.com.threeplayer.R;
import me.yokeyword.fragmentation.SupportActivity;
import utils.SlidingLayout;

/**
 * Created by Zhang on 2017/12/5.
 */

public class BaseActivity extends SupportActivity {
    private int theme;
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        sp= PreferenceManager.getDefaultSharedPreferences(this);
        theme=sp.getInt("theme_change", R.style.Theme7);
        setTheme(theme);
        setTranslucentStatus(true);

        if (enableSliding()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }




    }
    /**
     * 设置沉浸式
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    //    当Activity 回调onRestart时（从上一个页面返回），检查当前主题是否已将被更改。
    @Override
    protected void onRestart() {
        super.onRestart();
        int newTheme = sp.getInt("theme_change", theme);
        if (newTheme != theme) {
            recreate();
        }
    }
    protected boolean enableSliding() {
        return true;
    }
}
