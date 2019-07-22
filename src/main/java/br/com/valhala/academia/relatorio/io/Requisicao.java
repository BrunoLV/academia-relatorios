package br.com.valhala.academia.relatorio.io;

import java.io.Serializable;

import lombok.Data;

@Data
public class Requisicao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomeRelatorio;
	private String json;

}
