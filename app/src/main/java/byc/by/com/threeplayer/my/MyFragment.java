package byc.by.com.threeplayer.my;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseFragment;
import byc.by.com.threeplayer.my.adatersmy.MyRecyAdater2;
import byc.by.com.threeplayer.my.sqlites.dao;

/**
 * Created by Zhang on 2017/12/5.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private ImageButton mSetimageicon;
    private RecyclerView mWoderecy;
    private RelativeLayout mRe1;
    private RelativeLayout mL2;
    private RelativeLayout mL3;
    private RelativeLayout mL4;
    private View v;
    private RelativeLayout mL1;
    private byc.by.com.threeplayer.my.sqlites.dao dao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.my, container, false);


        initView(v);
        return v;
    }

    private void initView(View v) {
        mSetimageicon = (ImageButton) v.findViewById(R.id.setimageicon);
        mSetimageicon.setOnClickListener(this);
        mWoderecy = (RecyclerView) v.findViewById(R.id.woderecy);
        mRe1 = (RelativeLayout) v.findViewById(R.id.re1);
        mL2 = (RelativeLayout) v.findViewById(R.id.l2);
        mL2.setOnClickListener(this);
        mL3 = (RelativeLayout) v.findViewById(R.id.l3);
        mL4 = (RelativeLayout) v.findViewById(R.id.l4);
        mL4.setOnClickListener(this);
        mL1 = (RelativeLayout) v.findViewById(R.id.l1);
        mL1.setOnClickListener(this);


        dao = new dao(getContext());
        List<Myuser> list = dao.findAllstudent();

   //     Toast.makeText(getContext(), ""+list.size(), Toast.LENGTH_SHORT).show();
        if(list.size()==0){
            mRe1.setVisibility(View.GONE);
        }else{
            mRe1.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mWoderecy.setLayoutManager(linearLayoutManager);

            mWoderecy.setAdapter(new MyRecyAdater2(list,getContext()));
    }
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Myuser> list = dao.findAllstudent();

      //  Toast.makeText(getContext(), ""+list.size(), Toast.LENGTH_SHORT).show();
        if(list.size()==0){
            mRe1.setVisibility(View.GONE);
        }else{
            mRe1.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mWoderecy.setLayoutManager(linearLayoutManager);

            mWoderecy.setAdapter(new MyRecyAdater2(list,getContext()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setimageicon:
                startActivity(new Intent(getContext(), MySettings.class));
                break;

            case R.id.l1:
                startActivity(new Intent(getContext(), MyJilu.class));
                break;
            case R.id.l2:
                Toast.makeText(getContext(), "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.l4:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("主题");

                builder.setNegativeButton("完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Fnagfa();
                    }
                });
                builder.setPositiveButton("关闭", null);
                builder.show();
                break;
        }
    }

    private void Fnagfa() {
        SharedPreferences sp = getActivity().getSharedPreferences("ss", Context.MODE_PRIVATE);
        Boolean aBoolean = sp.getBoolean("night", false);

        if (aBoolean) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            sp.edit().putBoolean("night", false).commit();
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            sp.edit().putBoolean("night", true).commit();
        }

        getActivity().recreate();
    }


}
