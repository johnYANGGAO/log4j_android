package com.johnson.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

//    private MyApplication myApplication;
    public MyService() {
    }
    private final IBinder mBinder = new MyBind();
    @Override
    public IBinder onBind(Intent intent) {
       return  mBinder;
    }

    public class MyBind extends Binder {

          MyService getServices(){

              return MyService.this;
          }


    }

    public int getADD (int a,int b){

        return a+b;

    }
    @Override
    public void onCreate() {
        super.onCreate();
//        myApplication=(MyApplication) getApplication();
//        myApplication.gLogger.debug("service oncreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        myApplication.gLogger.debug("service ondestroy");
    }
}
