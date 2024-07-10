package uz.lee.onlineshoop.service;


import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import uz.lee.onlineshoop.dto.ApiResponse;
import uz.lee.onlineshoop.dto.auth.LoginDto;
import uz.lee.onlineshoop.dto.auth.RegisterDto;
import uz.lee.onlineshoop.entity.GenderEntity;
import uz.lee.onlineshoop.exception.ResourceNotFoundException;
import uz.lee.onlineshoop.repository.GenderRepository;
import uz.lee.onlineshoop.repository.RoleEntityRepository;
import uz.lee.onlineshoop.entity.UserEntity;
import uz.lee.onlineshoop.provider.JwtProvider;
import uz.lee.onlineshoop.repository.UserEntityRepository;
import uz.lee.onlineshoop.util.AppConstants;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final SendMessageService sendMessageService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GenderRepository genderRepository;

    private final JwtProvider jwtProvider;

    public AuthService(UserEntityRepository userEntityRepository,
                       RoleEntityRepository roleEntityRepository, SendMessageService sendMessageService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, GenderRepository genderRepository, JwtProvider jwtProvider) {
        this.userEntityRepository = userEntityRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.sendMessageService = sendMessageService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.genderRepository = genderRepository;
        this.jwtProvider = jwtProvider;
    }

    @SneakyThrows
    public ApiResponse register(RegisterDto registerDto) {
        UserEntity user = new UserEntity();
        user.setEmail(registerDto.getEmail());
        user.setGender(genderRepository.findById(registerDto.getGenderId()).orElseThrow(()-> new ResourceNotFoundException("No such kind of gender")));
        user.setFullName(registerDto.getFullName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setPhoneNumber(registerDto.getPhoneNumber());
        user.setUsername(registerDto.getUsername());

        String code = UUID.randomUUID() + "";
        user.setVerificationCode(code);

        sendMessageService.sendHtmlEmail(
                registerDto.getEmail(),
                AppConstants.verificationLinkSubject,
                AppConstants.verificationLinkBody.formatted(
                        "http://localhost:8080/api/auth/verify/%s".formatted(code))
                );


        user.setRole(roleEntityRepository.findRoleEntitiesByName(AppConstants.USER));


        userEntityRepository.save(user);
        return new ApiResponse(true,"Check your email to verify your account");
    }

    public ApiResponse verifyEmail(String verificationCode) {
        Optional<UserEntity> userOpt = userEntityRepository.findUserEntitiesByVerificationCode(verificationCode);
        if (userOpt.isPresent()){
            UserEntity user = userOpt.get();
            user.setEnabled(true);
            userEntityRepository.save(user);
            return new ApiResponse(true,"Your account enabled !!!");
        }

        return new ApiResponse(false,"There is no user with this code");
    }

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));
            UserEntity user = (UserEntity) authentication.getPrincipal();
            String token = jwtProvider.generateToken(user);
            return new ApiResponse(true,token);
        }catch (Exception e) {
            return new ApiResponse(false,"Invalid username or password");
        }
    }
}
