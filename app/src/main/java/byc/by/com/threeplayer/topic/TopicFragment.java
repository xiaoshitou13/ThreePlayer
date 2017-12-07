package byc.by.com.threeplayer.topic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseFragment;

/**
 * Created by Zhang on 2017/12/5.
 */

public class TopicFragment extends BaseFragment {
    @BindView(R.id.ryv_main)
    RecyclerView ryvMain;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout srlMain;
    Unbinder unbinder;
    private MyAdapter myAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.topic, container, false);

        unbinder = ButterKnife.bind(this, v);
        init();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void init() {
        List<String> list = new ArrayList<>();
        list.add("热点资讯");
        list.add("精彩推荐");
        list.add("大咖剧场");
        list.add("大片抢先看");
        list.add("微电影");
        list.add("香港映象");
        list.add("好莱坞");
        //数据适配
        ryvMain.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        myAdapter=new MyAdapter(list,getActivity());
        ryvMain.setAdapter(myAdapter);
        //点击事件
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),LieBiaoActivity.class);
                intent.putExtra("key",position+"");
                startActivity(intent);
            }
        });

        srlMain.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        srlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
//                tv.setText("正在刷新");
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
//                        tv.setText("刷新完成");
                        srlMain.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }
}
