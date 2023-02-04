package com.hackaton.project.controllers;

import com.hackaton.project.dtos.BusinessDTO;
import com.hackaton.project.dtos.BusinessLoginDTO;
import com.hackaton.project.dtos.BusinessResponseDTO;
import com.hackaton.project.dtos.UserAuthDTO;
import com.hackaton.project.entities.Business;
import com.hackaton.project.repositories.BusinessRepository;
import com.hackaton.project.services.BusinessService;
import com.hackaton.project.utils.JwtUtilImpl;
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

    @Autowired
    JwtUtilImpl jwtUtil;
    @GetMapping
    public List<BusinessDTO> getAll() {
        return Arrays.stream(businessService.getAll()).map(BusinessDTO::mapToDTO).toList();
    }

    @PostMapping("/register")
    public BusinessResponseDTO submitBusiness(@Valid @RequestBody Business business) {
        Business submittedBusiness = businessService.submitBusiness(business);
        UserAuthDTO userAuthDTO = BusinessDTO.mapToDTO(submittedBusiness);
        String token = jwtUtil.encode(userAuthDTO);

        return new BusinessResponseDTO(token, userAuthDTO);
    }
    @PostMapping("/login")
    public BusinessResponseDTO loginBusiness(@Valid @RequestBody BusinessLoginDTO businessLoginDTO) {
        Business loggedBusiness = businessService.loginBusiness(businessLoginDTO);
        UserAuthDTO userAuthDTO = BusinessDTO.mapToDTO(loggedBusiness);
        String token = jwtUtil.encode(userAuthDTO);

        return new BusinessResponseDTO(token, userAuthDTO);
    }
}
