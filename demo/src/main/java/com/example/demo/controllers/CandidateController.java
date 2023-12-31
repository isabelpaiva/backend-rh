package com.example.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.domain.candidate.Candidate;
import com.example.demo.domain.candidate.CandidateRepository;
import com.example.demo.domain.candidate.RequestCandidate;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/hiring")
public class CandidateController {

    @Autowired
    private CandidateRepository repository;

    @GetMapping("/status/candidate/{codCandidato}")
    public ResponseEntity<?> getCandidate(@PathVariable Long codCandidato) {
        Optional<Candidate> candidate = repository.findById(codCandidato);

        if (candidate.isPresent()) {
            return ResponseEntity.ok(candidate.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado");
        }
    }

    @PostMapping("/start")
    public ResponseEntity<?> createCandidate(@RequestBody @Validated RequestCandidate data) {
        String nome = data.name();

        if (!Pattern.matches("[\\p{L}\\s]+", nome)) {
            return ResponseEntity.badRequest().body("Nome de candidato inválido.");
        }    

        for (Candidate candidate : repository.findAll()) {
            if (candidate.getName().equalsIgnoreCase(nome)) {
                return ResponseEntity.badRequest().body("Candidato já participa do processo.");
            }
        }

        Candidate newCandidate = new Candidate(data);
        newCandidate.setStatus("Recebido");
        repository.save(newCandidate);
        return ResponseEntity.ok(newCandidate);
    }

    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleInterview(@RequestBody RequestCandidate requestCandidate) {
        Optional<Candidate> candidateOptional = repository.findById(requestCandidate.codCandidato());
    
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            if (candidate.getStatus().equals("Qualificado")) {
                return ResponseEntity.badRequest().body("Candidato já está qualificado");
            } else {
                candidate.setStatus("Qualificado");
                repository.save(candidate);
                return ResponseEntity.ok(candidate);
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Candidato não encontrado");
        }
    }

    @PostMapping("/approve")
    public ResponseEntity<String> approveCandidate(@RequestBody Map<String, Long> requestBody) {
        Long codCandidato = requestBody.get("codCandidato");
        Optional<Candidate> candidateOptional = repository.findById(codCandidato);

        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            if (candidate.getStatus().equals("Qualificado")) {
                candidate.setStatus("Aprovado");
                repository.save(candidate);
                return ResponseEntity.ok("Candidato aprovado com sucesso.");
            } else {
                return ResponseEntity.badRequest().body("O candidato não está qualificado para aprovação.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado.");
        }
    }

    @PostMapping("/disqualify")
    public ResponseEntity<String> disqualifyCandidate(@RequestBody Map<String, Long> requestBody) {
        Long codCandidato = requestBody.get("codCandidato");
        Optional<Candidate> candidateOptional = repository.findById(codCandidato);

        if (candidateOptional.isPresent()) {
            repository.delete(candidateOptional.get());
            return ResponseEntity.ok("Candidato removido com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado.");
        }
    }

    @GetMapping("/approved")
    public ResponseEntity<List<Candidate>> getApprovedCandidates() {
        List<Candidate> approvedCandidates = repository.findByStatus("Aprovado");
        return ResponseEntity.ok(approvedCandidates);
    }

    @GetMapping("/candidates")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = repository.findAll();
        return ResponseEntity.ok(candidates);
    }

}
