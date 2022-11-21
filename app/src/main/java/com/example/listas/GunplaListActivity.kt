package com.example.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.databinding.ActivityParticlesBinding
import com.google.gson.Gson

class GunplaListActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityParticlesBinding
    private  lateinit var detailsPageFragment : DetailsPageFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gunplaDatabase: GunplaDatabase = ReadJson()

        var gunplaList = listOf<gunplaItem>()

        gunplaList = gunplaDatabase.GunplaDatabase

        val detailsPageFragment = DetailsPageFragment(supportFragmentManager)
        /*setFragment(detailsPageFragment)
        hideFragment(detailsPageFragment)
*/
        binding.ParticleRecyclerViewAdapter.adapter = GunplaRecyclerViewAdapter(gunplaList,this)




    }

    fun getBinding(): ActivityParticlesBinding{
        return binding
    }
    fun getFragment(): DetailsPageFragment{
        return detailsPageFragment
    }

    fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            //replace(binding.fragmentContainer.id, fragment)
            commit()
        }
    }

    fun hideFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            this.hide(fragment)
            commit()
        }
    }
    fun ReadJson (): GunplaDatabase{
        val fileContent = getResourceAsText()
        println(fileContent)
        val gson = Gson()
        return gson.fromJson(fileContent, GunplaDatabase::class.java)
    }


    fun getResourceAsText(): String {
        val jsonData = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
            "gunpla_checklist_json_database",
            "raw", applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }
        return jsonData
    }

}