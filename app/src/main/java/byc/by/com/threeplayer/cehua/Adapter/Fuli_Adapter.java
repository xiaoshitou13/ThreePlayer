package byc.by.com.threeplayer.cehua.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import java.util.List;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.cehua.Bean.FuliBean;

/**
 * Created by Maibenben on 2017/12/6.
 */

public class Fuli_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FuliBean.DataBean> data;
    private Context context;
    private LayoutInflater inflater;

    public Fuli_Adapter(List<FuliBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void add(List<FuliBean.DataBean> list){
        for (int i =0;i<list.size();i++){
            data.add(0,list.get(i));
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.fuli_item, parent, false);
        Viewholder viewholder = new Viewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //  Tupian.jiazi(((Viewholder) holder).sim, context, data.get(position).url);

        Glide.with(context).load(data.get(position).url).priority(Priority.IMMEDIATE).into(((Viewholder) holder).sim);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        ImageView sim;

        public Viewholder(View itemView) {
            super(itemView);
            sim = itemView.findViewById(R.id.iv);
        }
    }
}
