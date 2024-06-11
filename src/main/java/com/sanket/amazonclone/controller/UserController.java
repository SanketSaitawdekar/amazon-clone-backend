package com.sanket.amazonclone.controller;

import com.sanket.amazonclone.model.User;
import com.sanket.amazonclone.service.JwtService;
import com.sanket.amazonclone.service.UserService;
import com.sanket.amazonclone.utilities.Response.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("register")
    public User register(@RequestBody User user)
    {
        return service.save(user);
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody User user, HttpServletResponse response) {
        try {
            System.out.println("username:" + user.getUsername());
            System.out.println("password:" + user.getPassword());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getUsername());

                Cookie cookie = new Cookie("token", token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true); // Use true in production with HTTPS
                cookie.setPath("/");
                cookie.setMaxAge(60 * 3); // Set the expiry time in seconds

                response.addCookie(cookie);

                ApiResponse<String> apiResponse = new ApiResponse<>();
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Login successful");
                apiResponse.setData(token); // Optionally return the token in the response body
                apiResponse.setError(null);

                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                System.out.println("unsuccessful");
                ApiResponse<String> apiResponse = new ApiResponse<>();
                apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                apiResponse.setMessage("Invalid credentials");
                apiResponse.setData(null);
                apiResponse.setError("Authentication failed");

                return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
            }
        } catch (BadCredentialsException e) {
            ApiResponse<String> apiResponse = new ApiResponse<>();
            apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            apiResponse.setMessage("Invalid credentials");
            apiResponse.setData(null);
            apiResponse.setError(e.getMessage());

            return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            ApiResponse<String> apiResponse = new ApiResponse<>();
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setMessage("An error occurred during login");
            apiResponse.setData(null);
            apiResponse.setError(e.getMessage());

            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/check-auth")
    public ResponseEntity<?> checkAuth(HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);

        if (token != null && jwtService.validateToken(token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
