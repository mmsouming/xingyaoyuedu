package com.shinread.StarPlan.Parent.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.HttpResultListener;
import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp.CardClassResponseVo;
import com.fancyfamily.primarylibrary.commentlibrary.framework.base.BaseActivity;
import com.fancyfamily.primarylibrary.commentlibrary.ui.book.SearchBookActivity;
import com.fancyfamily.primarylibrary.commentlibrary.util.ToastUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.shinread.StarPlan.Parent.engin.net.AppModel;
import com.shinread.StarPlan.Parent.lib.camera.CameraManager;
import com.shinread.StarPlan.Parent.lib.decoding.CaptureActivityHandler;
import com.shinread.StarPlan.Parent.lib.decoding.InactivityTimer;
import com.shinread.StarPlan.Parent.lib.decoding.ViewfinderView;
import com.shinyread.StarPlan.Parent.R;

import java.io.IOException;
import java.util.Vector;

public class CaptureActivity extends BaseActivity implements Callback {

    public final static String EXTRA_SEARCHBOOK = "extra_searchbook" ;
    private int toType ;//默认 获取二维码后去 添加孩子界面， 1 去图书搜索界面

    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;

    @Override
    protected String setTag() {
        return this.getClass().getSimpleName() ;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_capture);


        toType = getIntent().getIntExtra(EXTRA_SEARCHBOOK,-1) ;


        int hasWriteContactsPermission = ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
            return;
        }

        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100) {
            if (permissions[0].equals(Manifest.permission.CAMERA)
                    &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //用户同意使用write
                CameraManager.init(getApplication());
                viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
                hasSurface = false;
                inactivityTimer = new InactivityTimer(this);
            }else{
                //用户不同意，向用户展示该权限作用
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA
                )) {
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setMessage("需要赋予访问相机的权限，不开启将无法正常工作！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).create();
                    dialog.show();
                    return;
                }
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //CommonUtils.setWindowBrightness(this, 255);
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(
                AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (Exception ioe) {
            ToastUtil.showMsg(getString(R.string.capture_error_tip));
            finish();
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    public void handleDecode(final Result obj, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();


        if (toType == 1){
            Intent i = new Intent();
            i.setClass(this, SearchBookActivity.class);
            i.putExtra(SearchBookActivity.EXTRA_CODE, obj.getText().toString().trim());
            startActivity(i);
        }else {
            postCard(obj.getText().toString().trim());
        }

    }

    private void postCard(final String pwdCard) {
        if(TextUtils.isEmpty(pwdCard)){
            ToastUtil.showMsg(getString(R.string.code_get_fail_from_code));
            return ;
        }
        showLoaddingDialog();
        AppModel.schoolClassGetByCode(pwdCard,TAG,new HttpResultListener<CardClassResponseVo>()

                {
                    @Override
                    public void onSuccess (CardClassResponseVo resp){
                        dismissLoaddingDialog();
                        if (resp.isSuccess() && resp.schoolVo != null) {
                            Intent i = new Intent(CaptureActivity.this, AddKindsInfoActivity.class);
                            i.putExtra("SchoolVo", resp.schoolVo);
                            i.putExtra("pwdCard",pwdCard);
                            startActivity(i);            ;
                        }
                        finish();
                    }

                    @Override
                    public void onFailed (Exception e, String msg){
                        dismissLoaddingDialog();
                        onBackPressed();
                    }
                }

        );
    }




    @Override
    public void onBackPressed() {


        if (toType != 1) {
            startActivity(new Intent(this, MainTabActivity.class));
        }
        finish();
    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources()
                    .openRawResourceFd(R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

}