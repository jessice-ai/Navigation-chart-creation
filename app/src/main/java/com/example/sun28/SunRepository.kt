package com.example.sun28

import androidx.lifecycle.LiveData


class SunRepository(private val SunUserDao: SunUserDao) {

    var database:SunUserDataBase?= null

//    fun getAlldata(): List<SunUser>? {
//        //println("JessiceDao"+SunUserDao)
//        var xdun = SunUserDao?.getAll()
//        // println("Jessice---xxx---"+xdun)
//        return xdun
//    }
    fun getstrind():String{
        return "jessice:aaaa";
    }
    fun getmAllWords(): LiveData<List<SunUser>> {
        return SunUserDao?.getmAllWords()
    }
    fun deleteUserById(id: Int) {
        SunUserDao?.deleteUserById(id)
    }
    fun insert(SunUser:SunUser){
        SunUserDao?.insertData(SunUser)
    }
//    fun update_first_name(id: Int, value: String) {
//        SunUserDao?.update_first_name(id,value)
//    }

}
