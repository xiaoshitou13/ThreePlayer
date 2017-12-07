package byc.by.com.threeplayer.choice.presenter;

import byc.by.com.threeplayer.choice.ChoiceConstract;
import byc.by.com.threeplayer.choice.bean.ChoiceBean;
import byc.by.com.threeplayer.choice.model.ChoiceModel;

/**
 * Created by Zhang on 2017/12/5.
 */

public class ChoicePresenter implements ChoiceConstract.IChoicePresenter {

    ChoiceConstract.IChoiceView iChoiceView;
    ChoiceConstract.IChoiceModel iChoiceModel;

    public ChoicePresenter(ChoiceConstract.IChoiceView iChoiceView) {
        this.iChoiceView = iChoiceView;
        iChoiceModel = new ChoiceModel();
    }

    @Override
    public void LoadData(String url) {
        iChoiceModel.OnRequestData(url, new ChoiceConstract.OnChoiceListener() {
            @Override
            public void OnSuccess(ChoiceBean choiceBean) {
                iChoiceView.ShowData(choiceBean);
            }

            @Override
            public void OnError(String e) {
                iChoiceView.ShowError(e);
            }

            @Override
            public void OnEnd() {
                iChoiceView.ShowNetEnd();
            }
        });
    }
}
