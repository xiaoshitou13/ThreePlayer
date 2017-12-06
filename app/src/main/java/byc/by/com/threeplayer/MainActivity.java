package byc.by.com.threeplayer;

import android.os.Bundle;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.base.BaseActivity;
import byc.by.com.threeplayer.choice.ChoiceFragment;
import byc.by.com.threeplayer.find.FindFragment;
import byc.by.com.threeplayer.my.MyFragment;
import byc.by.com.threeplayer.topic.TopicFragment;

public class MainActivity extends BaseActivity {


    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

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
