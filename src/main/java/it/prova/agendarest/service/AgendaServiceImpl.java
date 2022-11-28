package it.prova.agendarest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.repository.agenda.AgendaRepository;

@Transactional
@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;

	@Autowired
	private UtenteService utenteService;

	@Transactional(readOnly = true)
	public List<Agenda> findAll() {
		return (List<Agenda>) agendaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Agenda> findAllUser(String username) {
		return agendaRepository.mieAgende(utenteService.findByUsername(username).getId());
	}

	@Transactional(readOnly = true)
	public Agenda findByIdUser(Long id, String username) {
		return agendaRepository.findByIdAgendeMie(utenteService.findByUsername(username).getId(), id);
	}

	@Transactional(readOnly = true)
	public Agenda findById(Long id) {
		return agendaRepository.findById(id).orElse(null);
	}

	public Agenda insert(Agenda agenda, String username) {
		agenda.setUtente(utenteService.findByUsername(username));
		return agendaRepository.save(agenda);
	}

	public void delete(Long id, String username) {
		agendaRepository.deleteMiaAgenda(utenteService.findByUsername(username).getId(), id);
	}

	public Agenda update(Agenda agenda, String username) {
		agenda.setUtente(utenteService.findByUsername(username));
		return agendaRepository.save(agenda);
	}

}
