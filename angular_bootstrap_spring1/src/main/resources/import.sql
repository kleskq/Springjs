insert into User (UserName,Password,Enabled) values('user','user','1');
insert into User (UserName,Password,Enabled) values('admin','admin','1');
insert into Userrole (Role,UserId) values ('ROLE_ADMIN','2');
insert into Userrole (Role,UserId) values ('ROLE_USER','1');
insert into Category  (CategoryName) values ('fast note');
insert into Category  (CategoryName) values ('programming');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('1','title1','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('2','title2','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('3','title3','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('4','title4','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('5','title5','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('6','title6','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('7','title7','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('8','title8','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('9','title9','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('10','title10','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('11','title11','text','2014-12-27 15:40:46.959','1','1');
insert into Note (linkId,NoteTitle,NoteText,CreateDate,UserId,notes) values ('12','new title','text','2014-12-27 19:20:27.112','2','2');
insert into Rate (Rating,UserId,NoteId) values ('4','1',12);


