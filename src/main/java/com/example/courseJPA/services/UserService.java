package com.example.courseJPA.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.courseJPA.entities.User;
import com.example.courseJPA.repositories.UserRepository;
import com.example.courseJPA.services.exceptions.DatabaseException;
import com.example.courseJPA.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> objUser = userRepository.findById(id);
		//return objUser.get();//Dá erro de código 500, caso o objeto não seja encontrado
		return objUser.orElseThrow(() -> new ResourceNotFoundException(id));//Faz o papel do get, mas se der erro lança a exceção personalizada
	}
	
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	public void delete(Long id) {
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User user) {
		/*Usamos getOne, ao invés de findById, por que o primeiro apenas prepara o objeto e não vai ao banco de dados com o objeto "cheio"
		e deixa apenas o objeto (user), monitorado pelo JPA, para eu trabalhar com ele e depois ir ao banco de dados fazer a operação. 
		diferente do findById() que faz ao contrário trabalhando com requisições e pode cair um pouco o desempenho, o que é menos eficiente */
		try {
			User objUser = userRepository.getOne(id);
			updateObjetoUser(objUser, user);//Atualizar o objeto com os dados que vierem no parâmetro user
			return userRepository.save(objUser);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateObjetoUser(User objUser, User user) {
		objUser.setName(user.getName());
		objUser.setEmail(user.getEmail());
		objUser.setPhone(user.getPhone());
	}
	
}
