package io.github.maxwellwbr.vendas.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/*

{
    "cliente" : 1,
    "total" : 100,
    "items":[
            {
                "produto": 1,
                "quantidade": 10
            }
    ]
}

* */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO
{
    private Integer cliente;

    private BigDecimal total;

    private List<ItemPedidoDTO> items;
}
