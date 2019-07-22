package br.com.valhala.academia.relatorio.emissor;

import java.io.Serializable;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "relatorio")
public class ConfiguracaoRelatorio implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, String> configuracao;

}
