insert into urls(id_deleted,total_count,url,tracking_url) values (false,5,'localhost://test/1','https://make.my.url/1');
insert into urls(id_deleted,total_count,url,tracking_url) values (false,3,'localhost://test/2','https://make.my.url/2');
insert into urls(id_deleted,total_count,url,tracking_url) values (false,4,'localhost://test/3','https://make.my.url/3');
insert into urls(id_deleted,total_count,url,tracking_url) values (false,5,'localhost://test/4','https://make.my.url/4');

insert into daily_count(daily_count,date,daily_tracking_url,daily_url) values (3,'2023-10-01','localhost://test/1','https://make.my.url/1');
insert into daily_count(daily_count,date,daily_tracking_url,daily_url) values (3,'2023-10-02','localhost://test/1','https://make.my.url/1');
insert into daily_count(daily_count,date,daily_tracking_url,daily_url) values (2,'2023-10-03','localhost://test/1','https://make.my.url/1');
insert into daily_count(daily_count,date,daily_tracking_url,daily_url) values (3,'2023-10-08','localhost://test/2','https://make.my.url/2');
insert into daily_count(daily_count,date,daily_tracking_url,daily_url) values (4,'2023-10-08','localhost://test/3','https://make.my.url/3');
insert into daily_count(daily_count,date,daily_tracking_url,daily_url) values (5,CURDATE(),'localhost://test/4','https://make.my.url/4');

