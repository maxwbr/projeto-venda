package io.github.maxwellwbr.vendas.domain.repository;

import io.github.maxwellwbr.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDAO extends JpaRepository <Produto, Integer>
{

}
