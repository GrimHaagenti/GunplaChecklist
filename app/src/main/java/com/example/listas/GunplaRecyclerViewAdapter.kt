package com.example.listas


import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.databinding.ItemGunplaBinding
import com.example.listas.dataclasses.gunplaItem
import com.squareup.picasso.Picasso


class GunplaRecyclerViewAdapter(val gunplas: List<gunplaItem>, val parent: GunplaListActivity):
    RecyclerView.Adapter<GunplaRecyclerViewAdapter.GunplaVH>()
{

    inner class GunplaVH(binding: ItemGunplaBinding):
        RecyclerView.ViewHolder(binding.root){

        val root = binding.root

        val img = binding.boxArtImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunplaVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGunplaBinding.inflate(layoutInflater)

        return GunplaVH(binding)

    }

    override fun onBindViewHolder(holder: GunplaVH, position: Int) {
        val element = gunplas[position]
        //getBoxArt(element.boxArtUrl, holder.img)
        holder.root.setOnClickListener {
            /*parent.getFragment().changeDetails(element.FullName)
            setFragment( parent.getFragment())
            */
        }


    }

    fun getBoxArt(url: String?, imageView: ImageView?){
        Picasso.with(parent).load(url).into(imageView)

    }
    fun setParent(par:GunplaListActivity){
        parent
    }
    override fun getItemCount(): Int {
        return gunplas.size
    }
    fun setFragment(fragment: Fragment){
        parent.supportFragmentManager.beginTransaction().apply {
            replace(parent.getBinding().fragmentContainer.id, fragment)
            commit()
        }
    }

    fun hideFragment(fragment: Fragment){
        parent.supportFragmentManager.beginTransaction().apply {
            this.hide(fragment)
            commit()
        }
    }


}

