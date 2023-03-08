package com.github.silviacristinaa.msclientes.application;

import com.github.silviacristinaa.msclientes.application.representation.CustomerResponse;
import com.github.silviacristinaa.msclientes.application.representation.CustomerSaveRequest;
import com.github.silviacristinaa.msclientes.domain.Customer;
import com.github.silviacristinaa.msclientes.exceptions.ConflictException;
import com.github.silviacristinaa.msclientes.exceptions.NotFoundException;
import com.github.silviacristinaa.msclientes.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Customer save(CustomerSaveRequest request) throws ConflictException {
        findByCpf(request);
        Customer customer = modelMapper.map(request, Customer.class);
        return customerRepository.save(customer);
    }

    public CustomerResponse getByCPF(String cpf) throws NotFoundException {
        Customer customer = findByCpf(cpf);
        return modelMapper.map(customer, CustomerResponse.class);
    }

    private void findByCpf(CustomerSaveRequest request) throws ConflictException {
        Optional<Customer> customer = customerRepository.findByCpf(request.getCpf());
        if(customer.isPresent()) {
            throw new ConflictException("Cpf already registered in the system");
        }
    }

    private Customer findByCpf(String cpf) throws NotFoundException {
        return customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException(String.format("Cpf %s not found", cpf)));
    }
}
