package com.java_oracle.project.oracle_db.Staff;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface Staff_Repo {

    List<Staff> findStaffByEmail(String email);

    int addStaff(Staff staff);

    List<Staff> findAllStaff();
}
