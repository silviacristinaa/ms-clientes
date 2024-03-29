package com.github.silviacristinaa.msclientes.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class ErrorMessage implements Serializable{

    private static final long serialVersionUID = 1L;

    private String message;

    @Builder.Default
    private List<String> errors = Collections.synchronizedList(new ArrayList<>());

    public void addError(final String error) {
        errors.add(error);
    }
}
