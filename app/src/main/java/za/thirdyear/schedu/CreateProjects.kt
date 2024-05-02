package za.thirdyear.schedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import layout.Category

class CreateProjects : AppCompatActivity() {

    private lateinit var categories : MutableList<String>
    lateinit var txtStartDate : EditText
    lateinit var txtEndDate : EditText
    lateinit var txtHoursRequired : EditText
    private lateinit var adapter: ArrayAdapter<String>




    // ArrayAdapter for projects
    lateinit var projectAdapter: ArrayAdapter<String>
    lateinit var btnCreateProject: Button // Button to add projects
    lateinit var txtProjectName : EditText
    lateinit var btnAddProjectPhoto:Button
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_projects)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)


        // Linking views using findViewById
        txtProjectName = findViewById(R.id.txtProjectName)
        txtStartDate = findViewById(R.id.txtStartDate)
        txtEndDate = findViewById(R.id.txtEndDate)
        txtHoursRequired = findViewById(R.id.txtHoursRequired)
        btnCreateProject=findViewById(R.id.btnCreateProject)
        btnAddProjectPhoto=findViewById(R.id.btnAddProjectPhoto)
        categories = ViewCategoriesActivity.categoryNameList //Mutable List of Categories from ViewCategories
        Project.projects = ArrayList()
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
                R.id.nav_logout->{
                    val moveIntent = Intent(this, LoginActivity::class.java)
                    startActivity(moveIntent)
                    true
                }
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

// Initializing the ArrayAdapter with the context, layout, and data
//adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, categories)
        projectAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Project.projects)

// Add Project to the Project list when the button is clicked
        btnCreateProject.setOnClickListener {
            val project = txtProjectName.text.toString()
            val sDate = txtStartDate.text.toString()
            val eDate = txtEndDate.text.toString()
            val hours =txtHoursRequired.text.toString()
            if (project.isNotEmpty() && !Project.projects.contains(project)) {
                Project.projects.add(project)
                Project.projects.add(sDate)
                Project.projects.add(eDate)
                Project.projects.add(hours)
                projectAdapter.notifyDataSetChanged()
                txtProjectName.text.clear()
                txtStartDate.text.clear()
                txtEndDate.text.clear()
                txtHoursRequired.text.clear()
            }
        }
        btnAddProjectPhoto.setOnClickListener {
            val moveIntent = Intent(this, AddPhotoActivity::class.java)
            startActivity(moveIntent)
            true
        }
    }
}