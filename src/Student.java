public class Student extends Person {
    private String lop;
    private String studentID;

    public Student(String name, String address, String phone, String id, String email, String lop, String studentID) {
        super(name, address, phone, id, email);
        setLop(lop);
        setStudentID(studentID);
    }
    
    public void setLop(String lop){
        this.lop = lop;
    }
    public String getLop(){
        return lop;
    }
    public void setStudentID(String studentID){
        this.studentID = studentID;
    }
    public String getStudentID(){
        return studentID;
    }
    public void registerSubject(){
        
    }
}
