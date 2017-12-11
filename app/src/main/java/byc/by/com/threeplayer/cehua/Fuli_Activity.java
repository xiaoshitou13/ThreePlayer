package byc.by.com.threeplayer.cehua;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseActivity;
import byc.by.com.threeplayer.cehua.Adapter.Fuli_Adapter;
import byc.by.com.threeplayer.cehua.Bean.FuliBean;
import byc.by.com.threeplayer.cehua.presenter.Presenter;
import byc.by.com.threeplayer.cehua.view.Iview;

public class Fuli_Activity extends BaseActivity implements Iview {

    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.swi)
    SwipeRefreshLayout swi;
    private List<FuliBean.DataBean> data;
    int i = 1;
    //String path = "meituApi?page=" + i;
    private Presenter presenter;
    private Fuli_Adapter adapter;

    String  path ="channel/listjson?pn="+i+"2&rn=30&tag1=%E7%BE%8E%E5%A5%B3&tag2=%E8%AF%B1%E6%83%91&ie=utf8&qq-pf-to=pcqq.temporaryc2c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuli_);
        ButterKnife.bind(this);
        presenter = new Presenter(this, this);


        presenter.getBean(path);

        swi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i++;
                presenter.getBean1(path);
                Toast.makeText(Fuli_Activity.this, "刷新成功", Toast.LENGTH_LONG).show();
                swi.setRefreshing(false);
            }
        });
    }

    @Override
    public void getBean1(FuliBean bean) {
        Log.d("tags", bean.toString());
        adapter.add(bean.data);
    }

    @Override
    public void getBean(FuliBean bean) {
        Log.e("sss", bean.tag1);
        data = bean.data;
        recy.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new Fuli_Adapter(data, Fuli_Activity.this);

        recy.setAdapter(adapter);


    }
}
