package com.pub.data.abstraction.core.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pub.data.abstraction.core.orm.entities.Student;


public interface StudentRepository extends JpaRepository<Student, String> {

}
