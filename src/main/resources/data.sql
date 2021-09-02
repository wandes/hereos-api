DROP TABLE IF EXISTS HERO_PHONES;
DROP TABLE IF EXISTS USER_PROFILES;
DROP TABLE IF EXISTS PHONE;
DROP TABLE IF EXISTS HERO;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS PROFILE;

CREATE TABLE PROFILE ( 
   id INT NOT NULL, 
   name VARCHAR(50)
);


CREATE TABLE USER ( 
   id INT NOT NULL, 
   name VARCHAR(50), 
   email VARCHAR(20),
   password VARCHAR(200),
   profiles_id INT NOT NULL, 
   foreign key (profiles_id) references PROFILE(id)
);


CREATE TABLE PHONE ( 
   id INT NOT NULL, 
   number VARCHAR(50), 
   type VARCHAR(20)
);


CREATE TABLE HERO ( 
   id INT NOT NULL, 
   name VARCHAR(50), 
   hero_name VARCHAR(50), 
   hero_identification VARCHAR(20),
   description VARCHAR(50),
   birth_date DATE,
   phones_id INT,
   foreign key (phones_id) references PHONE(id)
);

CREATE TABLE HERO_PHONES ( 
   hero_id INT NOT NULL, 
   phones_id INT NOT NULL,
   foreign key (hero_id) references HERO(id),
   foreign key (phones_id) references PHONE(id)
 
);

CREATE TABLE USER_PROFILES ( 
   user_id INT NOT NULL, 
   profiles_id INT NOT NULL,
   foreign key (user_id) references USER(id),
   foreign key (profiles_id) references PROFILE(id)
 
);

INSERT INTO PROFILE(id, name) VALUES(999, 'Professor');
INSERT INTO USER(id, name, email, password, profiles_id) VALUES(999, 'aizawa', 'aizawa@ua.com', '$2a$10$MZX4I/1emMsoDYCwz3rKiu0BemTbn.FA4JisBRB3Ui0XZmyKP8FGa', 999);
INSERT INTO PHONE(id, number, type) VALUES(999, '55 11987541245','MOBILE');
INSERT INTO HERO(id, name, hero_name, hero_identification, description, birth_date, phones_id) VALUES(999, 'Izuku Midoriya', 'Deku', '011', 'futuro heroi numero 1','2006-05-17', 999);
INSERT INTO HERO_PHONES(hero_id, phones_id) VALUES(999, 999);
INSERT INTO USER_PROFILES(user_id, profiles_id) VALUES(999, 999);

