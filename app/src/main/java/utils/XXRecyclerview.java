package utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Zhang on 2017/12/8.
 */

public class XXRecyclerview extends XRecyclerView{
    public XXRecyclerview(Context context) {
        super(context);
    }

    public XXRecyclerview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float lastX, lastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {

        boolean intercept = super.onInterceptTouchEvent(e);

        switch (e.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = e.getX();
                lastY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 只要横向大于竖向，就拦截掉事件。
                float slopX = Math.abs(e.getX() - lastX);
                float slopY = Math.abs(e.getY() - lastY);
                //  Log.log("slopX=" + slopX + ", slopY="  + slopY);
                if( slopY  >= slopX){
                    requestDisallowInterceptTouchEvent(true);
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        // Log.log("intercept"+e.getAction()+"=" + intercept);
        return intercept;
    }
}
