package byc.by.com.threeplayer.my;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseActivity;
import byc.by.com.threeplayer.my.Util.ThemeUtils;
import byc.by.com.threeplayer.my.Util.Utils;
import byc.by.com.threeplayer.my.adatersmy.MyRecyAdater;
import byc.by.com.threeplayer.my.sqlites.dao;

public class MyJilu extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mJilutou;
    private RecyclerView mJilurecy;
    private ImageButton mJiluimage;
    /**
     * 清空
     */
    private TextView mQk;
    private dao d;
    private MyRecyAdater myRecyAdater;
    private List<Myuser> list;
    /**
     * 暂无数据
     */
    private TextView mFff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jilu);
        initView();
    }

    private void initView() {
     //   mJilutou = (RelativeLayout) findViewById(R.id.jilutou);
        mJilurecy = (RecyclerView) findViewById(R.id.jilurecy);
   //     mJiluimage = (ImageButton) findViewById(R.id.jiluimage);
//        mJiluimage.setOnClickListener(this);
  //      mQk = (TextView) findViewById(R.id.qk);
        mFff = (TextView) findViewById(R.id.fff);
   //     mQk.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        Utils.context=MyJilu.this;

        ThemeUtils.initStatusBarColor(MyJilu.this,ThemeUtils.getPrimaryDarkColor(MyJilu.this));
        mJilurecy.setLayoutManager(new GridLayoutManager(this, 4));

        d = new dao(this);


        list = d.findAllstudent();
        if (mQk != null) {
            mFff.setVisibility(View.GONE);
        }

        myRecyAdater = new MyRecyAdater(list, MyJilu.this);
        mJilurecy.setAdapter(myRecyAdater);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
//            case R.id.jiluimage:
//                finish();
//                break;
//            case R.id.qk:
//                d.deleteStudent1();
//                list.clear();
//                myRecyAdater.notifyDataSetChanged();
//                break;
        }
    }
}
