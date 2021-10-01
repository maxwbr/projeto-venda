package io.github.maxwellwbr.vendas.domain.repository;

import io.github.maxwellwbr.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer>
{
    //    @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    @Query(value = "select c from Cliente c where c.nome like :nome")
    List<Cliente> encontraPorNome(@Param("nome") String nome);

    boolean existsByNome(String nome);

    @Query(value = "delete from Cliente c where c.nome = :nome")
    @Modifying
    void deleteByNome(String nome);

    @Query("select c from Cliente c left join c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}