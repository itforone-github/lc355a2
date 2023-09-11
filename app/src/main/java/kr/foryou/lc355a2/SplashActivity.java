package kr.foryou.lc355a2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;



/*
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;*/

public class SplashActivity extends AppCompatActivity {
  private static final int APP_PERMISSION_STORAGE = 9787;
  private final int APPS_PERMISSION_REQUEST=1000;
  final int SEC=1000;//다음 화면에 넘어가기 전에 머물 수 있는 시간(초)
  public static boolean isStart=true;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_splash);
    setContentView(R.layout.activity_splash);
    //퍼미션 설정
    if(Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
      TedPermission.create()
              .setPermissions(
                      Manifest.permission.READ_MEDIA_IMAGES,
                      Manifest.permission.READ_MEDIA_VIDEO,
                      Manifest.permission.READ_MEDIA_AUDIO,
                      Manifest.permission.CAMERA)
              .setRationaleMessage("이 앱은 권한설정을 하셔야 사용하실 수 있습니다.")
              .setDeniedMessage("권한설정에 거부하시면 앱설정에서 직접하셔야 합니다.")
              .setPermissionListener(permissionListener)
              .check();
    }else{
      TedPermission.create()
              .setPermissions(
                      Manifest.permission.WRITE_EXTERNAL_STORAGE,
                      Manifest.permission.READ_EXTERNAL_STORAGE,
                      Manifest.permission.CAMERA)
              .setRationaleMessage("이 앱은 권한설정을 하셔야 사용하실 수 있습니다.")
              .setDeniedMessage("권한설정에 거부하시면 앱설정에서 직접하셔야 합니다.")
              .setPermissionListener(permissionListener)
              .check();

    }
  }
  PermissionListener permissionListener = new PermissionListener() {
    //퍼미션 설정을 하면
    @Override
    public void onPermissionGranted() {
      goHandler();
    }
    //퍼미션 설정을 하지 않으면
    @Override
    public void onPermissionDenied(List<String> deniedPermissions) {

    }
  };

  //핸들러로 이용해서 3초간 머물고 이동이 됨
  public void goHandler() {
    Handler mHandler = new Handler();
    mHandler.postDelayed(new Runnable() {
      @Override
      public void run() {
        isStart=true;
        finish();
      }
    }, SEC);
  }
}
