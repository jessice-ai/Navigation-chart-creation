package com.example.sun28

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class MyViewModel(application: Application)  : AndroidViewModel(application) {
    var xun:Int = 1
    var mAllWords: LiveData<List<SunUser>>? = null
    var SunRepository:SunRepository? = null

    /**
     * 延迟初始化，一个 Repository 管理数据库操作
     * Repository 是 ViewModel 与 Room 之间的，中间层
     */
    fun getmAllWords(): LiveData<List<SunUser>>? {
        mAllWords = SunRepository?.getmAllWords()
        /**
         * 查一下数据库中是否有数据
         */

        println("Jessice:查询数据更新ViewModel")
        return mAllWords
    }
    /**
     * 添加数据
     */
    fun insert(SunUser: SunUser) {
        SunRepository?.insert(SunUser)
    }
////
//    /**
//     * 更新数据
//     */
//    fun update_first_name(id:Int, value:String){
//        SunRepository?.update_first_name(id,value)
//    }
//    /**
//     * 查询数据库中所有数据
//     */
//    fun getAllWords(): List<SunUser>? {
//        var listSun = SunRepository?.getAlldata()
//        return listSun;
//    }
//    /**
//     * 删除数据
//     */
    fun deleteUserById(id:Int){
        SunRepository?.deleteUserById(id)
    }
    //var inputAge = MutableLiveData<Int>()
    /**
     * 初始化模块
     */
    init {
        /**
         * 构造函数（constructor） 初始化代码块（init） 伴生对象（companion object），执行顺序
         *  1、首先执行伴生对象（Companion object）
         *  2、其次执行初始化代码块 （init）
         *  3、再执行构造函数 （constructor）
         */
        val dao = SunUserDataBase.getInstance(application)?.getUserDao()
        SunRepository = dao?.let { SunRepository(it) }

    }
}
