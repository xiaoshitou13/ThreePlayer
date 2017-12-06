package byc.by.com.threeplayer.find.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.find.bean.IjkitBean;
import byc.by.com.threeplayer.find.common.PlayerManager;


public class Ijkitplayer extends FragmentActivity implements PlayerManager.PlayerStateListener {

    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    private PlayerManager player;
    private String path;
    private String path1;
    private List<String> tb_list;
    private List<Fragment> flist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijkitplayer);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        addtab();
        //初始化播放器
        player = new PlayerManager(this);
        //player.setFullScreenOnly(true);
        player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
        //player.playInFullScreen(true);
        player.setPlayerStateListener(this);

    }

    private void addtab() {

        tb_list = new ArrayList<>();
        flist=new ArrayList<>();

        tb_list.add("简介");
        tb_list.add("评论");
        tablayout.addTab(tablayout.newTab().setText(tb_list.get(0)));
        tablayout.addTab(tablayout.newTab().setText(tb_list.get(1)));



        IntroduceFragment fragment1=new IntroduceFragment();
        CommentFragment fragment2=new CommentFragment();

        flist.add(fragment1);
        flist.add(fragment2);
        //Tablaout的方式进行联动效果
        tablayout.setTabMode(TabLayout.MODE_FIXED);//滑动模式
        Myviewpager myviewpager=new Myviewpager(getSupportFragmentManager());
        viewpage.setAdapter(myviewpager);
        //进行关联
        tablayout.setupWithViewPager(viewpage);
        tablayout.setTabsFromPagerAdapter(myviewpager);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (player.gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onPlay() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getpath(IjkitBean path) {
        path1 = path.getPath();
        player.play("http://video.jiecao.fm/5/1/%E8%87%AA%E5%8F%96%E5%85%B6%E8%BE%B1.mp4"

);
    }
    //ViewPager适配器，放入Fragment
    private class Myviewpager extends FragmentPagerAdapter {

        public Myviewpager(android.support.v4.app.FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tb_list.get(position);
        }


        @Override
        public Fragment getItem(int position) {
            return flist.get(position);
        }


        @Override
        public int getCount() {
            return flist.size();
        }

}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

