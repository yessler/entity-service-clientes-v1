package com.yessler.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yessler.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Override
	public List<Cliente> obtenerTodos() {
		try {
			FileReader reader = new FileReader("data.json");
			JSONParser jsonParser = new JSONParser();

			Object obj = jsonParser.parse(reader);

			JSONArray clientes = (JSONArray) obj;

			return clientes;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Cliente crear(Cliente cliente) {
		try {
			FileReader reader = new FileReader("data.json");
			JSONParser jsonParser = new JSONParser();

			Object obj = jsonParser.parse(reader);

			JSONArray clientes = (JSONArray) obj;

			List<Cliente> nuevosClientes = new LinkedList<Cliente>();

			for (int i = 0; i < clientes.size(); i++) {
				JSONObject clienteJSON = (JSONObject) clientes.get(i);

				long id = (long) clienteJSON.get("id");
				String nombre = (String) clienteJSON.get("nombre");
				String correo = (String) clienteJSON.get("correo");

				Cliente clienteExistente = new Cliente((int) id, nombre, correo);

				nuevosClientes.add(clienteExistente);
			}

			for (int i = 0; i < clientes.size(); i++) {
				if (i == clientes.size() - 1) {
					int autoId = i + 2;

					cliente = new Cliente(autoId, cliente.getNombre(), cliente.getCorreo());
					nuevosClientes.add(cliente);
				}
			}

			String clientesFormatoJson = new Gson().toJson(nuevosClientes.toArray());

			FileWriter file = new FileWriter("data.json");
			file.write(clientesFormatoJson);
			file.flush();
			file.close();

			return cliente;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Cliente actualizar(Cliente cliente, Integer id) {
		try {
			FileReader reader = new FileReader("data.json");
			JSONParser jsonParser = new JSONParser();

			Object obj = jsonParser.parse(reader);

			JSONArray clientes = (JSONArray) obj;

			Boolean existeCliente = false;

			for (int i = 0; i < clientes.size(); i++) {
				JSONObject clienteJSON = (JSONObject) clientes.get(i);

				long idExistente = (long) clienteJSON.get("id");

				if (id.intValue() == (int) idExistente) {
					existeCliente = true;
					break;
				}
			}

			if (!existeCliente) {
				return null;
			}

			List<Cliente> nuevosClientes = new LinkedList<Cliente>();

			for (int i = 0; i < clientes.size(); i++) {
				JSONObject clienteJSON = (JSONObject) clientes.get(i);

				long idExistente = (long) clienteJSON.get("id");
				String nombre = (String) clienteJSON.get("nombre");
				String correo = (String) clienteJSON.get("correo");

				if (id.intValue() == (int) idExistente) {
					Cliente clienteExistente = new Cliente(id, cliente.getNombre(), cliente.getCorreo());

					nuevosClientes.add(clienteExistente);
				} else {
					Cliente clienteExistente = new Cliente((int) idExistente, nombre, correo);

					nuevosClientes.add(clienteExistente);
				}
			}

			String clientesFormatoJson = new Gson().toJson(nuevosClientes.toArray());

			FileWriter file = new FileWriter("data.json");
			file.write(clientesFormatoJson);
			file.flush();
			file.close();

			return new Cliente(id, cliente.getNombre(), cliente.getCorreo());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Integer eliminar(int id) {
		try {
			FileReader reader = new FileReader("data.json");
			JSONParser jsonParser = new JSONParser();

			Object obj = jsonParser.parse(reader);

			JSONArray clientes = (JSONArray) obj;

			Boolean existeCliente = false;

			for (int i = 0; i < clientes.size(); i++) {
				JSONObject clienteJSON = (JSONObject) clientes.get(i);

				long idExistente = (long) clienteJSON.get("id");

				if (id == (int) idExistente) {
					existeCliente = true;
					break;
				}
			}

			if (!existeCliente) {
				return null;
			}

			List<Cliente> nuevosClientes = new LinkedList<Cliente>();

			for (int i = 0; i < clientes.size(); i++) {
				JSONObject clienteJSON = (JSONObject) clientes.get(i);

				long idExistente = (long) clienteJSON.get("id");
				String nombre = (String) clienteJSON.get("nombre");
				String correo = (String) clienteJSON.get("correo");

				if (id != (int) idExistente) {
					Cliente clienteExistente = new Cliente((int) idExistente, nombre, correo);

					nuevosClientes.add(clienteExistente);
				}
			}

			String clientesFormatoJson = new Gson().toJson(nuevosClientes.toArray());

			FileWriter file = new FileWriter("data.json");
			file.write(clientesFormatoJson);
			file.flush();
			file.close();

			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
