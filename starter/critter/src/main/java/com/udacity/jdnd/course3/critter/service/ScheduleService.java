package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    public ScheduleService(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule saveSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getAllSchedulesByPetIds(long petId){
        return scheduleRepository.findAllScheduleByPetId(petId);
    }

    public List<Schedule> getAllSchedulesByEmpIds(long empId){
        return scheduleRepository.findAllScheduleByEmpId(empId);
    }

    public List<Schedule> getAllSchedulesByCusIds(long cusId){
        return scheduleRepository.findAllScheduleByCusId(cusId);
    }
}
