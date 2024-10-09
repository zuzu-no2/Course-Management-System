public class Person {
    private String name;
    private String address;
    private String phone;
    private String id;
    private String email;

    public Person(String name, String address, String phone, String id, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.id = id;
        this.email = email;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }
    public String getId(){
        return id;
    }
    public String getEmail(){
        return email;
    }
    public String toString(String name, String address, String phone, String id, String email){
        return("Name: " + name + " Address: " + address + " Phone: " + phone + " ID: " + id + " Email: " + email);
    }
    
    
}
