package com.updatetech.tripDodol.service;

import java.io.File;

public interface UploadPathService {

    File getFilePath(String modifiedFilename, String path);
}
