package com.yessler.service;

import java.util.List;

import com.yessler.model.Cliente;

public interface ClienteService {
	
	List<Cliente> obtenerTodos();
	Cliente crear(Cliente cliente);
	Cliente actualizar(Cliente cliente, Integer id);
	Integer eliminar(int id);

}
