package com.example.listas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listas.GunplaListActivity
import com.example.listas.GunplaRecyclerViewAdapter
import com.example.listas.databinding.FragmentGunplaListBinding
import com.example.listas.dataclasses.gunplaItem

class GunplaListFragment(val suppManager: FragmentManager, val gunplaList: List<gunplaItem>, val parent: GunplaListActivity): Fragment() {

    private lateinit var binding : FragmentGunplaListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val manager = GridLayoutManager(parent,3)

        binding = FragmentGunplaListBinding.inflate(inflater)
        binding.gunplaRecyclerView.layoutManager = manager
        binding.gunplaRecyclerView.adapter = GunplaRecyclerViewAdapter(requireContext(), gunplaList, parent)

        return binding.root
    }

}