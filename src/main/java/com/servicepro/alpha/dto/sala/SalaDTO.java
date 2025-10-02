package com.servicepro.alpha.dto.sala;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class SalaDTO {
     String nome;
     String bloco;
     String capacidade;
     String tipo;

     List<String> equipamento;

     String status;
     String andar;
     String descricao;
}
