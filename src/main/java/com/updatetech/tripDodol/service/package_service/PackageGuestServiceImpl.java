package com.updatetech.tripDodol.service.package_service;


import com.updatetech.tripDodol.model.packageModel.PackageGuest;
import com.updatetech.tripDodol.repository.packageReposiroty.PackageGuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageGuestServiceImpl implements PackageGuestService{

    @Autowired
    private PackageGuestRepository packageGuestRepository;

    @Override
    public PackageGuest save(PackageGuest guest) {
        return packageGuestRepository.save(guest);
    }
}
