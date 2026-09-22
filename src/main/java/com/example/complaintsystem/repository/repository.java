package com.example.complaintsystem.repository;

import com.example.complaintsystem.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface repository extends JpaRepository<Complaint,Long>{

    List<Complaint> findByStatus(String status);
    List<Complaint> findByPriority(String priority);
    List<Complaint> findByDepartment(String department);
    List<Complaint> findByUsername(String username);
    Long countByStatus(String status);
    Long countByPriority(String priority);
    List<Complaint> findAllByOrderByCreatedatDesc();
    Long countByDepartment(String department);
}