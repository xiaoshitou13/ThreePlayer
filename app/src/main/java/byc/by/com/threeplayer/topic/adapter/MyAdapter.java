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
        if (position==0){
            Glide.with(context).load("https://ss0.bdstatic.com/" +
                    "70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1284277663,704882301" +
                    "&fm=27&gp=0.jpg").priority(Priority.HIGH).into(((ViewHolder)holder).img);
        }else if (position==1){
            Glide.with(context).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1960433012,512870012&fm=27&gp=0.jpg").priority(Priority.HIGH).into(((ViewHolder)holder).img);
        }else if (position==2){
            Glide.with(context).load("https://ss0.bdstatic." +
                    "com/70cFuHSh_Q1YnxGkpoWK1HF" +
                    "6hhy/it/u=977627844,3660044690&fm=27&gp=0." +
                    "jpg").priority(Priority.HIGH).into(((ViewHolder)holder).img);
        }else if (position==3){
            Glide.with(context).load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1500936734,2824320535&fm=27&gp=0.jpg").priority(Priority.HIGH).into(((ViewHolder)holder).img);
        }else if (position==4){
            Glide.with(context).load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3086034579,24204398&fm=27&gp=0.jpg" ).priority(Priority.HIGH).into(((ViewHolder)holder).img);
        }else if (position==5){
            Glide.with(context).load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3775571764,310723784&fm=27&gp=0.jpg" ).priority(Priority.HIGH).into(((ViewHolder)holder).img);
        }else if (position==6){
            Glide.with(context).load("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3652314724,2239330761&fm=27&gp=0.jpg").priority(Priority.HIGH).into(((ViewHolder)holder).img);
        }
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
