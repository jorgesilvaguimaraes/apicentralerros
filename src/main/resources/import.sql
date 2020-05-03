insert into user (id, email, password, created_at) values (1, 'jorge@gmail.com', '123456', now());

insert into event (id, date, description, level ,origin, count_event) values (1, now(),  'tinhas mujitos outros errros', 'ERROR', 'aplicacao1', 2);

insert into log (id, event_id, log) values (1, 1, 'teste log 1');
insert into log (id, event_id, log) values (2, 1, 'teste log 2');
insert into log (id, event_id, log) values (3, 1, 'teste log 3');



