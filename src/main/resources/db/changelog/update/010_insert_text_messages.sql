insert into MESSAGES (SENDER_ID, RECEIVER_ID, MESSAGE_TEXT, DATE_TIME)
VALUES ( 1, 2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit', now() ),
       (2, 1, 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem', now()),
       (1, 3, 'ab illo inventore veritatis et quasi architecto beata', now()),
       (1, 4, 'sit amet, consectetur, adipisci velit, sed quia non numquam', now()),
       (1, 5, 'nisi ut aliquid ex ea commodi consequatur? Quis autem', now()),
       (2, 5, 'fugiat quo voluptas nulla pariatur?', now()),
       (2, 3, 'magnam aliquam quaerat voluptatem. Ut enim ad', now()),
       (4, 2, 'nisi ut aliquid ex ea commodi consequatu', now()),
       (4, 5, 'vel illum qui dolorem eum fugiat', now());