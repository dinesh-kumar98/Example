package com.example.classroom.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.classroom.exception.ResourceNotFoundException;
import com.example.classroom.modal.Classroom;
import com.example.classroom.repository.ClassroomRepository;

@RestController
@RequestMapping("/api")
public class ClassroomController {
	
	@Autowired
	ClassroomRepository classroomRepository;
	
	@GetMapping("/addends")
	public List<Classroom> getAllDetails(){
		return classroomRepository.findAll();		
	}
	
	@PostMapping("/addends")
	public Classroom createClass(@Valid @RequestBody Classroom classroom) {
		return classroomRepository.save(classroom);		
	}
	
	@GetMapping("/addends/{id}")
	public Classroom getDetailById(@PathVariable(value="id")Long classroomId) {
		return classroomRepository.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("Classroom", "id",classroomId ));
		
	}
	@PutMapping("/addends/{id}")
	public Classroom updateDetails(@PathVariable(value="id")Long classroomId, @Valid @RequestBody Classroom classroomDetails) {
		
		Classroom classroom=classroomRepository.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("Classroom", "id",classroomId ));
		
		classroom.setRollno(classroomDetails.getRollno());
		classroom.setName(classroomDetails.getName());
		
		Classroom updateDetails=classroomRepository.save(classroom);
		return updateDetails;
	}
	
	@DeleteMapping("/addends/{id}")
	public ResponseEntity<?> deleteDetail(@PathVariable(value="id")Long classroomId){
		Classroom classroom=classroomRepository.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("Classroom", "id",classroomId ));
		
		classroomRepository.delete(classroom);
		return ResponseEntity.ok().build();		
	}
	
}
