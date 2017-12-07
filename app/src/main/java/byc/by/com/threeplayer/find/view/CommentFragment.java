package byc.by.com.threeplayer.find.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.find.bean.PingLun;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.ApiServer;
import utils.RetroFactory;

/**
 * Created by 郭宝 on 2017/12/6.
 */

public class CommentFragment extends Fragment {

    @BindView(R.id.lv)
    ListView lv;
    Unbinder unbinder;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    private View view;

    //
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.comment, container, false);
        init();
        unbinder = ButterKnife.bind(this, view);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(false);
            }
        });
        return view;
    }

    public void init() {
        ApiServer apiServer = RetroFactory.PingLun();
        Observable<PingLun> pingLunObservable = apiServer.PingLun("/front/Commentary/getCommentList.do?mediaId=CMCC_00000000000000001_621653189");
        pingLunObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PingLun>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PingLun pingLun) {
                        lv.setAdapter(new MyAdapter(getContext(), pingLun.getRet().getList()));
                    }
                });

//        ApiServer apiServer = RetroFactory.PingLun();
//        Observable<PingLun> findBeanObservable = apiServer.PingLun("/front/Commentary/getCommentList.do?mediaId=CMCC_00000000000000001_621653189");
//        findBeanObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PingLun>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(PingLun pingLun) {
//                        lv.setAdapter(new MyAdapter(getContext(), pingLun.getRet().getList()));
//                    }
//                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
