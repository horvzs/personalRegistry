insert into person (name)
values ('Jon Doe');
insert into dbo.address (address_type, city, street, zip_code, person_id)
values ('PERMANENT', 'Budapest', 'Jon Doe street 20/A', '1000', '1');
insert into contact (email, phone, address_id)
values ('jonD@email.com', '06301112222', '1');