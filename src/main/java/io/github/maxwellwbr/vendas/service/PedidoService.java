package io.github.maxwellwbr.vendas.service;

import io.github.maxwellwbr.vendas.api.dto.PedidoDTO;
import io.github.maxwellwbr.vendas.domain.entity.Pedido;

import java.util.Optional;

public interface PedidoService
{
    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
