package imovel;


//CLASSE PARA ENCAPSULAR A CRIAÇÃO DE OBJETOS
public class ImovelFactory {
	
	//MÉTODO PARA CRIAR IMÓVEL QUANDO FOR CASA
	public Imovel fabricaCasa(String proprietario, String coordenadaDeEndereco, double area,
			double preco, int numeroDeQuartos,int vagasNaGaragem){
		
		return new Casa(proprietario, coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem);		
	}
	
	//MÉTODO PARA CRIAR IMÓVEL QUANDO FOR APARTAMENTO
	public Imovel fabricaApartamento(String proprietario, String coordenadaDeEndereço, int andar, double area,
			double preco, int numeroDeQuartos, int vagasNaGaragem){
	
		return new Apartamento(proprietario, coordenadaDeEndereço, andar, area, preco, numeroDeQuartos, vagasNaGaragem);
	}
}
