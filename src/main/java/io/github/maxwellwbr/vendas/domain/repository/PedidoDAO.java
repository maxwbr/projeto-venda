package io.github.maxwellwbr.vendas.domain.repository;

import io.github.maxwellwbr.vendas.domain.entity.Cliente;
import io.github.maxwellwbr.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO extends JpaRepository<Pedido, Integer>
{
    List<Pedido> findByCliente(Cliente cliente);

    Optional<Pedido> findByIdFetchItens(Integer id);
}
