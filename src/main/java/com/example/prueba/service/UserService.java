package com.example.prueba.service;

import com.example.prueba.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    public Iterable<User> findAll();

    public Page<User> findAll(Pageable pageable);

    public Optional<User> findById(Long id); //como puede o no regresar valor tiene que regresar VALOR

    public User save(User user);

    public void deleteById(Long id);

}
