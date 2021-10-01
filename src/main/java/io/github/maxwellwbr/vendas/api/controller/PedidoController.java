package io.github.maxwellwbr.vendas.api.controller;

import io.github.maxwellwbr.vendas.api.dto.PedidoDTO;
import io.github.maxwellwbr.vendas.domain.entity.Pedido;
import io.github.maxwellwbr.vendas.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController
{
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService)
    {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO)
    {
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return  pedido.getId();
    }
        

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id)
    {
        return pedidoService
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado!"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido)
    {
        InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .to
    }
}
