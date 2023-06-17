package com.Lvoquang.sv7.handler.auth;

import com.Lvoquang.sv7.domain.Role;
import com.Lvoquang.sv7.domain.User;
import com.Lvoquang.sv7.exceptions.EmailExistedException;
import com.Lvoquang.sv7.exceptions.UserExistedException;
import com.Lvoquang.sv7.exceptions.UserNotFoundException;
import com.Lvoquang.sv7.repository.RoleRepository;
import com.Lvoquang.sv7.repository.UserRepository;
import com.Lvoquang.sv7.security.CustomUserDetails;
import com.Lvoquang.sv7.security.JwtTokenProvider;
import com.Lvoquang.sv7.security.auth.AuthenticationRequest;
import com.Lvoquang.sv7.security.auth.AuthenticationRestponse;
import com.Lvoquang.sv7.security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationHandler {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationRestponse register(RegisterRequest request) {
        User userByName = userRepository.findByUsername(request.getUsername());
        if (userByName != null) throw new UserExistedException();
        User userByEmail = userRepository.findByEmail(request.getEmail());
        if (userByEmail != null) throw new EmailExistedException();
        Set<Role> roles = new HashSet<Role>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        userRepository.save(user);
        User currentUser = userRepository.findByUsername(user.getUsername());
        String token = tokenProvider.generateToken(new CustomUserDetails(currentUser));
        return AuthenticationRestponse.builder()
                .token(token).build();
    }

    public AuthenticationRestponse login(AuthenticationRequest request) {
        User user = Optional.ofNullable(userRepository.findByUsername(request.getUsername()))
                .orElseThrow(() -> new UserNotFoundException());

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(new CustomUserDetails(user));
        return AuthenticationRestponse.builder()
                .token(token).build();
    }

}
