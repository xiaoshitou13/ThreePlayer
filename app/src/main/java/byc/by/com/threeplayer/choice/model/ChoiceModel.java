package byc.by.com.threeplayer.choice.model;

import byc.by.com.threeplayer.choice.ChoiceConstract;
import byc.by.com.threeplayer.choice.bean.ChoiceBean;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.ApiServer;
import utils.RetroFactory;

/**
 * Created by Zhang on 2017/12/5.
 */

public class ChoiceModel implements ChoiceConstract.IChoiceModel{

    @Override
    public void OnRequestData(String url, final ChoiceConstract.OnChoiceListener onChoiceListener) {
        ApiServer build = RetroFactory.build(url);
        Observable<ChoiceBean> choiceData = build.getChoiceData();
        choiceData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChoiceBean>() {
                    @Override
                    public void onCompleted() {
                        onChoiceListener.OnEnd();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onChoiceListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(ChoiceBean choiceBean) {
                        onChoiceListener.OnSuccess(choiceBean);
                    }
                });
    }
}
