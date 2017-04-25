package com.eqxiu.ui;

import com.eqxiu.view.ZoomTextView;
import com.eqxiu.zoomview.R;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

/**
 * Created by jiangchunbo on 15/11/18.
 */
public class MainActivity extends Activity {
	
	private ZoomTextView mZoomTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LinearLayout view = (LinearLayout) findViewById(R.id.rl);

		
		ZoomTextView tv = new ZoomTextView(this, new ZoomTextView.ZoomTextViewInterface() {
            @Override
            public void setDrawBorder(ZoomTextView view) {
                 int width = view.getWidth();
                Log.d("WIDTH.....","width..................." + width);

            }

        });
        String fileVedioPath = Environment.getExternalStorageDirectory().toString() + File.separator
                + "EQVedio";
        File fileAll = new File(fileVedioPath);
        if (!fileAll.exists()) {
            fileAll.mkdir();
        }
        File[] files = fileAll.listFiles();
        for (int j = 0; j < files.length; j++) {
            //取出视频的地址并且进行播放
            final File file1 = files[j];
            final MediaPlayer mediaPlayer = new MediaPlayer();
            //获取SurfaceHolder 可以通过该接口来操作SurfaceView中的Surface
            SurfaceHolder surfaceHolder = tv.getHolder();
            //设置Meiaplayer的准备监听
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //准备完成后播放
                    mediaPlayer.start();
                }
            });
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                                          //当SurfaceView中Surface创建时回掉
                                          //该方法表示Surface已经创建完成，可以在该方法中进行绘图操作
                                          @Override
                                          public void surfaceCreated(SurfaceHolder holder) {
                                              mediaPlayer.reset();
                                              try {
                                                  //设置视屏文件图像的显示参数
                                                  mediaPlayer.setDisplay(holder);

                                                  //file.getAbsolutePath()本地视频
                                                  //uri 网络视频
                                                  mediaPlayer.setDataSource(MainActivity.this, Uri.parse(file1.getAbsolutePath()));
                                                  //prepare();表示准备工作同步进行，（准备工作在UI线程中进行）
                                                  //当播放网络视频时，如果网络不要 会报ARN 所以不采用该方法
                                                  //mediaPlayer.prepare();
                                                  //异步准备 准备工作在子线程中进行 当播放网络视频时候一般采用此方法
                                                  mediaPlayer.prepareAsync();
                                              } catch (IOException e) {
                                                  e.printStackTrace();
                                              }
                                          }

                                          @Override
                                          public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                                          }

                                          @Override
                                          public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

                                          }

                                      });
        }
//        Resources resources = this.getResources();
//        Drawable drawable = resources.getDrawable(R.drawable.ic_launcher);
//		tv.setImageDrawable(drawable);
		RelativeLayout.LayoutParams layoutParamsContent = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParamsContent.leftMargin = 20;
		layoutParamsContent.topMargin = 20;
		tv.setLayoutParams(layoutParamsContent);
		view.addView(tv);
		tv.setZoomTextViewInterface(new ZoomTextView.ZoomTextViewInterface() {
            @Override
            public void setDrawBorder(ZoomTextView view) {
                if(mZoomTextView ==null){
                    view.setIsFocusableDrawRect(true);
                }else {
                    mZoomTextView.setIsFocusableDrawRect(false);
                    view.setIsFocusableDrawRect(true);
                }
                mZoomTextView = view;
            }


        });
		
		//拖动其中一个TextView改变位置，再点击另一个TextVeiw的任何一个边框，那么问题来了，上一个TextVeiw 回到了原来的位置，问题的根源在 ZoomTextView.java 的252行 调用的函数引起的
		
		
		ZoomTextView tv1 = new ZoomTextView(this, new ZoomTextView.ZoomTextViewInterface() {
            @Override
            public void setDrawBorder(ZoomTextView view) {

            }

        });
        String fileVedioPath1 = Environment.getExternalStorageDirectory().toString() + File.separator
        + "EQVedio";
        File fileAll1 = new File(fileVedioPath1);
        if (!fileAll1.exists()) {
            fileAll.mkdir();
        }
        File[] files1 = fileAll.listFiles();
        for (int j = 0; j < files1.length; j++) {
            //取出视频的地址并且进行播放
            final File file1s = files[j];
            final MediaPlayer mediaPlayer = new MediaPlayer();
            //获取SurfaceHolder 可以通过该接口来操作SurfaceView中的Surface
            SurfaceHolder surfaceHolder = tv.getHolder();
            //设置Meiaplayer的准备监听
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //准备完成后播放
                    mediaPlayer.start();
                }
            });
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                //当SurfaceView中Surface创建时回掉
                //该方法表示Surface已经创建完成，可以在该方法中进行绘图操作
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    mediaPlayer.reset();
                    try {
                        //设置视屏文件图像的显示参数
                        mediaPlayer.setDisplay(holder);

                        //file.getAbsolutePath()本地视频
                        //uri 网络视频
                        mediaPlayer.setDataSource(MainActivity.this, Uri.parse(file1s.getAbsolutePath()));
                        //prepare();表示准备工作同步进行，（准备工作在UI线程中进行）
                        //当播放网络视频时，如果网络不要 会报ARN 所以不采用该方法
                        //mediaPlayer.prepare();
                        //异步准备 准备工作在子线程中进行 当播放网络视频时候一般采用此方法
                        mediaPlayer.prepareAsync();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

                }

            });
        }
//        Resources resources2 = this.getResources();

//        Drawable drawable2 = resources2.getDrawable(R.drawable.ic_launcher);
//        tv.setImageDrawable(drawable2);
		RelativeLayout.LayoutParams layoutParamsContent1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParamsContent1.leftMargin = 100;
		layoutParamsContent1.topMargin = 200;
		tv1.setLayoutParams(layoutParamsContent1);
		view.addView(tv1);
		tv1.setZoomTextViewInterface(new ZoomTextView.ZoomTextViewInterface() {
            @Override
            public void setDrawBorder(ZoomTextView view) {
                if(mZoomTextView ==null){
                    view.setIsFocusableDrawRect(true);
                }else {
                    mZoomTextView.setIsFocusableDrawRect(false);
                    view.setIsFocusableDrawRect(true);
                }
                mZoomTextView = view;
            }

        });


	}

}
