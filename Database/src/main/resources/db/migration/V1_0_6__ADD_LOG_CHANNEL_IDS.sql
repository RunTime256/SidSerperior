INSERT INTO bot.log_channel
    (
         name,
         server_id,
         channel_id
    )
    VALUES
    (
        'error',
        ${DEVELOPER_SERVER},
        ${DEVELOPER_ERROR_CHANNEL}
    )
;
