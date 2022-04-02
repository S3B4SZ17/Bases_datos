package com.java_oracle.project.oracle_db.Clientes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Clientes {
    @Id
    @GeneratedValue
    private Long idCliente;

    @NonNull
    private String Nombre;
    @NonNull
    private String Apellido;
    
    private int Meses_Activos;
}
