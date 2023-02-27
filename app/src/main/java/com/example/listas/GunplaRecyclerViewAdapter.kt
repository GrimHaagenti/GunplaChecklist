package com.example.listas


import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.Activities.GunplaListActivity
import com.example.listas.databinding.ItemGunplaBinding
import com.example.listas.dataclasses.GunplaItem
import com.squareup.picasso.Picasso


class GunplaRecyclerViewAdapter (
    val parent: GunplaListActivity,
    val gunplas: List<GunplaItem>,
) :
    RecyclerView.Adapter<GunplaRecyclerViewAdapter.GunplaVH>() {

    inner class GunplaVH(binding: ItemGunplaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val img = binding.boxArtImage
        val name = binding.name
        val button = binding.transparency

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunplaVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGunplaBinding.inflate(layoutInflater)


        return GunplaVH(binding)

    }

    override fun onBindViewHolder(holder: GunplaVH, position: Int) {

        val element = gunplas[position]

        holder.name.text = element.fullName
        getBoxArt(element.boxArtURL, holder.img)

        holder.button.setOnClickListener {
            parent.detailActivityStart(position)
        }
        holder.button.setOnLongClickListener{
            val dialog = AlertDialog.Builder(parent)
            dialog.setTitle("Add to List")
            dialog.setMessage(" Aun no tengo las listas")
            dialog.setNegativeButton("Cancel") { _: DialogInterface, i: Int -> }
            dialog.show()
            true
        }

    }


    fun getBoxArt(url: String, imageView: ImageView?) {
//        Picasso.with(parent).load(url).resize(95, 95).into(imageView)
        //Picasso.Builder(parent).build().load(url).resize(95, 95).into(imageView)

        val pic = Picasso.Builder(parent).build()
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

