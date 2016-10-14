package com.johnson.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by johnsmac on 9/22/16.
 */
//特注 ： 这里的绑定 引用 会是 异步 过程，所以会出现 引用此类的 类 同方法中无法得到 及时的 绑定 信息。
public class BaseActivity extends AppCompatActivity {
     MyService myService;
     String result;
      boolean isBind;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            mApplication.gLogger.debug("service is connect");
            MyService.MyBind bind = (MyService.MyBind) service;
            myService = bind.getServices();
            isBind = true;
            result="is connected to the service"+myService.getADD(8,9);
            Log.d("B",result);
        }
        /** Called when a connection to the Service has been lost.
         * This typically happens when the process
         * hosting the service has crashed or been killed.
         * This does not remove the ServiceConnection itself --
         * this binding to the service will remain active,
         * and you will receive a call to {@link #onServiceConnected}
         * when the Service is next running.
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
//            mApplication.gLogger.debug("service is disconnect");
            isBind = false;
            result="unbind service" + isBind;
            Log.i("hello", "unbind");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = new Intent(this, MyService.class);

        bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }
}
