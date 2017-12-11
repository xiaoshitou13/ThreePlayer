package byc.by.com.threeplayer.topic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseActivity;
import byc.by.com.threeplayer.find.bean.IjkitBean;
import byc.by.com.threeplayer.find.view.Ijkitplayer;
import byc.by.com.threeplayer.my.sqlites.dao;
import byc.by.com.threeplayer.topic.adapter.MyAdapter_LieBiao;
import byc.by.com.threeplayer.topic.bean.Topic_Bean;
import byc.by.com.threeplayer.topic.presenter.Presenter;
import byc.by.com.threeplayer.topic.view.Iview;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.ApiServer;
import utils.RetroFactory;

/**
 * Created by ASUS on 2017/12/6.
 */

public class LieBiaoActivity extends BaseActivity implements Iview{
    @BindView(R.id.ryv_main)
    RecyclerView ryvMain;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout srlMain;
    private MyAdapter_LieBiao myAdapter;
    List<Topic_Bean.RetBean.ListBean> list;
    int index=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liebiao_activity);
        index=Integer.valueOf(getIntent().getStringExtra("key"));
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
        Presenter presenter = new Presenter(this);
        presenter.getdata(index+"");


        ApiServer topic = RetroFactory.Topic();
        Observable<Topic_Bean> topic1 = topic.getTopic("/front/columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum="+index);
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
                        ryvMain.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                        list=topic_bean.getRet().getList();
                        myAdapter=new MyAdapter_LieBiao(list,LieBiaoActivity.this);
                        ryvMain.setAdapter(myAdapter);
                        myAdapter.setOnItemClickListener(new MyAdapter_LieBiao.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                dao dao = new dao(LieBiaoActivity.this);
                                dao.add(list.get(position).getTitle(),list.get(position).getPic(),list.get(position).getLoadURL());
                                EventBus.getDefault().postSticky(new IjkitBean(list.get(position).getLoadURL()));
                                startActivity(new Intent(LieBiaoActivity.this, Ijkitplayer.class));
                            }
                        });
                    }
                });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void suss(Topic_Bean topic_bean) {
        ryvMain.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        list=topic_bean.getRet().getList();
        myAdapter=new MyAdapter_LieBiao(list,LieBiaoActivity.this);
        ryvMain.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter_LieBiao.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("TAGs",list.get(position).getLoadURL());
                if (list.get(position).getLoadURL().equals("")){
                    startActivity(new Intent(LieBiaoActivity.this, Ijkitplayer.class));
                }else {
                    EventBus.getDefault().postSticky(new IjkitBean(list.get(position).getLoadURL()));
                    startActivity(new Intent(LieBiaoActivity.this, Ijkitplayer.class));
                }

            }
        });
    }
}
