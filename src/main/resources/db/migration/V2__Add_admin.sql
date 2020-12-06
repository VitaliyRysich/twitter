insert into usr (id, username, password, active)
values (1, 'admin', '$2a$08$4tWqza.xCpzsF.tyS2Yu7e5.lxpfmJIdG.uVaYMUQo52hQiI/sb4O', true);

insert into user_role(user_id, roles)
values (1, 'USER'), (1, 'ADMIN');