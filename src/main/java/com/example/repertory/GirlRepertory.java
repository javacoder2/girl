package com.example.repertory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Girl;

public interface GirlRepertory extends JpaRepository<Girl, Integer> {
	//通过年龄来查询
	public List<Girl> findByAge(Integer age);
}
