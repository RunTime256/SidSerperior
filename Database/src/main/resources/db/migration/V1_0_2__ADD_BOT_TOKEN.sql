INSERT INTO bot.token
    (
         name,
         token,
         is_active
    )
    VALUES
    (
        ${BOT_NAME},
        ${BOT_TOKEN},
        true
    );
