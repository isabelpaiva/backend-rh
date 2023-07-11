package com.example.demo.domain.candidate;
import com.example.demo.domain.RequestCandidate;

import jakarta.persistence.*;
import lombok.*;


@Table(name="candidates")
@Entity(name="candidates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "codCandidate")

public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codCandidate;

    private String name;


    @Column(columnDefinition = "TEXT DEFAULT 'Recebidos'")
    private String status;

    public Candidate(RequestCandidate requestCandidate){
        this.name = requestCandidate.name();
        this.codCandidate = requestCandidate.codCandidate();
        this.status = requestCandidate.status();
    }

}
