package byc.by.com.threeplayer.cehua.shoucang;

/**
 * Created by Maibenben on 2017/12/12.
 */

public class Sc_Bean {
    private String name;
    private String image_url;
    private String sp_url;

    public Sc_Bean() {
    }

    public Sc_Bean(String name, String image_url, String sp_url) {
        this.name = name;
        this.image_url = image_url;
        this.sp_url = sp_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setSp_url(String sp_url) {
        this.sp_url = sp_url;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getSp_url() {
        return sp_url;
    }
}
