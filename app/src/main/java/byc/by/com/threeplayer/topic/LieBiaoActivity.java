package byc.by.com.threeplayer.topic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseActivity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.ApiServer;
import utils.RetroFactory;

/**
 * Created by ASUS on 2017/12/6.
 */

public class LieBiaoActivity extends BaseActivity {
    @BindView(R.id.ryv_main)
    RecyclerView ryvMain;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout srlMain;
    private MyAdapter_LieBiao myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liebiao_activity);
        ButterKnife.bind(this);
        init();
    }

    public void init() {

        srlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlMain.setRefreshing(false);
            }
        });

        ApiServer topic = RetroFactory.Topic();
        Observable<Topic_Bean> topic1 = topic.getTopic("/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum=1");
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
                        Log.d("sdfds",topic_bean.toString());
                        ryvMain.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                        myAdapter=new MyAdapter_LieBiao(topic_bean.getRet().getList(),LieBiaoActivity.this);
                        ryvMain.setAdapter(myAdapter);
                    }
                });
    }
}
