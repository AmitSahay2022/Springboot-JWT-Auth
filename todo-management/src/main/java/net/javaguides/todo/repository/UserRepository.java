package net.javaguides.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.todo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
   Optional<User> findByUserName(String userName);
   
   boolean existsByEmail(String email);
   
   Optional<User> findByUserNameOrEmail(String userName,String email);
}
