/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Author:  Natarajan Rodrigues
 * Created: 24/07/2016
 */

CREATE TABLE system_user (
    id BIGSERIAL PRIMARY KEY, 
    name VARCHAR(120) NOT NULL, 
    password VARCHAR(25) NOT NULL, 
    email VARCHAR(50) UNIQUE NOT NULL, 
    user_type INT NOT NULL
);

CREATE TABLE SpecifiedProvider (
    id SERIAL PRIMARY KEY, 
    name VARCHAR(120) NOT NULL, 
    address VARCHAR(300), 
    city VARCHAR(100), 
    state VARCHAR(2)
);

CREATE TABLE CorporationProvider (
    id int, 
    cnpj VARCHAR(18) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IndividualProvider (
    id int, 
    cpf VARCHAR(18) NOT NULL,
    nisNit VARCHAR(20),
    PRIMARY KEY (id)
);


CREATE TABLE Proposal (
    id SERIAL PRIMARY KEY, 
    name VARCHAR(200) NOT NULL, 
    group_or_artist VARCHAR(200), 
    release_text VARCHAR(2000), 
    duration int
);

CREATE TABLE Locality (
    id SERIAL PRIMARY KEY, 
    description VARCHAR(200) NOT NULL, 
    address VARCHAR(200), 
    city VARCHAR(100), 
    state VARCHAR(2)
);

CREATE TABLE Presentation (
    id SERIAL PRIMARY KEY, 
    dateTime TIMESTAMP NOT NULL,
    audience INT DEFAULT 0, 
    idLocality INT NOT NULL, 
    FOREIGN KEY (idLocality) REFERENCES Locality(id)    
);


CREATE TABLE Budget (
    id SERIAL PRIMARY KEY, 
    name VARCHAR(50)
);

CREATE TABLE Program (
    id SERIAL, 
    idBudget INT NOT NULL,  
    name VARCHAR(50), 
    FOREIGN KEY (idBudget) REFERENCES Budget(id),
    PRIMARY KEY (id)
);


CREATE TABLE Activity (
    id SERIAL PRIMARY KEY, 
    idProgram INT NOT NULL, 
    idProposal INT NOT NULL, 
    FOREIGN KEY (idProgram) REFERENCES Program(id), 
    FOREIGN KEY (idProposal) REFERENCES Proposal(id)
);

CREATE TABLE ActivityPresentation (
    idActivity INT NOT NULL, 
    idPresentation INT NOT NULL, 
    FOREIGN KEY (idActivity) REFERENCES Activity(id), 
    FOREIGN KEY (idPresentation) REFERENCES Presentation(id),
    PRIMARY KEY (idActivity, idPresentation)
);


--algumas dúvidas aqui
CREATE TABLE Rubric (
    id SERIAL, 
    idProgram INT NOT NULL, 
    name VARCHAR(50),
    total DECIMAL(10,2),
    FOREIGN KEY (idProgram) REFERENCES Program(id),
    PRIMARY KEY (id)
);


CREATE TABLE Payment (
    id SERIAL PRIMARY KEY, 
    idProvider INT NOT NULL, 
    paymentValue DECIMAL(10,2),
    description VARCHAR(600), 
    idRubric INT NOT NULL, 
    idActivity INT DEFAULT 0, 
    FOREIGN KEY (idProvider) REFERENCES SpecifiedProvider(id),
    FOREIGN KEY (idRubric) REFERENCES Rubric(id)
);


CREATE TABLE RubricPayment (
    idRubric INT NOT NULL, 
    idPayment INT NOT NULL, 
    FOREIGN KEY (idRubric) REFERENCES Rubric(id), 
    FOREIGN KEY (idPayment) REFERENCES Payment(id), 
    PRIMARY KEY (idRubric, idPayment)
);

