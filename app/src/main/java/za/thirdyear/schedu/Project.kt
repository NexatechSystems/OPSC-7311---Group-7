package za.thirdyear.schedu

import java.util.Date

class Project {
    private var projectID : String = "PRO0000001"
    private var categoryID : String = "CAT000000"
    private var userID : String = "User000000"
    private var projectName : String = "Project Name"
    private lateinit var startDate : Date
    private lateinit var endDate : Date
    private var hoursDuration : Double = 0.0
    private var hoursSpent : Double = 0.0
    public lateinit var Project : Project

    constructor(ID : String, CategoryID : String, UserID : String ,
                Name : String, StartDate : Date, EndDate : Date, HoursDuration : Double)
    {
        this.projectID = ID
        //Use selectable categories from list
        this.categoryID = CategoryID
        //Use Shared Preference from user details
        this.userID = UserID
        this.projectName = Name
        this.startDate = StartDate
        this.endDate = EndDate
        this.hoursDuration = HoursDuration
        hoursSpent = 0.0
    }

    companion object
    {
        public var projects: ArrayList<String> = ArrayList<String>()// ArrayLists to store projects (made public to make it accessible to whole project)

    }

    /******Create new Project:******/



    /******Retrieve Projects List:- List available as a public attribute******/



    /******Edit Project:******/




    /******Delete Category:******/




}