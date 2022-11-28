package it.prova.agendarest.web.api;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.agendarest.dto.AgendaDTO;
import it.prova.agendarest.dto.AgendaInsertDto;
import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.service.AgendaService;
import it.prova.agendarest.web.api.exception.AgendaNotFoundException;
import it.prova.agendarest.web.api.exception.IdNotNullForInsertException;

@RestController
@RequestMapping("api/agenda")
public class AgendaController {

	@Autowired
	private AgendaService agendaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AgendaDTO insert(@Valid @RequestBody AgendaInsertDto agendaDTO, Principal principal) {
		if (agendaDTO.getId() != null) {
			throw new IdNotNullForInsertException("Trovato Id nella richiesta");
		}
		return AgendaDTO.buildDTOFromModel(agendaService.insert(agendaDTO.buildModelFromDTO(), principal.getName()));
	}

	@GetMapping
	public List<AgendaDTO> findAllUser(Principal principal) {
		return AgendaDTO.buildDTOListFromModelList(agendaService.findAllUser(principal.getName()));
	}

	@GetMapping("/{id}")
	public AgendaDTO findById(@PathVariable(required = true) Long id, Principal principal) {
		Agenda agenda = agendaService.findByIdUser(id, principal.getName());
		if (agenda == null) {
			throw new AgendaNotFoundException("Elemento non trovato");
		}
		return AgendaDTO.buildDTOFromModel(agenda);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id, Principal principal) {
		Agenda agenda = agendaService.findByIdUser(id, principal.getName());
		if (agenda == null) {
			throw new AgendaNotFoundException("Elemento non trovato");
		}
		agendaService.delete(id, principal.getName());
	}

	@PutMapping
	public AgendaDTO update(@Valid @RequestBody AgendaDTO agendaDTO, Principal principal) {
		Agenda agenda = agendaService.findByIdUser(agendaDTO.getId(), principal.getName());
		if (agenda == null) {
			throw new AgendaNotFoundException("Elemento non trovato");
		}
		return AgendaDTO.buildDTOFromModel(agendaService.update(agendaDTO.buildModelFromDTO(), principal.getName()));
	}
}
