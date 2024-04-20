package za.thirdyear.schedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class DisplayDetails : AppCompatActivity() {

    /******Declare Control Variables- lateint var ******/
    lateinit var txtschedu : TextView
    lateinit var imgLogo : ImageView
    lateinit var txtCategory : TextView
    lateinit var imgInfoButton: ImageButton
    lateinit var btnFilter : Button
    lateinit var btnCalculate : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_details)

        /******Hooks******/
        imgLogo = findViewById(R.id.imageViewLogo)
        txtCategory = findViewById(R.id.textViewCategory)
        imgInfoButton = findViewById(R.id.imageViewInfoIcon)
        btnCalculate = findViewById(R.id.buttonCalculateHours)
        btnFilter = findViewById(R.id.buttonFilter)



    }
}