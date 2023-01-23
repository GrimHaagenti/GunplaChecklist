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
import java.util.*


enum class Screens { MAINSCREEN, MENUSCREEN, GUNPLALIST, DETAILSPAGE}
class GunplaListActivity : AppCompatActivity() {

    val fragmentStack: Stack<Fragment> = Stack()

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainScreenFragment :MainScreenFragment
    private lateinit var mainMenuFragment: MainMenuFragment
    private lateinit var gunplaListFragment: GunplaListFragment
    private lateinit var detailsPageFragment : DetailsPageFragment

    private lateinit var currentFragment : Fragment
    var currentScreen : Screens = Screens.MAINSCREEN


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var gunplaDatabase: GunplaDatabase = ReadJson()

        var gunplaList = gunplaDatabase.GunplaDatabase

        mainScreenFragment = MainScreenFragment(supportFragmentManager, this)
        mainMenuFragment = MainMenuFragment(supportFragmentManager, this)
        gunplaListFragment = GunplaListFragment(supportFragmentManager, gunplaList, this)


        currentFragment = gunplaListFragment

        SetFragment(currentFragment)
        //hideFragment(detailsPageFragment)





    }

    fun GoForward()
    {
        when(currentScreen)
        {
            Screens.MAINSCREEN -> TODO()
            Screens.MENUSCREEN -> TODO()
            Screens.GUNPLALIST -> TODO()
            Screens.DETAILSPAGE -> TODO()
        }

    }

    fun ChangeToDetailsPageFragment(item: gunplaItem)
    {
            detailsPageFragment = DetailsPageFragment(supportFragmentManager,item, this)
            SetFragment(detailsPageFragment)
    }


    fun getBinding(): ActivityMainBinding{
        return binding
    }
    fun getFragment( name: Screens): Fragment {
        return when (name){
            Screens.MAINSCREEN -> mainScreenFragment
            Screens.MENUSCREEN -> mainMenuFragment
            Screens.GUNPLALIST -> gunplaListFragment
            Screens.DETAILSPAGE -> detailsPageFragment
        }
    }

    public fun getCurrentFragment(): Fragment {
        return currentFragment
    }

    fun SetFragment(fragment: Fragment){

        fragmentStack.push(currentFragment)
        currentFragment = fragment

        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, currentFragment)
            commit()
        }
    }


    fun ReturnToLastFragment()
    {
        try {
            val lastFragment = fragmentStack.pop()
            SetFragment(lastFragment)
        }
        catch (e: Exception) {
            /// Something to say can't go back
        }

    }
    fun HideFragment(fragment: Fragment){
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