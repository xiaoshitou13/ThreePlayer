package byc.by.com.threeplayer;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hjm.bottomtabbar.BottomTabBar;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import byc.by.com.threeplayer.base.BaseActivity;
import byc.by.com.threeplayer.cehua.Adapter.QQLoginBean;
import byc.by.com.threeplayer.cehua.Fuli_Activity;
import byc.by.com.threeplayer.choice.view.ChoiceFragment;
import byc.by.com.threeplayer.find.FindFragment;
import byc.by.com.threeplayer.my.MyColorActivity;
import byc.by.com.threeplayer.my.MyFragment;
import byc.by.com.threeplayer.my.MySettings;
import byc.by.com.threeplayer.my.Util.ThemeUtils;
import byc.by.com.threeplayer.my.Util.Utils;
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
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;

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

        mTencent = Tencent.createInstance(APP_ID, MainActivity.this.getApplicationContext());


        bottomTabBar.init(getSupportFragmentManager())

                .addTabItem("精选", R.mipmap.found, ChoiceFragment.class)
                .addTabItem("专题", R.mipmap.special, TopicFragment.class)
                .addTabItem("发现", R.mipmap.search, FindFragment.class)
                .addTabItem("我的", R.mipmap.my, MyFragment.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Utils.context=MainActivity.this;
        ThemeUtils.initStatusBarColor(MainActivity.this,ThemeUtils.getPrimaryDarkColor(MainActivity.this));


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
                startActivity(new Intent(this,MyColorActivity.class));

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



    @OnClick(R.id.avatar)
    public void onClick() {
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(MainActivity.this, "all", mIUiListener);
    }
    class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        //是一个json串response.tostring，直接使用gson解析就好
                        Log.e(TAG, "登录成功" + response.toString());
                        Gson gson=new Gson();

                        QQLoginBean qqLoginBean = gson.fromJson(response.toString(), QQLoginBean.class);
                        Glide.with(MainActivity.this).load(qqLoginBean.getFigureurl_1()).into(avatar);
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
//            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

