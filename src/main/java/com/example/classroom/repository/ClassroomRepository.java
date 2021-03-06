package com.example.classroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.classroom.modal.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long>{

}
