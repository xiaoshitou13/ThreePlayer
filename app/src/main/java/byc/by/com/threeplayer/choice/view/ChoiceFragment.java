package byc.by.com.threeplayer.choice.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseFragment;
import byc.by.com.threeplayer.choice.ChoiceConstract;
import byc.by.com.threeplayer.choice.bean.ChoiceBean;

/**
 * Created by Zhang on 2017/12/5.
 */

public class ChoiceFragment extends BaseFragment implements ChoiceConstract.IChoiceView{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.choice,container,false);


        return v;
    }

    @Override
    public void ShowData(ChoiceBean choiceBean) {

    }

    @Override
    public void ShowError(String e) {

    }
}
