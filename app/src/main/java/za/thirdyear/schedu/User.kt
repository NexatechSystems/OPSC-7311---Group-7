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

    //Insert Method to add Details to Firebase




}