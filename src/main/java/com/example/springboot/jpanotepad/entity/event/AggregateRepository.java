package com.example.springboot.jpanotepad.entity.event;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AggregateRepository extends CrudRepository<Aggregate, Long> {

    Optional<Aggregate> findById(long id);

}
