package za.thirdyear.schedu

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DisplayDetails : AppCompatActivity() {

    /******Declare Control Variables- lateint var ******/
    private lateinit var imgLogo: ImageView
    private lateinit var listViewDisplayDetails : ListView
    private lateinit var txtCategory: TextView
    private lateinit var imgInfoButton: ImageButton
    private lateinit var btnFilter: Button
    private lateinit var btnCalculate: Button
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_details)

        /******Hooks******/
        imgLogo = findViewById(R.id.imageViewLogo)
        txtCategory = findViewById(R.id.textViewCategory)
        imgInfoButton = findViewById(R.id.imageViewInfoIcon)
        btnCalculate = findViewById(R.id.buttonCalculateHours)
        btnFilter = findViewById(R.id.buttonFilter)

        /******Variable Declaration******/
        var projectList : ArrayList<String> = ArrayList<String>()
        val listViewAdapter : Adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, projectList)
        listViewDisplayDetails = findViewById(R.id.ltvProjects)
        var categoryName : String = ViewCategoriesActivity.selectedCategoryName
        if(categoryName == "")
        {
            txtCategory.text = "Projects"
        }
        else
        {
            txtCategory.text = categoryName
        }
        var totalDurationInCategory : Double //Add sum of total Hours spent and Duration of Projects in category

        //Code to display Project Details in a table
        //Note- Project Array is defined as Project.projects and use this when filtering
            if(Project.projects.isEmpty())
            {
                projectList.add("No projects entered") //Initialize array if there are no projects
            }
             else
            {
                for(project in Project.projects)
                {
                    //if(categoryName == null || categoryName == "")
                    //{Display all of the projects}
                    projectList.add(project) //Add projects stored in project class

                }
            }

        val list = projectList.toTypedArray() //Add Projects to Project List
        listViewDisplayDetails.adapter = listViewAdapter as ListAdapter?

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

                R.id.nav_view_categories ->{

                    val moveIntent = Intent(this, ViewCategoriesActivity::class.java)
                    startActivity(moveIntent)
                    true
                }

                R.id.nav_display_details->{

                    val moveIntent = Intent(this, DisplayDetails::class.java)
                    startActivity(moveIntent)
                    true
                }

                R.id.nav_logout->{

                    val moveIntent = Intent(this, LoginActivity::class.java)
                    startActivity(moveIntent)
                    true
                }




                else -> false
            }
        }


    }
}