CREATE TABLE IF NOT EXISTS bot.server (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    server_id BIGINT NOT NULL
);
