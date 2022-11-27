package ro.usv.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.usv.backend.config.JwtTokenUtil;
import ro.usv.backend.dto.JwtResponse;
import ro.usv.backend.dto.LoginDto;
import ro.usv.backend.dto.RegisterDto;
import ro.usv.backend.service.LocalUserService;

@RestController
@RequestMapping("security")
public class SecurityController {
    private final AuthenticationManager authenticationManager;
    private final LocalUserService localUserService;

    private  final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public SecurityController(AuthenticationManager authenticationManager, LocalUserService localUserService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.localUserService = localUserService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public String  regiseter(@RequestBody RegisterDto registerDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        registerDto.setPassword(encodedPassword);

        localUserService.register(registerDto);
        return "registred";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception {
        authenticate(loginDto.getIdentifier(), loginDto.getPassword());
        final UserDetails userDetails = localUserService
                .loadUserByUsername(loginDto.getIdentifier());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
