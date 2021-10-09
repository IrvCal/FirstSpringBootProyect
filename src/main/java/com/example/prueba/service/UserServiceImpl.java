package com.example.prueba.service;

import com.example.prueba.entity.User;
import com.example.prueba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional (readOnly = true) //se tiene que hacer asi por que jpa implementa metodos transaccionales, y readoly indica que no se guardara nada en la base de datos
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional // si no se le coloca el readonly es porque este si hace cambios db
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
