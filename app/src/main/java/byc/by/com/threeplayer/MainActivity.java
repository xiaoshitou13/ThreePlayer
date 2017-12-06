package byc.by.com.threeplayer;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.base.BaseActivity;
import byc.by.com.threeplayer.choice.view.ChoiceFragment;
import byc.by.com.threeplayer.find.FindFragment;
import byc.by.com.threeplayer.my.MyFragment;
import byc.by.com.threeplayer.topic.TopicFragment;

public class MainActivity extends BaseActivity {
    private long firstTime = 0;


    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        bottomTabBar.init(getSupportFragmentManager())

                .addTabItem("精选", R.mipmap.found, ChoiceFragment.class)
                .addTabItem("专题", R.mipmap.special, TopicFragment.class)
                .addTabItem("发现", R.mipmap.search, FindFragment.class)
                .addTabItem("我的", R.mipmap.my, MyFragment.class) ;


    }


}
