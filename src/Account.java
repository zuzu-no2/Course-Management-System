public class Account {
    private String username;
    private String password;
    private String role;
    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public void setusername(String username){
        this.username = username;
    }
    public String getusername(){
        return username;
    }
    public void setrole(String role){
        getOTP();
        this.role = role;
    }
    public String getrole(){
        return role;
    }
    public void setpassword(String password){
        this.password = password;
    }
    protected String getpassword(){
        return password;
    }


    public void register(){
        
    }
    public void signin(){

    }
    public void signout(){

    }
    public void getOTP(){
        //human is teacher

        //human is student
    }
    public void changePass(){
        
    }


}
