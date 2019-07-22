package br.com.valhala.academia.relatorio.emissor;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.valhala.academia.relatorio.io.Relatorio;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class EmissorRelatorio {
	
	@Autowired
	private ConfiguracaoRelatorio configuracaoRelatorio;
	
	public Relatorio emite(final String nomeRelatorio, final byte[] json) throws Exception {
		
		Map<String, String> configuracao = configuracaoRelatorio.getConfiguracao();
		
		if (configuracao.containsKey(nomeRelatorio)) {
			
			try {
				String jasper = configuracao.get(nomeRelatorio);
				
				URL stream = EmissorRelatorio.class.getClassLoader().getResource(jasper);

				JRDataSource dataSource = new JsonDataSource(new ByteArrayInputStream(json));
				JasperReport report = (JasperReport) JRLoader.loadObject(stream);

				JasperPrint print = JasperFillManager.fillReport(report, new HashMap<String, Object>(), dataSource);
				byte[] arquivo = JasperExportManager.exportReportToPdf(print);

				Relatorio relatorio = Relatorio
						.builder()
						.nomeArquivo("arquivo.pdf")
						.formato("application/pdf")
						.arquivo(arquivo)
						.build();

				return relatorio;
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new IllegalArgumentException("O nome de relatorio enviado nao consta no sistema.");
		}
	}

}
