package com.algaworks.logistica.api.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.logistica.domain.model.Cliente;
import com.algaworks.logistica.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll(); // retorna todos clientes
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)		    // retorna apenas um cliente
					.map(ResponseEntity::ok)				    // verifica se existe o cliente e retorna um status 200
					.orElse(ResponseEntity.notFound().build()); // se o cliente nao existir, retorna um status 404
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)							// retorna o status de criação
	public Cliente adicionar(@RequestBody Cliente cliente) {	// adiciona clientes
		return clienteRepository.save(cliente);					//salva e retorna o cliente no corpo da resposta
	}
	
	@PutMapping("/{clienteId}")												// mapeia o id do cliente
	public ResponseEntity<Cliente> Atualiza(@PathVariable Long clienteId,   // @PpathVariable faz a busca do cliente
			@RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {         			// verifica se existe o cliente
			return ResponseEntity.notFound().build();          				// se nao existe retorna o status 400
		}
		
		cliente.setId(clienteId);								// faz o JPA a entender que o cliente existe
		cliente = clienteRepository.save(cliente);				// e atualiza o cliente
		
		return ResponseEntity.ok(cliente);						// retorna o status 200 
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {		// @PpathVariable faz a busca do cliente
		if (!clienteRepository.existsById(clienteId)) {         			// verifica se existe o cliente
			return ResponseEntity.notFound().build();          				// se nao existe retorna o status 404
		}
		
		clienteRepository.deleteById(clienteId);							// se existe, exclui o cliente
		
		return ResponseEntity.noContent().build();							// retorna o status 204
	}
	
}
