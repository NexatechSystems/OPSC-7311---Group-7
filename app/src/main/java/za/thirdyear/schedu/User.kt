package za.thirdyear.schedu

class User {
    private var userID : String = "User000000"
    private var userName : String = "User Default Name"
    private var userSurname : String = "User Default Surname"
    private var userEmail : String = "User Default Email"
    private var userUserName : String = "User Default Username"
    private var userPassword : String = "User Default Password"

    constructor(ID : String, Name : String, Surname : String, Email : String, Username : String, Password : String)
    {
        this.userID = ID
        this.userName = Name
        this.userSurname = Surname
        this.userEmail = Email
        this.userUserName = Username
        this.userPassword = Password
    }

    /******Create- Insert data to add Details to Firebase for Registration******/
    public fun RegisterUser(NewUser : User) : Boolean
    {
        var registered : Boolean = false
            //Enter newUser into Firebase

        return registered
    }


    /******Retrieve username and password from database******/
    public fun LoginUser (Username: String, Password : String) : Boolean
    {
        var loggedIn = false
        //Search Firebase for username and password

        return loggedIn
    }


    /******Update User Details:******/
    public fun UpdateUserDetails (newData : String, Column : String) : Boolean
    {
        var updated = false

        return updated
    }





    /******Delete User Details:******/
    public fun DeleteUserDetails (newData : String, Column : String) : Boolean
    {
        var deleted = false

        return deleted
    }



}