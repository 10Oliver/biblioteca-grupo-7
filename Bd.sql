DROP DATABASE IF EXISTS Biblioteca;
CREATE DATABASE Biblioteca;

USE Biblioteca;

CREATE TABLE Roles (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       NombreRol VARCHAR(255)
);
CREATE TABLE Usuarios (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          NombreUsuario VARCHAR(255),
                          Contrasena VARCHAR(255),
                          Correo VARCHAR(255),
                          FechaNacimiento DATE,
                          PassTemporal VARCHAR(255),
                          Telefono INT,
                          idRol INT,
                          FOREIGN KEY (idRol) REFERENCES Roles(id)
);



CREATE TABLE ParametrosMora (
                                idParametros INT PRIMARY KEY AUTO_INCREMENT,
                                Mora FLOAT,
                                MaxPrestamo INT,
                                idRol INT,
                                FOREIGN KEY (idRol) REFERENCES Roles(id)
);

CREATE TABLE Prestamos (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           idUsuario INT,
                           FechaPrestamo DATE,
                           FechaDevolucion DATE,
                           FechaDevolucionReal DATE,
                           Mora FLOAT,
                           CodigoEjemplar VARCHAR(255),
                           FOREIGN KEY (idUsuario) REFERENCES Usuarios(id)
);

CREATE TABLE Estantes (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          NombreEstante VARCHAR(255)
);

CREATE TABLE Ebooks (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        CodigoIdentificacion VARCHAR(255),
                        Titulo VARCHAR(255),
                        Autor VARCHAR(255),
                        Editorial VARCHAR(255),
                        NumeroPaginas VARCHAR(255),
                        URL VARCHAR(255),
                        ISBN INT,
                        Edicion VARCHAR(255),
                        LugarPublicacion VARCHAR(255),
                        FechaPublicacion DATE,
                        Genero VARCHAR(255),
                        Idioma VARCHAR(255),
                        Notas VARCHAR(255),
                        Stock INT,
                        idEstante INT,
                        FOREIGN KEY (idEstante) REFERENCES Estantes(id)
);

CREATE TABLE Libros (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        CodigoIdentificacion VARCHAR(255),
                        Titulo VARCHAR(255),
                        Autor VARCHAR(255),
                        Editorial VARCHAR(255),
                        NumeroPaginas INT,
                        ISBN INT,
                        Edicion VARCHAR(255),
                        LugarPublicacion VARCHAR(255),
                        FechaPublicacion DATE,
                        Genero VARCHAR(255),
                        Idioma VARCHAR(255),
                        Notas LONGTEXT,
                        Stock INT,
                        idEstante INT,
                        FOREIGN KEY (idEstante) REFERENCES Estantes(id)
);

CREATE TABLE Periodicos (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            CodigoIdentificacion VARCHAR(255),
                            Titulo VARCHAR(255),
                            NombrePeriodico VARCHAR(255),
                            FechaPublicacion DATE,
                            NumeroPaginas INT,
                            LugarPublicacion VARCHAR(255),
                            Stock INT,
                            idEstante INT,
                            FOREIGN KEY (idEstante) REFERENCES Estantes(id)
);

CREATE TABLE Tesis (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       CodigoIdentificacion VARCHAR(255),
                       Titulo VARCHAR(255),
                       Autor VARCHAR(255),
                       FechaPublicacion DATE,
                       NumeroPaginas INT,
                       Editorial VARCHAR(255),
                       NivelAcademico VARCHAR(255),
                       InstitucionAcademica VARCHAR(255),
                       Facultad VARCHAR(255),
                       Stock INT,
                       idEstante INT,
                       FOREIGN KEY (idEstante) REFERENCES Estantes(id)
);

CREATE TABLE Peliculas (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           CodigoIdentificacion VARCHAR(255),
                           Titulo VARCHAR(255),
                           Director VARCHAR(255),
                           Duracion VARCHAR(255),
                           Tipo VARCHAR(255),
                           Productor VARCHAR(255),
                           PaisCiudad VARCHAR(255),
                           FechaPublicacion DATE,
                           Stock INT,
                           idEstante INT,
                           FOREIGN KEY (idEstante) REFERENCES Estantes(id)
);

CREATE TABLE Cds (
                     id INT PRIMARY KEY AUTO_INCREMENT,
                     CodigoIdentificacion VARCHAR(255),
                     Titulo VARCHAR(255),
                     Autor VARCHAR(255),
                     NumCanciones VARCHAR(255),
                     Genero VARCHAR(255),
                     FechaPublicacion DATE,
                     Stock INT,
                     idEstante INT,
                     FOREIGN KEY (idEstante) REFERENCES Estantes(id)
);

