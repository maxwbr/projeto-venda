package io.github.maxwellwbr.vendas.api.controller;

import io.github.maxwellwbr.vendas.domain.entity.Cliente;
import io.github.maxwellwbr.vendas.domain.repository.ClienteDAO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController
{
    private ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO)
    {
        this.clienteDAO = clienteDAO;
    }

    @GetMapping("{id}")
    public Cliente getClienteById (@PathVariable Integer id)
    {
        return clienteDAO
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado!"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente save(@RequestBody Cliente cliente)
    {
        return clienteDAO.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id)
    {
        clienteDAO
                .findById(id)
                .map(cliente ->
                {
                    clienteDAO.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,"Cliente não encontrado!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Cliente cliente)
    {
                clienteDAO
                .findById(id)
                .map(clienteExistente ->
                {
                    cliente.setId(clienteExistente.getId());
                    clienteDAO.save(cliente);
                    return clienteExistente;
                 })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado!"));
    }

    @GetMapping
    public List<Cliente> find (Cliente filtro)
    {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);

        return clienteDAO.findAll(example);


    }
}
