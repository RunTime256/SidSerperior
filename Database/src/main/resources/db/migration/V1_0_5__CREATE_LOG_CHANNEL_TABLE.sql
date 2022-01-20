CREATE TABLE IF NOT EXISTS bot.log_channel (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    server_id BIGINT NOT NULL,
    channel_id BIGINT NOT NULL
);
