package com.files.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.files.Entities.User;

public interface UserRepository extends JpaRepository <User, Long>
{

}
