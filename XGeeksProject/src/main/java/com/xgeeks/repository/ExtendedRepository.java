package com.xgeeks.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ExtendedRepository <T, ID extends Serializable> 
  extends JpaRepository<T, ID> {
  

}
