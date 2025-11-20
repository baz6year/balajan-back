-- ==========================
-- ROLES
-- ==========================
CREATE TABLE roles (
                       id   SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

-- ==========================
-- USERS
-- ==========================
CREATE TABLE users (
                       id       SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255)       NOT NULL,
                       enabled  BOOLEAN DEFAULT TRUE
);

-- User ↔ Role (many-to-many)
CREATE TABLE user_roles (
                            user_id INT REFERENCES users(id) ON DELETE CASCADE,
                            role_id INT REFERENCES roles(id) ON DELETE CASCADE,
                            CONSTRAINT pk_user_roles PRIMARY KEY (user_id, role_id)
);

-- ==========================
-- PAGES (static content)
-- ==========================
CREATE TABLE pages (
                       id      SERIAL PRIMARY KEY,
                       slug    VARCHAR(100) UNIQUE NOT NULL,
                       title   VARCHAR(255),
                       content TEXT
);

-- ==========================
-- NEWS
-- ==========================
CREATE TABLE news (
                      id              SERIAL PRIMARY KEY,
                      title           VARCHAR(255) NOT NULL,
                      slug            VARCHAR(255) UNIQUE NOT NULL,
                      published_at    TIMESTAMPTZ DEFAULT NOW(),
                      image_url       VARCHAR(255),
                      excerpt         TEXT,
                      content         TEXT
);

-- ==========================
-- CONTESTS
-- ==========================
CREATE TABLE contests (
                          id              SERIAL PRIMARY KEY,
                          title           VARCHAR(255) NOT NULL,
                          slug            VARCHAR(255) UNIQUE NOT NULL,
                          excerpt         TEXT,
                          image_url       VARCHAR(255),
                          category        VARCHAR(50), -- TEACHER / KID / ALL
                          start_date      DATE,
                          end_date        DATE,
                          is_featured     BOOLEAN DEFAULT FALSE,
                          stages          JSONB,
                          content         TEXT

);




