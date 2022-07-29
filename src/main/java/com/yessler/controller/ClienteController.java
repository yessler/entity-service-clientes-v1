package com.yessler.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yessler.model.Cliente;
import com.yessler.service.ClienteService;

@RestController
@RequestMapping("/gs/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public ResponseEntity<Object> obtenerTodos() {
		List<Cliente> clientes = clienteService.obtenerTodos();

		JSONObject respuesta = new JSONObject();
		respuesta.put("msg", "ok");
		respuesta.put("body", clientes);

		return new ResponseEntity<Object>(respuesta.toMap(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> crear(@RequestBody Cliente cliente) {
		Cliente clienteCreado = clienteService.crear(cliente);

		JSONObject respuesta = new JSONObject();
		respuesta.put("msg", "ok");
		respuesta.put("body", clienteCreado);

		return new ResponseEntity<Object>(respuesta.toMap(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> actualizar(@RequestBody Cliente cliente, @PathVariable("id") String clienteId) {
		Cliente clienteActualizado = clienteService.actualizar(cliente, Integer.valueOf(clienteId));

		JSONObject respuesta = new JSONObject();
		respuesta.put("msg", clienteActualizado == null ? "Cliente no encontrado" : "ok");
		respuesta.put("body", clienteActualizado == null ? "" : clienteActualizado);

		return new ResponseEntity<Object>(respuesta.toMap(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") String clienteId) {
		Integer clienteIdEliminado = clienteService.eliminar(Integer.valueOf(clienteId));

		JSONObject respuesta = new JSONObject();
		respuesta.put("msg", clienteIdEliminado == null ? "Cliente no encontrado" : "ok");
		respuesta.put("body", clienteIdEliminado == null ? "" : clienteIdEliminado);

		return new ResponseEntity<Object>(respuesta.toMap(), HttpStatus.OK);
	}

}

