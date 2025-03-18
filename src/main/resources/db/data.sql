CREATE TABLE book_authors
(
    author_id INT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(255)       NULL,
    CONSTRAINT pk_book_authors PRIMARY KEY (author_id)
);

CREATE TABLE book_categories
(
    category_id INT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255)       NULL,
    CONSTRAINT pk_book_categories PRIMARY KEY (category_id)
);

CREATE TABLE books
(
    offer_id         INT AUTO_INCREMENT NOT NULL,
    title            VARCHAR(255)       NULL,
    rating           DOUBLE             NULL,
    category_id      INT                NULL,
    accepted_user_id VARCHAR(255)       NULL,
    profile_username VARCHAR(255)       NULL,
    CONSTRAINT pk_books PRIMARY KEY (offer_id)
);

CREATE TABLE books_authors
(
    book_author_id INT NOT NULL,
    book_offer_id  INT NOT NULL
);

CREATE TABLE books_requesting_users
(
    book_offer_id      INT          NOT NULL,
    requesting_user_id VARCHAR(255) NOT NULL
);

CREATE TABLE profiles_authors
(
    book_author_id  INT          NOT NULL,
    user_profile_id VARCHAR(255) NOT NULL
);

CREATE TABLE profiles_categories
(
    book_category_id INT          NOT NULL,
    user_profile_id  VARCHAR(255) NOT NULL
);

CREATE TABLE user_profiles
(
    username  VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NULL,
    age       INT          NULL,
    CONSTRAINT pk_user_profiles PRIMARY KEY (username)
);

CREATE TABLE users
(
    username VARCHAR(255) NOT NULL,
    password VARCHAR(250) NULL,
    `role`   VARCHAR(255) NULL,
    CONSTRAINT pk_users PRIMARY KEY (username)
);

ALTER TABLE books
    ADD CONSTRAINT uc_books_accepted_user UNIQUE (accepted_user_id);

ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_ACCEPTED_USER FOREIGN KEY (accepted_user_id) REFERENCES user_profiles (username);

ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES book_categories (category_id);

ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_PROFILE_USERNAME FOREIGN KEY (profile_username) REFERENCES user_profiles (username);

ALTER TABLE books_authors
    ADD CONSTRAINT fk_booaut_on_book FOREIGN KEY (book_offer_id) REFERENCES books (offer_id);

ALTER TABLE books_authors
    ADD CONSTRAINT fk_booaut_on_book_author FOREIGN KEY (book_author_id) REFERENCES book_authors (author_id);

ALTER TABLE books_requesting_users
    ADD CONSTRAINT fk_boorequse_on_book FOREIGN KEY (book_offer_id) REFERENCES books (offer_id);

ALTER TABLE books_requesting_users
    ADD CONSTRAINT fk_boorequse_on_user_profile FOREIGN KEY (requesting_user_id) REFERENCES user_profiles (username);

ALTER TABLE profiles_authors
    ADD CONSTRAINT fk_proaut_on_book_author FOREIGN KEY (book_author_id) REFERENCES book_authors (author_id);

ALTER TABLE profiles_authors
    ADD CONSTRAINT fk_proaut_on_user_profile FOREIGN KEY (user_profile_id) REFERENCES user_profiles (username);

ALTER TABLE profiles_categories
    ADD CONSTRAINT fk_procat_on_book_category FOREIGN KEY (book_category_id) REFERENCES book_categories (category_id);

ALTER TABLE profiles_categories
    ADD CONSTRAINT fk_procat_on_user_profile FOREIGN KEY (user_profile_id) REFERENCES user_profiles (username);
