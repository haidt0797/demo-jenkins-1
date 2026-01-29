package com.example.demo.repo;

import com.example.demo.entity.UsersRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UsersRole, Long> {
}
