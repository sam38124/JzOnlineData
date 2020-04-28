package com.example.jzonlinedata

import android.content.Context
import android.util.Log
import com.example.jzfixlanguage.converData
import com.example.jztaskhandler.TaskHandler
import com.example.jztaskhandler.runner
import com.jzsql.lib.mmySql.JzSqlHelper
import com.jzsql.lib.mmySql.Sql_Result
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class OnlineData {
    companion object{
        private var instance: OnlineData? = null
        val newInstance: OnlineData
        get() {
            if(instance ==null){
                instance = OnlineData()
            }
            return instance!!
        }

    }
    fun  setUP(context:Context,url:String,result: callback){
        sqlHelper=JzSqlHelper(context,"onlinedata").create()
        sqlHelper.exsql("CREATE TABLE if not exists onlinedata (\n" +
                "    repl   VARCHAR PRIMARY KEY,\n" +
                "    replto VARCHAR\n" +
                ");\n")

        val profilePreferences =context.getSharedPreferences("Setting", Context.MODE_PRIVATE)
        val versionNow=profilePreferences.getString("onlinedata","nodata")
        Log.e("versionNow",versionNow)
        Thread{

            TaskHandler.newInstance.runTaskOnce("onlinedata", runner {
                try{
                    val url: HttpURLConnection =(URL(url).openConnection()) as HttpURLConnection
                    url.connectTimeout=10*1000
                    val dataStram = InputStreamReader(url.inputStream,"utf-8")
                    var reader=BufferedReader(dataStram)
                    var first=true
                    var version=""
                    var a=reader.readLine()
                    while (a != null){
                        if(first){
                            first=false
                            if(versionNow== converData.Data(a)[0].value){
                                break
                            }else{
                                sqlHelper.dropTb("onlinedata")
                                sqlHelper.exsql("CREATE TABLE if not exists onlinedata (\n" +
                                        "    repl   VARCHAR PRIMARY KEY,\n" +
                                        "    replto VARCHAR\n" +
                                        ");\n")
                                version= converData.Data(a)[0].value
                                sqlHelper.exsql("insert or replace into onlinedata(repl,replto) values ('version','${version}')")
                            }
                        }else{
                            val s= converData.Data(a)
                            for(data in s){
                                sqlHelper.exsql("insert or replace into onlinedata(repl,replto) values ('${data.name.replace("'","''")}','${data.value.replace("'","''")}')")
                            }
                        }
                        a=reader.readLine()
                    }
                    profilePreferences.edit().putString("onlinedata",version).commit()
                    result.result(true)
                }catch (e:Exception){e.printStackTrace()
                    result.result(false)
                }
            })
        }.start()

    }


    lateinit var sqlHelper: JzSqlHelper
}
interface callback{
    fun result(a:Boolean)
}

fun String.getOnlineData():String?{
    var b:String?=null
    OnlineData.newInstance.sqlHelper.query("select replto from fixlan where repl='${this.replace("'","")}'",
        Sql_Result {
            b=it.getString(0)
        })
    return b
}