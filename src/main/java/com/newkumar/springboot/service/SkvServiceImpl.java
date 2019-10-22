package com.newkumar.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newkumar.springboot.model.Skv;
import com.newkumar.springboot.repositories.SkvRepository;



@Service("skvService")
@Transactional
public class SkvServiceImpl implements SkvService{

	@Autowired
	private SkvRepository skvRepository;

	public Skv findById(Long id) {
		return skvRepository.findOne(id);
	}

	public Skv findByName(String name) {
		return skvRepository.findByName(name);
	}

	public void saveUser(Skv skv) {
		skvRepository.save(skv);
	}

	public void updateUser(Skv skv){
		saveUser(skv);	
	}

	public void deleteUserById(Long id){
		skvRepository.delete(id);
	}

	public void deleteAllUsers(){
		skvRepository.deleteAll();
	}

	public List<Skv> findAllUsers(){
		return skvRepository.findAll();
	}

	public boolean isUserExist(Skv skv) {
		return findByName(skv.getName()) != null;
	}

}
