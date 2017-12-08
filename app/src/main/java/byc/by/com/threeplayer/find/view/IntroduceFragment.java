package byc.by.com.threeplayer.find.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import byc.by.com.threeplayer.find.adapter.HomeAdapter;
import byc.by.com.threeplayer.find.bean.IjkitBean;
import byc.by.com.threeplayer.find.bean.Video;
import okhttp3.Request;

/**
 * Created by 郭宝 on 2017/12/6.
 */

public class IntroduceFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
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
//        Log.d("TAGS",path+"--------");
//        if (path==null){
//            Toast.makeText(getContext(), "视屏已下载", Toast.LENGTH_SHORT).show();
//        }else {
            EventBus.getDefault().register(this);
            MyOkhttp.getAsync(path, new MyOkhttp.DataCallBack() {
                @Override
                public void requestFailure(Request request, IOException e) {

                }

                @Override
                public void requestSuccess(String result) throws Exception {
                    Gson gson = new Gson();
                    video = gson.fromJson(result, Video.class);
                    if (video.getCode().equals("600")){
                        Toast.makeText(getContext(), "视屏已下载", Toast.LENGTH_SHORT).show();
                    }else {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recycleview.setLayoutManager(linearLayoutManager);
                        HomeAdapter gridAdapter = new HomeAdapter(video.getRet(), getActivity());
                        recycleview.setAdapter(gridAdapter);
                    }

                }
            });
        }


//    }

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
