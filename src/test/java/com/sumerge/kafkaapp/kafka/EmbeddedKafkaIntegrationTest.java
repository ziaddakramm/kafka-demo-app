//package com.sumerge.kafkaapp.kafka;
//
//
//import com.sumerge.kafkaapp.KafkaAppApplication;
//import com.sumerge.kafkaapp.common.enums.OperationType;
//import com.sumerge.kafkaapp.employee.control.EmployeeControl;
//import com.sumerge.kafkaapp.employee.entity.Employee;
//import com.sumerge.kafkaapp.producer.boundary.EventProducer;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringRunner.class)
//@Import(com.baeldung.kafka.testcontainers.KafkaTestContainersLiveTest.KafkaTestContainersConfiguration.class)
//@SpringBootTest(classes = KafkaAppApplication.class)
//@DirtiesContext
//@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9093", "port=9093" })
//
//public class EmbeddedKafkaIntegrationTest {
//
//    @MockBean
////    private KafkaConsumer consumer;
//    private EmployeeControl service;
//
//    @Autowired
//    private EventProducer producer;
//
//    @Autowired
//    private EmployeeControl employeeControl;
//
//    @Value("${test.topic}")
//    private String topic;
//
//    Employee testEmployee1=new Employee(
//
//            "ziad",
//            "akram",
//            "za@g.com"
//    );
//
//    @Test
//    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
//            throws Exception {
//
//        testEmployee1.setId(1);
//        producer.send(OperationType.CREATE,"user", testEmployee1.getId(), testEmployee1);
//
//
//
//
//    }
//}