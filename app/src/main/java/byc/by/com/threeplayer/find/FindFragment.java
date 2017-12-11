package byc.by.com.threeplayer.find;

import android.content.Intent;
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

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseFragment;
import byc.by.com.threeplayer.find.adapter.MyAdapters;
import byc.by.com.threeplayer.find.bean.IjkitBean;
import byc.by.com.threeplayer.find.cardswipelayout.CardConfig;
import byc.by.com.threeplayer.find.cardswipelayout.CardItemTouchHelperCallback;
import byc.by.com.threeplayer.find.cardswipelayout.CardLayoutManager;
import byc.by.com.threeplayer.find.cardswipelayout.OnSwipeListener;
import byc.by.com.threeplayer.find.presenter.Presenter;
import byc.by.com.threeplayer.find.view.Ijkitplayer;
import byc.by.com.threeplayer.find.view.Iview;
import byc.by.com.threeplayer.my.sqlites.dao;


/**
 * Created by Zhang on 2017/12/5.
 */

public class FindFragment extends BaseFragment implements Iview {
    @BindView(R.id.recyclers)
    RecyclerView recyclers;
    @BindView(R.id.huanyipi)
    Button huanyipi;
    Unbinder unbinder;
    private Presenter presenter;
    private int page = 0;
    private FindBean.RetBean ret;
    private MyAdapters mAdapter;

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
        presenter = new Presenter(this);
        presenter.getdata(page + "");

    }
    @Override
    public void setdata(FindBean findBean) {
        ret = findBean.getRet();
        initData();
    }

    private void initData() {

        mAdapter = new MyAdapters(getActivity(), ret.getList());
        recyclers.setItemAnimator(new DefaultItemAnimator());
        recyclers.setAdapter(mAdapter);
                CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclers.getAdapter(), ret.getList());
                cardCallback.setOnSwipedListener(new OnSwipeListener<FindBean.RetBean.ListBean>() {
                    @Override
                    public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                        MyAdapters.MyHolder myHolder = (MyAdapters.MyHolder) viewHolder;
                        viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);

                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, FindBean.RetBean.ListBean listBean, int direction) {
                        MyAdapters.MyHolder myHolder = (MyAdapters.MyHolder) viewHolder;
                        viewHolder.itemView.setAlpha(1f);
                        //Toast.makeText(getActivity(), direction == CardConfig.SWIPED_LEFT ? "swiped left" : "swiped right", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onSwipedClear() {
                        Toast.makeText(getActivity(), "data clear", Toast.LENGTH_SHORT).show();
                        recyclers.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                                recyclers.getAdapter().notifyDataSetChanged();
                            }
                        }, 3000L);
                    }

                });
                final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
                final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclers, touchHelper);
                recyclers.setLayoutManager(cardLayoutManager);
                touchHelper.attachToRecyclerView(recyclers);
                mAdapter.setItemOnclicklistener(new MyAdapters.ItemOnclicklistener() {
            @Override
            public void item(View view, int postion) {
                dao dao = new dao(getContext());
                dao.add(ret.getList().get(postion).getTitle(),ret.getList().get(postion).getPic(),ret.getList().get(postion).getLoadURL());
                String loadURL = ret.getList().get(postion).getLoadURL();
                EventBus.getDefault().postSticky(new IjkitBean(loadURL));
                startActivity(new Intent(getActivity(), Ijkitplayer.class));


            }
        });
            }



            @OnClick(R.id.huanyipi)
            public void onClick() {
                page++;
                presenter.getdata(page + "");
            }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}