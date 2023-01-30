package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    public User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value="UPDATE users SET username = ?1, fullname = ?2, role = ?3 WHERE id = ?4", nativeQuery = true)
    public void saveWithoutPassword(String username, String fullname, String role, long id);

}
