package byc.by.com.threeplayer.find.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import byc.by.com.threeplayer.R;

/**
 * Created by 郭宝 on 2017/12/6.
 */

public class CommentFragment extends Fragment {

    private View view;

    //
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.comment, container, false);
        return view;
    }
}
