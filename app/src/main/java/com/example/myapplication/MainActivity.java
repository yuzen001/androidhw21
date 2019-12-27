package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            final Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ConnectivityManager connMgr = (ConnectivityManager)
                            getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        // Create background thread to connect and get data
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("網路狀態")   		//設定視窗標題
                                .setMessage("目前有網路")	//設定顯示的文字
                                .setPositiveButton("好的",new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();//dismiss:關閉回傳值 ；cancel:尚未完成工作關閉
                                    }
                                })			//設定結束的子視窗
                                .show();		//呈現對話視窗
                    }
                    else {
                        //產生視窗物件
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("目前沒有網路")   		//設定視窗標題
                                .setMessage("是否要前往設定")	//設定顯示的文字
                                .setNegativeButton("設定網路",new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                                        dialog.cancel();//dismiss:關閉回傳值 ；cancel:尚未完成工作關閉
                                    }
                                })			//設定結束的子視窗
                                .setPositiveButton("不用設定",new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();//dismiss:關閉回傳值 ；cancel:尚未完成工作關閉
                                    }
                                })			//設定結束的子視窗
                                .show();		//呈現對話視窗
                    }
                }
            });
        }
}
