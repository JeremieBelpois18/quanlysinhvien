import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class Student extends People {

    private String studentCode ;

    private String nameSchool ;

    private int startYear;

    private float gpa;

    private Rank rank;

    Student(){}

    Student(long id , String name , String birthDate , String address , double height , double weight,String studentCode , String nameSchool, int startYear, float gpa){
        super(id,name,birthDate,address,height,weight);
        this.studentCode = studentCode ; this.nameSchool = nameSchool;
        this.startYear = startYear;
        this.gpa = gpa;
        this.setRank(gpa);
    }


    public String toString(){
        return  super.toString()+" student code: "+ this.studentCode +" name school: "+ this.nameSchool +" start year: "+ this.startYear +" gpa: "+this.gpa+ " title " +this.rank;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getNameSchool() {
        return nameSchool;
    }

    public void setNameSchool(String nameSchool) {
        this.nameSchool = nameSchool;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
        setRank(gpa);
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(float gpa) {
        if (gpa <= 3) {
            this.rank = Rank.POOR;
        } else if ( 3 < gpa && gpa <= 5) {
            this.rank = Rank.WEAK;
        } else if (5 < gpa && gpa <= 6.5) {
            this.rank = Rank.AVERAGE;
        } else if (6.5 < gpa && gpa <= 7.5) {
            this.rank = Rank.GOOD;
        } else if (7.5 < gpa && gpa < 9) {
            this.rank = Rank.VERYGOOD;
        } else if(gpa >=9){
            this.rank = Rank.EXCELLENT;
        }
    }
}

