package com.sumerge.kafkaapp.boundary;

import com.sumerge.kafkaapp.common.enums.OperationType;
import com.sumerge.kafkaapp.employee.boundary.EmployeeResource;
import com.sumerge.kafkaapp.employee.entity.Employee;
import com.sumerge.kafkaapp.producer.EmployeeProducer;
import com.sumerge.kafkaapp.employee.control.EmployeeControl;
import com.sumerge.kafkaapp.employee.repository.EmployeeRepository;
import com.sumerge.kafkaapp.producer.boundary.EventProducer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeResource.class)
public class EmployeeResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeControl employeeControl;

    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeProducer employeeProducer;

    @MockBean
    private EventProducer eventProducer;
    private AutoCloseable autoCloseable;
    Employee testEmployee1=new Employee(
           "ziad",
        "akram",
            "za@g.com"
    );

    Employee testEmployee2=new Employee(
            "sherif",
            "moataz",
            "sm@g.com"
    );
    List<Employee> testList;
    @Before
    public void setUp() {
        testEmployee1.setId(0);
        testEmployee2.setId(1);
         testList= Arrays.asList(testEmployee1, testEmployee2);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void retrieveAllTest() throws Exception
    {
        Mockito.when(employeeRepository.findAll()).thenReturn(testList);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/employees/retrieve")
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath( "$").isNotEmpty())
                .andExpect(jsonPath("$[0]").value(testList.get(0)))
                .andExpect(jsonPath("$[1]").value(testList.get(1)));
    }

    @Test
    public void retrieveByIdTest() throws Exception {




        Mockito.when(employeeRepository.findById(0)).thenReturn(Optional.of(testEmployee1));

        Mockito.when(employeeControl.checkFindById(Optional.of(testEmployee1),0)).thenReturn(testEmployee1);


        mockMvc.perform(
                        MockMvcRequestBuilders.get("/employees/retrieve/0")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(testEmployee1));
    }



    @Test
    public void saveTest() throws  Exception
    {

        Mockito.doNothing().when(employeeControl).setEmployeeId(testEmployee1);
        Mockito.doNothing().when(employeeControl).fieldsValidator(testEmployee1);
        Mockito.doNothing().when(eventProducer).send(OperationType.CREATE,"user", testEmployee1.getId(), testEmployee1);

        MvcResult result=mockMvc.perform(
                        MockMvcRequestBuilders.post("/employees/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"firstName\":\"ziad\",\"lastName\":\"akram\",\"email\":\"za@g.com\"}")
                )
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertEquals("employee successfully saved", responseContent);

        Mockito.verify(employeeControl, Mockito.times(1)).setEmployeeId(testEmployee1);
        Mockito.verify(eventProducer, Mockito.times(1)).send(OperationType.CREATE,"user", testEmployee1.getId(), testEmployee1);

    }




    @Test
    public void updateEmployeeTest() throws Exception {


        Optional<Employee> mockResult = Optional.of(testEmployee1);

        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(mockResult);
        Mockito.when(employeeControl.checkFindById(mockResult,0)).thenReturn(testEmployee1);
        Mockito.doNothing().when(eventProducer).send(OperationType.UPDATE,"user", testEmployee1.getId(), testEmployee1);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.put("/employees/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":0,\"firstName\":\"ziad\",\"lastName\":\"akram\",\"email\":\"za@g.com\"}")
                )
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertEquals("employee successfully updated", responseContent);

        Mockito.verify(employeeRepository, Mockito.times(1)).findById(Mockito.anyInt());
        Mockito.verify(employeeControl, Mockito.times(1)).checkFindById(mockResult,0);
        Mockito.verify(eventProducer, Mockito.times(1)).send(OperationType.UPDATE,"user", testEmployee1.getId(), testEmployee1);;
    }



}