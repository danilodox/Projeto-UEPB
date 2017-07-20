package imovel;

public abstract class Imovel {
	private String nomeDoProprietario;
	private boolean isAlugado;
	private double area;
	private double preco;
	private int numeroDeQuartos;
	private int vagasNaGaragem;
	private String codigoUnico;
	private String coordenadaDeEndereco;
	
	//M�TODO CONSTRUTOR
	public Imovel(String nomeDoProprietario, String coordenadaDeEndereco, double area, double preco, int numeroDeQuartos, int vagasNaGaragem){
		this.isAlugado = false;
		this.nomeDoProprietario = nomeDoProprietario;
		this.coordenadaDeEndereco = coordenadaDeEndereco;
		this.area = area;
		this.preco = preco;
		this.numeroDeQuartos = numeroDeQuartos;
		this.vagasNaGaragem = vagasNaGaragem;
		this.codigoUnico = Integer.toString(hashCode());  //C�DIGO GERADO PARA CADA IM�VEL
	}
	

	// M�TODOS GETS E SETS...
	
	public boolean isAlugado(){
		return isAlugado;
	}
	
	public String getNomeDoProprietario() {
		return nomeDoProprietario;
	}

	public String getCodigoUnico() {
		return codigoUnico;
	}
	
	public String getCoordenadaDeEndere�o() {
		return this.coordenadaDeEndereco;
	}
	
	public double getArea() {
		return area;
	}

	public double getPreco() {
		return preco;
	}
	
	public int getNumeroDeQuartos() {
		return numeroDeQuartos;
	}

	public int getVagasNaGaragem() {
		return vagasNaGaragem;
	}

	public void setProprietario(String nomeDoProprietario) {
		this.nomeDoProprietario = nomeDoProprietario;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void setAlugado(boolean isAlugado) {
		this.isAlugado = isAlugado;
	}
	
	//M�TODO HASHCODE, PARA DIFERENCIAR UM IMOVEL DO OUTRO
	
	@Override
	public int hashCode(){
		return nomeDoProprietario.hashCode() + numeroDeQuartos + vagasNaGaragem + (int) area + coordenadaDeEndereco.hashCode();
	}
	
	//M�TODO TO STRING DO IM�VEL
	
	@Override
	public String toString(){
		return"Codigo do im�vel: " + codigoUnico + "\n"
				+ "Propriet�rio: " + nomeDoProprietario + "\n"
				+ "Area: " + area + "\n"
				+ "Numero de quartos: " + "\n"
				+ "Vagas na garagem: " + vagasNaGaragem + "\n"
				+ "Pre�o: " + preco + "\n";
	}
	
}
