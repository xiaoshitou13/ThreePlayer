package byc.by.com.threeplayer.topic.presenter;

import byc.by.com.threeplayer.topic.bean.Topic_Bean;
import byc.by.com.threeplayer.topic.model.Model;
import byc.by.com.threeplayer.topic.view.Iview;

/**
 * Created by ASUS on 2017/12/8.
 */

public class Presenter {
    private Model model;
    private Iview iview;

    public Presenter( Iview iview) {
        this.model = new Model();
        this.iview = iview;
    }
    public void getdata(String s){
        model.get(new ipresenter() {
            @Override
            public void getdata(Topic_Bean topic_bean) {
                iview.suss(topic_bean);
            }
        },s);
    }
}
