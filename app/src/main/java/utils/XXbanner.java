package utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.stx.xhb.xbanner.XBanner;

/**
 * Created by Zhang on 2017/12/7.
 */

public class XXbanner extends XBanner {
    public XXbanner(Context context) {
        super(context);
    }

    public XXbanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
