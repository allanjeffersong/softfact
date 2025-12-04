package br.edu.uniesp.softfact.application.stack;

import jakarta.validation.constraints.NotBlank;

public record StackRequest(
        @NotBlank String nome,
        String categoria
) {}
