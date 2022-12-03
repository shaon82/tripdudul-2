package com.updatetech.tripDodol.service.package_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;

@Service
public class PackageFilePathServiceImpl implements PackageFilePathService{

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
}
