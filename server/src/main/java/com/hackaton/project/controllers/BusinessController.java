package com.hackaton.project.controllers;

import com.hackaton.project.dtos.BusinessDTO;
import com.hackaton.project.dtos.BusinessLoginDTO;
import com.hackaton.project.entities.Business;
import com.hackaton.project.services.BusinessService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessController {
    @Autowired
    BusinessService businessService;
    @GetMapping
    public List<BusinessDTO> getAll() {
        return Arrays.stream(businessService.getAll()).map(BusinessDTO::mapToDTO).toList();
    }

    @PostMapping("/register")
    public BusinessDTO submitBusiness(@Valid @RequestBody Business business) {
        Business submittedBusiness = businessService.submitBusiness(business);
        return BusinessDTO.mapToDTO(submittedBusiness);
    }
    @PostMapping("/login")
    public BusinessDTO loginBusiness(@Valid @RequestBody BusinessLoginDTO businessLoginDTO) {
        Business loggedBusiness = businessService.loginBusiness(businessLoginDTO);
        return BusinessDTO.mapToDTO(loggedBusiness);
    }
}
