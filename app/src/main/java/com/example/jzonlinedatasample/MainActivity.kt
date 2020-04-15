package com.example.jzonlinedatasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jzfixlanguage.OnlineData
import com.example.jzfixlanguage.callback
import com.example.jzfixlanguage.getOnlineData

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        OnlineData.newInstance.setUP(this,"https://bento2.orange-electronic.com/Orange%20Cloud/OnlineData/Oglite.txt",object :
            callback {
            override fun result(a: Boolean) {
                Log.e("加載結果","$a")
                if(a){
                    "a".getOnlineData()
                    Log.e("加載結果", "version".getOnlineData())
                }
            }
        })

    }
}
