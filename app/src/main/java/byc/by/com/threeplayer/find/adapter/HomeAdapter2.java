package byc.by.com.threeplayer.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import java.util.List;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.find.bean.Video;

/**
 * Created by 郭宝 on 2017/12/7.
 */

public class HomeAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Video.RetBean.ListBean.ChildListBean> list;
    private Context context;
    private MyAdapters.ItemOnclicklistener itemOnclicklistener;

    public void setItemOnclicklistener(MyAdapters.ItemOnclicklistener itemOnclicklistener) {
        this.itemOnclicklistener = itemOnclicklistener;
    }

    public MyAdapters.ItemOnclicklistener getItemOnclicklistener() {
        return itemOnclicklistener;
    }

    public interface ItemOnclicklistener{
        void item(View view,int postion);
    }
    public HomeAdapter2(List<Video.RetBean.ListBean.ChildListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homeitem2, parent, false);
        Myhodler myhodler=new Myhodler(view);
        return myhodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((Myhodler)holder).textView.setText(list.get(position).getTitle());
        if(list.get(position).getPic()!=null){
            Glide.with(context).load(list.get(position).getPic()).priority(Priority.IMMEDIATE).into(((Myhodler)holder).imageView);

        }
        ((Myhodler)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnclicklistener.item(view,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myhodler extends RecyclerView.ViewHolder {
        TextView textView;
      ImageView imageView;
        public Myhodler(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
            imageView=itemView.findViewById(R.id.imageview);
        }
    }
}
