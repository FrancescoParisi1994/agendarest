package it.prova.agendarest.service;

import java.util.List;

import it.prova.agendarest.model.Agenda;

public interface AgendaService {

	public List<Agenda> findAll();
	
	public List<Agenda> findAllUser(String username);

	public Agenda findByIdUser(Long id, String username);

	public Agenda insert(Agenda agenda, String username);

	public void delete(Long id,String username);

	public Agenda update(Agenda agenda,String username);
	
	public Agenda findById(Long id);
}
