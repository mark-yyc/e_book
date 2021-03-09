package com.mark.repository;


import com.mark.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "from User where username = :username and password = :password")
    User checkUser(@Param("username") String username, @Param("password") String password);

    @Query("select u from User u")
    List<User> getUsers();

    @Transactional
    @Modifying
    @Query(value="update User set state=:state where userId = :userId ")
    void modifyState(@Param("userId") Integer userId, @Param("state") Integer state);

    @Query(value = "from User where username = :username")
    User findUser(@Param("username") String username);
}
