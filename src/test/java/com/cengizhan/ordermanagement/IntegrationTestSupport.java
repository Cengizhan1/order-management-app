package com.cengizhan.ordermanagement;

import com.cengizhan.ordermanagement.repository.ICustomerRepository;
import com.cengizhan.ordermanagement.repository.IOrderRepository;
import com.cengizhan.ordermanagement.service.CustomerService;
import com.cengizhan.ordermanagement.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
@DirtiesContext
@AutoConfigureMockMvc
public abstract class IntegrationTestSupport extends TestSupport{

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ICustomerRepository customerRepository;

    @Autowired
    public IOrderRepository orderRepository;

    @Autowired
    public CustomerService customerService;

    @Autowired
    public OrderService orderService;

    public final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}