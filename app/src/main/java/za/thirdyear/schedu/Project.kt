package za.thirdyear.schedu

import java.util.Date

class Project {
     lateinit var projectID : String
     lateinit var categoryName : String
     lateinit var userID : String
     lateinit var projectName : String
     lateinit var startDate : Date
     lateinit var endDate : Date
     var hoursDuration : Double
     var hoursSpent : Double
    public lateinit var Project : Project

    constructor(ID : String, CategoryID : String ,
                Name : String, StartDate : Date, EndDate : Date, HoursDuration : Double)
    {
        this.projectID = ID
        //Use selectable categories from list
        this.categoryName = CategoryID
        //Use Shared Preference from user details
        this.projectName = Name
        this.startDate = StartDate
        this.endDate = EndDate
        this.hoursDuration = HoursDuration
        hoursSpent = 0.0
    }

    companion object
    {
        public var projects: ArrayList<Project> = ArrayList<Project>()// ArrayLists to store projects (made public to make it accessible to whole project)

    }

    /******Create new Project:******/



    /******Retrieve Projects List:- List available as a public attribute******/



    /******Edit Project:******/




    /******Delete Category:******/




}