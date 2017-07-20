package usuario;

import java.util.HashMap;
import java.util.Map;

import imovel.Imovel;

public class Usuario {
	private String nome;
	private int idade;
	private String login;
	private String cpf;
	private String senha;
	private Map<String, Imovel> bancoDeImoveisParaVenda; //ATRIBUTO PARA ARMAZENAR APENAS OS IMOVEIS PARA VENDA
	private Map<String, Imovel> bancoDeImoveisParaAlugar;//ATRIBUTO PARA ARMAZENAR APENAS OS IMOVEIS PARA ALUGAR
	private Map<String, Imovel> bancoDeImoveisAlugadosPeloUsuario; //ATRIBUTOS PARA ARMAZENAR APENAS IMOVEIS ALUGADOS PELO USUARIO
	private double saldoNaConta;
	private int numeroDeVendas;
	private int numeroDeCompras;
	private int imoveisAlugadosLocador;
	private int imoveisAlugadosInquilino;

	
	//MÉTODO CONSTRUTOR DO USUARIO
	public Usuario(String nome, int idade, String login, String cpf, String senha) {
		this.nome = nome;
		this.idade = idade;
		this.login = login;
		this.cpf = cpf;
		this.senha = senha;
		this.bancoDeImoveisParaVenda = new HashMap<>();
		this.bancoDeImoveisParaAlugar = new HashMap<>();
		this.bancoDeImoveisAlugadosPeloUsuario = new HashMap<>();
		this.numeroDeVendas = 0;
		this.numeroDeCompras = 0;
		this.imoveisAlugadosLocador = 0;
		this.imoveisAlugadosInquilino = 0;
	}

	/**
	 * ADICIONA DINHEIRO NA CONTA DO USUARIO
	 * @param valor
	 * @throws Exception
	 */
	public void adicionaDinheiro(double valor) throws Exception {
		if (valor < 0) {
			throw new Exception("Valor não pode ser negativo");
		}

		this.saldoNaConta += valor;
	}

		/**
		 * EFETUA PAGAMENTO, RETIRANDO DINHEIRO DA CONTA DO USUARIO
		 * @param valor
		 * @throws Exception
		 */
	public void efetuaPagamento(double valor) throws Exception {
		if (valor < 0) {
			throw new Exception("Valor não pode ser negativo");
		}

		this.saldoNaConta -= valor;
	}

	
	/**
	 * VERIFICA SE O IMOVEL ATRIBUIDO É ACEITÁVEL PARA ALUGAR
	 * @param imovel
	 * @throws Exception
	 */
	public void alugaImovel(Imovel imovel) throws Exception {
		if (imovel == null) {
			throw new Exception("Imóvel não pode ser nulo");
		}
		if (bancoDeImoveisAlugadosPeloUsuario.containsKey(imovel.getCodigoUnico())) { 
			throw new Exception("imovel já linkado");
		}
	}

	
	/**
	 * ADICIONA IMOVEL (VENDA) AO MAP bancoDeImoveisParaVenda 
	 * @param imovel
	 * @throws Exception
	 */
	public void adicionaImovelParaVenda(Imovel imovel) throws Exception{
		if (imovel == null) {
			throw new Exception ("Imóvel não pode ser nulo");
		}
		
		if (bancoDeImoveisParaVenda.containsKey(imovel.getCodigoUnico())) {
			throw new Exception("imovel pertencente linkado");
		}
		bancoDeImoveisParaVenda.put(imovel.getCodigoUnico(), imovel);
	}
	
	
	/**
	 * ADICIONA IMOVEL (ALUGAR) AO MAP bancoDeImoveisParaAlugar
	 * @param imovel
	 * @throws Exception
	 */
	public void adicionaImovelParaAlugar (Imovel imovel) throws Exception {
		if (imovel == null) {
			throw new Exception("Imóvel não pode ser nulo");
		}
		if (bancoDeImoveisParaAlugar.containsKey(imovel.getCodigoUnico())) {
			throw new Exception("imovel pertencente linkado");
		}
		bancoDeImoveisParaAlugar.put(imovel.getCodigoUnico(), imovel);
	}
	
	
	/**
	 * REMOVE IMOVEL (VENDA) DO MAP bancoDeImoveisParaVenda
	 * @param codigoDoImovel
	 * @throws Exception
	 */
	public void removeImovelParaVenda(String codigoDoImovel) throws Exception {
		if (codigoDoImovel == null || codigoDoImovel.trim().isEmpty()) {
			throw new Exception("Codigo do imóvel não pode ser vazio ou nulo");
		}
		if (!(bancoDeImoveisParaVenda.containsKey(codigoDoImovel))) { 
			throw new Exception("Imovel nao linkado");
		}
		bancoDeImoveisParaVenda.remove(codigoDoImovel);
	}
	
