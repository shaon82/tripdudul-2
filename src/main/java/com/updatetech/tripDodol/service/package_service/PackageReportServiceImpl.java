package com.updatetech.tripDodol.service.package_service;


import com.updatetech.tripDodol.model.packageModel.PackageBooking;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PackageReportServiceImpl implements PackageReportService {


    @Override
    public void createPackageBookingReport(List<PackageBooking> packageBookingList, String packageName) {
            String filePath = "/home/utech/updateTechProject/tripdodul-app/reports/package-report/packagebooking";

            try{
                JasperReport mainReport = JasperCompileManager.compileReport(filePath+"/"+"packagebookingreport.jrxml");
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(packageBookingList);
                Map<String, Object>parameter = new HashMap<>();
                parameter.put("packageName", packageName);

                JasperPrint jasperPrint = JasperFillManager.fillReport(mainReport, parameter, dataSource);
                JasperExportManager.exportReportToPdfFile(jasperPrint,filePath+"/"+"test.pdf");
                System.out.println("file is created............");
            }catch (JRException e){
                e.printStackTrace();
            }
            
    }
}
