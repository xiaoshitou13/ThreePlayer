package byc.by.com.threeplayer.find.model;


import byc.by.com.threeplayer.find.FindBean;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.Api;
import utils.ApiServer;
import utils.RetroFactory;

/**
 * Created by 郭宝 on 2017/12/5.
 */

public class Model implements Imodel  {

    @Override
    public void getdata(String s, final Finddata finddata) {
        ApiServer build = RetroFactory.build(Api.path);
        build.getdata("columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum="+s+"")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<FindBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(FindBean findBean) {
                finddata.finddata(findBean);
            }
        });
    }
}
