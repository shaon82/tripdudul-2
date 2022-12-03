package com.updatetech.tripDodol.service;

import java.io.File;

public interface FilePathService {

    File getFilePath(String modifiedFileName, String path);

    File getRoomFilePath(String modifiedFileName, String roomPath);

    File getManagerFilePath(String modifiedFileName, String managerFilePath);
}
