package it.prova.agendarest.repository.agenda;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.agendarest.model.Agenda;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {

	@Query("from Agenda a left join fetch a.utente u where u.id=:id")
	public List<Agenda> mieAgende(Long id);

	@Query("from Agenda a left join fetch a.utente u where u.id=:idUtente and a.id=:idAgenda")
	public Agenda findByIdAgendeMie(Long idUtente, Long idAgenda);

	@Query(value = "delete a from agenda a inner join utente u where u.id=:idUtente and a.id=:idAgenda", nativeQuery = true)
	public void deleteMiaAgenda(Long idUtente, Long idAgenda);

}
