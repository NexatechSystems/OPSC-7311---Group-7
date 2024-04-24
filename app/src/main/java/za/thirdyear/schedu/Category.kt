package layout

import android.media.Image
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import java.util.Date


class Category
{
    //Attributes
    private var categoryID : String = "CAT000000"
    private var userIU : String = "User000000"
    private var categoryName : String = "Default Category Name"
    @RequiresApi(Build.VERSION_CODES.O)
    private var categoryDateCreated : Date = Date()
    private lateinit var categoryImage : ImageView


    //Constructor for Class
    @RequiresApi(Build.VERSION_CODES.O)
    constructor(categoryID: String, userIU: String, categoryName: String, Image: ImageView)
    {
        this.categoryID = categoryID
        this.userIU = userIU
        this.categoryName = categoryName
        this.categoryDateCreated = Date()
        this.categoryImage = Image
    }

    /******Create new Category:******/
    public fun NewCategory (newCategory: Category) : Boolean
    {
        var created : Boolean = false

        return created
    }


    /******Companion Object for Static Methods******/
    companion object StaticMethods
    {
        //Static Attributes
        public lateinit var Category : Category

        /******static Retrieve Categories List with UserID as parameter:- List available as a public attribute******/
        public lateinit var Categories : List<Category>

        fun CategoryList(UserID : String) : List<Category>
        {
            //Add data to Categories : List<Category>


            return Categories
        }


        /******Edit Category:******/
        fun UpdateCategory () : Boolean
        {
            var updated : Boolean = false

            return updated
        }


        /******Delete Category:******/
        public fun DeleteCategory (categoryID: String) : Boolean
        {
            var created : Boolean = false

            return created
        }


        /******Create Category Image******/
        public fun CategoryImage (categoryName : String)
        {

        }


    }






}