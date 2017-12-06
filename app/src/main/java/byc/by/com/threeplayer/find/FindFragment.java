package byc.by.com.threeplayer.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseFragment;
import byc.by.com.threeplayer.find.adapter.MyAdapters;
import byc.by.com.threeplayer.find.bean.FindBean;
import byc.by.com.threeplayer.find.cardswipelayout.CardConfig;
import byc.by.com.threeplayer.find.cardswipelayout.CardItemTouchHelperCallback;
import byc.by.com.threeplayer.find.cardswipelayout.CardLayoutManager;
import byc.by.com.threeplayer.find.cardswipelayout.OnSwipeListener;

/**
 * Created by Zhang on 2017/12/5.
 */

public class FindFragment extends BaseFragment {
    @BindView(R.id.recyclers)
    RecyclerView recyclers;
    @BindView(R.id.huanyipi)
    Button huanyipi;
    Unbinder unbinder;
    MyAdapters mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.find, container, false);

        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
