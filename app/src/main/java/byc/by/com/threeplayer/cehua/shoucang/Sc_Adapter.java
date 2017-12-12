package byc.by.com.threeplayer.cehua.shoucang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import java.util.ArrayList;

import byc.by.com.threeplayer.R;

/**
 * Created by Maibenben on 2017/12/12.
 */

public class Sc_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Sc_Bean> list;
    private Context context;
    private LayoutInflater layoutManager;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }


    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public Sc_Adapter(ArrayList<Sc_Bean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutManager = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutManager.inflate(R.layout.sc_item, parent, false);
        Viewholder viewholder = new Viewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        Glide.with(context).load(list.get(position).getImage_url()).priority(Priority.IMMEDIATE).into(((Viewholder) holder).iv);
        ((Viewholder) holder).tv.setText(list.get(position).getName());
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = ((Viewholder) holder).getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(((Viewholder) holder).itemView, position); // 2
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = ((Viewholder) holder).getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(((Viewholder) holder).itemView, position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv;

        public Viewholder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
