package byc.by.com.threeplayer.find.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.find.bean.IjkitBean;
import byc.by.com.threeplayer.find.bean.Video;
import byc.by.com.threeplayer.find.view.Ijkitplayer;

/**
 * Created by 郭宝 on 2017/12/7.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Video.RetBean list;
    private Context context;
    private boolean b=false;

    public HomeAdapter(Video.RetBean list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homeitem, parent, false);
        Myhodler myhodler=new Myhodler(view);
        return myhodler;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int positions) {
        ((Myhodler)holder).textView.setText("主演："+list.getActors());
        ((Myhodler)holder).textView1.setText("剧情简介："+list.getDescription());
        ((Myhodler)holder).xianshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b){
                    ((Myhodler)holder).textView1.setSingleLine(true);
                    ((Myhodler)holder).textView1.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
                    b=false;
                }else {
                    ((Myhodler)holder).textView1.setSingleLine(false);
                    b=true;
                }
            }
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        ((Myhodler)holder).recyclerView.setLayoutManager(staggeredGridLayoutManager);
        if (list.getList()==null){}else {
            HomeAdapter2 homeAdapter2 = new HomeAdapter2(list.getList().get(positions).getChildList(), context);
            ((Myhodler) holder).recyclerView.setAdapter(homeAdapter2);
            homeAdapter2.setItemOnclicklistener(new MyAdapters.ItemOnclicklistener() {
                @Override
                public void item(View view, int postion) {
                    String loadURL = list.getList().get(0).getChildList().get(postion).getLoadURL();
                    EventBus.getDefault().postSticky(new IjkitBean(loadURL));
                    context.startActivity(new Intent(context, Ijkitplayer.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class Myhodler extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;
        TextView xianshi;
        RecyclerView recyclerView;
        public Myhodler(View itemView) {
            super(itemView);
           textView=itemView.findViewById(R.id.yanyuan);
           textView1=itemView.findViewById(R.id.jieshao);
           recyclerView=itemView.findViewById(R.id.recycle);
           xianshi=itemView.findViewById(R.id.xianshi);
        }
    }

}
