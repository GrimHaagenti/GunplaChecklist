package com.example.listas


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.listas.databinding.ItemParticleBinding


class GunplaRecyclerViewAdapter(val particles: List<String>):
    RecyclerView.Adapter<GunplaRecyclerViewAdapter.ParticleVH>()
{
    inner class ParticleVH(binding: ItemParticleBinding):
        RecyclerView.ViewHolder(binding.root){
        val name = binding.particleText
        val image = binding.particleImage
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticleVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemParticleBinding.inflate(layoutInflater)
        return ParticleVH(binding)

    }

    override fun onBindViewHolder(holder: ParticleVH, position: Int) {
        val element = particles[position]

        holder.name.text = element

        holder.root.setOnClickListener {

        }


    }

    override fun getItemCount(): Int {
        return particles.size
    }

}

