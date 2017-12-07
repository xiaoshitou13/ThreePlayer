package byc.by.com.threeplayer.cehua.model;

import byc.by.com.threeplayer.cehua.Bean.FuliBean;

/**
 * Created by Maibenben on 2017/12/6.
 */

public class ModelInfo {
    public interface Model_bean{
        void getData(GetBean getBean,String path);
        void getData1(GetBean getBean,String path);
    }
    public interface GetBean{

        void setBean(FuliBean shangPin_bean);
        void setBean1(FuliBean shangPin_bean);
    }

}
