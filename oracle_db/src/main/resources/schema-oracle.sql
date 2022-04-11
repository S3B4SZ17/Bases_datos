CREATE TABLE Clientes (
	idCliente int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Apellido VARCHAR2(20) NOT NULL,
	Meses_Activos INT NOT NULL,
	Correo VARCHAR2(20) NOT NULL UNIQUE,
	constraint CLIENTES_PK PRIMARY KEY (idCliente));

CREATE TABLE Matricula (
	idMatricula int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	Cliente INT NOT NULL,
	Suscripcion INT NOT NULL,
	Nutricion INT NOT NULL,
	constraint MATRICULA_PK PRIMARY KEY (idMatricula));

CREATE TABLE Suscripciones (
	idSuscripcion int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Description VARCHAR2(500) NOT NULL,
	Entrenador INT NOT NULL,
	Horario INT NOT NULL,
	Precio FLOAT(100) NOT NULL,
	constraint SUSCRIPCIONES_PK PRIMARY KEY (idSuscripcion));

CREATE TABLE Nutricion (
	idNutricion int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Descripcion VARCHAR2(500) NOT NULL,
	Nutricionista INT NOT NULL,
	constraint NUTRICION_PK PRIMARY KEY (idNutricion));

CREATE TABLE Staff (
	idStaff int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Apellido VARCHAR2(20) NOT NULL,
	Correo VARCHAR2(20) NOT NULL UNIQUE,
	Especialidad VARCHAR2(50) NOT NULL,
	Salario INT NOT NULL,
	constraint STAFF_PK PRIMARY KEY (idStaff));

CREATE TABLE Cuentas_IBAN (	
	idCuenta int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	Staff INT NOT NULL,
	constraint CUENTAS_IBAN_PK PRIMARY KEY (idCuenta));

CREATE TABLE Horarios (
	idHorario int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Descripcion VARCHAR2(500) NOT NULL,
	constraint HORARIOS_PK PRIMARY KEY (idHorario));

CREATE TABLE Salarios (
	idSalario int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
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

CREATE sequence CLIENTES_IDCLIENTE_SEQ
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  NOORDER  
  NOCYCLE
  CACHE 20;

CREATE sequence MATRICULA_IDMATRICULA_SEQ
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  NOORDER  
  NOCYCLE
  CACHE 20;

CREATE sequence SUSCRIPCIONES_IDSUSCRIPCION_SEQ
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  NOORDER  
  NOCYCLE
  CACHE 20;

CREATE sequence NUTRICION_IDNUTRICION_SEQ
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  NOORDER  
  NOCYCLE
  CACHE 20;

CREATE sequence STAFF_IDSTAFF_SEQ
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  NOORDER  
  NOCYCLE
  CACHE 20;

CREATE sequence HORARIOS_IDHORARIO_SEQ
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  NOORDER  
  NOCYCLE
  CACHE 20;

CREATE sequence CUENTAS_IBAN_IDCUENTA_SEQ
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  NOORDER  
  NOCYCLE
  CACHE 20;

CREATE sequence SALARIOS_IDSALARIO_SEQ
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  NOORDER  
  NOCYCLE
  CACHE 20;

CREATE OR REPLACE trigger BI_CLIENTES_IDCLIENTE
before insert on Clientes
for each row
begin
select CLIENTES_IDCLIENTE_SEQ.nextval into :NEW.idCliente from dual;
end;
/
CREATE OR REPLACE trigger BI_MATRICULA_IDMATRICULA
before insert on Matricula
for each row
begin
select MATRICULA_IDMATRICULA_SEQ.nextval into :NEW.idMatricula from dual;
end;
/
CREATE OR REPLACE trigger BI_SUSCRIPCIONES_IDSUSCRIPCION
before insert on Suscripciones
for each row
begin
	select SUSCRIPCIONES_IDSUSCRIPCION_SEQ.nextval into :NEW.idSuscripcion from dual;
end;
/
CREATE OR REPLACE trigger BI_NUTRICION_IDNUTRICION
before insert on Nutricion
for each row
begin 
select NUTRICION_IDNUTRICION_SEQ.nextval into :NEW.idNutricion from dual;
end;
/
CREATE OR REPLACE trigger BI_STAFF_IDSTAFF
before insert on Staff
for each row
begin
select STAFF_IDSTAFF_SEQ.nextval into :NEW.idStaff from dual;
end;
/
CREATE OR REPLACE trigger BI_HORARIOS_IDHORARIO
before insert on Horarios
for each row
begin
select HORARIOS_IDHORARIO_SEQ.nextval into :NEW.idHorario from dual;
end;
/
CREATE OR REPLACE trigger BI_SALARIOS_IDSALARIO
before insert on Salarios
for each row
begin
select SALARIOS_IDSALARIO_SEQ.nextval into :NEW.idSalario from dual;
end;
/
CREATE OR REPLACE trigger BI_CUENTAS_IBAN_IDCUENTA
before insert on Cuentas_IBAN
for each row
begin
select CUENTAS_IBAN_IDCUENTA_SEQ.nextval into :NEW.idCuenta from dual;
end;
/

--- Remember to place a '/' after the triggers to terminate them!!!!!
-- Stored PROCEDURES
--- Package definition --------------------------------


CREATE OR REPLACE PACKAGE clientes_pack AS
	procedure find_client(v_correo in Clientes.Correo%type, o_client out SYS_REFCURSOR);
	procedure add_client(
		v_nombre in Clientes.Nombre%type, 
		v_apellido in Clientes.APELLIDO%type, 
		v_meses_activos in Clientes.MESES_ACTIVOS%type,
		v_correo in Clientes.CORREO%type);
END clientes_pack;
/

CREATE OR REPLACE PACKAGE BODY clientes_pack AS
	-- Procedure find_client that will search for a client based on the email address
	-- Take into account that we should have the name of the in variable different than any column name
	procedure find_client(v_correo in Clientes.Correo%type, o_client out SYS_REFCURSOR)
	is
	--variables
	BEGIN
		OPEN o_client FOR
		SELECT * FROM Clientes WHERE CORREO = v_correo;
		-- dbms_output.put_line(resultado.NOMBRE||' '||resultado.APELLIDO||' lleva '||resultado.MESES_ACTIVOS||' meses activo.');
	EXCEPTION WHEN NO_DATA_FOUND THEN  -- catches all 'no data found' errors
		dbms_output.put_line( 'No se encontraron datos con el correo '||v_correo);
	END;

	-- Procedure add_client ----------------------------------------------------
	procedure add_client(
		v_nombre in Clientes.Nombre%type, 
		v_apellido in Clientes.APELLIDO%type, 
		v_meses_activos in Clientes.MESES_ACTIVOS%type,
		v_correo in Clientes.CORREO%type)
	is
	--variables
	BEGIN
		INSERT INTO Clientes (Nombre, Apellido, Meses_Activos, Correo)  VALUES (v_nombre, v_apellido, v_meses_activos, v_correo);
		COMMIT;
	END;
END clientes_pack; 
/

begin
	clientes_pack.find_client('sebas@example.com1');
end;
BEGIN
	clientes_pack.add_client('Jason','Todd', 35,'jason@example.com');
END;