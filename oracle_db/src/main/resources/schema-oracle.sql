CREATE TABLE Clientes (
	idCliente INT NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Apellido VARCHAR2(20) NOT NULL,
	Meses_Activos INT NOT NULL,
	constraint CLIENTES_PK PRIMARY KEY (idCliente));

CREATE TABLE Matricula (
	idMatricula INT NOT NULL,
	Cliente INT NOT NULL,
	Suscripcion INT NOT NULL,
	Nutricion INT NOT NULL,
	constraint MATRICULA_PK PRIMARY KEY (idMatricula));

CREATE TABLE Suscripciones (
	idSuscripcion INT NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Description VARCHAR2(500) NOT NULL,
	Entrenador INT NOT NULL,
	Horario INT NOT NULL,
	Precio FLOAT(100) NOT NULL,
	constraint SUSCRIPCIONES_PK PRIMARY KEY (idSuscripcion));

CREATE TABLE Nutricion (
	idNutricion INT NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Descripcion VARCHAR2(500) NOT NULL,
	Nutricionista INT NOT NULL,
	constraint NUTRICION_PK PRIMARY KEY (idNutricion));

CREATE TABLE Staff (
	idStaff INT NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Apellido VARCHAR2(20) NOT NULL,
	Especialidad VARCHAR2(50) NOT NULL,
	Salario INT NOT NULL,
	constraint STAFF_PK PRIMARY KEY (idStaff));

CREATE TABLE Cuentas_IBAN (	
	idCuenta INT NOT NULL,
	Staff INT NOT NULL,
	constraint CUENTAS_IBAN_PK PRIMARY KEY (idCuenta));

CREATE TABLE Horarios (
	idHorario INT NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Descripcion VARCHAR2(500) NOT NULL,
	constraint HORARIOS_PK PRIMARY KEY (idHorario));

CREATE TABLE Salarios (
	idSalario INT NOT NULL,
	Monto FLOAT(100) NOT NULL,
	Cuenta_IBAN INT NOT NULL,
	constraint SALARIOS_PK PRIMARY KEY (idSalario));

ALTER TABLE Matricula ADD CONSTRAINT Matricula_fk0 FOREIGN KEY (Cliente) REFERENCES Clientes(idCliente);
ALTER TABLE Matricula ADD CONSTRAINT Matricula_fk1 FOREIGN KEY (Suscripcion) REFERENCES Suscripciones(idSuscripcion);
ALTER TABLE Matricula ADD CONSTRAINT Matricula_fk2 FOREIGN KEY (Nutricion) REFERENCES Nutricion(idNutricion);
ALTER TABLE Suscripciones ADD CONSTRAINT Suscripciones_fk0 FOREIGN KEY (Entrenador) REFERENCES Staff(idStaff);
ALTER TABLE Suscripciones ADD CONSTRAINT Suscripciones_fk1 FOREIGN KEY (Horario) REFERENCES Horarios(idHorario);
ALTER TABLE Nutricion ADD CONSTRAINT Nutricion_fk0 FOREIGN KEY (Nutricionista) REFERENCES Staff(idStaff);
ALTER TABLE Staff ADD CONSTRAINT Staff_fk0 FOREIGN KEY (Salario) REFERENCES Salarios(idSalario);
ALTER TABLE Salarios ADD CONSTRAINT Salarios_fk0 FOREIGN KEY (Cuenta_IBAN) REFERENCES Cuentas_IBAN(idCuenta);
ALTER TABLE Cuentas_IBAN ADD CONSTRAINT Cuentas_IBAN_fk0 FOREIGN KEY (Staff) REFERENCES Staff(idStaff);

CREATE sequence CLIENTES_IDCLIENTE_SEQ;

CREATE sequence MATRICULA_IDMATRICULA_SEQ;

CREATE sequence SUSCRIPCIONES_IDSUSCRIPCION_SEQ;

CREATE sequence NUTRICION_IDNUTRICION_SEQ;

CREATE sequence STAFF_IDSTAFF_SEQ;

CREATE sequence HORARIOS_IDHORARIO_SEQ;

CREATE sequence CUENTAS_IBAN_IDCUENTA_SEQ;

CREATE sequence SALARIOS_IDSALARIO_SEQ;

CREATE OR REPLACE trigger BI_CLIENTES_IDCLIENTE
  before insert on Clientes
  for each row
begin
  select CLIENTES_IDCLIENTE_SEQ.nextval into :NEW.idCliente from dual
end;

CREATE OR REPLACE trigger BI_MATRICULA_IDMATRICULA
  before insert on Matricula
  for each row
begin
  select MATRICULA_IDMATRICULA_SEQ.nextval into :NEW.idMatricula from dual
end;

CREATE OR REPLACE trigger BI_SUSCRIPCIONES_IDSUSCRIPCION
  before insert on Suscripciones
  for each row
begin
  select SUSCRIPCIONES_IDSUSCRIPCION_SEQ.nextval into :NEW.idSuscripcion from dual
end;

CREATE OR REPLACE trigger BI_NUTRICION_IDNUTRICION
  before insert on Nutricion
  for each row
begin
  select NUTRICION_IDNUTRICION_SEQ.nextval into :NEW.idNutricion from dual
end;

CREATE OR REPLACE trigger BI_STAFF_IDSTAFF
  before insert on Staff
  for each row
begin
  select STAFF_IDSTAFF_SEQ.nextval into :NEW.idStaff from dual
end;

CREATE OR REPLACE trigger BI_HORARIOS_IDHORARIO
  before insert on Horarios
  for each row
begin
  select HORARIOS_IDHORARIO_SEQ.nextval into :NEW.idHorario from dual
end;

CREATE OR REPLACE trigger BI_SALARIOS_IDSALARIO
  before insert on Salarios
  for each row
begin
  select SALARIOS_IDSALARIO_SEQ.nextval into :NEW.idSalario from dual
end;

CREATE OR REPLACE trigger BI_CUENTAS_IBAN_IDCUENTA
  before insert on Cuentas_IBAN
  for each row
begin
  select CUENTAS_IBAN_IDCUENTA_SEQ.nextval into :NEW.idCuenta from dual
end;