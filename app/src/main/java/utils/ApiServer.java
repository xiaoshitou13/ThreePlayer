package utils;


import byc.by.com.threeplayer.choice.bean.ChoiceBean;
import retrofit2.http.GET;
import rx.Observable;

import byc.by.com.threeplayer.find.FindBean;
import retrofit2.http.GET;
import retrofit2.http.Url;


/**
 * Created by fan on 2017/11/8.
 */

public interface ApiServer {
//


    @GET("front/homePageApi/homePage.do")
    Observable<ChoiceBean> getChoiceData();

   @GET
   Observable<FindBean> getdata(@Url String s);


}
