package booleandevops.io.dietn;

public class userinformation {
    public String email;
    public String Bodytype;



    public userinformation() {

    }

    public userinformation(String email) {
        this.email = email;

    }
    public userinformation(String email, String bodytype){
        this.email = email;
        this.Bodytype = bodytype;
    }
}
