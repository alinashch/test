CREATE SCHEMA IF NOT EXISTS "books";
SET SEARCH_PATH TO "books";

CREATE TYPE "books"."book_status" AS ENUM ('FREE', 'TAKEN');


CREATE TABLE "books"."books"
(
    id          BIGSERIAL PRIMARY KEY,
    title       TEXT        NOT NULL,
    author      TEXT        NOT NULL,
    book_id     BIGINT UNIQUE,
    description TEXT,
    book_status varchar(50) NOT NULL,
    minimum_age INT         NOT NULL
);

