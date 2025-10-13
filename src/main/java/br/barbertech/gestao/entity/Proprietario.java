package br.barbertech.gestao.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

@Entity
@DiscriminatorValue("1")
public class Proprietario extends Usuario {

}
