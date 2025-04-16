package com.tech.society.residents.services;

import com.tech.society.residents.models.GuestVisit;
import com.tech.society.residents.repositories.GuestVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestVisitService {

    @Autowired
    private GuestVisitRepository repository;

    public List<GuestVisit> getAll() {
        return repository.findAll();
    }

    public GuestVisit create(GuestVisit visit) {
        return repository.save(visit);
    }
}