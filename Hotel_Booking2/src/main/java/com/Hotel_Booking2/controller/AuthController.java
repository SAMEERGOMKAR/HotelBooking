package com.Hotel_Booking2.controller;

import com.Hotel_Booking2.entity.User;
import com.Hotel_Booking2.payload.JWTAuthResponse;
import com.Hotel_Booking2.payload.LoginDto;
import com.Hotel_Booking2.payload.RegisterDto;
import com.Hotel_Booking2.repository.UserRepository;
import com.Hotel_Booking2.security.JwtTokenProvider;
import com.Hotel_Booking2.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController( UserRepository userRepository,
                           AuthService authService,
                           AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider){
        this.authService = authService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;

    }
//    @PostMapping("/register")
//    public ResponseEntity<?> createUser(@RequestBody RegisterDto registerDto){
//
//        // add check for username exists in a DB
//        if(userRepository.existsByUsername(registerDto.getUsername())){
//            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
//        }
//
//        // add check for email exists in DB
//        if(userRepository.existsByEmail(registerDto.getEmail())){
//            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
//        }
//        RegisterDto register = authService.register(registerDto);
//        return  new ResponseEntity<>(register, HttpStatus.CREATED);
//    }


//    @PostMapping("/login")
//    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        //get token from token provider
//        String token = jwtTokenProvider.generateToken(authentication);
//          return new ResponseEntity<>(new JWTAuthResponse(token),HttpStatus.OK);
//
//    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        User register = authService.register(registerDto);
        return new ResponseEntity<>(register,HttpStatus.CREATED);
    }
}
