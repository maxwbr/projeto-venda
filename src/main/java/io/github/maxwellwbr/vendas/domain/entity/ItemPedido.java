package io.github.maxwellwbr.vendas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item_pedido")
public class ItemPedido
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "produto_id")
    @ManyToOne
    private Produto produto;

    @JoinColumn(name = "pedido_id")
    @ManyToOne
    private Pedido pedido;

    @Column(name = "quantidade")
    private Integer quantidade;
}
