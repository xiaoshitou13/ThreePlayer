package byc.by.com.threeplayer;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;
import com.makeramen.roundedimageview.RoundedImageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import byc.by.com.threeplayer.base.BaseActivity;
import byc.by.com.threeplayer.cehua.Fuli_Activity;
import byc.by.com.threeplayer.choice.view.ChoiceFragment;
import byc.by.com.threeplayer.find.FindFragment;
import byc.by.com.threeplayer.my.MyFragment;
import byc.by.com.threeplayer.my.MySettings;
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
                new ShareAction(MainActivity.this).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN)
                        .setContentList(new ShareContent(), new ShareContent())
                        .withText("title")
                        .setListenerList(shareListener, shareListener)
                        .setShareboardclickCallback(shareBoardlistener)
                        .open();
                break;
            case R.id.menutext5:
                break;
            case R.id.menutext6:
                startActivity(new Intent(MainActivity.this,MySettings.class));
                break;
            case R.id.guanyu:
                break;
            case R.id.zhuti:
                break;
        }
    }

    private ShareBoardlistener shareBoardlistener = new ShareBoardlistener() {

        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            if (share_media == null) {
                if (snsPlatform.mKeyword.equals("11")) {
                    Toast.makeText(MainActivity.this, "add button success", Toast.LENGTH_LONG).show();
                }

            } else {
                UMImage image = new UMImage(MainActivity.this,
                        BitmapFactory.decodeResource(getResources(), R.drawable.card_bg_def));

                new ShareAction(MainActivity.this).setPlatform(share_media).withMedia(image).setCallback(shareListener)
                        .withText("多平台分享")
                        .share();
            }
        }
    };
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected boolean enableSliding() {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(MainActivity.this).onActivityResult(requestCode, resultCode, data);
    }
}
