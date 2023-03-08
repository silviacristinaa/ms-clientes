package com.github.silviacristinaa.msclientes.application;

import com.github.silviacristinaa.msclientes.application.representation.CustomerResponse;
import com.github.silviacristinaa.msclientes.application.representation.CustomerSaveRequest;
import com.github.silviacristinaa.msclientes.exceptions.ConflictException;
import com.github.silviacristinaa.msclientes.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerResource {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid CustomerSaveRequest request) throws ConflictException {
        customerService.save(request);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(request.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<CustomerResponse> customerData(@RequestParam("cpf") String cpf) throws NotFoundException {
        return ResponseEntity.ok(customerService.getByCPF(cpf));
    }
}
