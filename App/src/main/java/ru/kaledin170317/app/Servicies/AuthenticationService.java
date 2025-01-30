package ru.kaledin170317.app.Servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kaledin170317.app.Entities.*;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final OwnerService ownerService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = UserDTO.builder()
                .username(request.getUsername())

                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();


        ownerService.OwnerOperation(new Owner(userService.create(user).getId()),"create");

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
