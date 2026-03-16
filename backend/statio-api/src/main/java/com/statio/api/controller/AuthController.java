package com.statio.api.controller;

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

@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody User user){

if(userRepository.findByEmail(user.getEmail()).isPresent()){
return ResponseEntity.badRequest().body("Email ya registrado");
}

userRepository.save(user);

return ResponseEntity.ok(user);
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User loginRequest){

Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

if(user.isEmpty()){
return ResponseEntity.status(401).body("Usuario no encontrado");
}

if(!user.get().getPassword().equals(loginRequest.getPassword())){
return ResponseEntity.status(401).body("Password incorrecta");
}

return ResponseEntity.ok(user.get());
}

}