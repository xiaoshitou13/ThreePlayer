package byc.by.com.threeplayer.find.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.find.MyOkhttp;
import byc.by.com.threeplayer.find.adapter.GridAdapter;
import byc.by.com.threeplayer.find.bean.IjkitBean;
import byc.by.com.threeplayer.find.bean.Video;
import okhttp3.Request;

/**
 * Created by 郭宝 on 2017/12/6.
 */

public class IntroduceFragment extends Fragment {
    @BindView(R.id.yanyuan)
    TextView yanyuan;
    @BindView(R.id.jeishao)
    TextView jeishao;
    @BindView(R.id.gridview)
    GridView gridview;
    Unbinder unbinder;
    private View view;
    private String path;
    private Video video;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.introduce, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        MyOkhttp.getAsync(path, new MyOkhttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                video = gson.fromJson(result, Video.class);
                yanyuan.setText("主演："+ video.getRet().getActors().toString());
                jeishao.setText("剧情介绍："+ video.getRet().getDescription().toString());
                GridAdapter gridAdapter=new GridAdapter(video.getRet().getList().get(0).getChildList(),getActivity());
                gridview.setAdapter(gridAdapter);
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String loadURL = video.getRet().getList().get(0).getChildList().get(i).getLoadURL();
                        EventBus.getDefault().postSticky(new IjkitBean(loadURL));
                        startActivity(new Intent(getActivity(), Ijkitplayer.class));

                    }
                });
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getdata(IjkitBean ijkitBean) {
        path = ijkitBean.getPath();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
