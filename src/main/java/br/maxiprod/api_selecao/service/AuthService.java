package br.maxiprod.api_selecao.service;

import br.maxiprod.api_selecao.dto.RegistroRequest;
import br.maxiprod.api_selecao.models.Role;
import br.maxiprod.api_selecao.models.User;
import br.maxiprod.api_selecao.repository.RoleRepository;
import br.maxiprod.api_selecao.repository.UserRepository;
import br.maxiprod.api_selecao.security.JwtTokenProvider;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.maxiprod.api_selecao.dto.LoginRequest;

import java.util.HashSet;
import java.util.Set;


@Service
public class AuthService {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder encoder;

    private JwtTokenProvider jwtTokenProvider;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtTokenProvider.generateToken(auth);

        return token;
    }

    public String register(RegistroRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new EntityExistsException("Usuário já cadastrado com esse nome username!");
        }else if(userRepository.existsByEmail(request.getEmail())){
            throw new EntityExistsException("Usuário já cadastrado com esse email!");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();

        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "Usuário cadastrado com sucesso!";
    }

}
