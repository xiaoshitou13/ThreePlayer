package byc.by.com.threeplayer.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseFragment;

/**
 * Created by Zhang on 2017/12/5.
 */

public class FindFragment extends BaseFragment {
    @BindView(R.id.recyclers)
    RecyclerView recyclers;
    @BindView(R.id.huanyipi)
    Button huanyipi;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.find, container, false);

        unbinder = ButterKnife.bind(this, v);
        return v;
    }

 
}
