package br.com.chat.bigChatBrasil.api.controller;

import br.com.chat.bigChatBrasil.api.app.user.dto.AuthenticationDTO;
import br.com.chat.bigChatBrasil.api.app.user.dto.LoginResponseDTO;
import br.com.chat.bigChatBrasil.api.app.user.dto.RegisterDTO;
import br.com.chat.bigChatBrasil.domain.user.User;
import br.com.chat.bigChatBrasil.domain.user.service.UserService;
import br.com.chat.bigChatBrasil.util.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin({"http://localhost:4200"})
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    private final UserService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getTelefone(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(this.service.buscarUserPorTelefone(data.getTelefone()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getTelefone(), encryptedPassword, data.getTipoUser());

        this.service.salvarUser(newUser);


        return ResponseEntity.ok().build();
    }
}
