package it.prova.agendarest.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.agendarest.model.Agenda;

public class AgendaInsertDto {
	private Long id;
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;
	@NotNull(message = "{dataOraInizio.notnull}")
	private LocalDateTime dataOraInizio;
	@NotNull(message = "{dataOraFine.notnull}")
	private LocalDateTime dataOraFine;

	public AgendaInsertDto() {
		super();
	}

	public AgendaInsertDto(Long id, String descrizione, LocalDateTime dataOraInizio, LocalDateTime dataOraFine) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
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

	public Agenda buildModelFromDTO() {
		Agenda result = new Agenda(this.id, this.descrizione, this.dataOraInizio, this.dataOraFine);
		return result;
	}

}
