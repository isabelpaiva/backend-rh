package com.example.demo.domain.candidate;
import jakarta.persistence.*;
import lombok.*;


@Table(name="candidates")
@Entity(name="candidates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "codCandidato")

public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codCandidato;

    private String name;

    private String status;

    public Candidate(RequestCandidate requestCandidate){
        this.name = requestCandidate.name();
        this.codCandidato = requestCandidate.codCandidato();
        this.status = requestCandidate.status();
   }

}
