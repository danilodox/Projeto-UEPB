package imovel;


/**
 * Classe responsável por fabricar instancias da classe Imovel.
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */
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
