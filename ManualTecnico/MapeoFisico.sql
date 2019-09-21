CREATE DATABASE paginaRevistas;
USE paginaRevistas;

CREATE TABLE Departamento (
    Tipo INT NOT NULL,
    Nombre VARCHAR(15) NOT NULL,
    CONSTRAINT _TIPO PRIMARY KEY(Tipo)
);
CREATE TABLE Persona (
    UserName VARCHAR(15) NOT NULL,
    Tipo INT NOT NULL,
    Contrasena VARCHAR(15) NOT NULL,
    CONSTRAINT PK_USERNAME PRIMARY KEY(userName),
    CONSTRAINT FK_DEPARTAMENTO_TIPO FOREIGN KEY(Tipo)
	REFERENCES Departamento(Tipo)
);
CREATE TABLE Perfil (
    UserName VARCHAR(15) NOT NULL,
    Foto BLOB,
    Descripcion VARCHAR(40) NOT NULL,
    TemasDeInteres VARCHAR(40) NOT NULL,
    Hobbies VARCHAR(40) NOT NULL,
    Gustos VARCHAR(40) NOT NULL,
    CONSTRAINT PK_CODIGO PRIMARY KEY(UserName),
    CONSTRAINT FK_PERSONA_CODIGO FOREIGN KEY(UserName)
	REFERENCES Persona(UserName)
	ON DELETE CASCADE
);
CREATE TABLE Categoria (
    Nombre VARCHAR(15) NOT NULL,
    CONSTRAINT PK_NOMBRE PRIMARY KEY(Nombre)

);
CREATE TABLE Revista (
    Codigo  CHAR(4)  NOT NULL,
    CostoPorSuscripcion FLOAT NOT NULL,
    UserName VARCHAR(15) NOT NULL,
    NombreCategoria VARCHAR(15) NOT NULL,
    CONSTRAINT PK_CODIGO PRIMARY KEY(Codigo),
    CONSTRAINT FK_PERSONA_USER FOREIGN KEY(UserName)
	REFERENCES Persona(UserName)
	ON DELETE CASCADE,
    CONSTRAINT _CATEGORIA_NOMBRE FOREIGN KEY(NombreCategoria)
	REFERENCES Categoria(Nombre)
);

CREATE TABLE Etiqueta (
    Nombre VARCHAR(15) NOT NULL,
    Categoria VARCHAR(15) NOT NULL,
    CONSTRAINT PK_NOMBRE PRIMARY KEY(Nombre),
    CONSTRAINT FK_CATEGORIA_ETIQUETA FOREIGN KEY(Categoria)
	REFERENCES Categoria(Nombre)
);
CREATE TABLE Suscripcion (
    Codigo  CHAR(4)  NOT NULL,
    CodigoRevista  CHAR(4)  NOT NULL,
    UserName VARCHAR(15) NOT NULL,
    Fecha DATE NOT NULL,
    CONSTRAINT PK_CODIGO PRIMARY KEY(Codigo),
    CONSTRAINT FK_REVISTA_CODIGO FOREIGN KEY(CodigoRevista)
	REFERENCES Revista(Codigo)
	ON DELETE CASCADE,
    CONSTRAINT FK_PERSONA_USERNAME FOREIGN KEY(UserName)
	REFERENCES Persona(UserName)
	ON DELETE CASCADE
);
CREATE TABLE Reaccion (
    Codigo  CHAR(5)  NOT NULL,
    CodigoRevista  CHAR(4)  NOT NULL,
    UserName VARCHAR(15) NOT NULL,
    Fecha DATE NOT NULL,
    Likes  BOOLEAN  NOT NULL,
    Comentario  VARCHAR(25)  NOT NULL,
    CONSTRAINT PK_CODIGO PRIMARY KEY(Codigo),
    CONSTRAINT FK_REVISTA_REACCION FOREIGN KEY(CodigoRevista)
	REFERENCES Revista(Codigo)
	ON DELETE CASCADE,
    CONSTRAINT FK_PERSONA_REACCION FOREIGN KEY(UserName)
	REFERENCES Persona(UserName)
	ON DELETE CASCADE
);
CREATE TABLE Pago (
    Codigo  CHAR(4)  NOT NULL,
    Fecha DATE NOT NULL,
    CodigoSuscripcion  CHAR(4)  NOT NULL,
    Tarjeta INT NOT NULL,
    Cantidad FLOAT NOT NULL,
    CONSTRAINT PK_CODIGO PRIMARY KEY(Codigo),
    CONSTRAINT FK_SUSCRIPCION_CODIGO FOREIGN KEY(CodigoSuscripcion)
	REFERENCES Suscripcion(Codigo)
);
CREATE TABLE Tag (
    Codigo  CHAR(4)  NOT NULL,
    CodigoRevista  CHAR(4)  NOT NULL,
    NombreEtiqueta VARCHAR(15) NOT NULL,
    CONSTRAINT PK_CODIGO PRIMARY KEY(Codigo),
    CONSTRAINT FK_REVISTA_TAGA FOREIGN KEY(CodigoRevista)
	REFERENCES Revista(Codigo),
    CONSTRAINT FK_ETIQUETA_NOMBRE FOREIGN KEY(NombreEtiqueta)
	REFERENCES Etiqueta(Nombre)
);
CREATE TABLE PreciosAdmin (
    Fecha  DATETIME  NOT NULL,
    CostoPorDiaGlobal  FLOAT  NOT NULL,
    CONSTRAINT PK_Fecha PRIMARY KEY(Fecha)

);
CREATE TABLE CostosRevistas (
    Fecha  DATETIME  NOT NULL,
    CodigoRevista  CHAR(4)  NOT NULL,
    CostoPorDia  FLOAT  NOT NULL,
    CONSTRAINT PK_Fecha PRIMARY KEY(Fecha),
    CONSTRAINT FK_REVISTA_PRECIO FOREIGN KEY(CodigoRevista)
	REFERENCES Revista(Codigo)
);
CREATE TABLE Edicion(
     NumeroEd INT NOT NULL,
    CodigoRevista  CHAR(4)  NOT NULL,
    Archivo BLOB NOT NULL,
    Fecha  DATETIME  NOT NULL,
    CONSTRAINT PK_Fecha PRIMARY KEY(NumeroEd,CodigoRevista),
    CONSTRAINT FK_REVISTA_EDICION FOREIGN KEY(CodigoRevista)
	REFERENCES Revista(Codigo)
);
