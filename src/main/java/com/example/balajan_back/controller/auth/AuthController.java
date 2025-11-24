package com.example.balajan_back.controller.auth;

import com.example.balajan_back.entity.User;
import com.example.balajan_back.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void login(@RequestBody LoginRequest req, HttpSession session) {

        String email = req.email() == null ? "" : req.email().trim().toLowerCase();
        String raw = req.password() == null ? "" : req.password();

        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Неверный email или пароль"
                ));

        if (Boolean.FALSE.equals(user.getEnabled())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Пользователь отключен");
        }

        String stored = user.getPassword();
        boolean matches = false;

        if (stored != null) {
            // 1) если это bcrypt-хеш (будущее)
            if (stored.startsWith("$2a$") || stored.startsWith("$2b$") || stored.startsWith("$2y$")) {
                try {
                    matches = passwordEncoder.matches(raw, stored);
                } catch (Exception ignored) {
                    matches = false;
                }
            } else {
                // 2) обычный текстовый пароль (как сейчас)
                matches = stored.equals(raw);
            }
        }

        if (!matches) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Неверный email или пароль");
        }

        session.setAttribute("USER_ID", user.getId());
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}

record LoginRequest(String email, String password) {}
