package byc.by.com.threeplayer.my;

import android.annotation.TargetApi;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.base.BaseActivity;
import byc.by.com.threeplayer.my.Util.ThemeUtils;
import byc.by.com.threeplayer.my.Util.Utils;
import byc.by.com.threeplayer.my.yuying.AudioRecoderDialog;
import byc.by.com.threeplayer.my.yuying.AudioRecoderUtils;
import utils.DataCleanManager;

import static utils.DataCleanManager.getFolderSize;
import static utils.DataCleanManager.getFormatSize;

public class MySettings extends BaseActivity implements View.OnClickListener , View.OnTouchListener, AudioRecoderUtils.OnAudioStatusUpdateListener{
    private AudioRecoderDialog recoderDialog;
    private AudioRecoderUtils recoderUtils;
    private long downT;
    private RelativeLayout mJilutou;
    private RelativeLayout mJiluone;
    private RelativeLayout mJlhaoyou;
    private RelativeLayout mJlhaoyou1;
    private RelativeLayout mJlhaoyou2;
    private RelativeLayout mJlhaoyou3;
    private RelativeLayout mJiluti;
    /**
     * 20.00kb
     */
    private TextView mSettvhc;
    private ImageButton mSeticon;
    private Button yuy;
    private EditText fked2;
    private EditText fked1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);
        initView();
    }

    private void initView() {
//        mJilutou = (RelativeLayout) findViewById(R.id.jilutou);
//        mJilutou.setOnClickListener(this);
        mJiluone = (RelativeLayout) findViewById(R.id.jiluone);
        mJiluone.setOnClickListener(this);
        mJlhaoyou = (RelativeLayout) findViewById(R.id.jlhaoyou);
        mJlhaoyou.setOnClickListener(this);
        mJlhaoyou1 = (RelativeLayout) findViewById(R.id.jlhaoyou1);
        mJlhaoyou1.setOnClickListener(this);
        mJlhaoyou2 = (RelativeLayout) findViewById(R.id.jlhaoyou2);
        mJlhaoyou2.setOnClickListener(this);
        mJlhaoyou3 = (RelativeLayout) findViewById(R.id.jlhaoyou3);
        mJlhaoyou3.setOnClickListener(this);
        mJiluti = (RelativeLayout) findViewById(R.id.jiluti);
        mJiluti.setOnClickListener(this);
        mSettvhc = (TextView) findViewById(R.id.settvhc);
//        mSeticon = (ImageButton) findViewById(R.id.seticon);
//        mSeticon.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        Utils.context=MySettings.this;

        ThemeUtils.initStatusBarColor(MySettings.this,ThemeUtils.getPrimaryDarkColor(MySettings.this));

        try {
            String size = getTotalCacheSize(getApplicationContext());
            mSettvhc.setText(""+size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
//            case R.id.jilutou:
//                break;
            case R.id.jiluone:
                break;
            case R.id.jlhaoyou:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("发现一个看片神器");

                builder.setMessage("https://github.com/xiaoshitou13/ThreePlayer.git");

                builder.setNegativeButton("复制", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ClipboardManager clip = (ClipboardManager) MySettings.this.getSystemService(Context.CLIPBOARD_SERVICE);
                        clip.setText("https://github.com/xiaoshitou13/ThreePlayer.git");
                        Toast.makeText(MySettings.this, "复制成功", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("关闭", null);
                builder.show();
                break;
            case R.id.jlhaoyou1:
                DataCleanManager.cleanInternalCache(this);
                DataCleanManager.cleanExternalCache(this);

                 clearAllCache(getApplicationContext());  //清除
                mSettvhc.setText("0");
                Toast.makeText(this, "清理完毕", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jlhaoyou2:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

                builder1.setTitle("关于我们");



                View vs = LayoutInflater.from(this).inflate(R.layout.gywmitm, null);
                TextView boke = vs.findViewById(R.id.gytv);
                TextView github = vs.findViewById(R.id.gytv2);
                builder1.setView(vs);
                builder1.setPositiveButton("关闭", null);
                builder1.show();

//                String bo  =   boke.getText().toString();
//                String git = github.getText().toString();
                SpannableStringBuilder spannable=new SpannableStringBuilder(boke.getText().toString());
                CharacterStyle span=new UnderlineSpan();
                spannable.setSpan(span, 3, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                boke . setText(spannable);

                SpannableStringBuilder spannables=new SpannableStringBuilder(github.getText().toString());
                CharacterStyle spans=new UnderlineSpan();
                spannables.setSpan(spans, 6, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                github . setText(spannables);

                boke.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent gy = new Intent(MySettings.this,MywebViewClass.class);
                        gy.putExtra("bo","http://my.csdn.net/?ref=toolbar");
                        startActivity(gy);
                    }
                });

                github.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent gy = new Intent(MySettings.this,MywebViewClass.class);
                        gy.putExtra("git","https://github.com/xiaoshitou13/ThreePlayer");
                        startActivity(gy);
                    }
                });

                break;
            case R.id.jlhaoyou3:

                //判断SDK版本是否大于等于19，大于就让他显示，小于就要隐藏，不然低版本会多出来一个
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    setTranslucentStatus(true);
                    //还有设置View的高度，因为每个型号的手机状态栏高度都不相同
                }

                final AlertDialog.Builder builder3 = new AlertDialog.Builder(this);

                View s = LayoutInflater.from(MySettings.this).inflate(R.layout.fankui,null);

                builder3.setView(s);

                final AlertDialog alertDialog =builder3.show();

                fked1 = s.findViewById(R.id.fktv1);
                fked2 = s.findViewById(R.id.fktv2);
                Button gb = s.findViewById(R.id.fkqx);
                Button ss = s.findViewById(R.id.fkss);
                yuy = s.findViewById(R.id.fkluying);

                yuy.setOnTouchListener(this);
                gb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                recoderDialog = new AudioRecoderDialog(this);
                recoderDialog.setShowAlpha(0.98f);
                recoderUtils = new AudioRecoderUtils(new File(Environment.getExternalStorageDirectory() + "/recoder.amr"));
                recoderUtils.setOnAudioStatusUpdateListener(this);



                ss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!fked1.getText().toString().equals("")&&!fked2.getText().toString().equals("")) {
                            Toast.makeText(MySettings.this, "发送成功", Toast.LENGTH_SHORT).show();
                            fked1.setText("");
                            fked2.setText("");
                        }else{
                            Toast.makeText(MySettings.this, "请输入邮箱或者信息", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                break;
            case R.id.jiluti:
                break;
//            case R.id.seticon:
//                finish();
//                break;
        }
    }


    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                recoderUtils.startRecord();
                downT = System.currentTimeMillis();
                recoderDialog.showAtLocation(view, Gravity.CENTER, 0, 0);
                yuy.setBackgroundResource(R.drawable.shape_recoder_btn_recoding);
                return true;
            case MotionEvent.ACTION_UP:
                recoderUtils.stopRecord();
                recoderDialog.dismiss();
                yuy.setBackgroundResource(R.drawable.shape_recoder_btn_normal);
                File f = new File(Environment.getExternalStorageDirectory() + "/recoder.mp3");

                try {
                    FileReader reader = new FileReader(f);// 获取该文件的输入流
                    char[] bb = new char[1024];// 用来保存每次读取到的字符
                    String str = "";// 用来将每次读取到的字符拼接，当然使用StringBuffer类更好
                    int n;// 每次读取到的字符长度
                    while ((n = reader.read(bb)) != -1) {
                        str += new String(bb, 0, n);
                    }
                    reader.close();// 关闭输入流，释放连接
                    fked2.setText(str);


                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;

        }
        return false;
    }


    @Override
    public void onUpdate(double db) {
        if(null != recoderDialog) {
            int level = (int) db;
            recoderDialog.setLevel((int)db);
            recoderDialog.setTime(System.currentTimeMillis() - downT);
        }
    }


    /**
     * 获取缓存大小
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 清除缓存
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }


}
