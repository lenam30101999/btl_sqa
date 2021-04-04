package com.btl.sqa.dao;

import com.btl.sqa.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDAO extends JpaRepository<Class, Integer> {

}
