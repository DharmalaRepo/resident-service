package com.tech.society.residents.controllers;

import com.tech.society.residents.models.GuestVisit;
import com.tech.society.residents.services.GuestVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guest-visits")
public class GuestVisitController {

    @Autowired
    private GuestVisitService guestVisitService;

    @GetMapping
    public List<GuestVisit> getAll() {
        return guestVisitService.getAll();
    }

    @PostMapping
    public GuestVisit create(@RequestBody GuestVisit visit) {
        return guestVisitService.create(visit);
    }
}