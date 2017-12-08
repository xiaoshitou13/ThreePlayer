package byc.by.com.threeplayer.topic.model;

import android.content.Intent;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import byc.by.com.threeplayer.find.bean.IjkitBean;
import byc.by.com.threeplayer.find.view.Ijkitplayer;
import byc.by.com.threeplayer.topic.LieBiaoActivity;
import byc.by.com.threeplayer.topic.adapter.MyAdapter_LieBiao;
import byc.by.com.threeplayer.topic.bean.Topic_Bean;
import byc.by.com.threeplayer.topic.presenter.ipresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.ApiServer;
import utils.RetroFactory;

/**
 * Created by ASUS on 2017/12/8.
 */

public class Model implements IMode {
    @Override
    public void get(final ipresenter ipresenter,String s) {
        ApiServer topic = RetroFactory.Topic();
        Observable<Topic_Bean> topic1 = topic.getTopic("/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum="+s);
        topic1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Topic_Bean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Topic_Bean topic_bean) {
                        ipresenter.getdata(topic_bean);
                    }
                });
    }
}
