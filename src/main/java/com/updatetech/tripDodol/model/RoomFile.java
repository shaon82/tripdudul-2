package com.updatetech.tripDodol.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RoomFile implements Serializable {

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
    @JoinColumn(name = "room_id")
    private Room room;


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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
