package com.hackaton.project.services;

import com.hackaton.project.dtos.business.BusinessLoginDTO;
import com.hackaton.project.entities.Business;
import com.hackaton.project.entities.Student;
import com.hackaton.project.enums.Role;
import com.hackaton.project.exceptions.business.BusinessDoesNotExistsException;
import com.hackaton.project.exceptions.business.BusinessExistsException;
import com.hackaton.project.exceptions.business.InvalidBusinessDataException;
import com.hackaton.project.exceptions.common.InsufficientPermissionsException;
import com.hackaton.project.exceptions.student.StudentDoesNotExistsException;
import com.hackaton.project.repositories.BusinessRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {
    @Autowired
    BusinessRepository businessRepository;

    public Optional<Business> getById(Long id) {
        return businessRepository.findById(id);
    }

    public Business[] getBySearch(String search) {
        return businessRepository.findBySearch(search);
    }


    public Business submitBusiness(@Valid @RequestBody Business business) {

        Optional<Business> optionalBusiness = businessRepository.findOneByEmail(business.getEmail());
        business.setRole(Role.BUSINESS);
        business.setVerified(false);

        if (optionalBusiness.isPresent()) {
            throw new BusinessExistsException("email");
        }

        return businessRepository.save(business);
    }
    public Business[] getAll() {
        return businessRepository.getAll();
    }
    public Business loginBusiness(BusinessLoginDTO businessLoginDTO) {
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
    public Business updateBusiness(Long businessId, Business business, HttpServletRequest request) {
        Optional<Business> optionalBusiness = businessRepository.findById(businessId);
        if (optionalBusiness.isEmpty()) {
            throw new BusinessDoesNotExistsException();
        }
        if (!(businessId.equals(optionalBusiness.get().getId()))){
            throw new InsufficientPermissionsException();
        }
        List<Business> businessList = (List<Business>) businessRepository.findAll();
        for (int i = 0; i < businessList.size(); i++) {
            Business b = businessList.get(i);
            if (b.getId().equals(businessId)) {
                businessList.set(i, business);
            }
        }
        business.setId(optionalBusiness.get().getId());
        business.setRole(optionalBusiness.get().getRole());
        return business;
    }
}
