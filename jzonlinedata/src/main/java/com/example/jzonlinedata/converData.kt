package com.example.jzfixlanguage

import java.util.*

object converData {
    fun Data(data: String): ArrayList<Data> {
        var data = data
        val dd =
            ArrayList<Data>()
        data = data.replace("\\\"", "jzR1")
        val t = data.split("\";").toTypedArray()
        for (i in t) {
            val spia = i.split("\"=\"").toTypedArray()
            if (spia.size == 2) {
                val ee = Data()
                if (spia[0].length > 1) {
                    val index = spia[0].indexOf("\"")
                    ee.name = spia[0].substring(index + 1).replace("jzR1", "\'")
                }
                ee.value = spia[1].replace("jzR1", "\'")
                dd.add(ee)
            }
        }
        return dd
    }
}
class Data {
    var name = ""
    var value = ""
}