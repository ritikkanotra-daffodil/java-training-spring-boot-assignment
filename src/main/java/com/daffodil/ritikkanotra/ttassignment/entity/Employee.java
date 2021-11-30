package com.daffodil.ritikkanotra.ttassignment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String employeeCode;
    private String employeeName;
    private String employeeDesignation;
    private String employeeDepartment;
    private String employeeEmail;
    // TODO Add Date employeeRegDate here



}
