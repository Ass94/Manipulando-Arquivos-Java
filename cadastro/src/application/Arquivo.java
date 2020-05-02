package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

	private String localImportacao = "C:\\Softwares\\Java Arquivos\\cadastro\\enviados\\";
	private String localExportacao = "C:\\Softwares\\Java Arquivos\\cadastro\\recebidos\\";
	private String separador_linha = "\n";

	private List<String> conteudo;

	public Arquivo() {
		this.conteudo = new ArrayList<>();
	}

	public Cliente lerArquivo() {
		File arquivo = importarArquivo();
		Cliente cliente = new Cliente();;
		try {
			long tamanhoArquivo = arquivo.length();
			if (tamanhoArquivo != 0) {
				final int MAX_SIZE = (int) tamanhoArquivo;
				char[] buffer = new char[MAX_SIZE];
				FileReader reader = new FileReader(localImportacao + arquivo.getName());
				BufferedReader leitor = new BufferedReader(reader);
				leitor.read(buffer, 0, MAX_SIZE);
				String[] linhas = new String(buffer).split("\n");
				String linha = "";
				for (String ln : linhas) {
					linha = ln;
					conteudo.add(linha);
					

					int tipo = Integer.parseInt(String.valueOf(linha.charAt(0)));

					if (tipo == 0) {
						cliente.setNome(linha.substring(1, linha.length()));
					}
					if (tipo == 1) {
						cliente.setCpf(linha.substring(1, linha.length()));
					}

					System.out.println(cliente);
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cliente;
	}

	public File importarArquivo() {
		File file = new File(localImportacao);
		File[] arquivos = file.listFiles();
		File arquivo = null;
		for (int i = 0; i < arquivos.length; i++) {
			arquivo = arquivos[i];
		}
		return arquivo;
	}

	public File exportarArquivo() {
		File arquivo = importarArquivo();
		File file = new File(localExportacao + arquivo.getName() + ".TMP");
		try {

			FileWriter writer = new FileWriter(file);
			PrintWriter saida = new PrintWriter(writer);

			for (String line : conteudo) {
				if (line.trim().length() > 0) {
					saida.write(line + separador_linha);
				}
			}

			writer.close();
			saida.close();
			file.renameTo(new File(localExportacao + arquivo.getName() + ".TXT"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return arquivo;
	}

	public String getLocalImportacao() {
		return localImportacao;
	}

	public String getLocalExportacao() {
		return localExportacao;
	}

	public String getSeparador_linha() {
		return separador_linha;
	}

	public List<String> getConteudo() {
		return conteudo;
	}

}
