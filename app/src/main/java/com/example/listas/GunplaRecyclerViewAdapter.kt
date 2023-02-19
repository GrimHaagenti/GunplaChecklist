package com.example.listas


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.databinding.ItemGunplaBinding
import com.example.listas.dataclasses.GunplaItem
import com.squareup.picasso.Picasso


class GunplaRecyclerViewAdapter (
    val context: Context,
    val gunplas: List<GunplaItem>,
) :
    RecyclerView.Adapter<GunplaRecyclerViewAdapter.GunplaVH>() {

    inner class GunplaVH(binding: ItemGunplaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val root = binding.root

        val img = binding.boxArtImage
        val name = binding.name

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunplaVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGunplaBinding.inflate(layoutInflater)


        return GunplaVH(binding)

    }

    override fun onBindViewHolder(holder: GunplaVH, position: Int) {
        val element = gunplas[position]
        //println(element.BoxArt)
        println(element.fullName)
        holder.name.text = element.fullName
        getBoxArt(element.boxArtURL, holder.img)


    }


    fun getBoxArt(url: String, imageView: ImageView?) {
//        Picasso.with(parent).load(url).resize(95, 95).into(imageView)
        //Picasso.Builder(parent).build().load(url).resize(95, 95).into(imageView)

        val pic = Picasso.Builder(context).build()
        pic.setLoggingEnabled(true)
        pic.load(url).resize(95, 95)
            .onlyScaleDown()
            .centerCrop().
            into(imageView)

    }



    override fun getItemCount(): Int {
        return gunplas.size
    }

    /*fun setFragment(fragment: Fragment) {
        parent.supportFragmentManager.beginTransaction().apply {
            replace(parent.getBinding().fragmentContainer.id, fragment)
            commit()
        }
    }

    fun hideFragment(fragment: Fragment) {
        parent.supportFragmentManager.beginTransaction().apply {
            this.hide(fragment)
            commit()
        }
    }*/


}

