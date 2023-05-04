package com.example.listas


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
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



    val notOnListAlpha=  R.drawable.not_on_list_button
    val onListAlpha = R.drawable.on_list_button



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

    override fun onBindViewHolder(holder: GunplaVH, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            if(payloads[0] is Boolean){

                val onList: Boolean = payloads.get(0) as Boolean

                if (onList) {
                    holder.button.setBackgroundResource(onListAlpha)
                }else{
                    holder.button.setBackgroundResource(notOnListAlpha)
                }
            }

        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: GunplaVH, position: Int) {
        val element = gunplas[position]

        holder.name.text = element.fullName
        getBoxArt(element.boxArtURL, holder.img)



        holder.button.setOnLongClickListener {
            parent.detailActivityStart(position)
            true
        }
        holder.button.setOnClickListener{

            parent.manageOnClickItem(position)
            parent.changeList()
            /*val dialog = AlertDialog.Builder(parent)
            dialog.setTitle("Add to List")
            dialog.setMessage(" Aun no tengo las listas")
            dialog.setNegativeButton("Cancel") { _: DialogInterface, i: Int -> }
            dialog.show()*/
        }
        val idList = parent.gunplalistmodelview.getCurrentIdsOnList()

        if(idList.contains(position)){
            holder.button.setBackgroundResource(onListAlpha)
        }else{
            holder.button.setBackgroundResource(notOnListAlpha)
        }

    }



    fun getBoxArt(url: String, imageView: ImageView?) {
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

}

