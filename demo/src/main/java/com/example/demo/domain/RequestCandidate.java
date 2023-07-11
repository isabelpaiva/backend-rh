package com.example.demo.domain;

// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;


public record RequestCandidate(

    Long codCandidate,
    String name,
    String status
) {
}



