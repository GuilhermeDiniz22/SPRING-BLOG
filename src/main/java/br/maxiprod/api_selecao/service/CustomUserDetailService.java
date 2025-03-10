package br.maxiprod.api_selecao.service;

import br.maxiprod.api_selecao.models.User;
import br.maxiprod.api_selecao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if(user == null) throw new UsernameNotFoundException("Usuário não encontrado!");

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword() , null);
    }
}
