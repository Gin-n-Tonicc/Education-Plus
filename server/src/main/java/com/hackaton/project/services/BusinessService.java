package com.hackaton.project.services;

import com.hackaton.project.dtos.BusinessLoginDTO;
import com.hackaton.project.entities.Business;
import com.hackaton.project.enums.Role;
import com.hackaton.project.exceptions.BusinessExistsException;
import com.hackaton.project.exceptions.InvalidBusinessDataException;
import com.hackaton.project.repositories.BusinessRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class BusinessService {
    @Autowired
    BusinessRepository businessRepository;
    public Business submitBusiness(@Valid @RequestBody Business business) {
        Optional<Business> optionalBusiness = businessRepository.findOneByEmail(business.getEmail());
        business.setRole(Role.USER);
        business.setVerified(false);

        if (optionalBusiness.isPresent()) {
            throw new BusinessExistsException("email");
        }

        return businessRepository.save(business);
    }
    public Business[] getAll() {
        return businessRepository.getAll();
    }
    public Business loginBusiness(@Valid @RequestBody BusinessLoginDTO businessLoginDTO) {
        Optional<Business> optionalBusiness = businessRepository.findOneByEmail(businessLoginDTO.getEmail());

        if (optionalBusiness.isEmpty()) {
            throw new InvalidBusinessDataException();
        }

        Business business = optionalBusiness.get();
        if (!business.getPassword().equals(businessLoginDTO.getPassword())) {
            throw new InvalidBusinessDataException();
        }
        return business;
    }
}
