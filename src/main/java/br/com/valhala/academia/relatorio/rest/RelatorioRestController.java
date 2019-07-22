package br.com.valhala.academia.relatorio.rest;

import org.castor.core.util.Base64Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.valhala.academia.relatorio.emissor.EmissorRelatorio;
import br.com.valhala.academia.relatorio.io.Relatorio;
import br.com.valhala.academia.relatorio.io.Requisicao;

@RestController
@RequestMapping(value = "/relatorio")
public class RelatorioRestController {

	@Autowired
	private EmissorRelatorio emissor;

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Relatorio emite(@RequestBody Requisicao requisicao) throws Exception {
		byte[] json = Base64Decoder.decode(requisicao.getJson());
		return emissor.emite(requisicao.getNomeRelatorio(), json);
	}

}
