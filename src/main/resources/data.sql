insert into usuarios (username, password, email)
values ('root', '$2a$15$dU.9zR3Ffs7Il3usU/rb8.IEOZPPRYIsE6zaRKj4u7mmy.fwDpjFu','root@email.com');
insert into roles (authority) 
values ('ROLE_NORMAL');
insert into roles_usuarios values(1,1);
