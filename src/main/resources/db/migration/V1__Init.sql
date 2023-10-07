CREATE TABLE api_user
(
    id       BIGSERIAL PRIMARY KEY,
    name     TEXT NOT NULL,
    user_name TEXT NOT NULL,
    user_surname TEXT NOT NULL,
    user_mail TEXT NOT NULL,
    user_phone TEXT NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE item
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT  NOT NULL REFERENCES api_user (id) ON DELETE CASCADE,
    name    TEXT    NOT NULL,
    count   INTEGER NOT NULL,
    note    TEXT
);
