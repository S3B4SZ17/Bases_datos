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
	Descripcion VARCHAR2(500) NOT NULL,
	Entrenador INT NOT NULL,
	Horario INT NOT NULL,
	Precio FLOAT(100) NOT NULL,
	constraint SUSCRIPCIONES_PK PRIMARY KEY (idSuscripcion));

CREATE TABLE Nutricion (
	idNutricion int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Descripcion VARCHAR2(500) NOT NULL,
	Nutricionista INT NOT NULL UNIQUE,
	constraint NUTRICION_PK PRIMARY KEY (idNutricion));

CREATE TABLE Staff (
	idStaff int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	Nombre VARCHAR2(20) NOT NULL,
	Apellido VARCHAR2(20) NOT NULL,
	Correo VARCHAR2(20) NOT NULL UNIQUE,
	Especialidad VARCHAR2(50) NOT NULL,
	constraint STAFF_PK PRIMARY KEY (idStaff));

CREATE TABLE Horarios (
	idHorario int GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	Nombre VARCHAR2(20) NOT NULL UNIQUE,
	Descripcion VARCHAR2(500) NOT NULL,
	constraint HORARIOS_PK PRIMARY KEY (idHorario));

ALTER TABLE Matricula ADD CONSTRAINT Matricula_fk0 FOREIGN KEY (Cliente) REFERENCES Clientes(idCliente);
ALTER TABLE Matricula ADD CONSTRAINT Matricula_fk1 FOREIGN KEY (Suscripcion) REFERENCES Suscripciones(idSuscripcion);
ALTER TABLE Matricula ADD CONSTRAINT Matricula_fk2 FOREIGN KEY (Nutricion) REFERENCES Nutricion(idNutricion);
ALTER TABLE Suscripciones ADD CONSTRAINT Suscripciones_fk0 FOREIGN KEY (Entrenador) REFERENCES Staff(idStaff);
ALTER TABLE Suscripciones ADD CONSTRAINT Suscripciones_fk1 FOREIGN KEY (Horario) REFERENCES Horarios(idHorario);
ALTER TABLE Nutricion ADD CONSTRAINT Nutricion_fk0 FOREIGN KEY (Nutricionista) REFERENCES Staff(idStaff);

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

--- Staff Package ---------------------------
CREATE OR REPLACE PACKAGE staff_pack AS
    procedure find_staff(v_correo in Staff.Correo%type, o_staff out SYS_REFCURSOR);
    procedure add_staff(
        v_nombre in Staff.Nombre%type,
        v_apellido in Staff.Apellido%type,
        v_correo in Staff.Correo%type,
        v_especialidad in Staff.Especialidad%type);
END staff_pack;
/

CREATE OR REPLACE PACKAGE BODY staff_pack AS

    procedure find_staff(v_correo in Staff.Correo%type, o_staff out SYS_REFCURSOR)
        is
        --variables
    BEGIN
        OPEN o_staff FOR
            SELECT * FROM Staff WHERE CORREO = v_correo;
    EXCEPTION WHEN NO_DATA_FOUND THEN  -- catches all 'no data found' errors
        dbms_output.put_line( 'No se encontraron datos con el correo '||v_correo);
    END;

    procedure add_staff(
        v_nombre in Staff.Nombre%type,
        v_apellido in Staff.Apellido%type,
        v_correo in Staff.Correo%type,
        v_especialidad in Staff.Especialidad%type)
        is
        --variables
    BEGIN
        INSERT INTO Staff (Nombre, Apellido, Correo, Especialidad)  VALUES (v_nombre, v_apellido, v_correo, v_especialidad);
        COMMIT;
    END;
END staff_pack;
/

CREATE OR REPLACE PACKAGE nutricion_pack AS
    procedure find_nutricionista(v_nombre in Nutricion.Nombre%type, o_nutricion out SYS_REFCURSOR);
    procedure add_nutricionista(
        v_nombre in Nutricion.Nombre%type,
        v_descripcioin in Nutricion.Descripcion%type,
        v_nutricionista in Nutricion.Nutricionista%type);
END nutricion_pack;
/

CREATE OR REPLACE PACKAGE BODY nutricion_pack AS

    procedure find_nutricionista(v_nombre in Nutricion.Nombre%type, o_nutricion out SYS_REFCURSOR)
        is
        --variables
    BEGIN
        OPEN o_nutricion FOR
            SELECT * FROM Nutricion WHERE Nombre LIKE '%'||v_nombre||'%';
    EXCEPTION WHEN NO_DATA_FOUND THEN  -- catches all 'no data found' errors
        dbms_output.put_line( 'No se encontraron datos con el nombre '||v_nombre);
    END;

    procedure add_nutricionista(
        v_nombre in Nutricion.Nombre%type,
        v_descripcioin in Nutricion.Descripcion%type,
        v_nutricionista in Nutricion.Nutricionista%type)
        is
        --variables
    BEGIN
        INSERT INTO Nutricion (Nombre, Descripcion, Nutricionista)  VALUES (v_nombre, v_descripcioin, v_nutricionista);
        COMMIT;
    END;
END nutricion_pack;
/

CREATE OR REPLACE PACKAGE Matricula_pack AS
    procedure find_Matricula(v_cliente in Matricula.Cliente%type, o_matricula out SYS_REFCURSOR);
    procedure add_Matricula(
        v_cliente in Matricula.Cliente%type,
        v_suscripcion in Matricula.Suscripcion%type,
        v_nutricionista in Matricula.Nutricion%type);
