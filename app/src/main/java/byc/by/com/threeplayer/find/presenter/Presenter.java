package byc.by.com.threeplayer.find.presenter;


import byc.by.com.threeplayer.find.FindBean;
import byc.by.com.threeplayer.find.model.Finddata;
import byc.by.com.threeplayer.find.model.Model;
import byc.by.com.threeplayer.find.view.Iview;

/**
 * Created by 郭宝 on 2017/12/5.
 */

public class Presenter {
    private Model model;
    private Iview iview;

    public Presenter(Iview iview) {
        this.model = new Model();
        this.iview = iview;
    }

    public void getdata(String p){
        model.getdata(p, new Finddata() {
            @Override
            public void finddata(FindBean findBean) {
                iview.setdata(findBean);
            }
        });
    }
}
