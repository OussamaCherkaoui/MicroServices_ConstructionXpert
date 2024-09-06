package org.authentificationservice.controller;

import lombok.RequiredArgsConstructor;
import org.authentificationservice.config.AuthenticationResponse;
import org.authentificationservice.exception.UserNotFoundException;
import org.authentificationservice.model.AuthenticationRequest;
import org.authentificationservice.model.Utilisateur;
import org.authentificationservice.service.UtilisateurService;
import org.authentificationservice.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UtilisateurController {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurService utilisateurService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        final UserDetails userDetails = utilisateurService.loadUserByUsername(authenticationRequest.getUsername());
        Utilisateur utilisateur = utilisateurService.getUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails,utilisateur.getRole());
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @GetMapping("/getIdByUserName/{username}")
    public ResponseEntity<?> getIdByUserName(@PathVariable("username") String username) {
        try {
            Utilisateur user = utilisateurService.getUserByUsername(username);
            return ResponseEntity.ok(user.getId());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/getUserByIdUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            Utilisateur user = utilisateurService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
