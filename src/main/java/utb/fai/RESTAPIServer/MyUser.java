package utb.fai.RESTAPIServer;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class MyUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String phoneNumber;

    public MyUser() {}

    public MyUser(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public boolean isUserDataValid() {
        if (name == null || name.trim().isEmpty()){
            return false;
        }
        if(phoneNumber == null || !phoneNumber.matches("\\+?[0-9]*")){
            return false;
        }
        if(email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            return false;
        }
        return true;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setID(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhoneNumber(String phonenumber){
        this.phoneNumber = phonenumber;
    }
    
}
