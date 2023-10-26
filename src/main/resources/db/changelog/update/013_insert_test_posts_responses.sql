insert into POSTS (USER_ID, CATEGORY_ID, TITLE, DESCRIPTION, WORK_REQUIRED_TIME, PUBLISHED_DATE)
VALUES (2,
        (select id from CATEGORIES where CATEGORY_NAME like 'Компьютеры'),
        'Установка windows', 'Требуется установить windows  на компьютер', '2023-10-23 10:00:00', '2023-10-21 10:00:00'),
       (4,
        (select id from CATEGORIES where CATEGORY_NAME like 'Красота и Здоровье'),
        'Женская прическа', 'Требуется мастер с выездом на дом', '2023-10-23 15:00:00','2023-10-21 15:00:00' );

insert into RESPONSES (POST_ID, SPECIALIST_ID, USER_ID, conversation_id, RESPONSE, DATE_TIME)
values ( 1, 1, null, '1-1', 'Здравствуйте! Я могу выподнить эти работы. Вы можете со мной связаться по номер 123', '2023-10-20 11:00:00' ),
       (1, null, 2, '1-1', 'Здравствуйте! Сколько будет стоить работа?', '2023-10-20 12:00:00'),
       (1, 1, null, '1-1', 'Нужно сначала посмотреть объем работ', '2023-10-20 12:05:00');