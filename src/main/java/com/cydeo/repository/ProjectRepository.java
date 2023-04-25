package com.cydeo.repository;

import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByProjectCode(String code);
    Project findAllByAssignedManager(User manager);
}
