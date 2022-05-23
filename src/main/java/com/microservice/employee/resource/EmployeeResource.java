package com.microservice.employee.resource;

import com.microservice.employee.domain.Department;
import com.microservice.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> findOne(@PathVariable Long id) {
        kafkaTemplate.send("DEPARTMENT_SEARCH","id", id.toString());
        return ResponseEntity.ok(departmentService.findOne(id));
    }

}
