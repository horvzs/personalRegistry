DECLARE @PersonId int
INSERT INTO personalRegistry.dbo.person (name)
VALUES ('Jon Doe');

SET @PersonId = SCOPE_IDENTITY()

INSERT INTO personalRegistry.dbo.address (address_type, city, street, zip_code, person_id)
VALUES ('PERMANENT', 'Budapest', 'Jon Doe street 20/A', '1000', @PersonId);

INSERT INTO personalRegistry.dbo.address (address_type, city, street, zip_code, person_id)
VALUES ('TEMPORARY', 'Dabas', 'Dabasi street 21', '2050', @PersonId);

INSERT INTO personalRegistry.dbo.contact (email, phone, address_id)
VALUES ('jonD@email.com', '06301112222', @PersonId);