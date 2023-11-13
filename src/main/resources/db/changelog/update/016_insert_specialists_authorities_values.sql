insert into AUTHORITIES(AUTHORITY_NAME)
values ('FULL'),
       ('LIMITED');

INSERT INTO SPECIALISTS_AUTHORITIES (specialist_id, authority_id, date_start, date_end)
SELECT
    s.id AS specialist_id,
    a.id AS authority_id,
    NOW() AS date_start,
    DATEADD(MONTH, 1, CURRENT_TIMESTAMP) AS date_end
FROM
    SPECIALISTS s
        CROSS JOIN AUTHORITIES a
WHERE
        a.AUTHORITY_NAME = 'FULL';
