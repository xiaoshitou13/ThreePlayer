package byc.by.com.threeplayer.welcome;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import byc.by.com.threeplayer.MainActivity;
import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome_img)
    ImageView welcomeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        ObjectAnimator xx = ObjectAnimator.ofFloat(welcomeImg, "scaleX", 1f, 1.25f);//
        ObjectAnimator yy = ObjectAnimator.ofFloat(welcomeImg, "scaleY", 1f, 1.25f);//
        AnimatorSet animSeta = new AnimatorSet();
        animSeta.play(xx).with(yy);
        animSeta.setDuration(3000);
        animSeta.start();
        animSeta.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent in = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(in);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }

    @Override
    protected boolean enableSliding() {
        return false;
    }
}
