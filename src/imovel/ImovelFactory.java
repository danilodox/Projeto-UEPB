package imovel;


//CLASSE PARA ENCAPSULAR A CRIA��O DE OBJETOS
public class ImovelFactory {
	
	//M�TODO PARA CRIAR IM�VEL QUANDO FOR CASA
	public Imovel fabricaCasa(String proprietario, String coordenadaDeEndereco, double area,
			double preco, int numeroDeQuartos,int vagasNaGaragem){
		
		return new Casa(proprietario, coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem);		
	}
	
	//M�TODO PARA CRIAR IM�VEL QUANDO FOR APARTAMENTO
	public Imovel fabricaApartamento(String proprietario, String coordenadaDeEndere�o, int andar, double area,
			double preco, int numeroDeQuartos, int vagasNaGaragem){
	
		return new Apartamento(proprietario, coordenadaDeEndere�o, andar, area, preco, numeroDeQuartos, vagasNaGaragem);
	}
}
