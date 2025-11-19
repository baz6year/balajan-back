-- ==========================
-- ROLES
-- ==========================
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO roles (name) VALUES ('ADMIN'), ('EDITOR');

-- ==========================
-- USERS
-- ==========================
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

-- User ↔ Role (many-to-many)
CREATE TABLE user_roles (
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    role_id INT REFERENCES roles(id) ON DELETE CASCADE
);

-- Create admin with password "admin123"
INSERT INTO users (username, password)
VALUES ('admin', '{noop}admin123');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1); -- user 1 → ADMIN

-- ==========================
-- PAGES (static content)
-- ==========================
CREATE TABLE pages (
    id SERIAL PRIMARY KEY,
    slug VARCHAR(100) UNIQUE NOT NULL,
    title VARCHAR(255),
    content TEXT
);

INSERT INTO pages (slug, title, content) VALUES
    ('about', 'О нас', 'Контент страницы О нас...'),
    ('contacts', 'Контакты', 'Телефон, email и адрес...'),
    ('mission', 'Наша миссия', 'Контент миссии...');

-- ==========================
-- NEWS
-- ==========================
CREATE TABLE news (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    short_description TEXT,
    content TEXT,
    image_url VARCHAR(255),
    published_at TIMESTAMP DEFAULT NOW()
);

-- ==========================
-- CONTESTS
-- ==========================
CREATE TABLE contests (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    short_description TEXT,
    content TEXT,
    image_url VARCHAR(255),
    category VARCHAR(50), -- TEACHERS / CHILDREN / ALL
    event_date DATE,
    is_featured BOOLEAN DEFAULT FALSE
);

-- ==========================
-- TEAM MEMBERS
-- ==========================
CREATE TABLE team_members (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(255),
    photo_url VARCHAR(255),
    description TEXT
);
