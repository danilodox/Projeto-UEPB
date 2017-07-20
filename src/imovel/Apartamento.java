package imovel;

public class Apartamento extends Imovel{
	private int andar;
	
	//MÉTODO CONSTRUTOR COM O ATRIBUTO ADICIONAL "ANDAR" PARA DIFERENCIAR DE CASA
	public Apartamento(String nomeDoProprietario, String coordenadaDeEndereco, int andar, double area, double preco,
			int numeroDeQuartos, int vagasNaGaragem) {
		super(nomeDoProprietario, coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem);
		this.andar = andar;
	}

	//MÉTODO GET
	public int getAndar() {
		return andar;
	}
	
	
	//MÉTODO TO STRING
	public String toString(){
		
		String apartamentoEmString = "";
		
		apartamentoEmString += "Codigo do imóvel: " + getCodigoUnico() + "\n";
		apartamentoEmString += "Proprietario: " + getNomeDoProprietario() + "\n";
		apartamentoEmString += "Area: " +  getArea() + "\n";
		apartamentoEmString += "Numero de quartos: " + getNumeroDeQuartos() + "\n";
		apartamentoEmString += "Vagas na garagem: " + getVagasNaGaragem() + "\n";
		apartamentoEmString += "Preco: " + getPreco() + "\n";
		
		return apartamentoEmString;
	}

}
