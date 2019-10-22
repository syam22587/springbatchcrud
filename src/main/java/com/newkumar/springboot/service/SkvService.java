package com.newkumar.springboot.service;


import java.util.List;

import com.newkumar.springboot.model.Skv;

public interface SkvService {
	
	Skv findById(Long id);

	Skv findByName(String name);

	void saveUser(Skv user);

	void updateUser(Skv user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<Skv> findAllUsers();

	boolean isUserExist(Skv user);
}