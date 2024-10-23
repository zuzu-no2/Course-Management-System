public class Teacher extends Person {
    public String position;
    public Teacher(String name, String address, String phone, String id, String email, String position) {
        super(name, address, phone, id, email);
        setPosition(position);
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    
}
