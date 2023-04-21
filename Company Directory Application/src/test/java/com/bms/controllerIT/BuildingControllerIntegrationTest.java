package com.bms.controllerIT;

import com.bms.entity.Building;
import com.bms.repository.BuildingRepository;
import com.bms.repository.CompanyRepository;
import com.bms.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BuildingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void beforeEach() {
        buildingRepository.deleteAll();
        companyRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testCreateBuilding() throws Exception {
        Building building = new Building();
        building.setName("Building 1");
        building.setAddress("Address 1");
        building.setOutOfOrder(false);

        mockMvc.perform(post("/buildings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(building)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(building.getName()))
                .andExpect(jsonPath("$.address").value(building.getAddress()))
                .andExpect(jsonPath("$.outOfOrder").value(building.isOutOfOrder()));
    }

    @Test
    @Order(2)
    public void testGetBuildingById() throws Exception {
        Building building = new Building();
        building.setName("Building 1");
        building.setAddress("Address 1");
        building.setOutOfOrder(false);
        buildingRepository.save(building);

        mockMvc.perform(get("/buildings/{id}", building.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(building.getId()))
                .andExpect(jsonPath("$.name").value(building.getName()))
                .andExpect(jsonPath("$.address").value(building.getAddress()))
                .andExpect(jsonPath("$.outOfOrder").value(building.isOutOfOrder()));
    }

    @Test
    @Order(3)
    public void testUpdateBuilding() throws Exception {
        Building building = new Building();
        building.setName("Building 1");
        building.setAddress("Address 1");
        building.setOutOfOrder(false);
        buildingRepository.save(building);

        building.setName("Building 2");

        mockMvc.perform(put("/buildings/{id}", building.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(building)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(building.getId()))
                .andExpect(jsonPath("$.name").value(building.getName()))
                .andExpect(jsonPath("$.address").value(building.getAddress()))
                .andExpect(jsonPath("$.outOfOrder").value(building.isOutOfOrder()));
    }

    @Test
    @Order(4)
    public void testDeleteBuilding() throws Exception {
        Building building = new Building();
        building.setName("Building 1");
        building.setAddress("Address 1");
        building.setOutOfOrder(false);
        buildingRepository.save(building);

        mockMvc.perform(delete("/buildings/{id}", building.getId()))
                .andExpect(status().isOk());

        assertFalse(buildingRepository.findById(building.getId()).isPresent());
    }
}