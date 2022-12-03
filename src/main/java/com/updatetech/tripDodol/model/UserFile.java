package com.updatetech.tripDodol.model;


import javax.persistence.*;

@Entity
@Table(name = "user_file")
public class UserFile {

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
    @JoinColumn(name = "user_id")
    private User user;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
