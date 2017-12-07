package byc.by.com.threeplayer.cehua.presenter;

import android.content.Context;

import byc.by.com.threeplayer.cehua.Bean.FuliBean;
import byc.by.com.threeplayer.cehua.model.ModelInfo;
import byc.by.com.threeplayer.cehua.model.Mymodel;
import byc.by.com.threeplayer.cehua.view.Iview;

/**
 * Created by Maibenben on 2017/12/6.
 */

public class Presenter {
    public Context context;
    public Iview iview;
    private Mymodel mymodel;

    public Presenter(Context context, Iview iview) {
        this.context = context;
        this.iview = iview;
        mymodel=new Mymodel();
    }

    public void getBean(String path){
        mymodel.getData(new ModelInfo.GetBean() {
            @Override
            public void setBean(FuliBean fuliBean) {
                iview.getBean(fuliBean);
            }

            @Override
            public void setBean1(FuliBean shangPin_bean) {
                iview.getBean1(shangPin_bean);
            }
        },path);
    }
    public void getBean1(String path){
        mymodel.getData1(new ModelInfo.GetBean() {
            @Override
            public void setBean(FuliBean fuliBean) {

            }

            @Override
            public void setBean1(FuliBean shangPin_bean) {
                iview.getBean1(shangPin_bean);
            }
        },path);
    }
}
