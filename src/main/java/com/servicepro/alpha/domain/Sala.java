package com.servicepro.alpha.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "sala")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // alterado para Long (mais comum para ID auto gerado)

    private String nome;
    private String bloco;
    private String capacidade;
    private String tipo;

    @ElementCollection
    @CollectionTable(name = "equipamento_dias", joinColumns = @JoinColumn(name = "equipamento_id"))
    @Column(name = "equipamento")
    private List<String> equipamento = new ArrayList<>();

    private String status;
    private String andar;
    private String descricao;
}
