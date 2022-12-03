package com.updatetech.tripDodol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;


@Service
public class UploadPathServiceImpl implements UploadPathService{


    @Autowired
    ServletContext context;


    @Override
    public File getFilePath(String modifiedFilename, String path) {

        boolean exists= new File(context.getRealPath("/"+path+"/")).exists();
        if (!exists){
            new File(context.getRealPath("/"+path+"/")).mkdir();
        }
        String modifiedFilePath = context.getRealPath("/"+path+"/"+File.separator+modifiedFilename);
        File file = new File(modifiedFilePath);
        return file;
    }
}
