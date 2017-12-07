package byc.by.com.threeplayer.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseFragment;

/**
 * Created by Zhang on 2017/12/5.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    private ImageButton mSetimageicon;
    private RecyclerView mWoderecy;
    private RelativeLayout mRe1;
    private RelativeLayout mL2;
    private RelativeLayout mL3;
    private RelativeLayout mL4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my, container, false);


        initView(v);
        return v;
    }

    private void initView(View v) {
        mSetimageicon = (ImageButton) v.findViewById(R.id.setimageicon);
        mSetimageicon.setOnClickListener(this);
        mWoderecy = (RecyclerView) v.findViewById(R.id.woderecy);
        mRe1 = (RelativeLayout) v.findViewById(R.id.re1);
        mL2 = (RelativeLayout) v.findViewById(R.id.l2);
        mL3 = (RelativeLayout) v.findViewById(R.id.l3);
        mL4 = (RelativeLayout) v.findViewById(R.id.l4);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setimageicon:
                startActivity(new Intent(getContext(),MySettings.class));
                break;
        }
    }
}
