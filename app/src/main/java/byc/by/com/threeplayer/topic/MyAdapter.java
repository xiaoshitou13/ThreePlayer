package byc.by.com.threeplayer.topic;

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


/**
 * Created by ASUS on 2017/12/5.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> list;
    private Context context;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    private OnItemClickListener mOnItemClickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.glv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tv.setText(list.get(position));
        Glide.with(context).load("https://img03.sogoucdn.com/net/a/04/link?url=http%3A%2F%2Fimg02." +
                "sogoucdn.com%2Fapp%2Fa%2F100520024%2F14370e41bd054e673d3daa8a2a119520&appid=122").priority(Priority.HIGH).into(((ViewHolder)holder).img);
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
