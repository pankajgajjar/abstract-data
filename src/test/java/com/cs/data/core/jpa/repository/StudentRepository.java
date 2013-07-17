package com.cs.data.core.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs.data.core.jpa.entities.Student;


public interface StudentRepository extends JpaRepository<Student, String> {

}
