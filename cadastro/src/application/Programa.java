package application;

public class Programa {
	
	public static void main(String[] args) {
		Arquivo arquivo = new Arquivo();
		Cliente cliente = new Cliente();
		cliente = arquivo.lerArquivo();
		arquivo.exportarArquivo();
		
		System.out.println("Arquivo lido com sucesso");
		System.out.println(cliente);
		
	}

}
