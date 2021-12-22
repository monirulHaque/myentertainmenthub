package com.monirul.myentertainmenthub.repositories;

import com.monirul.myentertainmenthub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
