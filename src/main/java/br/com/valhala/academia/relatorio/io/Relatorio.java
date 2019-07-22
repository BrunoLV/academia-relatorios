package br.com.valhala.academia.relatorio.io;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Relatorio implements Serializable {

	private static final long serialVersionUID = 1L;

	private String formato;
	private String nomeArquivo;
	private byte[] arquivo;

}
