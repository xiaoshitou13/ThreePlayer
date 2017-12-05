package byc.by.com.threeplayer.my;

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

public class MyFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my,container,false);

        return v;
    }
}
