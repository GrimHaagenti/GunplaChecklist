package com.example.listas


import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.listas.databinding.ItemParticleBinding
import kotlinx.coroutines.NonDisposableHandle.parent


class GunplaRecyclerViewAdapter(val gunplas: List<gunplaItem>, val parent: GunplaListActivity):
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
        val element = gunplas[position]

        holder.name.text = element.FullName

        holder.root.setOnClickListener {
            parent.getFragment().changeDetails(element.FullName)
            setFragment( parent.getFragment())

        }


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

