package com.example.listas

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.listas.dataclasses.GunplaDatabase
import com.example.listas.dataclasses.GunplaItem
import com.example.listas.Activities.DetailsPageActivity
import com.example.listas.Activities.GunplaListActivity
import com.example.listas.Activities.MainMenuActivity
import com.example.listas.Activities.MainScreenActivity
import com.google.gson.Gson
import java.util.*


enum class Screens { MAINSCREEN, MENUSCREEN, GUNPLALIST, DETAILSPAGE}
class GunplaListActivity : AppCompatActivity() {

    val fragmentStack: Stack<Activity> = Stack()

    //private lateinit var binding: ActivityMainBinding

    private lateinit var mainScreenActivity :MainScreenActivity
    private lateinit var mainMenuActivity: MainMenuActivity
    private lateinit var gunplaListActivity: GunplaListActivity
    private lateinit var detailsPageFragment : DetailsPageActivity

    private lateinit var currentFragment : Activity
    var currentScreen : Screens = Screens.MAINSCREEN


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(binding.root)

        var gunplaDatabase: GunplaDatabase = ReadJson()

        var gunplaList = gunplaDatabase.gunplaDatabase

        mainScreenActivity = MainScreenActivity(supportFragmentManager, this)
        mainMenuActivity = MainMenuActivity(supportFragmentManager, this)
        gunplaListActivity = GunplaListActivity(supportFragmentManager, gunplaList, this)


        //currentFragment = gunplaListActivity

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

    fun ChangeToDetailsPageFragment(item: GunplaItem)
    {
            detailsPageFragment = DetailsPageActivity(supportFragmentManager,item, this)
            SetFragment(detailsPageFragment)
    }



    fun getFragment( name: Screens): Activity {
        return when (name){
            Screens.MAINSCREEN -> mainScreenActivity
            Screens.MENUSCREEN -> mainMenuActivity
            Screens.GUNPLALIST -> gunplaListActivity
            Screens.DETAILSPAGE -> detailsPageFragment
        }
    }

    public fun getCurrentFragment(): Activity {
        return currentFragment
    }

    fun SetFragment(fragment: Activity){

        fragmentStack.push(currentFragment)
        //currentFragment = fragment

        supportFragmentManager.beginTransaction().apply {
            //replace(binding.fragmentContainer.id, currentFragment)
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