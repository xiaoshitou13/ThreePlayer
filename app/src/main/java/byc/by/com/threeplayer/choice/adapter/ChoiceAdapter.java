package byc.by.com.threeplayer.choice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.choice.bean.ChoiceBean;

/**
 * Created by Zhang on 2017/12/6.
 */

public class ChoiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChoiceBean.RetBean.ListBean> list;
    private Context context;
    private List<ChoiceBean.RetBean.ListBean.ChildListBean> childLists;


    public ChoiceAdapter(List<ChoiceBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choiceitem, parent, false));
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final List<ChoiceBean.RetBean.ListBean.ChildListBean> childList = list.get(4).getChildList();
        ((MyViewHolder) holder).choiceItemTv.setText(childList.get(position).getTitle());
        if (childList.get(position).getPic() != null) {
            Glide.with(context).load(childList.get(position).getPic()).priority(Priority.IMMEDIATE).into(((MyViewHolder) holder).choiceItemImg);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        for (int i = 0; i < list.size(); i++) {
            childLists = list.get(4).getChildList();

        }
        return childLists.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.choice_item_img)
        ImageView choiceItemImg;
        @BindView(R.id.choice_item_tv)
        TextView choiceItemTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
