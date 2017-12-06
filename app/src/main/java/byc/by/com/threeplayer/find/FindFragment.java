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
import butterknife.OnClick;
import butterknife.Unbinder;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseFragment;
import byc.by.com.threeplayer.find.adapter.MyAdapters;
import byc.by.com.threeplayer.find.cardswipelayout.CardConfig;
import byc.by.com.threeplayer.find.cardswipelayout.CardItemTouchHelperCallback;
import byc.by.com.threeplayer.find.cardswipelayout.CardLayoutManager;
import byc.by.com.threeplayer.find.cardswipelayout.OnSwipeListener;
import byc.by.com.threeplayer.find.presenter.Presenter;
import byc.by.com.threeplayer.find.view.Iview;


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
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, FindBean.RetBean.ListBean listBean, int direction) {
                MyAdapters.MyHolder myHolder = (MyAdapters.MyHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.huanyipi)
    public void onClick() {
        page++;
        presenter.getdata(page+"");
    }
}
