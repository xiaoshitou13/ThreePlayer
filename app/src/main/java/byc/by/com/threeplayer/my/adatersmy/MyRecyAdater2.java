package byc.by.com.threeplayer.my.adatersmy;

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
import byc.by.com.threeplayer.my.Myuser;

/**
 * Created by 白玉春 on 2017/12/8.
 */

public class MyRecyAdater2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Myuser> myUsers;
    Context context;

    public MyRecyAdater2(List<Myuser> myUsers, Context context) {
        this.myUsers = myUsers;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolders1(LayoutInflater.from(context).inflate(R.layout.myitems,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         if(holder instanceof ViewHolders1){
             ((ViewHolders1) holder).t1.setText(""+myUsers.get(position).getName());
             Glide.with(context).load(myUsers.get(position).getUrl()).into(((ViewHolders1) holder).i1);
         }
    }

    @Override
    public int getItemCount() {
        return myUsers!=null?myUsers.size():0;
    }

    class  ViewHolders1 extends RecyclerView.ViewHolder{
        ImageView i1;
        TextView t1;
        public ViewHolders1(View itemView) {
            super(itemView);
            i1 = itemView.findViewById(R.id.jlitem1);
            t1 = itemView.findViewById(R.id.jlitem2);
        }
    }
}
