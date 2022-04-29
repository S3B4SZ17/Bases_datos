INSERT INTO Clientes (Nombre, Apellido, Meses_Activos, Correo)  VALUES ('Sebastian','Zumbado', 7, 'sebas@example.com');
INSERT INTO Clientes (Nombre, Apellido, Meses_Activos, Correo)  VALUES ('Arnold','Schwarzenegger', 3455, 'arnold@example.com');

-- Horarios
INSERT INTO Horarios (NOMBRE, DESCRIPCION)  VALUES ('Diurno','De 5:00 AM - 3:00 PM');
INSERT INTO Horarios (NOMBRE, DESCRIPCION)  VALUES ('Nocturno','De 6:00 PM - 11:00 PM');
INSERT INTO Horarios (NOMBRE, DESCRIPCION)  VALUES ('Mixto','De 8:00 AM - 8:00 PM');

INSERT INTO NUTRICION (NOMBRE, DESCRIPCION, NUTRICIONISTA) VALUES ('Proteinico','Aumento de masa muscular.', 1);

INSERT INTO SUSCRIPCIONES (NOMBRE, DESCRIPCION, ENTRENADOR, HORARIO, PRECIO)
VALUES ('Standard', 'Acceso a instalaciones horario diurno con entrenador. No mediciones.', 2, 1, 20000);

INSERT INTO CLIENTES (NOMBRE, APELLIDO, MESES_ACTIVOS, CORREO)
VALUES ('Sebastian', 'Zumbado', 3, 'sebas@example.com');