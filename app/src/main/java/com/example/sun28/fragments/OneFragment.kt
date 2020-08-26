package com.example.sun28.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sun28.*
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.fragment_one.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    /**
     * Fuel 使用 POST 方式获取远程数据，返回的是 Javabean，代码
     * 获取远程数据，并转化为数组
     */
    fun getremotedata(){
        val httpAsync = Fuel.get("https://pixabay.com/api/?key=17946669-543fe6c4c313739ab33b63515&q=yellow+flowers&image_type=photo&pretty=true&per_page=200")
            .responseObject(Animal.Deserializer()) { request, response, result ->
                val(animals, err) = result   //Kotlin 写法
                //val animals = result.component1() //java写法
                if (animals != null) {
                    /**
                     * 把远程数据，写入数据库
                     */
                    //val userDao = SunUserDataBase.getInstance(this)?.getUserDao()
                    var i=1
                    var user: SunUser
                    var myViewModel = ViewModelProvider(this@OneFragment).get(MyViewModel::class.java)
                    //var item: LiveData<List<SunUser>>? = null
                    for (cursor in animals.hits){
                        user = SunUser(i, cursor.largeImageURL)
                        myViewModel.insert(user)
                        //myViewModel?.deleteUserById(i)
                        //println("Jessice:"+i)
                        i++
                    }
                    myViewModel.getmAllWords()
                }
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    /**
     * onActivityCreated 内容放在它这里
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /**
         * 添加部分测试数据
         */
        //getremotedata()

        var adapterxd = SunPageListAdapter()
        sun_list_item_Recyclerview_id.apply {
            adapter = adapterxd
            layoutManager = GridLayoutManager(requireContext(),3)  //2代替两列
        }

        var myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        myViewModel.getmAllWords()?.observe(viewLifecycleOwner, Observer {
            println("Jessice:mAllWords数据发生变化")
            adapterxd.submitList(it)
        })
        myViewModel.mAllWords?.value ?:myViewModel.getmAllWords()
        /**
         * 临时查询数据库信息
         */
//        val userDao = SunUserDataBase.getInstance(requireContext())?.getUserDao()
//        var userDataArray = userDao?.loadAllUsers()
//        var names_str = "";
//        userDataArray?.let {
//            for (sun in it) {
//                println("Jessice - firstName："+sun.picture)
//            }
//        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}