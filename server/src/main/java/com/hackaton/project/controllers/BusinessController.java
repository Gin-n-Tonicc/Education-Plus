package com.hackaton.project.controllers;

import com.hackaton.project.dtos.business.BusinessDTO;
import com.hackaton.project.dtos.business.BusinessLoginDTO;
import com.hackaton.project.dtos.business.BusinessResponseDTO;
import com.hackaton.project.entities.Business;
import com.hackaton.project.exceptions.common.EntityNotFoundException;
import com.hackaton.project.services.BusinessService;
import com.hackaton.project.utils.JwtUtilImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public BusinessDTO getById(@PathVariable("id") Long id) throws EntityNotFoundException {
        EntityNotFoundException exception = new EntityNotFoundException(Business.class.getSimpleName());

        if (Objects.isNull(id)) {
            throw exception;
        }

        Optional<Business> business = businessService.getById(id);

        if (business.isEmpty()) {
            throw exception;
        }

        return BusinessDTO.mapToDTO(business.get());
    }

    @PostMapping("/register")
    public BusinessResponseDTO submitBusiness(@Valid @RequestBody Business business) {
        Business submittedBusiness = businessService.submitBusiness(business);
        BusinessDTO businessDTO = BusinessDTO.mapToDTO(submittedBusiness);
        String token = jwtUtil.encode(businessDTO);

        return new BusinessResponseDTO(token, businessDTO);
    }
    @PostMapping("/login")
    public BusinessResponseDTO loginBusiness(@Valid @RequestBody BusinessLoginDTO businessLoginDTO) {
        Business loggedBusiness = businessService.loginBusiness(businessLoginDTO);
        BusinessDTO businessDTO = BusinessDTO.mapToDTO(loggedBusiness);
        String token = jwtUtil.encode(businessDTO);

        return new BusinessResponseDTO(token, businessDTO);
    }
    @PutMapping("/update/{id}")
    public BusinessDTO updateStudent(@PathVariable("id") Long businessId, @RequestBody @Valid Business business, HttpServletRequest request) {
        return BusinessDTO.mapToDTO(businessService.updateBusiness(businessId, business, request));
    }
}
