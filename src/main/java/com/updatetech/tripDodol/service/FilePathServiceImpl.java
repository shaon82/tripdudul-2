package com.updatetech.tripDodol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.io.File;

@Service
@Transactional
public class FilePathServiceImpl implements FilePathService{

    @Autowired
    ServletContext context;

    @Override
    public File getFilePath(String modifiedFileName, String path) {
        boolean exists = new File(context.getRealPath("/"+path+"/")).exists();
        if (!exists){
            new File(context.getRealPath("/"+path+"/")).mkdir();
        }
        String modifiedFilePath = context.getRealPath("/"+path+"/"+File.separator+modifiedFileName);
        File file = new File(modifiedFilePath);
        return file;
    }




    @Override
    public File getRoomFilePath(String modifiedFileName, String roomPath) {
        boolean exists = new File(context.getRealPath("/"+roomPath+"/")).exists();
        if (!exists){
            new File(context.getRealPath("/"+roomPath+"/")).mkdir();
        }
        String modifiedFilePath = context.getRealPath("/"+roomPath+"/"+File.separator+modifiedFileName);
        File file = new File(modifiedFilePath);
        return file;
    }

    @Override
    public File getManagerFilePath(String modifiedFileName, String managerFilePath) {
        boolean exists = new File(context.getRealPath("/"+managerFilePath+"/")).exists();
        if (!exists){
            new File(context.getRealPath("/"+managerFilePath+"/")).mkdir();
        }
        String modifiedFilePath = context.getRealPath("/"+managerFilePath+"/"+File.separator+modifiedFileName);
        File file = new File(modifiedFilePath);
        return file;
    }
}
