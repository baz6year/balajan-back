-- Роль ADMIN
INSERT INTO roles (name)
VALUES ('ADMIN')
    ON CONFLICT (name) DO NOTHING;

-- Пользователь-админ
INSERT INTO users (username, email, password, enabled)
VALUES ('admin', 'admin@example.com', 'admin123', TRUE)
    ON CONFLICT (username) DO NOTHING;

-- Привязка роли ADMIN к пользователю admin
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.username = 'admin'
  AND r.name = 'ADMIN'
    ON CONFLICT DO NOTHING;
