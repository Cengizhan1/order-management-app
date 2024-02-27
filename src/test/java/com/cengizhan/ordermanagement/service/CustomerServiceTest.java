package com.cengizhan.ordermanagement.service;

import com.cengizhan.ordermanagement.dto.CustomerDto;
import com.cengizhan.ordermanagement.dto.request.CustomerCreateRequest;
import com.cengizhan.ordermanagement.dto.request.CustomerUpdateRequest;
import com.cengizhan.ordermanagement.entity.Customer;
import com.cengizhan.ordermanagement.exception.CustomerNotFoundException;
import com.cengizhan.ordermanagement.repository.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private ICustomerRepository iCustomerRepository;
    private CustomerService service;



    @BeforeEach
    void setUp() {
        iCustomerRepository = mock(ICustomerRepository.class);
        service = new CustomerService(iCustomerRepository);
    }

    @Test
    void testCustomerCreate_ShouldReturnCustomerDto() {
        CustomerCreateRequest createRequest = new CustomerCreateRequest("Cengizhan", 30);
        Customer savedCustomer =  Customer.builder().name(createRequest.name()).age(createRequest.age()).build();
        CustomerDto expectedDto = CustomerDto.convert(savedCustomer);

        when(iCustomerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDto actualDto = service.customerCreate(createRequest);

        assertEquals(expectedDto, actualDto);
        verify(iCustomerRepository).save(any(Customer.class));
    }

    @Test
    void testCustomerList_ShouldReturnCustomerDtoList() {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().name("Cengizhan").age(30).build());
        customers.add(Customer.builder().name("Jane Doe").age(35).build());
        
        when(iCustomerRepository.findAll()).thenReturn(customers);
        
        List<CustomerDto> actualList = service.customerList();
        
        assertEquals(2, actualList.size());

        verify(iCustomerRepository).findAll();
    }
      

    @Test
    void testCustomerFindById_whenCustomerExists_ShouldReturnCustomerDto() {
        Customer customer = Customer.builder().name("Cengizhan").age(30).build();
        CustomerDto expectedDto = CustomerDto.convert(customer);

        when(iCustomerRepository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerDto actualDto = service.customerFindById(1L);

        assertEquals(expectedDto, actualDto);

        verify(iCustomerRepository).findById(1L);
    }

    @Test
    void testCustomerFindById_whenCustomerDoesNotExists_ShouldReturnCustomerNotFoundException() {
        when(iCustomerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,() -> service.customerFindById(1L));
    }



    @Test
    void testCustomerUpdate_whenCustomerExists_ShouldReturnCustomerDto() {
        Customer customer = Customer.builder().name("Cengizhan").age(30).build();
        CustomerDto expectedDto = CustomerDto.convert(customer);

        when(iCustomerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(iCustomerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDto actualDto = service.customerUpdate(1L,
                new CustomerUpdateRequest("Cengizhan", 30));

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testCustomerUpdate_whenCustomerDoesNotExists_ShouldReturnCustomerNotFoundException() {
        when(iCustomerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,() ->
                service.customerUpdate(1L, new CustomerUpdateRequest("Cengizhan", 30)));
    }

    @Test
    void testCustomerDeleteById_whenCustomerExists_ShouldDeleteCustomer() {
        Customer customer = Customer.builder().name("Cengizhan").age(30).build();

        when(iCustomerRepository.findById(1L)).thenReturn(Optional.of(customer));

        service.customerDeleteById(1L);

        verify(iCustomerRepository).deleteById(1L);
    }

    @Test
    void testCustomerDeleteById_whenCustomerDoesNotExists_ShouldDeleteCustomerNotFoundException() {
        when(iCustomerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,() -> service.customerDeleteById(1L));
    }

    @Test
    void testCustomerDeleteAll_ShouldDeleteAllCustomers() {
        service.customerDeleteAll();

        verify(iCustomerRepository).deleteAll();
    }

    @Test
    void testGetCustomersByNameContaining_ShouldReturnCustomerDtoList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().name("Cengizhan Yavuz").age(30).build());
        customers.add(Customer.builder().name("Cengizhan").age(35).build());

        when(iCustomerRepository.findAllByNameContainingIgnoreCase("Cengizhan")).thenReturn(customers);

        List<CustomerDto> actualList = service.getCustomersByNameContaining("Cengizhan");

        assertEquals(2, actualList.size());
    }

    @Test
    void testGetCustomersWithoutOrders_ShouldReturnCustomerDtoList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().name("Cengizhan Yavuz").age(30).build());
        customers.add(Customer.builder().name("Cengizhan").age(35).build());

        when(iCustomerRepository.findAllByRelationOrderListEmpty()).thenReturn(customers);

        List<CustomerDto> actualList = service.getCustomersWithoutOrders();

        assertEquals(2, actualList.size());
    }

    private Customer genereateCustomer(CustomerCreateRequest customerCreateRequest){
        Customer customer = new Customer();
        customer.setName(customerCreateRequest.name());
        customer.setAge(customerCreateRequest.age());
        return customer;
    }
}