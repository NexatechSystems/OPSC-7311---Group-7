package za.thirdyear.schedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Date

class ProjectFilteringActivity : AppCompatActivity() {

    /******Declare Control Variables- lateint var ******/
    private lateinit var projects: ArrayList<String>
    private lateinit var filteredProjects: ArrayList<String>
    private lateinit var projectAdapter: ArrayAdapter<String>
    private lateinit var btnApplyFilter: Button
    private lateinit var txtFilter: EditText
    private lateinit var txtStartDate : EditText
    private lateinit var txtEndDate : EditText

    companion object
    {
        lateinit var projectName : String
        lateinit var startDate : Date
        lateinit var endDate : Date
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_filtering)

        //projects = intent.getStringArrayListExtra("projects") // Assuming you passed projects from CreateProjects activity
        txtFilter = findViewById(R.id.editTextText)
        btnApplyFilter = findViewById(R.id.buttonFilter)
        txtStartDate = findViewById(R.id.txtStartDateFilter)
        txtEndDate = findViewById(R.id.txtEndDateFilter)

        startDate = SimpleDateFormat("dd-MM-yyyy").parse(txtStartDate.text.toString())!!
        endDate = SimpleDateFormat("dd-MM-yyyy").parse(txtEndDate.text.toString())!!

        filteredProjects = ArrayList(projects) // Initialize filteredProjects with all projects initially
        projectAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredProjects)

        btnApplyFilter.setOnClickListener {
            applyFilter()
        }
    }

    private fun applyFilter() {
        val filterText = txtFilter.text.toString().trim()
        if (filterText.isEmpty()) {
            filteredProjects.clear()
            filteredProjects.addAll(projects) // Reset to all projects if filter is empty
        }

        else {
            filteredProjects.clear()
            for (project in projects) {
                if (project.contains(filterText, ignoreCase = true)) {
                    filteredProjects.add(project)
                }
            }


        }
        projectAdapter.notifyDataSetChanged()

        val moveIntent = Intent(this, DisplayDetails::class.java)
        startActivity(moveIntent)
    }
}