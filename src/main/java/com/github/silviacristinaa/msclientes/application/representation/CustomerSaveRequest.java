package com.github.silviacristinaa.msclientes.application.representation;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CustomerSaveRequest {

    @NotBlank
    private String cpf;
    @NotBlank
    private String name;
    @NotNull
    private Integer age;
}
