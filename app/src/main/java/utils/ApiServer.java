package utils;


import byc.by.com.threeplayer.choice.bean.ChoiceBean;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by fan on 2017/11/8.
 */

public interface ApiServer {
//

    @GET("front/homePageApi/homePage.do")
    Observable<ChoiceBean> getChoiceData();
}
