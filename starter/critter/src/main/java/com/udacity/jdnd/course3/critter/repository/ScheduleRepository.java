package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s from Schedule s INNER JOIN s.pets p WHERE p.id = :petId")
    List<Schedule> findAllScheduleByPetId(@Param("petId") Long petId);

    @Query("SELECT s from Schedule s INNER JOIN s.employees e WHERE e.id = :empId")
    List<Schedule> findAllScheduleByEmpId(@Param("empId") Long empId);

    @Query("SELECT s from Schedule s INNER JOIN s.pets p INNER JOIN p.customer c WHERE c.id = :cusId")
    List<Schedule> findAllScheduleByCusId(@Param("cusId") Long cusId);
}
