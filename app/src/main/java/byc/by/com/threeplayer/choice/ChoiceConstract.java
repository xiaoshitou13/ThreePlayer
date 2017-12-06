package byc.by.com.threeplayer.choice;

import byc.by.com.threeplayer.choice.bean.ChoiceBean;

/**
 * Created by Zhang on 2017/12/5.
 */

public interface ChoiceConstract {
    interface IChoiceView{
        void ShowData(ChoiceBean choiceBean);
        void ShowError(String e);
        void ShowNetEnd();
    }
    interface IChoiceModel{
        void OnRequestData(String url,OnChoiceListener onChoiceListener);
    }
    interface OnChoiceListener{
        void OnSuccess(ChoiceBean choiceBean);
        void OnError(String e);
        void OnEnd();
    }
    interface IChoicePresenter{
        void LoadData(String url);
    }
}
