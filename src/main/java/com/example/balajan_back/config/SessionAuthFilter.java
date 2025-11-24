package com.example.balajan_back.config;

import com.example.balajan_back.entity.Role;
import com.example.balajan_back.entity.User;
import com.example.balajan_back.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SessionAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // если ещё нет аутентификации
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            HttpSession session = request.getSession(false);

            if (session != null) {
                Object userIdObj = session.getAttribute("USER_ID");

                if (userIdObj != null) {
                    Long userId = (userIdObj instanceof Long)
                            ? (Long) userIdObj
                            : Long.valueOf(userIdObj.toString());

                    // Загружаем пользователя из БД
                    User user = userRepository.findById(userId).orElse(null);
                    if (user != null && Boolean.TRUE.equals(user.getEnabled())) {

                        Set<Role> roles = user.getRoles();
                        // конвертируем роли в GrantedAuthority, например ROLE_ADMIN
                        var authorities = roles.stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                                .collect(Collectors.toSet());

                        UsernamePasswordAuthenticationToken auth =
                                new UsernamePasswordAuthenticationToken(
                                        user, // principal
                                        null,
                                        authorities
                                );
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
