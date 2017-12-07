package byc.by.com.threeplayer.cehua.model;

import android.util.Log;

import byc.by.com.threeplayer.cehua.Bean.FuliBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.Api;
import utils.ApiServer;

/**
 * Created by Maibenben on 2017/12/6.
 */

public class Mymodel implements ModelInfo.Model_bean {


    @Override
    public void getData(final ModelInfo.GetBean getBean,String path) {
       Retrofit retrofit = new Retrofit.Builder()
                       .baseUrl(Api.FULI)
                       .addConverterFactory(GsonConverterFactory.create())
                       .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                       .build();
               ApiServer apiServer = retrofit.create(ApiServer.class);
               Observable<FuliBean> observable = apiServer.Fuli(path);
               Subscription sss = observable.subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Observer<FuliBean>() {
                           @Override
                           public void onCompleted() {

                           }

                           @Override
                           public void onError(Throwable e) {

                           }

                           @Override
                           public void onNext(FuliBean shangPin_bean) {
                               getBean.setBean(shangPin_bean);
                           }
                       });
    }

    @Override
    public void getData1(final ModelInfo.GetBean getBean, String path) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.FULI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<FuliBean> observable = apiServer.Fuli(path);
        Subscription sss = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuliBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FuliBean shangPin_bean) {
                        Log.d("tags",shangPin_bean.toString());
                        getBean.setBean1(shangPin_bean);
                    }
                });
    }
}
