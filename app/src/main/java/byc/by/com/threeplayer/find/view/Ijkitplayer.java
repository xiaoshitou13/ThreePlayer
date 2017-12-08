package byc.by.com.threeplayer.find.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.find.MyOkhttp;
import byc.by.com.threeplayer.find.bean.IjkitBean;
import byc.by.com.threeplayer.find.bean.Video;
import byc.by.com.threeplayer.find.common.PlayerManager;
import okhttp3.Request;


public class Ijkitplayer extends FragmentActivity implements PlayerManager.PlayerStateListener {

    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    private PlayerManager player;
    private List<String> tb_list;
    private List<Fragment> flist;
    private String paths;
    private String smoothURLs;

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

    private void start(String path) {
        MyOkhttp.getAsync(paths, new MyOkhttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
            }
            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                Video video = gson.fromJson(result, Video.class);
                smoothURLs = video.getRet().getSmoothURL();
                if(smoothURLs!=null){
                    player.play(smoothURLs);
                }else{
                    Toast.makeText(Ijkitplayer.this, "暂时无资源", Toast.LENGTH_SHORT).show();
                }

            }
        });
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
        //Tablaout的方式进行联动效 果
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
    protected void onPause() {
        super.onPause();
        player.stop();
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
    public void getpath(IjkitBean video) {
        paths = video.getPath();
        Log.i("p",""+paths);
        start(paths);

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

