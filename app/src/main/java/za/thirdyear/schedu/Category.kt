package layout

import android.os.Build
import androidx.annotation.RequiresApi
import java.sql.Date
import java.time.LocalDate

class Category
{
    var categoryID : String = "CAT000000"
    var userIU : String = "User000000"
    var categoryName : String = "Default Category Name"
    @RequiresApi(Build.VERSION_CODES.O)
    var categoryDateCreated : LocalDate = LocalDate.now()


    //Insert Method to add Details to Firebase

}