package byc.by.com.threeplayer.topic.adapter;

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
import byc.by.com.threeplayer.topic.bean.Topic_Bean;


/**
 * Created by ASUS on 2017/12/5.
 */

public class MyAdapter_LieBiao extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Topic_Bean.RetBean.ListBean> list;
    private Context context;

    public MyAdapter_LieBiao(List<Topic_Bean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.glv_item1,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {



        ((ViewHolder)holder).tv.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getPic()).priority(Priority.HIGH).into(((ViewHolder)holder).img);
        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.glv_img);
            tv = itemView.findViewById(R.id.glv_tv);
        }
    }
}
