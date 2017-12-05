package byc.by.com.threeplayer.app;

import android.app.Application;

import byc.by.com.threeplayer.utils.NetUtils;
import byc.by.com.threeplayer.utils.Toasts;

/**
 * Created by Zhang on 2017/12/5.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //判断网络状态
        isnet();

    }

    private void isnet() {
        boolean connected = NetUtils.isConnected(this);
        if(connected){
            boolean available = NetUtils.isAvailable(this);
            if(available){
                Toasts.showLong(this,"网络已连接");
            }else{
                Toasts.showLong(this,"当前网络不可用");
            }
        }else{
            Toasts.showLong(this,"当前网络无法连接");
        }
    }
}
