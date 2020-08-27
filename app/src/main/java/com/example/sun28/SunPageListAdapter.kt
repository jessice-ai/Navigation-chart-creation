package com.example.sun28

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.sun_cell.view.*


class SunPageListAdapter : ListAdapter<SunUser, SunPageListAdapter.SunMyViewHolder>(DIFFCALLBACK) {

    class SunMyViewHolder(var sunHolder_View : View) : RecyclerView.ViewHolder(sunHolder_View){
        //sunHolder_textView.sun_layout_text1.text = sunholder!!.title
        //val imageView: ImageView = sunHolder_View.image_view
//        val textView1: TextView = sunHolder_View.text_view_1
        //val textView2: ImageView = sunHolder_View

    }
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SunMyViewHolder {
        /**
         * onCreateViewHolder 方法作用： 加载一个视图出来，并创建一个 Holder
         * 说明：只执行一次
         */
        val holder = SunMyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sun_cell,parent,false))
        return holder
    }

    override fun onBindViewHolder(holder: SunMyViewHolder, position: Int) {
        /**
         * onBindViewHolder 为每一项赋值
         * 多少个元素，就运行多少次
         */
        //println("Jessice:Picture----"+getItem(position).picture)
        var imgUrl = getItem(position).picture
        //holder.itemView.imageView3 = getItem(viewType).picture
//        holder.itemView.shimmerlayout.shimmerColor = "0x55FFFFFF"
//        holder.itemView.shimmerlayout.shimmerAngle = 0
//        holder.itemView.shimmerlayout.shi = 0
        Glide.with(holder.itemView)
            .load(imgUrl)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    /**
                     * 失败时，不做处理
                     */
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    /**
                     * 成功时关闭图片加载效果
                     */
                    return false.also {
                        //shimmerLayout?.stopShimmerAnimation()
                        return false
                    }
                }
            })
            .into(holder.itemView.imageView3)
        /**
         * 这里方的内容，比如点击之后执行的操作 结束
         */
        //holder.imageView.
    }
    object DIFFCALLBACK: DiffUtil.ItemCallback<SunUser>(){
        override fun areItemsTheSame(oldItem: SunUser, newItem: SunUser): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SunUser, newItem: SunUser): Boolean {
            return oldItem.id == newItem.id
        }

    }


}