CREATE TABLE Revistas (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          CodigoIdentificacion VARCHAR(255),
                          Titulo VARCHAR(255),
                          Autor VARCHAR(255),
                          NumeroPaginas INT,
                          ISBN INT,
                          Editorial VARCHAR(255),
                          Periodicidad VARCHAR(255),
                          FechaPublicacion DATE,
                          PaisCiudad VARCHAR(255),
                          Notas VARCHAR(255),
                          Stock INT,
                          idEstante INT,
                          FOREIGN KEY (idEstante) REFERENCES Estantes(id)
);

-- Trigger for Libros
DELIMITER //
CREATE TRIGGER libros_before_insert_trigger
    BEFORE INSERT ON Libros
    FOR EACH ROW
BEGIN
    DECLARE lastId INT;
    SELECT MAX(id) INTO lastId FROM Libros;
    IF lastId IS NULL THEN
        SET lastId = 0;
    END IF;
    SET NEW.CodigoIdentificacion = CONCAT('LIB', LPAD(lastId + 1, 5, '0'));
END //
DELIMITER ;

-- Trigger for Revistas
DELIMITER //
CREATE TRIGGER revistas_before_insert_trigger
    BEFORE INSERT ON Revistas
    FOR EACH ROW
BEGIN
    DECLARE lastId INT;
    SELECT MAX(id) INTO lastId FROM Revistas;
    IF lastId IS NULL THEN
        SET lastId = 0;
    END IF;
    SET NEW.CodigoIdentificacion = CONCAT('REV', LPAD(lastId + 1, 5, '0'));
END //
DELIMITER ;

-- Trigger for Tesis
DELIMITER //
CREATE TRIGGER tesis_before_insert_trigger
    BEFORE INSERT ON Tesis
    FOR EACH ROW
BEGIN
    DECLARE lastId INT;
    SELECT MAX(id) INTO lastId FROM Tesis;
    IF lastId IS NULL THEN
        SET lastId = 0;
    END IF;
    SET NEW.CodigoIdentificacion = CONCAT('TES', LPAD(lastId + 1, 5, '0'));
END //
DELIMITER ;

-- Trigger for Cds
DELIMITER //
CREATE TRIGGER cds_before_insert_trigger
    BEFORE INSERT ON Cds
    FOR EACH ROW
BEGIN
    DECLARE lastId INT;
    SELECT MAX(id) INTO lastId FROM Cds;
    IF lastId IS NULL THEN
        SET lastId = 0;
    END IF;
    SET NEW.CodigoIdentificacion = CONCAT('CDA', LPAD(lastId + 1, 5, '0'));
END //
DELIMITER ;


-- Trigger for Peliculas
DELIMITER //
CREATE TRIGGER peliculas_before_insert_trigger
    BEFORE INSERT ON Peliculas
    FOR EACH ROW
BEGIN
    DECLARE lastId INT;
    SELECT MAX(id) INTO lastId FROM Peliculas;
    IF lastId IS NULL THEN
        SET lastId = 0;
    END IF;
    SET NEW.CodigoIdentificacion = CONCAT('PEL', LPAD(lastId + 1, 5, '0'));
END //
DELIMITER ;

-- Trigger for Ebooks
DELIMITER //
CREATE TRIGGER ebooks_before_insert_trigger
    BEFORE INSERT ON Ebooks
    FOR EACH ROW
BEGIN
    DECLARE lastId INT;
    SELECT MAX(id) INTO lastId FROM Ebooks;
    IF lastId IS NULL THEN
        SET lastId = 0;
    END IF;
    SET NEW.CodigoIdentificacion = CONCAT('EBK', LPAD(lastId + 1, 5, '0'));
END //
DELIMITER ;

-- Trigger for Periodicos
DELIMITER //
CREATE TRIGGER periodicos_before_insert_trigger
    BEFORE INSERT ON Periodicos
    FOR EACH ROW
BEGIN
    DECLARE lastId INT;
    SELECT MAX(id) INTO lastId FROM Periodicos;
    IF lastId IS NULL THEN
            SET lastId = 0;
    END IF;
    SET NEW.CodigoIdentificacion = CONCAT('PER', LPAD(lastId + 1, 5, '0'));
END //
DELIMITER ;



INSERT INTO Roles (NombreRol)
VALUES ('Admin');

INSERT INTO Roles (NombreRol)
VALUES ('Profesor');

INSERT INTO Roles (NombreRol)
VALUES ('Alumno');