package com.github.meloflavio.ias.util.Abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface AbstractRepository<T , ID >  extends JpaRepository<T, ID>  {

}
