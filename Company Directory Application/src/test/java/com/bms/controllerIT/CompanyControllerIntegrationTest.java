package com.bms.controllerIT;

import com.bms.entity.Building;
import com.bms.entity.Company;
import com.bms.entity.Employee;
import com.bms.repository.BuildingRepository;
import com.bms.repository.CompanyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CompanyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    private Building building;
    private Company company;
    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        building = new Building();
        building.setName("Test Building");
        building.setAddress("123 Test Street");
        building.setOutOfOrder(false);
        building = buildingRepository.save(building);

        company = new Company();
        company.setName("Test Company");
        company.setBuilding(building);
        company.setNumEmp(10);
        company = companyRepository.save(company);

        employees = Arrays.asList(
                new Employee(null, "Employee 1", company),
                new Employee(null, "Employee 2", company),
                new Employee(null, "Employee 3", company)
        );
    }

    @Test
    void testGetAllCompanies() throws Exception {
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", equalTo(company.getId().intValue())))
                .andExpect(jsonPath("$[0].name", equalTo(company.getName())))
                .andExpect(jsonPath("$[0].building.id", equalTo(building.getId().intValue())))
                .andExpect(jsonPath("$[0].building.name", equalTo(building.getName())))
                .andExpect(jsonPath("$[0].building.address", equalTo(building.getAddress())))
                .andExpect(jsonPath("$[0].building.outOfOrder", equalTo(building.isOutOfOrder())))
                .andExpect(jsonPath("$[0].numEmp", equalTo(company.getNumEmp())));
    }

    @Test
    void testGetCompanyById() throws Exception {
        mockMvc.perform(get("/companies/{id}", company.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(company.getId().intValue())))
                .andExpect(jsonPath("$.name", equalTo(company.getName())))
                .andExpect(jsonPath("$.building.id", equalTo(building.getId().intValue())))
                .andExpect(jsonPath("$.building.name", equalTo(building.getName())))
                .andExpect(jsonPath("$.building.address", equalTo(building.getAddress())))
                .andExpect(jsonPath("$.building.outOfOrder", equalTo(building.isOutOfOrder())))
                .andExpect(jsonPath("$.numEmp", equalTo(company.getNumEmp())));
    }


}