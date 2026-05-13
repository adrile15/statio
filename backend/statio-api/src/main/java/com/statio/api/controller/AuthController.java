package com.statio.api.controller;

import com.statio.api.dto.FirebaseLoginRequest;
import com.statio.api.model.User;
import com.statio.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // REGISTER NORMAL
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody User user){

        if(userRepository
                .findByEmail(user.getEmail())
                .isPresent()){

            return ResponseEntity
                    .badRequest()
                    .body("Email ya registrado");
        }

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    // LOGIN NORMAL
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody User loginRequest){

        Optional<User> user =
            userRepository.findByEmail(
                loginRequest.getEmail()
            );

        if(user.isEmpty()){

            return ResponseEntity
                    .status(401)
                    .body("Usuario no encontrado");
        }

        if(!user.get()
                .getPassword()
                .equals(loginRequest.getPassword())){

            return ResponseEntity
                    .status(401)
                    .body("Password incorrecta");
        }

        return ResponseEntity.ok(user.get());
    }

    // LOGIN GOOGLE FIREBASE
    @PostMapping("/firebase-login")
    public User firebaseLogin(
            @RequestBody FirebaseLoginRequest request){

        User existingUser =
            userRepository.findByFirebaseUid(
                request.getUid()
            );

        // ya existe
        if(existingUser != null){
            return existingUser;
        }

        // crear nuevo usuario
        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setFirebaseUid(request.getUid());

        // password vacío
        user.setPassword("");

        return userRepository.save(user);
    }
}