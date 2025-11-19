-- V2__seed_data.sql
-- Добавляем колонку slug и уникальные индексы (если отсутствуют), затем вставляем seed-данные (идемпотентно).

/* ====== Добавляем slug для contests (если нет) ====== */
ALTER TABLE IF EXISTS contests
    ADD COLUMN IF NOT EXISTS slug varchar(255);

DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1
    FROM pg_class c
    JOIN pg_namespace n ON n.oid = c.relnamespace
    WHERE c.relkind = 'i'
      AND c.relname = 'idx_contests_slug'
      AND n.nspname = 'public'
  ) THEN
    EXECUTE 'CREATE UNIQUE INDEX idx_contests_slug ON public.contests (slug)';
END IF;
END
$$;

/* ====== Добавляем slug для news (если нет) ====== */
ALTER TABLE IF EXISTS news
    ADD COLUMN IF NOT EXISTS slug varchar(255);

DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1
    FROM pg_class c
    JOIN pg_namespace n ON n.oid = c.relnamespace
    WHERE c.relkind = 'i'
      AND c.relname = 'idx_news_slug'
      AND n.nspname = 'public'
  ) THEN
    EXECUTE 'CREATE UNIQUE INDEX idx_news_slug ON public.news (slug)';
END IF;
END
$$;

/* ====== ROLES / USERS (без дублирования) ====== */
/* вставляем роли по имени (name UNIQUE в V1) */
INSERT INTO roles (name)
VALUES ('ROLE_ADMIN')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO roles (name)
VALUES ('ROLE_USER')
    ON CONFLICT (name) DO NOTHING;

/* вставляем пользователя admin (если нет) — username уникален */
INSERT INTO users (username, password, enabled)
VALUES ('admin', '{noop}admin123', true)
    ON CONFLICT (username) DO NOTHING;

/* user_roles: вставляем связь только если её нет */
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM user_roles ur
    JOIN users u ON u.id = ur.user_id
    JOIN roles r ON r.id = ur.role_id
    WHERE u.username = 'admin' AND r.name IN ('ROLE_ADMIN','ADMIN','ADMINISTRATOR','ADMIN')
  ) THEN
    -- пытаемся найти id роли ROLE_ADMIN или ADMIN (учитываем, что в V1 вы использовали 'ADMIN')
    INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.username = 'admin' AND (r.name = 'ROLE_ADMIN' OR r.name = 'ADMIN')
    LIMIT 1;
END IF;
END
$$;


/* ====== CONTESTS: seed (под структуру из V1) ====== */
-- вставляем два тестовых конкурса; используем колонки: id, title, slug, short_description, content, image_url, category, event_date, is_featured
INSERT INTO contests (id, title, slug, short_description, content, image_url, category, event_date, is_featured)
VALUES
    (1,
     'Методический кейс',
     'metodicheskij-kejs',
     'Конкурс по разработке методических материалов и инновационных форм работы с детьми.',
     '<p>Выявление лучших методических кейсов, разработанных педагогами...</p>',
     '/images/expo.png',
     'TEACHERS',
     '2024-10-07',
     false
    )
    ON CONFLICT (slug) DO NOTHING;

INSERT INTO contests (id, title, slug, short_description, content, image_url, category, event_date, is_featured)
VALUES
    (2,
     'Программа развития',
     'programma-razvitiya',
     'lorem ispum',
     '<p>Полное описание программы развития: цели, условия участия...</p>',
     '/images/expo.png',
     'TEACHERS',
     '2025-11-01',
     true
    )
    ON CONFLICT (slug) DO NOTHING;


/* ====== NEWS: seed (под структуру из V1) ====== */
INSERT INTO news (id, title, slug, short_description, content, image_url, published_at)
VALUES
    (1,
     'Протокол Международного детского конкурса прикладного творчества',
     'rukodelnye-sokrovisha',
     'В наше время все больше людей заинтересованы ручным творчеством и самодеятельностью.',
     '<p>В наше время все больше людей заинтересованы ручным творчеством и самодеятельностью. Ручные изделия домашнего обихода...</p>',
     '/images/news1.jpg',
     now()
    )
    ON CONFLICT (slug) DO NOTHING;
