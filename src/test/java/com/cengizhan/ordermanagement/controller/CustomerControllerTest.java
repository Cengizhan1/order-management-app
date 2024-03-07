package com.cengizhan.ordermanagement.controller;

import com.cengizhan.ordermanagement.IntegrationTestSupport;
import com.cengizhan.ordermanagement.dto.CustomerDto;
import com.cengizhan.ordermanagement.dto.request.CustomerCreateRequest;
import com.cengizhan.ordermanagement.dto.request.CustomerUpdateRequest;
import com.cengizhan.ordermanagement.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest extends IntegrationTestSupport {


    @Test
    void testCustomerCreate_shouldReturnCustomerDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest(
                customer.getName(),
                customer.getAge()
        );

        this.mockMvc.perform(post(CUSTOMER_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(customerCreateRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is(customer.getName())))
                .andExpect(jsonPath("$.age", is(customer.getAge())));

    }

    @Test
    void testCustomerList_shouldReturnCustomerDtoList() throws Exception {
        List<CustomerDto> customerDtoList = customerRepository.findAll()
                .stream()
                .map(CustomerDto::convert)
                .toList();

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter()
                        .writeValueAsString(customerDtoList), false))
                .andReturn();
    }

    @Test
    void testCustomerFindById_whenIdExists_shouldReturnCustomerDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + "/" + customer.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is(customer.getName())))
                .andExpect(jsonPath("$.age", is(customer.getAge())));
    }

    @Test
    void testCustomerFindById_whenIdDoesNotExists_shouldReturn404NotFound() throws Exception {
        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + "/" + 514151L))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void testCustomerUpdate_whenIdExists_shouldReturnCustomerDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest(
                customer.getName(),
                customer.getAge()
        );

        this.mockMvc.perform(put(CUSTOMER_API_ENDPOINT + "/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(customerUpdateRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is(customer.getName())))
                .andExpect(jsonPath("$.age", is(customer.getAge())));
    }

    @Test
    void testCustomerUpdate_whenIdDoesNotExists_shouldReturn404NotFound() throws Exception {
        this.mockMvc.perform(put(CUSTOMER_API_ENDPOINT + "/" + 514151L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CustomerUpdateRequest("name", 20))))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void testCustomerDeleteById_whenIdExists_shouldReturn204NoContent() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());

        this.mockMvc.perform(delete(CUSTOMER_API_ENDPOINT + "/" + customer.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void testCustomerDeleteById_whenIdDoesNotExists_shouldReturn404NotFound() throws Exception {
        this.mockMvc.perform(delete(CUSTOMER_API_ENDPOINT + "/" + 514151L))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void testCustomerAllDelete_shouldReturnAllDeleted() throws Exception {
        this.mockMvc.perform(post(CUSTOMER_API_ENDPOINT + "/all/delete"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void testGetCustomersByNameContains_whenNameExists_shouldReturnCustomerDtoList() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        List<CustomerDto> expected = customerRepository.findAllByNameContainingIgnoreCase(customer.getName())
                .stream().map(CustomerDto::convert).toList();

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + "/getByNameContains/" + customer.getName()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter()
                        .writeValueAsString(expected), false))
                .andReturn();
    }

    @Test
    void testGetCustomersWithoutOrders_shouldReturnCustomerDtoList() throws Exception {
        List<CustomerDto> expected = customerRepository.findAllByRelationOrderListEmpty()
                .stream().map(CustomerDto::convert).toList();

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + "/getCustomersWithoutOrders"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter()
                        .writeValueAsString(expected), false))
                .andReturn();
    }
}