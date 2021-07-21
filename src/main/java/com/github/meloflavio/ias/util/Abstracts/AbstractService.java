package com.github.meloflavio.ias.util.Abstracts;


import com.github.meloflavio.ias.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@NoRepositoryBean
public abstract class AbstractService<T extends AbstractEntity<ID>, ID extends Serializable, R extends AbstractRepository<T,ID>> {
    @Autowired
    protected R repository;

    public T find(ID id){
        Optional<T> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + " " ));
    }

    public List<T> findAll(){
        return repository.findAll();
    }

    public void save(T subject) {
        repository.save(subject);
    }
    public void delete(T subject) {
        repository.delete(subject);
    }
}
