package com.bms.controllerIT;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EmployeeControllerIntegrationTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private BuildingRepository buildingRepository;
//
//    @Autowired
//    private CompanyRepository companyRepository;
//
//    private Employee employee1;
//    private Employee employee2;
//
//    @BeforeEach
//    public void setUp() {
//        // Create test employees
//        Company company = new Company();
//        company.setName("ABC Company");
//        company.setNumEmp(100);
//        company.setBuilding(buildingRepository.findById(1L).orElse(null));
//        companyRepository.save(company);
//
//        Employee employee = new Employee();
//        employee.setName("John Doe");
//        employee.setCompany(company);
//        employeeRepository.save(employee);
//
//        Company company1 = new Company();
//        company1.setName("ABC Company");
//        company1.setNumEmp(100);
//        company1.setBuilding(buildingRepository.findById(1L).orElse(null));
//        companyRepository.save(company1);
//
//        Employee employee1 = new Employee();
//        employee1.setName("John Doe");
//        employee1.setCompany(company1);
//        employeeRepository.save(employee1);
//
//
//        // Save employees to database
//        employeeRepository.saveAll(Arrays.asList(employee1, employee2));
//    }
//
//    @Test
//    public void testGetAllEmployees() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(employee1.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is(employee2.getName())));
//    }
//
//    @Test
//    public void testGetEmployeeById() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee1.getId()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(employee1.getName())));
//    }
//
//    @Test
//    public void testCreateEmployee() throws Exception {
//        Company company = new Company();
//        company.setName("ABC Company");
//        company.setNumEmp(100);
//        company.setBuilding(buildingRepository.findById(1L).orElse(null));
//        companyRepository.save(company);
//
//        Employee employee = new Employee();
//        employee.setName("John Doe");
//        employee.setCompany(company);
//        employeeRepository.save(employee);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(employee)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(employee.getName())));
//
//        // Verify employee was saved to database
//        List<Employee> employees = employeeRepository.findAll();
//        assertThat(employees).hasSize(3);
//        assertThat(employees.get(2)).isEqualToIgnoringGivenFields(employees, "id");
//    }
//
//    @Test
//    public void testUpdateEmployee() throws Exception {
//        employee1.setName("John Smith");
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", employee1.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(employee1)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(employee1.getName())));
//
//        // Verify employee was updated in database
//        Employee updatedEmployee = employeeRepository.findById(employee1.getId()).orElse(null);
//        assertThat(updatedEmployee).isNotNull().isEqualToComparingFieldByField(employee1);
//    }
//
//    @Test
//    public void testDeleteEmployee() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", employee1.getId()))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//
//        // Verify employee was deleted from database
//        List<Employee> employees = employeeRepository.findAll();
//        assertThat(employees).hasSize(1).contains(employee2);
//    }
}
