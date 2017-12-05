package byc.by.com.threeplayer.choice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseFragment;

/**
 * Created by Zhang on 2017/12/5.
 */

public class ChoiceFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.choice,container,false);

        return v;
    }
}
