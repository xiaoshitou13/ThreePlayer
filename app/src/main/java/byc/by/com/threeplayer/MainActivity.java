package byc.by.com.threeplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import byc.by.com.threeplayer.base.BaseActivity;
import byc.by.com.threeplayer.cehua.Fuli_Activity;
import byc.by.com.threeplayer.choice.view.ChoiceFragment;
import byc.by.com.threeplayer.find.FindFragment;
import byc.by.com.threeplayer.my.MyFragment;
import byc.by.com.threeplayer.topic.TopicFragment;

public class MainActivity extends BaseActivity {
    @BindView(R.id.avatar)
    RoundedImageView avatar;
    @BindView(R.id.menutext)
    TextView menutext;
    @BindView(R.id.menuimage1)
    ImageView menuimage1;
    @BindView(R.id.menutext1)
    TextView menutext1;
    @BindView(R.id.menuimage2)
    ImageView menuimage2;
    @BindView(R.id.menutext2)
    TextView menutext2;
    @BindView(R.id.menuimage3)
    ImageView menuimage3;
    @BindView(R.id.menutext3)
    TextView menutext3;
    @BindView(R.id.menuimage4)
    ImageView menuimage4;
    @BindView(R.id.menutext4)
    TextView menutext4;
    @BindView(R.id.menuimage5)
    ImageView menuimage5;
    @BindView(R.id.menutext5)
    TextView menutext5;
    @BindView(R.id.menuimage6)
    ImageView menuimage6;
    @BindView(R.id.menutext6)
    TextView menutext6;
    @BindView(R.id.jiao)
    ImageView jiao;
    @BindView(R.id.guanyu)
    TextView guanyu;
    @BindView(R.id.ti)
    ImageView ti;
    @BindView(R.id.zhuti)
    TextView zhuti;
    @BindView(R.id.bottom_menu)
    LinearLayout bottomMenu;
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
                .addTabItem("我的", R.mipmap.my, MyFragment.class);



    }


    @OnClick({R.id.menutext, R.id.menutext1, R.id.menutext2, R.id.menutext3, R.id.menutext4, R.id.menutext5, R.id.menutext6, R.id.guanyu, R.id.zhuti})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menutext:
                break;
            case R.id.menutext1:
                break;
            case R.id.menutext2:
                Toast.makeText(MainActivity.this, "敬请期待", Toast.LENGTH_LONG).show();
                break;
            case R.id.menutext3:
                startActivity(new Intent(MainActivity.this, Fuli_Activity.class));
                break;
            case R.id.menutext4:
                break;
            case R.id.menutext5:
                break;
            case R.id.menutext6:
                break;
            case R.id.guanyu:
                break;
            case R.id.zhuti:
                break;
        }
    }

    @Override
    protected boolean enableSliding() {
        return false;
    }


}
