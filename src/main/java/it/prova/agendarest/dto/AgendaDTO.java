package it.prova.agendarest.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.agendarest.model.Agenda;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgendaDTO {

	private Long id;
	private String descrizione;
	private LocalDateTime dataOraInizio;
	private LocalDateTime dataOraFine;

	@JsonIgnoreProperties(value = "agendaDTO")
	private UtenteDTO utente;

	public AgendaDTO() {
		super();
	}

	public AgendaDTO(Long id, String descrizione, LocalDateTime dataOraInizio, LocalDateTime dataOraFine,
			@NotNull(message = "{utente.notnull}") UtenteDTO utente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.utente = utente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDateTime getDataOraInizio() {
		return dataOraInizio;
	}

	public void setDataOraInizio(LocalDateTime dataOraInizio) {
		this.dataOraInizio = dataOraInizio;
	}

	public LocalDateTime getDataOraFine() {
		return dataOraFine;
	}

	public void setDataOraFine(LocalDateTime dataOraFine) {
		this.dataOraFine = dataOraFine;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public Agenda buildModelFromDTO() {
		Agenda result = new Agenda(this.id, this.descrizione, this.dataOraInizio, this.dataOraFine);
		return result;
	}

	public static AgendaDTO buildDTOFromModel(Agenda agenda) {
		AgendaDTO result = new AgendaDTO(agenda.getId(), agenda.getDescrizione(), agenda.getDataOraInizio(),
				agenda.getDataOraFine(), UtenteDTO.buildUtenteDTOFromModel(agenda.getUtente()));
		return result;
	}
	
	public static List<AgendaDTO> buildDTOListFromModelList(List<Agenda> agende) {
		return agende.stream().map(i->{return AgendaDTO.buildDTOFromModel(i);}).collect(Collectors.toList());
	}
}
