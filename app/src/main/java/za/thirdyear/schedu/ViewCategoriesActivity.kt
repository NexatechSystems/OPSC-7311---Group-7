package za.thirdyear.schedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import layout.Category

class ViewCategoriesActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var btnCreateCategory : Button
    private lateinit var listViewCategories : ListView

    //Use to filter projects by category name
    companion object
    {
        public var selectedCategoryName : String = ""
        val categoryNameList = mutableListOf<String>() //Create String List
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_categories)

        /******Hooks******/
        btnCreateCategory = findViewById(R.id.btnCreateCategory)
        listViewCategories = findViewById(R.id.ltvCategories)
        //Colors alternate ListView Elements Colour
        val list = categoryNameList.toTypedArray() //Add Categories to Listview
        val listViewAdapter : Adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, categoryNameList)

        val teal = ContextCompat.getColor(this, R.color.teal_200)
        val orange = ContextCompat.getColor(this, R.color.orange)
        val pink = ContextCompat.getColor(this, R.color.pink)
        val purple = ContextCompat.getColor(this, R.color.purple_200)
        val white = ContextCompat.getColor(this, R.color.white)
        for( i in 0 until listViewCategories.childCount)
        {
            val listItem = listViewCategories.getChildAt(i)
            if( i % 2 == 0)
            {
                listItem.setBackgroundColor(white)
            }
            else if(i % 3 == 0)
            {
                listItem.setBackgroundColor(teal)
            }
            else if(i % 5 == 0)
            {
                listItem.setBackgroundColor(orange)
            }
            else
            {
                listItem.setBackgroundColor(purple)
            }

        }

        /******Load Categories onto ListView******/
        for(array in Category.categoryList) //List to reassign values of List into String List
        {
            if(Category.categoryList.count() >= categoryNameList.count() && !categoryNameList.contains(array[1]))
            {
                val stringArray = array as Array<String>
                categoryNameList.add(stringArray[1])
            }

        }
        listViewCategories.adapter = listViewAdapter as ListAdapter?

        //Allow users to click on element of ListView
        listViewCategories.setOnItemClickListener { parent, view, position, id ->
            selectedCategoryName = categoryNameList[position]
            // Redirect to Display Pages and Filter Projects based on CategoryName
            val displayDetails : Intent = Intent(this, DisplayDetails::class.java)
            startActivity(displayDetails)
        }



        //Create Category
        btnCreateCategory.setOnClickListener()
        {
            val createCategory : Intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(createCategory)
        }


        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val moveIntent = Intent(this, MainActivity::class.java)
                    startActivity(moveIntent)
                    true
                }

                R.id.nav_view_categories -> {
                    val moveIntent = Intent(this, ViewCategoriesActivity::class.java)
                    startActivity(moveIntent)
                    true
                }

                R.id.nav_display_details -> {
                    val moveIntent = Intent(this, DisplayDetails::class.java)
                    startActivity(moveIntent)
                    true
                }

                R.id.nav_logout -> {
                    val moveIntent = Intent(this, LoginActivity::class.java)
                    startActivity(moveIntent)
                    true
                }

                else -> false
            }
        }

    }

}