package com.roganin.cv.repository;


import com.roganin.cv.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface BasicProfileRepository extends JpaRepository<Profile, Long>, QueryDslPredicateExecutor<Profile> {

}
