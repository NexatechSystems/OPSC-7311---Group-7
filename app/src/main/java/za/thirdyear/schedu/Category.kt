package layout

import android.media.Image
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Date
import java.time.LocalDate
import java.util.Calendar

class Category
{
    private var categoryID : String = "CAT000000"
    private var userIU : String = "User000000"
    private var categoryName : String = "Default Category Name"
    @RequiresApi(Build.VERSION_CODES.O)
    private var categoryDateCreated : Date = Date()
    private lateinit var categoryImage : Image

    //Constructor for Class
    @RequiresApi(Build.VERSION_CODES.O)
    constructor(categoryID: String, userIU: String, categoryName: String, categoryDateCreated : Date, Image : Image)
    {
        this.categoryID = categoryID
        this.userIU = userIU
        this.categoryName = categoryName
        this.categoryDateCreated = categoryDateCreated
        this.categoryImage = Image
    }

    //Insert Method to add Details to Firebase


}