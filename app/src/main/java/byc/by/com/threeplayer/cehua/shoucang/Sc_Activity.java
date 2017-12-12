package byc.by.com.threeplayer.cehua.shoucang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.find.bean.IjkitBean;
import byc.by.com.threeplayer.find.view.Ijkitplayer;

public class Sc_Activity extends AppCompatActivity {

    @BindView(R.id.recy)
    RecyclerView mRecy;
    ArrayList<Sc_Bean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sc_);
        ButterKnife.bind(this);
        Sc_Dao dao = new Sc_Dao(this);
        list = dao.findAllstudent();
        mRecy.setLayoutManager(new LinearLayoutManager(this));
        Sc_Adapter adapter = new Sc_Adapter(list, this);
        mRecy.setAdapter(adapter);
        adapter.setOnItemClickListener(new Sc_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                EventBus.getDefault().postSticky(new IjkitBean(list.get(position).getSp_url()));
                Intent in = new Intent(Sc_Activity.this, Ijkitplayer.class);
                startActivity(in);
            }
        });
    }
}
