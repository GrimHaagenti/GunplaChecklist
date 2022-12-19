package com.example.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.listas.databinding.ActivityMainBinding
import com.example.listas.dataclasses.GunplaDatabase
import com.example.listas.dataclasses.gunplaItem
import com.example.listas.fragments.DetailsPageFragment
import com.example.listas.fragments.GunplaListFragment
import com.example.listas.fragments.MainMenuFragment
import com.example.listas.fragments.MainScreenFragment
import com.google.gson.Gson


enum class Screens { MAINSCREEN, MENUSCREEN, GUNPLALIST, DETAILSPAGE}
class GunplaListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainScreenFragment :MainScreenFragment
    private lateinit var mainMenuFragment: MainMenuFragment
    private lateinit var gunplaListFragment: GunplaListFragment
    private lateinit var detailsPageFragment : DetailsPageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var gunplaDatabase: GunplaDatabase = ReadJson()

        var gunplaList = listOf<gunplaItem>()

        gunplaList = gunplaDatabase.GunplaDatabase

        mainScreenFragment = MainScreenFragment()
        mainMenuFragment = MainMenuFragment(supportFragmentManager)
        gunplaListFragment = GunplaListFragment(supportFragmentManager, gunplaList, this)
        detailsPageFragment = DetailsPageFragment(supportFragmentManager)

        setFragment(gunplaListFragment)
        //hideFragment(detailsPageFragment)





    }

    fun getBinding(): ActivityMainBinding{
        return binding
    }
    public fun getFragment( name: Screens): Fragment? {
        return when (name){
            Screens.MAINSCREEN -> mainScreenFragment
            Screens.MENUSCREEN -> mainMenuFragment
            Screens.GUNPLALIST -> gunplaListFragment
            Screens.DETAILSPAGE -> detailsPageFragment
        }
    }

    fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, fragment)
            commit()
        }
    }

    fun hideFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            this.hide(fragment)
            commit()
        }
    }
    fun ReadJson (): GunplaDatabase {
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