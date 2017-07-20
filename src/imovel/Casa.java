package imovel;

public class Casa extends Imovel {
	
	//MÉTODO CONSTRUTOR
	public Casa(String nomeDoProprietario, String coordenadaDeEndereco, double area, double preco, int numeroDeQuartos,
			int vagasNaGaragem) {
		super(nomeDoProprietario, coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem);
		
	}
	
	
	//MÉTODO TO STRING
	@Override
	public String toString() {
		String casaEmString = "";
		
		casaEmString += "Codigo do imóvel: " + getCodigoUnico() + "\n";
		casaEmString += "Proprietario: " + getNomeDoProprietario() + "\n";
		casaEmString += "Area: " +  getArea() + "\n";
		casaEmString += "Numero de quartos: " + getNumeroDeQuartos() + "\n";
		casaEmString += "Vagas na garagem: " + getVagasNaGaragem() + "\n";
		casaEmString += "Preco: " + getPreco() + "\n";
		
		return casaEmString;
	}

}
