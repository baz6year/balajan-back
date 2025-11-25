ALTER TABLE users
    ADD COLUMN IF NOT EXISTS created_at timestamp;

ALTER TABLE users
    ADD COLUMN IF NOT EXISTS updated_at timestamp;