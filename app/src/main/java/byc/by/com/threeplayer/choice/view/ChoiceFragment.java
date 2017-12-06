package byc.by.com.threeplayer.choice.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseFragment;
import byc.by.com.threeplayer.choice.ChoiceConstract;
import byc.by.com.threeplayer.choice.adapter.ChoiceAdapter;
import byc.by.com.threeplayer.choice.bean.ChoiceBean;
import byc.by.com.threeplayer.choice.presenter.ChoicePresenter;
import utils.Api;
import utils.ObservableScrollView;

/**
 * Created by Zhang on 2017/12/5.
 */

public class ChoiceFragment extends BaseFragment implements ChoiceConstract.IChoiceView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.rcv)
    RecyclerView rcv;

    Unbinder unbinder;
    @BindView(R.id.xban)
    XBanner xban;
    @BindView(R.id.osl)
    ObservableScrollView osl;
    @BindView(R.id.tv_choice)
    TextView tvChoice;
    @BindView(R.id.toptoolbar)
    Toolbar toptoolbar;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    private ChoicePresenter choicePresenter;
    private int banheight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.choice, container, false);
        unbinder = ButterKnife.bind(this, v);

        choicePresenter = new ChoicePresenter(this);
        choicePresenter.LoadData(Api.CHOICE_PATH);
        initdatas();

        return v;
    }

    private void initdatas() {
        tvChoice.bringToFront();
        ViewTreeObserver vto = xban.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                xban.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                banheight = xban.getHeight();
            }
        });
        srl.setOnRefreshListener(this);
    }

    @Override
    public void ShowData(ChoiceBean choiceBean) {
        List<ChoiceBean.RetBean.ListBean> list = choiceBean.getRet().getList();
        ChoiceAdapter choiceAdapter = new ChoiceAdapter(list, getActivity());
        rcv.setAdapter(choiceAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<ChoiceBean.RetBean.ListBean.ChildListBean> childList0 = list.get(0).getChildList();
        final List<String> xbanimg = new ArrayList<>();
        for (int i = 0; i < childList0.size(); i++) {
            xbanimg.add(childList0.get(i).getPic());
        }

        xban.setData(xbanimg, null);
        xban.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(xbanimg.get(position)).into((ImageView) view);
            }
        });
        osl.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    tvChoice.setVisibility(View.GONE);
                    toptoolbar.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= banheight) {
                    tvChoice.setVisibility(View.VISIBLE);
                    float scale = (float) y / banheight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    toptoolbar.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                } else {
                    tvChoice.setVisibility(View.VISIBLE);
                    toptoolbar.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                }
            }
        });

    }

    @Override
    public void ShowError(String e) {
        Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
        Log.e("错误信息", e);
    }

    @Override
    public void ShowNetEnd() {
        srl.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        choicePresenter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onRefresh() {
        choicePresenter.LoadData(Api.CHOICE_PATH);
    }
}
