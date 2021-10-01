package io.github.maxwellwbr.vendas.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemPedidoDTO
{
    private Integer produto;
    private Integer quantidade;
}
