import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class People {

    private long id ;
    private String name;

    private LocalDate birthDate ;

    private String address ;

    private double height ;

    private double weight ;


    People(){}

    People( long id , String name , String birthDate , String address , double height , double weight){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.id = id; this.name = name;
        this.birthDate = LocalDate.parse(birthDate,dateTimeFormatter);
        this.address = address ;
        this.height = height ; this.weight = weight;
    }

    public String toString(){
        return "id: "+ this.id +" name: "+this.name+ " birth date: "+this.birthDate +" address: "+ this.address +" height: "+this.height +" weight: "+this.weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.birthDate = LocalDate.parse(birthDate,dateTimeFormatter);;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
