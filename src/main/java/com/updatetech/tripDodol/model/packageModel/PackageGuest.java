package com.updatetech.tripDodol.model.packageModel;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class PackageGuest implements Serializable {

    private static final Long serialVersionUID = 76L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Name initialisation should not be empty !")
    private String nameInit;
    @NotEmpty(message = "Name should not be empty !")
    @Size(min = 2,max = 50,message = "Name should be in between 2 to 20!")
    private String name;
    @NotEmpty(message = "Age should not be empty !")
    private String age;
    private String gender;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "package_booking_id")
    private PackageBooking packageBooking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameInit() {
        return nameInit;
    }

    public void setNameInit(String nameInit) {
        this.nameInit = nameInit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public PackageBooking getPackageBooking() {
        return packageBooking;
    }

    public void setPackageBooking(PackageBooking packageBooking) {
        this.packageBooking = packageBooking;
    }
}
