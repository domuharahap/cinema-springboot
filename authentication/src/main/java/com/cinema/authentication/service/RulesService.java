package com.cinema.authentication.service;

import com.cinema.authentication.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RulesService {

    @Autowired
    RolesRepository repository;


}
