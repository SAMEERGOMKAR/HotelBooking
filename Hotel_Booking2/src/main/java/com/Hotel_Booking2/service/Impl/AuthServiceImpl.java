package com.Hotel_Booking2.service.Impl;

import com.Hotel_Booking2.entity.Role;
import com.Hotel_Booking2.entity.User;
import com.Hotel_Booking2.exception.UserAPIException;
import com.Hotel_Booking2.payload.LoginDto;
import com.Hotel_Booking2.payload.RegisterDto;
import com.Hotel_Booking2.repository.RoleRepository;
import com.Hotel_Booking2.repository.UserRepository;
import com.Hotel_Booking2.security.JwtTokenProvider;
import com.Hotel_Booking2.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

private AuthenticationManager authenticationManager;
private UserRepository userRepository;
private RoleRepository roleRepository;
private PasswordEncoder passwordEncoder;
private JwtTokenProvider jwtTokenProvider;

public AuthServiceImpl(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider){
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
    this.jwtTokenProvider = jwtTokenProvider;
}



    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public User register(RegisterDto registerDto) {
      //add check for username exist in database
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Username Is already exist!.");
        }
        //add check for email exist in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Email is already exist!.");
        }
         User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        User save = userRepository.save(user);

        return save;
    }

//    @Override
//    public RegisterDto register(RegisterDto registerDto) {
//    //add check for username exist in database
//
//       User user = new User();
//       user.setFirstName(registerDto.getFirstName());
//       user.setLastName(registerDto.getLastName());
//       user.setEmail(registerDto.getEmail());
//       user.setUsername(registerDto.getUsername());
//       user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//
//        User saveUser = userRepository.save(user);
//        RegisterDto registerDto1 = mapToDTO(saveUser);
//        return registerDto1;
//    }

    public RegisterDto mapToDTO(User saveUser) {

    RegisterDto registerDto = new RegisterDto();
    registerDto.setId(saveUser.getId());
    registerDto.setFirstName(saveUser.getFirstName());
    registerDto.setLastName(saveUser.getLastName());
    registerDto.setUsername(saveUser.getUsername());
    registerDto.setPassword(saveUser.getPassword());
    registerDto.setEmail(saveUser.getEmail());
    return registerDto;
    }
}
