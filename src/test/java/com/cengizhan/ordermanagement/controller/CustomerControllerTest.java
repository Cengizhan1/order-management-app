package com.cengizhan.ordermanagement.controller;

import com.cengizhan.ordermanagement.IntegrationTestSupport;
import com.cengizhan.ordermanagement.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class CustomerControllerTest extends IntegrationTestSupport {


    @Test
    void testCustomerCreate_shouldReturnCustomerDto() throws Exception {
        Customer customer = customerRepository.save(new Customer());
    }

    @Test
    void customerList() {
    }

    @Test
    void customerFindById() {
    }

    @Test
    void customerUpdate() {
    }

    @Test
    void customerDeleteById() {
    }

    @Test
    void customerAllDelete() {
    }

    @Test
    void getCustomersByNameContains() {
    }

    @Test
    void getCustomersWithoutOrders() {
    }
}