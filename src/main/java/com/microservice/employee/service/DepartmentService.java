package com.microservice.employee.service;

import com.microservice.employee.domain.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentService {

    private final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    public Department findOne(Long id) {
        log.debug("Request to find an employee {}", id);
        return restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + id
                , Department.class);
    }
}
