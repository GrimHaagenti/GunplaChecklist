package com.example.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.databinding.ActivityParticlesBinding
import com.google.gson.Gson

class GunplaListActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityParticlesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gunpladatabase: GunplaDatabase = ReadJson()

        var nameList = arrayListOf<String>()

        gunpladatabase.GunplaDatabase.forEach(){
            nameList.add(it.FullName)
            println(it.FullName)
        }
        /*val detailsPageFragment = DetailsPageFragment()
        setFragment(detailsPageFragment)
        hideFragment(detailsPageFragment)
*/
        binding.ParticleRecyclerViewAdapter.adapter = GunplaRecyclerViewAdapter(nameList)






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