package byc.by.com.threeplayer.find.view;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.cehua.shoucang.Sc_Bean;
import byc.by.com.threeplayer.cehua.shoucang.Sc_Dao;
import byc.by.com.threeplayer.find.MyOkhttp;
import byc.by.com.threeplayer.find.bean.IjkitBean;
import byc.by.com.threeplayer.find.bean.Video;
import okhttp3.Request;
import utils.MediaUtils;


public class Ijkitplayer extends FragmentActivity {

    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.iv)
    ImageView mIv;
    private PlayerView player;
    private List<String> tb_list;
    private List<Fragment> flist;
    private String paths;
    private String smoothURLs;
    private Context mContext;
    private List<VideoijkBean> list;
    private PowerManager.WakeLock wakeLock;
    View rootView;
    private Video.RetBean ret;
    private String sp_url;
    private String image_url;
    private String name;
    private Sc_Bean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        rootView = getLayoutInflater().from(this).inflate(R.layout.activity_ijkitplayer, null);
        setContentView(rootView);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);

        /**虚拟按键的隐藏方法*/
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                //比较Activity根布局与当前布局的大小
                int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
                if (heightDiff > 100) {
                    //大小超过100时，一般为显示虚拟键盘事件
                    rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                } else {
                    //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
                    rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                }
            }
        });

        /**常亮*/
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();
        list = new ArrayList<VideoijkBean>();
        //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
        //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中

        player = new PlayerView(this, rootView) {
            @Override
            public PlayerView toggleProcessDurationOrientation() {
                hideSteam(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? PlayStateParams.PROCESS_PORTRAIT : PlayStateParams.PROCESS_LANDSCAPE);
            }

            @Override
            public PlayerView setPlaySource(List<VideoijkBean> list) {
                return super.setPlaySource(list);
            }
        }
                .setTitle("")
                .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                .setScaleType(PlayStateParams.fillparent)
                .forbidTouch(false)
                .hideSteam(true)
                .hideCenterPlayer(true)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(mContext)
                                .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                                .placeholder(R.color.cl_default)
                                .error(R.color.cl_error)
                                .into(ivThumbnail);
                    }
                })

                .setChargeTie(true, 60);
        addtab();

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
                ret = video.getRet();
                Log.d("ssss", ret.getPic());
                smoothURLs = video.getRet().getSmoothURL();
                bean = new Sc_Bean();
                name = ret.getTitle();
                image_url = ret.getPic();
                sp_url = ret.getSmoothURL();
                mTv.setText(name);
                String url1 = getLocalVideoPath("my_video.mp4");
                if (!new File(url1).exists()) {
                    url1 = smoothURLs;
                }
                String url2 = smoothURLs;
                VideoijkBean m1 = new VideoijkBean();
                m1.setStream("标清");
                m1.setUrl(url1);
                VideoijkBean m2 = new VideoijkBean();
                m2.setStream("高清");
                m2.setUrl(url2);
                list.add(m1);
                list.add(m2);
                player.setPlaySource(list).startPlay();
            }
        });
    }

    private void addtab() {

        tb_list = new ArrayList<>();
        flist = new ArrayList<>();

        tb_list.add("简介");
        tb_list.add("评论");
        tablayout.addTab(tablayout.newTab().setText(tb_list.get(0)));
        tablayout.addTab(tablayout.newTab().setText(tb_list.get(1)));


        IntroduceFragment fragment1 = new IntroduceFragment();
        CommentFragment fragment2 = new CommentFragment();

        flist.add(fragment1);
        flist.add(fragment2);
        //Tablaout的方式进行联动效 果
        tablayout.setTabMode(TabLayout.MODE_FIXED);//滑动模式
        Myviewpager myviewpager = new Myviewpager(getSupportFragmentManager());
        viewpage.setAdapter(myviewpager);
        //进行关联
        tablayout.setupWithViewPager(viewpage);
        tablayout.setTabsFromPagerAdapter(myviewpager);


    }


    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        MediaUtils.muteAudioFocus(mContext, true);
        finish();
//        player.stop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getpath(IjkitBean video) {
        paths = video.getPath();
        start(paths);


    }

    @OnClick({R.id.tv, R.id.iv})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv:
                break;
            case R.id.iv:

                Resources resources = getBaseContext().getResources();
                Drawable imageDrawable = resources.getDrawable(R.mipmap.collection_select); //图片在drawable目录下
                mIv.setBackgroundDrawable(imageDrawable);

                Sc_Dao dao = new Sc_Dao(mContext);
                dao.add(name, image_url, sp_url);

                break;
        }
    }

    //ViewPager适配器，放入Fragment
    private class Myviewpager extends FragmentPagerAdapter {

        public Myviewpager(FragmentManager supportFragmentManager) {
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
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        /**demo的内容，恢复设备亮度状态*/
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        /**demo的内容，暂停系统其它媒体的状态*/
        MediaUtils.muteAudioFocus(mContext, false);
        /**demo的内容，激活设备常亮状态*/
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    private String getLocalVideoPath(String name) {
        String sdCard = Environment.getExternalStorageDirectory().getPath();
        String uri = sdCard + File.separator + name;
        return uri;
    }
}

