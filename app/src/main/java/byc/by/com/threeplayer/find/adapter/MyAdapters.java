package byc.by.com.threeplayer.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.find.FindBean;


/**
 * date：2017/11/20
 * 用途：
 */
public class MyAdapters extends RecyclerView.Adapter {
    private Context mContext;
    private List<FindBean.RetBean.ListBean> list;

    public MyAdapters(Context mContext, List<FindBean.RetBean.ListBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.iiiii, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.description.setText(list.get(position).getDescription());
        myHolder.mTextView.setText(list.get(position).getTitle());
        Glide.with(mContext)
                .load(list.get(position).getPic())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(myHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        ImageView img;
        TextView description;
        public ImageView dislikeImageView;
        public ImageView likeImageView;

        public MyHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.titless);
            img = itemView.findViewById(R.id.imggg);
            description = itemView.findViewById(R.id.description);
            dislikeImageView=itemView.findViewById(R.id.iv_dislike);
            likeImageView=itemView.findViewById(R.id.iv_like);
        }


    }
}
