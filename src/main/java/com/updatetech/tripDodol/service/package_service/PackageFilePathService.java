package com.updatetech.tripDodol.service.package_service;

import java.io.File;

public interface PackageFilePathService {
    File getFilePath(String modifiedFileName, String packageImage);
}
