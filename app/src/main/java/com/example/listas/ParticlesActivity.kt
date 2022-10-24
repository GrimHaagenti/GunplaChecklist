package com.example.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listas.databinding.ActivityParticlesBinding

class ParticlesActivity : AppCompatActivity() {

    private val particles = listOf("Quark Up",
        "Quark Charm",
        "Quark Top",
        "Quark Down",
        "Quark Strange",
        "Quark Bottom",
        "Electron",
        "Muon",
        "Tau",
        "Electron neutrino",
        "Muon neutrino",
        "Tau neutrino",
        "Gluon",
        "Photon",
        "Z boson",
        "W boson",
        "Higgs")

    private lateinit var  binding: ActivityParticlesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ParticleRecyclerViewAdapter.adapter = ParticleRecyclerViewAdapter(particles)
    }
}