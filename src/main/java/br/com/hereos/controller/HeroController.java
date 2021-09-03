package br.com.hereos.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.hereos.dto.request.HeroRequestDTO;
import br.com.hereos.dto.response.HeroResponseDTO;
import br.com.hereos.exception.HeroNotFoundException;
import br.com.hereos.service.impl.HeroServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/hero")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Hero Controller")
@Log4j2
public class HeroController {

	private HeroServiceImpl heroService;

	@ApiOperation(value = "Cria um Herói")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Heroi criado com sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })

	@RequestMapping(method = RequestMethod.POST) // ,produces="application/json", consumes="application/json")
	public ResponseEntity<HeroResponseDTO> create(@RequestBody @Valid HeroRequestDTO heroDto) {

		HeroResponseDTO savedHero = heroService.create(heroDto);

		URI locationResource = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedHero.getId()).toUri();

		log.info("Sucesso ao criar Hero com ID " + savedHero.getId());

		return ResponseEntity.created(locationResource).body(savedHero);

	}

	@ApiOperation(value = "Retorna uma lista de herois")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista retornada com sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })

	@RequestMapping(method = RequestMethod.GET) // , produces="application/json", consumes="application/json")
	public ResponseEntity<List<HeroResponseDTO>> listAll() {

		log.info("Listando herois");

		return ResponseEntity.ok(heroService.listAll());
	}

	@ApiOperation(value = "Pesquisa um heroi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Heroi retornado com sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // , produces="application/json",
																	// consumes="application/json")
	public ResponseEntity<HeroResponseDTO> findById(@PathVariable Long id) throws HeroNotFoundException {
		log.info("Localizando heroi por Id" + id);
		return ResponseEntity.ok().body(heroService.findById(id));

	}

	@ApiOperation(value = "Exclui um heroi")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Solicitação de exclusão processada pelo servidor mas sem resposta"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) // ,produces="application/json",
																	// consumes="application/json")
	public ResponseEntity delete(@PathVariable Long id) throws HeroNotFoundException {

		heroService.delete(id);
		log.info("Sucesso em excluir heroi com Id" + id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Atualiza os dados de um heroi")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Heroi atualizadi com sucesso"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	public ResponseEntity<HeroResponseDTO> update(@PathVariable Long id, @RequestBody @Valid HeroRequestDTO heroDTO)
			throws HeroNotFoundException {

		HeroResponseDTO heroUpdate = heroService.update(heroDTO, id);
		log.info("Sucesso em atualizar heroi com Id" + id);
		return ResponseEntity.ok(heroUpdate);
	}

}
