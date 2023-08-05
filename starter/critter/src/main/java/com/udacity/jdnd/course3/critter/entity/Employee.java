package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Setter
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection
    @JoinTable( name = "employee_skill")
    private Set<EmployeeSkill> skills;


    @ElementCollection
    @JoinTable(name = "days_available")
    @Column(name = "daysAvailable")
    private Set<DayOfWeek> daysAvailable;
}
