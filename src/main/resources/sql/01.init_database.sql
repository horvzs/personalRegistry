IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'personalRegistry')
    BEGIN
        CREATE DATABASE personalRegistry
        END
    GO
USE personalRegistry
GO

IF OBJECT_ID('personalRegistry.dbo.person') IS NULL
    CREATE TABLE person
    (
        id   bigint identity primary key,
        name varchar(255)
    )
GO


IF OBJECT_ID('personalRegistry.dbo.address') IS NULL
CREATE TABLE address
(
    id bigint identity primary key,
    address_type varchar(255),
    city varchar(255),
    street varchar(255),
    zip_code varchar(255),
    person_id bigint
        constraint FK81ihijcn1kdfwffke0c0sjqeb
            references person
)
GO

IF OBJECT_ID('personalRegistry.dbo.contact') IS NULL
CREATE TABLE contact
(
    id bigint identity primary key,
    email varchar(255),
    phone varchar(255),
    address_id bigint
        constraint FKl0sju2uai8cgdtic18wy5hlfr
            references address
)
GO