	/**
	 * REMOVE IMOVEL (ALUGAR) DO MAP bancoDeImoveisParaAlugar
	 * @param codigoDoImovel
	 * @throws Exception
	 */
	public void removeImovelParaAlugar(String codigoDoImovel) throws Exception{
		if (codigoDoImovel == null || codigoDoImovel.trim().isEmpty()) {
			throw new Exception("Codigo do imóvel não pode ser vazio ou nulo");
		}
		if (!(bancoDeImoveisParaAlugar.containsKey(codigoDoImovel))) { 
			throw new Exception("Imovel nao linkado");
		}
		bancoDeImoveisParaAlugar.remove(codigoDoImovel);
	}
	
	
	public void incrementaNumeroDeVendas() {
		this.numeroDeVendas++;
	}
	
	public void incrementaNumeroDeCompras() {
		this.numeroDeCompras++;
	}
	
	public void incrementaimoveisAlugadosLocador() {
		this.imoveisAlugadosLocador++;
	}
	
	public void incrementaimoveisAlugadosInquilino() {
		this.imoveisAlugadosInquilino++;
	}
	
	/**
	 * MOSTRA O MAP bancoDeImoveisParaVenda
	 * @return
	 */
	public Map<String, Imovel> getImoveisParaVenda() { 
		Map<String, Imovel> imoveisAux = bancoDeImoveisParaVenda;
		return imoveisAux;

	}
	
	/**
	 * MOSTRA O MAP bancoDeImoveisParaAlugar
	 * @return
	 */
	public Map<String, Imovel> getImoveisParaAlugar() { 
		Map<String, Imovel> imoveisAux = bancoDeImoveisParaAlugar;
		return imoveisAux;

	}
	
	/**
	 * MOSTRA O MAP bancoDeImoveisAlugadosPeloUsuario
	 * @return
	 */
	public Map<String, Imovel> getImoveisAlugadosPeloUsuario() { 
		Map<String, Imovel> imoveisAux = bancoDeImoveisAlugadosPeloUsuario;
		return imoveisAux;

	}
	
	//MÉTODOS GETS E SETS
	
	public String getNome() {
		return nome;
	}
	
	public int getIdade() {
		return idade;
	}

	public String getLogin() {
		return login;
	}

	public String getCpf() {
		return cpf;
	}

	public String getSenha() {
		return senha;
	}
	
	public double getSaldoNaConta() {
		return saldoNaConta;
	}
	
	public int getNumeroDeVendas() {
		return numeroDeVendas;
	}

	public int getNumeroDeCompras() {
		return numeroDeCompras;
	}

	public int getImoveisAlugadosLocador() {
		return imoveisAlugadosLocador;
	}

	public int getImoveisAlugadosInquilino() {
		return imoveisAlugadosInquilino;
	}

	public int getNumeroDeImoveisParaVenda() {
		return this.bancoDeImoveisParaVenda.size();
	}
	
	public int getNumeroDeImoveisParaAlugar() {
		return this.bancoDeImoveisParaVenda.size();
	}
	
	
	/**
	 * MOSTRA O HISTÓRICO DE OPERAÇÕES REALIZADAS
	 * @return
	 */
	public String historicoDeOperacoesRealizadas() {
		return  "Imoveis vendidos: " + numeroDeVendas + "\n" 
			  + "Imoveis comprados: " + numeroDeCompras +"\n"
			  + "Imoveis alugados como locador: " + imoveisAlugadosLocador + "\n"
			  + "Imoveis alugados como inquilino: " + imoveisAlugadosInquilino + "\n";
	}
	
	/**
	 * MÉTODO ToString DO USUÁRIO
	 */
	@Override
	public String toString(){
		String usuarioEmString = "Nome: " + nome + "\n"
							   + "Idade: " + idade + "\n"
				  			   + "Login: " + login + "\n"
				  			   + "CPF: " + cpf + "\n"
				  			   + "Saldo: " + saldoNaConta + "\n"
				  			   + "Identificador Unico: " + hashCode() + "\n";
		
		if (bancoDeImoveisParaVenda.size() > 0) {
			usuarioEmString += "IMOVEIS PARA VENDA:" + "\n";	
		}
		for (Imovel imovelParaVenda: bancoDeImoveisParaVenda.values()) {
			usuarioEmString += imovelParaVenda.toString() + "\n";
		}
		
		if (bancoDeImoveisParaAlugar.size() > 0) {
			usuarioEmString += "IMOVEIS PARA ALUGAR:" + "\n";	
		}
		for (Imovel imovelParaAlugar: bancoDeImoveisParaAlugar.values()) {
			usuarioEmString += imovelParaAlugar.toString() + "\n";
		}
		
		if (bancoDeImoveisAlugadosPeloUsuario.size() > 0) {
			usuarioEmString += "IMOVEIS QUE ESTE USUARIO ALUGOU:" + "\n";	
		}
		for (Imovel imovelParaAlugar: bancoDeImoveisAlugadosPeloUsuario.values()) {
			usuarioEmString += imovelParaAlugar.toString() + "\n";
		}
		
		
		usuarioEmString += "HISTORICO:" + "\n";
		return usuarioEmString + historicoDeOperacoesRealizadas();
	}

	
	/**
	 * MÉTODO HashCode PARA GERAR UM CÓDIGO DIFERENTE PARA CADA USUÁRIO
	 */
	@Override
	public int hashCode(){
		return (nome + login + cpf + senha).hashCode(); 
	}

}


