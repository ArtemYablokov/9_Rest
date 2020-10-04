package com.yabloko.repositories;

import com.yabloko.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.ResultSet;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByLogin(String login);

//    @Query(value = "SELECT id, password FROM apple_user;")
//    ResultSet getPasswodsOnly();
}