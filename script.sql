CREATE TABLE users (
  userid SERIAL NOT NULL,
  firstname varchar(45) DEFAULT NULL,
  lastname varchar(45) DEFAULT NULL,
  dob date DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  login varchar(45) ,
  pass varchar(45) ,
   PRIMARY KEY (userid)
);
