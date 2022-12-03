package com.updatetech.tripDodol.model.packageModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PackageFile implements Serializable {
    private static final Long serialVersionUID = 123L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "modified_filename")
    private String modifiedfileName;
    @Column(name = "file_extension")
    private String fileExtension;

    @ManyToOne
    @JoinColumn(name = "trip_package_id")
    private TripPackage tripPackage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getModifiedfileName() {
        return modifiedfileName;
    }

    public void setModifiedfileName(String modifiedfileName) {
        this.modifiedfileName = modifiedfileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public TripPackage getTripPackage() {
        return tripPackage;
    }

    public void setTripPackage(TripPackage tripPackage) {
        this.tripPackage = tripPackage;
    }


}
