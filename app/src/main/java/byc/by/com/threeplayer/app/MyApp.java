package byc.by.com.threeplayer.app;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * Created by Zhang on 2017/12/5.
 */

public class MyApp extends Application {
    {
        //这里只做了QQ，微信，微博，其他平台同理
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
    }

}
