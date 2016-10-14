package com.johnson.myapplication;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

public class MainActivity extends BaseActivity {
    public MyApplication mApplication;


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApplication = (MyApplication)getApplication();
        mApplication.gLogger.debug("here we are onCreate");
        textView = (TextView) findViewById(R.id.hello_title);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isBind) {
                    textView.setText("connection failed");

                } else {
                    textView.setText("result is : " + myService.getADD(3, 3));
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        textView.setText(result);
        mApplication.gLogger.debug("here we are onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mApplication.gLogger.debug("here we are onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind) {
            isBind = false;
            unbindService(connection);
        }
        mApplication.gLogger.error("here we are onDestroy");
    }
}
