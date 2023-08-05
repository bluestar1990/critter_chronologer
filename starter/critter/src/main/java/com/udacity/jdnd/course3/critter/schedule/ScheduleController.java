package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    private PetService petService;

    private EmployeeService employeeService;

    public ScheduleController(ScheduleService scheduleService, PetService petService,  EmployeeService employeeService){
        this.scheduleService = scheduleService;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(LocalDate.now());
        schedule.setPets(petService.findPetByPetId(scheduleDTO.getPetIds()));
        schedule.setEmployees(employeeService.getAllEmployeeByEmpId(scheduleDTO.getEmployeeIds()));
        schedule.setEmployeeSkills(scheduleDTO.getActivities());

        Schedule scheduleResult = scheduleService.saveSchedule(schedule);
        ScheduleDTO scheduleDTOResponse = new ScheduleDTO();
        scheduleDTOResponse.setActivities(scheduleResult.getEmployeeSkills());
        scheduleDTOResponse.setDate(scheduleResult.getDate());
        scheduleDTOResponse.setId(scheduleResult.getId());
        List<Long> empIds = new ArrayList<>();
        scheduleResult.getEmployees().forEach(e -> {
            empIds.add(e.getId());
        });
        scheduleDTOResponse.setEmployeeIds(empIds);
        List<Long> petIds = new ArrayList<>();
        scheduleResult.getPets().forEach(p -> {
            petIds.add(p.getId());
        });
        scheduleDTOResponse.setPetIds(petIds);
        return scheduleDTOResponse;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> schedules = scheduleService.getAllSchedules();
        schedules.forEach(s -> {
            ScheduleDTO scheduleDTOResponse = new ScheduleDTO();
            scheduleDTOResponse.setActivities(s.getEmployeeSkills());
            scheduleDTOResponse.setDate(s.getDate());
            scheduleDTOResponse.setId(s.getId());
            List<Long> empIds = new ArrayList<>();
            s.getEmployees().forEach(e -> {
                empIds.add(e.getId());
            });
            scheduleDTOResponse.setEmployeeIds(empIds);
            List<Long> petIds = new ArrayList<>();
            s.getPets().forEach(p -> {
                petIds.add(p.getId());
            });
            scheduleDTOResponse.setPetIds(petIds);
            scheduleDTOS.add(scheduleDTOResponse);
        });
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getAllSchedulesByPetIds(petId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        schedules.forEach(s -> {
            ScheduleDTO scheduleDTOResponse = new ScheduleDTO();
            scheduleDTOResponse.setActivities(s.getEmployeeSkills());
            scheduleDTOResponse.setDate(s.getDate());
            scheduleDTOResponse.setId(s.getId());
            List<Long> empIds = new ArrayList<>();
            s.getEmployees().forEach(e -> {
                empIds.add(e.getId());
            });
            scheduleDTOResponse.setEmployeeIds(empIds);
            List<Long> petIds = new ArrayList<>();
            s.getPets().forEach(p -> {
                petIds.add(p.getId());
            });
            scheduleDTOResponse.setPetIds(petIds);
            scheduleDTOS.add(scheduleDTOResponse);
        });
        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getAllSchedulesByEmpIds(employeeId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        schedules.forEach(s -> {
            ScheduleDTO scheduleDTOResponse = new ScheduleDTO();
            scheduleDTOResponse.setActivities(s.getEmployeeSkills());
            scheduleDTOResponse.setDate(s.getDate());
            scheduleDTOResponse.setId(s.getId());
            List<Long> empIds = new ArrayList<>();
            s.getEmployees().forEach(e -> {
                empIds.add(e.getId());
            });
            scheduleDTOResponse.setEmployeeIds(empIds);
            List<Long> petIds = new ArrayList<>();
            s.getPets().forEach(p -> {
                petIds.add(p.getId());
            });
            scheduleDTOResponse.setPetIds(petIds);
            scheduleDTOS.add(scheduleDTOResponse);
        });
        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getAllSchedulesByCusIds(customerId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        schedules.forEach(s -> {
            ScheduleDTO scheduleDTOResponse = new ScheduleDTO();
            scheduleDTOResponse.setActivities(s.getEmployeeSkills());
            scheduleDTOResponse.setDate(s.getDate());
            scheduleDTOResponse.setId(s.getId());
            List<Long> empIds = new ArrayList<>();
            s.getEmployees().forEach(e -> {
                empIds.add(e.getId());
            });
            scheduleDTOResponse.setEmployeeIds(empIds);
            List<Long> petIds = new ArrayList<>();
            s.getPets().forEach(p -> {
                petIds.add(p.getId());
            });
            scheduleDTOResponse.setPetIds(petIds);
            scheduleDTOS.add(scheduleDTOResponse);
        });
        return scheduleDTOS;
    }
}
