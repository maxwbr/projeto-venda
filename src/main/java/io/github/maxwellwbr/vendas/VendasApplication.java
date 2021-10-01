package io.github.maxwellwbr.vendas;

//import io.github.maxwellwbr.vendas.domain.entity.Cliente;
//import io.github.maxwellwbr.vendas.domain.entity.Pedido;
//import io.github.maxwellwbr.vendas.domain.repository.ClienteDAO;
//import io.github.maxwellwbr.vendas.domain.repository.PedidoDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.context.annotation.Bean;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication
{
/*	@Bean
	public CommandLineRunner init(@Autowired ClienteDAO clienteDao, @Autowired PedidoDAO pedidoDAO)
	{
		return args ->
		{
			System.out.println("Salvando clientes!");

			Cliente c1 = new Cliente("Jeane Rocha");
			Cliente c2 = new Cliente("Maxwell Rocha");
			Cliente c3 = new Cliente("Isadora Rocha");
			Cliente c4 = new Cliente("Rafael Rocha");

			clienteDao.save(c1);
			clienteDao.save(c2);
			clienteDao.save(c3);
			clienteDao.save(c4);

			Pedido p = new Pedido();
			p.setCliente(c1);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));

			pedidoDAO.save(p);

			Cliente c = clienteDao.findClienteFetchPedidos(c1.getId());
			System.out.println(c);
			System.out.println(c.getPedidos());

     		pedidoDAO.findByCliente(c1).forEach(System.out::println);
		};
	}*/

	public static void main(String[] args)
	{
		SpringApplication.run(VendasApplication.class, args);
	}

}
