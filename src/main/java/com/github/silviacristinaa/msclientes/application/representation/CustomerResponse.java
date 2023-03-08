package com.github.silviacristinaa.msclientes.application.representation;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerResponse {

    private Long id;
    private String cpf;
    private String name;
    private Integer age;
}