END Matricula_pack;
/

CREATE OR REPLACE PACKAGE BODY Matricula_pack AS

    procedure find_Matricula(v_cliente in Matricula.Cliente%type, o_matricula out SYS_REFCURSOR)
        is
        --variables
    BEGIN
        OPEN o_matricula FOR
            SELECT * FROM Matricula WHERE Cliente = v_cliente;
    EXCEPTION WHEN NO_DATA_FOUND THEN  -- catches all 'no data found' errors
        dbms_output.put_line( 'No se encontraron datos con el cliente '||v_cliente);
    END;

    procedure add_Matricula(
        v_cliente in Matricula.Cliente%type,
        v_suscripcion in Matricula.Suscripcion%type,
        v_nutricionista in Matricula.Nutricion%type)
        is
        --variables
    BEGIN
        INSERT INTO Matricula (Cliente, Suscripcion, Nutricion)  VALUES (v_cliente, v_suscripcion, v_nutricionista);
        COMMIT;
    END;
END Matricula_pack;
/

CREATE OR REPLACE PACKAGE Suscripcion_pack AS
    procedure find_Suscripcion(v_nombre in Suscripciones.Nombre%type, o_suscripcion out SYS_REFCURSOR);
    procedure add_Suscripcion(
        v_nombre in Suscripciones.Nombre%type,
        v_descripcion in Suscripciones.Descripcion%type,
        v_entrenador in Suscripciones.Entrenador%type,
        v_horario in Suscripciones.Horario%type,
        v_precio in Suscripciones.Precio%type);
END Suscripcion_pack;
/

CREATE OR REPLACE PACKAGE BODY Suscripcion_pack AS

    procedure find_Suscripcion(v_nombre in Suscripciones.Nombre%type, o_suscripcion out SYS_REFCURSOR)
        is
        --variables
    BEGIN
        OPEN o_suscripcion FOR
            SELECT * FROM Suscripciones WHERE Nombre LIKE '%'||v_nombre||'%';
    EXCEPTION WHEN NO_DATA_FOUND THEN  -- catches all 'no data found' errors
        dbms_output.put_line( 'No se encontraron datos con el nombre '||v_nombre);
    END;

    procedure add_Suscripcion(
        v_nombre in Suscripciones.Nombre%type,
        v_descripcion in Suscripciones.Descripcion%type,
        v_entrenador in Suscripciones.Entrenador%type,
        v_horario in Suscripciones.Horario%type,
        v_precio in Suscripciones.Precio%type)
        is
        --variables
    BEGIN
        INSERT INTO Suscripciones (Nombre, Descripcion, Entrenador, Horario, Precio)  VALUES (v_nombre, v_descripcion, v_entrenador, v_horario, v_precio);
        COMMIT;
    END;
END Suscripcion_pack;
/

begin
	clientes_pack.find_client('sebas@example.com1');
end;
BEGIN
	clientes_pack.add_client('Jason','Todd', 35,'jason@example.com');
END;

create or replace function f_consulta_staff(p_codigo_staff in number)
return varchar
as
    cursor c_staff is select * from Staff where idStaff = p_codigo_staff;
    v_staff Staff%rowtype;
        
begin
    open c_staff;
    fetch c_staff into v_staff;
    while (c_staff%found) loop
        return 'El empleado es ' || v_staff.Nombre || ' ' || v_staff.Apellido || 'y su ID es ' || v_Staff.idStaff;
        fetch c_staff into v_staff;
    end loop;
end;

declare
    v_resultado varchar2(100);
begin
    v_resultado := f_consulta_staff(1);
    dbms_output.put_line(v_resultado);
end;

create or replace function f_consulta_horarios(h_nombre in varchar)
return varchar
AS
    horario_row Horarios%rowtype;
    out_resp VARCHAR(200);
        
begin
    select NOMBRE, DESCRIPCION INTO horario_row.NOMBRE, horario_row.DESCRIPCION from Horarios where upper(NOMBRE) = upper(h_nombre);
    out_resp := 'Horario = ' || horario_row.NOMBRE || '. Descripcion = ' || horario_row.DESCRIPCION;
    return out_resp;
    
    EXCEPTION WHEN NO_DATA_FOUND THEN  -- catches all 'no data found' errors
        return 'No hay horarios disponibles.';
end;

-- drop procedure f_consulta_horarios;
declare
    v_resultado varchar2(100);
begin
    v_resultado := f_consulta_horarios('Diurno');
    dbms_output.put_line(v_resultado);
end;

-- Dynamic SQL
create or replace function get_PlanNutri(p_nombre in varchar2)
return varchar2
is
    nutri varchar2(100);
begin
    execute immediate 'select Descripcion from Nutricion where Nombre Like :P' into nutri using p_nombre;
    return nutri;
    EXCEPTION WHEN NO_DATA_FOUND THEN  -- catches all 'no data found' errors
        return 'No hay planes nutrionales con el nombre suministrado = '||p_nombre; 
end;

drop function get_value;

declare
    v_resultado varchar2(100);
begin
    v_resultado := get_PlanNutri('Proteinico');
  dbms_output.put_line(v_resultado);
end;
