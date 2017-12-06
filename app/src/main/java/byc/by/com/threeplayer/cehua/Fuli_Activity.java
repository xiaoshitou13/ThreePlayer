package byc.by.com.threeplayer.cehua;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseActivity;

public class Fuli_Activity extends BaseActivity {

    @BindView(R.id.recy)
    RecyclerView recy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuli_);
        ButterKnife.bind(this);
    }
}
