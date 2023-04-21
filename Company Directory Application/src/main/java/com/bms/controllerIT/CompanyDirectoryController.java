package com.bms.controllerIT;

import com.bms.entity.Building;
import com.bms.entity.Company;
import com.bms.exception.ResourceNotFoundException;
import com.bms.repository.BuildingRepository;
import com.bms.repository.CompanyRepository;
import com.bms.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyDirectoryController {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/buildings")
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @GetMapping("/buildings/{id}")
    public ResponseEntity<Building> getBuildingById(@PathVariable(value = "id") Long buildingId)
            throws ResourceNotFoundException {
        Building building =
                buildingRepository
                        .findById(buildingId)
                        .orElseThrow(() -> new ResourceNotFoundException("Building not found for this id :: " + buildingId));
        return ResponseEntity.ok().body(building);
    }

    @PostMapping("/buildings")
    public Building createBuilding(@Valid @RequestBody Building building) {
        return buildingRepository.save(building);
    }

    @PutMapping("/buildings/{id}")
    public ResponseEntity<Building> updateBuilding(
            @PathVariable(value = "id") Long buildingId, @Valid @RequestBody Building buildingDetails)
            throws ResourceNotFoundException {
        Building building =
                buildingRepository
                        .findById(buildingId)
                        .orElseThrow(() -> new ResourceNotFoundException("Building not found for this id :: " + buildingId));

        building.setName(buildingDetails.getName());
        building.setAddress(buildingDetails.getAddress());
        building.setOutOfOrder(buildingDetails.isOutOfOrder());
        final Building updatedBuilding = buildingRepository.save(building);
        return ResponseEntity.ok(updatedBuilding);
    }

    @DeleteMapping("/buildings/{id}")
    public Map<String, Boolean> deleteBuilding(@PathVariable(value = "id") Long buildingId)
            throws ResourceNotFoundException {
        Building building =
                buildingRepository
                        .findById(buildingId)
                        .orElseThrow(() -> new ResourceNotFoundException("Building not found for this id :: " + buildingId));

        buildingRepository.delete(building);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") Long companyId)
            throws ResourceNotFoundException {
        Company company =
                companyRepository
                        .findById(companyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));
        return ResponseEntity.ok().body(company);
    }

    @PostMapping("/companies")
    public Company createCompany(@Valid @RequestBody Company company) {
        return companyRepository.save(company);
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(
            @PathVariable(value = "id") Long companyId, @Valid @RequestBody Company companyDetails)
            throws ResourceNotFoundException {
        Company company =
                companyRepository
                        .findById(companyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not found for this id :: " + companyId));

        company.setName(companyDetails.getName());
        company.setBuilding(companyDetails.getBuilding());
        company.setNumEmp(companyDetails.getNumEmp());
        final Company updatedCompany = companyRepository.save(company);
        return ResponseEntity.ok(updatedCompany);
    }

}