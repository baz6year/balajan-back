-- V2__seed_data.sql
-- Seed-данные для contests и news
-- ==========================
-- INITIAL SEED (roles, admin user, pages)
-- ==========================

-- роли
INSERT INTO roles (name) VALUES ('ROLE_ADMIN')
    ON CONFLICT (name) DO NOTHING;

INSERT INTO roles (name) VALUES ('ROLE_USER')
    ON CONFLICT (name) DO NOTHING;

-- админ
INSERT INTO users (username, password, enabled)
VALUES ('admin', '{noop}admin123', TRUE)
    ON CONFLICT (username) DO NOTHING;

-- привязка admin → ROLE_ADMIN
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.username = 'admin' AND r.name = 'ROLE_ADMIN'
    ON CONFLICT (user_id, role_id) DO NOTHING;

-- базовые страницы
INSERT INTO pages (slug, title, content) VALUES
                                             ('main', 'Главная', 'Контент страницы Главная...'),
                                             ('about', 'О нас', 'Контент страницы О нас...'),
                                             ('contacts', 'Контакты', 'Телефон, email и адрес...'),
                                             ('contests', 'Конкурсы', 'Контент страницы Конкурсы...')
    ON CONFLICT (slug) DO NOTHING;




/* ====== CONTESTS: seed ====== */

INSERT INTO contests (
    id,
    title,
    slug,
    excerpt,
    image_url,
    category,
    start_date,
    end_date,
    is_featured,
    stages,
    content
)
VALUES (
           1,
           'Программа развития',
           'programma-razvitiya',
           'lorem ispum',
           '/images/expo.png',
           'teacher',
           DATE '2025-11-01',
           DATE '2025-12-15',
           TRUE,
           '[
      {
        "title": "Прием заявок",
        "date": "2025-11-01",
        "description": "Прием заявок до 15 ноября."
      },
      {
        "title": "Отборочный тур",
        "date": "2025-11-20",
        "description": "Описание отбора..."
      },
      {
        "title": "Финал",
        "date": "2025-12-10",
        "description": "Финал и награждение."
      }
    ]'::jsonb,
           '<p>Полное описание программы развития: цели, условия участия, требования к заявкам.</p><p>Доп. информация: ...</p>'
       )
    ON CONFLICT (slug) DO NOTHING;

-- можно добавить второй конкурс, если нужно:
INSERT INTO contests (
    id,
    title,
    slug,
    excerpt,
    image_url,
    category,
    start_date,
    end_date,
    is_featured,
    stages,
    content
)
VALUES (
           2,
           'Методический кейс',
           'metodicheskij-kejs',
           'Конкурс по разработке методических материалов и инновационных форм работы с детьми.',
           '/images/expo.png',
           'teacher',
           DATE '2024-10-07',
           DATE '2024-11-25',
           FALSE,
           '[
      {
        "title":"Подача заявок на онлайн-конкурс",
        "date":"07-10-2024 до 17-10-2024",
        "description":"Сбор материалов до 31 октября."
      },
      {
        "title":"1 этап",
        "date":"25-10-2024 до 05-11-2024",
        "description":"профессиональная экспертиза методических кейсов"
      },
      {
        "title":"2 этап",
        "date":"10-11-2024 до 20-11-2024",
        "description":"Республиканский этап онлайн-конкурса, экспертиза методических кейсов победителей 1 заочного этапа"
      },{
        "title":"3 этап",
        "date":"22-11-2024",
        "description":"Онлайн-защита методических кейсов победителей 2 республиканского этапа"
      }

    ]'::jsonb,
    'Выявление лучших методических кейсов, разработанных педагогами организаций дополнительного образования детей образовательных программ художественной или социально-педагогической направленностей. Выявление методических разработок, ориентированных на обновление содержания и технологий дополнительного образования детей в рамках реализации задач развития региональных систем образования. Совершенствование методических компетенций, повышение профессиональной мотивации, профессионального роста педагогов внешкольного дополнительного образования детей.'
       )
    ON CONFLICT (slug) DO NOTHING;


/* ====== NEWS: seed ====== */

INSERT INTO news (
    id,
    title,
    slug,
    published_at,
    image_url,
    excerpt,
    content
)
VALUES (
           1,
           'Протокол Международного детского конкурса прикладного творчества',
           'rukodelnye-sokrovisha',
           TIMESTAMPTZ '2024-12-23 00:00:00+06',
           '../images/news1.jpg',
           'В наше время все больше людей заинтересованы ручным творчеством и самодеятельностью. Ручные изделия домашнего обихода не только выражают творческий потенциал, но также являются средством сохранения традиций, создания уюта и выражения индивидуальности дома.',
           'В наше время все больше людей заинтересованы ручным творчеством и самодеятельностью. Ручные изделия домашнего обихода не только выражают творческий потенциал, но также являются средством сохранения традиций, создания уюта и выражения индивидуальности дома.'
       )
    ON CONFLICT (slug) DO NOTHING;
