package com.example.demo.repo;

import com.example.demo.entity.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface RolesRepository extends CrudRepository<Roles, Long> {
  Set<Roles> findAllByRoleIn(Collection<String> strings);
}
