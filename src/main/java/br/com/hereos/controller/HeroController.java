package br.com.hereos.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hereos.dto.HeroDTO;
import br.com.hereos.dto.MessageResponseDTO;
import br.com.hereos.exception.HeroNotFoundException;
import br.com.hereos.model.Hero;
import br.com.hereos.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/hero")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HeroController {

	private HeroService heroService;

//	@Autowired
//	public HeroController(HeroService heroService) {
//		this.heroService = heroService;
//	}
//	
	@ApiOperation(value = "Cria um Herói")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Heroi criado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	//@RequestMapping( method =  RequestMethod.POST, produces="application/json", consumes="application/json")
	@PostMapping
	public ResponseEntity<MessageResponseDTO> createHero(@RequestBody @Valid HeroDTO heroDto) {

		Hero savedHero = heroService.createHero(heroDto);

		return createMessageResponse("Heroi criado com sucesso", 200);

	}
	
	@ApiOperation(value = "Retorna uma lista de herois")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Lista retornada com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	
	//@RequestMapping(method =  RequestMethod.GET, produces="application/json", consumes="application/json")
	@GetMapping
	public ResponseEntity<List<HeroDTO>> listAll() {
		return new ResponseEntity<List<HeroDTO>>(heroService.listAll(), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Pesquisa um heroi")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Heroi retornado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	
	//@RequestMapping(value = "/{id}",method =  RequestMethod.GET, produces="application/json", consumes="application/json")
	@GetMapping(value = "/{id}")
	public  ResponseEntity<HeroDTO> findById(@PathVariable Long id) throws HeroNotFoundException{
		return new ResponseEntity<HeroDTO>(heroService.findById(id), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Exclui um heroi")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Solicitação de exclusão processada pelo servidor mas sem resposta"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	
	//@RequestMapping(value = "/{id}",method =  RequestMethod.DELETE, produces="application/json", consumes="application/json")
	@DeleteMapping(value = "/{id}")
	public  ResponseEntity deleteById(@PathVariable Long id) throws HeroNotFoundException{
		heroService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Atualiza os dados de um heroi")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Heroi atualizadi com sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	
	//@RequestMapping(value = "/{id}",method =  RequestMethod.PUT, produces="application/json", consumes="application/json")
	@PutMapping(value = "/{id}")
	public  ResponseEntity<MessageResponseDTO> updateById(@PathVariable Long id, @RequestBody @Valid HeroDTO heroDTO) throws HeroNotFoundException{
		
		Hero savedHero = heroService.update(id, heroDTO);

		return createMessageResponse("Heroi atualizado com sucesso", 201);
	}

	private ResponseEntity<MessageResponseDTO> createMessageResponse(String message, int statusCode) {
		return new ResponseEntity<MessageResponseDTO>(MessageResponseDTO.builder()
				.message(message).build(), HttpStatus.valueOf(statusCode));
	}
}
