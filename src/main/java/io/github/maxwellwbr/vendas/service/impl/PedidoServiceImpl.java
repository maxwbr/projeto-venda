package io.github.maxwellwbr.vendas.service.impl;

import io.github.maxwellwbr.vendas.api.dto.ItemPedidoDTO;
import io.github.maxwellwbr.vendas.api.dto.PedidoDTO;
import io.github.maxwellwbr.vendas.domain.entity.Cliente;
import io.github.maxwellwbr.vendas.domain.entity.ItemPedido;
import io.github.maxwellwbr.vendas.domain.entity.Pedido;
import io.github.maxwellwbr.vendas.domain.entity.Produto;
import io.github.maxwellwbr.vendas.domain.repository.ClienteDAO;
import io.github.maxwellwbr.vendas.domain.repository.ItemPedidoDAO;
import io.github.maxwellwbr.vendas.domain.repository.PedidoDAO;
import io.github.maxwellwbr.vendas.domain.repository.ProdutoDAO;
import io.github.maxwellwbr.vendas.exception.RegraNegocioException;
import io.github.maxwellwbr.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService

{
    private final PedidoDAO pedidoDAO;
    private final ClienteDAO clienteDAO;
    private final ProdutoDAO produtoDAO;
    private final ItemPedidoDAO itemPedidoDAO;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO)
    {
        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = clienteDAO
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedido = converterItens(pedido, pedidoDTO.getItems());
        pedidoDAO.save(pedido);
        pedido.setItens(itemPedido);
        itemPedidoDAO.saveAll(itemPedido);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id)
    {
        return pedidoDAO.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itensDTO)
    {
        if(itensDTO.isEmpty())
        {
            throw new RegraNegocioException("Não possível realizar um pedido sem itens!");
        }

        return itensDTO
                .stream()
                .map( dto ->
                {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoDAO
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: "+ idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
