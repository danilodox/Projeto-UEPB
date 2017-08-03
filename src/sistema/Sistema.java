package sistema;

import java.util.HashMap;
import java.util.Map;

import excecoes.ValueInvalidException;
import validacao.ValidationModule;
import imovel.Imovel;
import imovel.ImovelFactory;
import usuario.Usuario;

/**
 * Classe que encapsula os m�todos das demais classes. Aqui � implementado todos os
 * tratamento de erros.
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */

public class Sistema {
	private final String SENHA_DO_ADM = "uepb";
	private String loginDoUsuarioLogado;
	private Usuario usuarioLogado;
	private boolean isLogado;
	private ImovelFactory fabricaDeImoveis;		
	private Map<String, Imovel> bancoDeImoveisParaVenda;
	private Map<String, Imovel> bancoDeImoveisParaAlugar;
	private Map<String, Usuario> bancoDeUsuarios; 
	private Map<String, String> bancoDeSenhas;
	
	/**
	 * Construtor da classe Sistema.
	 *  
	 */
	public Sistema(){
		this.isLogado = false;
		this.fabricaDeImoveis = new ImovelFactory(); 
		this.bancoDeImoveisParaVenda = bancoDeImoveisParaVendaDefault();
		this.bancoDeImoveisParaAlugar = new HashMap<>();
		this.bancoDeUsuarios = bancoDeUsuariosDefault();
		this.bancoDeSenhas = bancoDeSenhasDefault();
	}
	
	/**
	 * M�todo get
	 * @return
	 */
	public boolean isLogado(){
		return this.isLogado;
	}
	
	/**
	 * Recebe como par�metro a senha do adm (apenas com o acesso dessa senha que pode adicionar algum valor), 
	 * 		o login (para adicionar o dinheiro no usuario com o nome do login) e 
	 * 		o valor (a quantidade do dinheiro a ser adicionado).
	 * 
	 * @param senhaDoADM, login, valor
	 * @throws Exception
	 */
	public void adicionaDinheiroAoUsuario(String senhaDoADM, String login, double valor) throws Exception{
		ValidationModule.analyzeString(login, "Login inv�lido");
		ValidationModule.analizeSenhaADM(senhaDoADM);
		ValidationModule.analizeValue(valor, "O valor a ser adicionado n�o pode ser negativo");
		ValidationModule.analizeBooleanFalse(bancoDeUsuarios.containsKey(login), "Login inv�lido");
		
		bancoDeUsuarios.get(login).adicionaDinheiro(valor);
		
	}
	
	/**
	 * Cria o objeto usuario, adiciona o login e senha ao banco de senhas e
	 * 		adiciona o usuario ao Map banco de usuarios.
	 * 
	 * @param nome, login, cpf, senha
	 * @return
	 * @throws Exception
	 */
	public boolean criaContaDeUsuario(String nome, int idade, String login, String cpf, String senha) throws Exception{
		ValidationModule.analizeAccount(nome, cpf, idade);
		ValidationModule.analyzeString(login, "Login inv�lido");
		ValidationModule.analyzeString(senha, "Senha inv�lida");
		
		Usuario usuario = new Usuario(nome,idade, login, cpf, senha);
		
		ValidationModule.analizeUserPresente(bancoDeSenhas, usuario.getLogin());
		
		this.bancoDeSenhas.put(login, senha);
		this.bancoDeUsuarios.put(login, usuario);
		logar(login, senha);
		return true;
	}
	
	/**
	 * Mostra o hist�rico do usu�rio que estiver logado.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHistoricoDoUsuarioLogado() throws Exception {
		ValidationModule.analyzeString(loginDoUsuarioLogado, "Login inv�lido");
		return usuarioLogado.toString();    
	}
	
	/**
	 * Mostra o login do usu�rio logado.
	 * @return
	 * @throws Exception
	 */
	public String getLoginDoUsuarioLogado(){
		
			return this.loginDoUsuarioLogado;
	}
	
	/**
	 * Verifica se o login e senha est�o no banco de senhas e ent�o, (caso seja true) modifica o
	 * 		atributo isLogado para true, o atributo loginDoUsuarioLogado recebe o login do usuario, e 
	 * 		usuarioLogado recebe como tipo usuario o usuario que logou. 
	 * 
	 * @param login, senha
	 * @return
	 * @throws Exception
	 */
	public boolean logar(String login, String senha) throws Exception {
		ValidationModule.analyzeString(login, "Login inv�lido");
		ValidationModule.analyzeString(senha, "Senha inv�lida");
		ValidationModule.analizeUser(bancoDeSenhas, login);
		
			if (autenticaSenha(login, senha)) {
				this.isLogado = true;
				this.loginDoUsuarioLogado = login;
				this.usuarioLogado = bancoDeUsuarios.get(login);
				return true;
			}
			throw new Exception("Login ou senha incorreta!!");
	}
	
	/**
	 * Desloga do sistema, modificando os atributos para null e false.
	 */
	public void deslogar(){
		this.loginDoUsuarioLogado = null;
		this.usuarioLogado = null;
		this.isLogado = false;
	}
	
	 /**
	  * Verifica se o login existe no banco de senhas e compara se a senha passada
	  * 	como par�metro � correspondente com a chave do usu�rio. 
	  * @param login
	  * @param senha
	  * @return
	  * @throws Exception
	  */
	private boolean autenticaSenha(String login, String senha) throws Exception{
		ValidationModule.analyzeString(login, "Login inv�lido");
		ValidationModule.analyzeString(senha, "Senha inv�lida");
		
		return bancoDeSenhas.get(login).equals(senha);
	}
	
	/**
	 * Cria o objeto imovel, verifica se o usuario tem saldo suficiente para fazer a compra,
	 * 		efetua o pagamento adiquindo o valor com um getPreco,
	 * 		guarda o imovel comprado no Map bancoDeImoveisParaVenda do novo usuario que comprou o imovel.
	 * 
	 * Modifica o valores do imovel comprado para o novo dono do im�vel,
	 * 		adiciona o dinheiro ao antigo dono do imovel e
	 * 		remove o imovel do usuario que era dono antes.
	 * 
	 * @param codigoDoImovel
	 * @return
	 * @throws Exception
	 */
	public boolean comprarImovel(String codigoDoImovel) throws Exception {
		ValidationModule.analyzeString(codigoDoImovel, "C�digo do im�vel inv�lido");
		ValidationModule.analizeImmobileVendaPresent(bancoDeImoveisParaVenda, codigoDoImovel);
		
		Imovel imovel = bancoDeImoveisParaVenda.get(codigoDoImovel);
		
		ValidationModule.analizeCash(usuarioLogado.getSaldoNaConta(), imovel.getPreco());
		
		usuarioLogado.efetuaPagamento(imovel.getPreco());
		usuarioLogado.adicionaImovelParaVenda(imovel); //GUARDA O IMOVEL COMPRADO NO MAP bancoDeImoveisParaVenda DO NOV USUARIO QUE EST� COMPRANDO
		usuarioLogado.incrementaNumeroDeCompras(); 
		
		String nomeDoProprietario = imovel.getNomeDoProprietario();
		for (Usuario usuario : bancoDeUsuarios.values()){
			
			if (usuario.getNome().equals(nomeDoProprietario)){//PROCURA O USUARIO DONO DO IMOVEL Q ACABOU SENDO VENDIDO
				
				imovel.setProprietario(usuarioLogado.getNome());
				adicionaDinheiroAoUsuario(SENHA_DO_ADM, usuario.getLogin(), imovel.getPreco());//ADICIONA DINHEIRO AO USUARIO Q FOI ACHADO(DONO DO IMOVEL Q ACABOU DE VENDER)
				usuario.removeImovelParaVenda(codigoDoImovel);//J� QUE FOI COMPRADO O IMOVEL, REMOVE O IMOVEL DO MAP
				removeImovel(codigoDoImovel);
				return true;
			}
		}
		removeImovel(codigoDoImovel);
		return true;
	}
	
	
	/**
	 * Aluga im�vel de terceiros, armazena o imovel no bancoDeImoveisParaAlugar do usuario logado e
	 * 		cadastra o imovel.
	 * 
	 * @param codigoDoImovel
	 * @return
	 * @throws Exception
	 */
	public boolean alugaImovelParaTerceiros(String codigoDoImovel) throws Exception {
		
		ValidationModule.analyzeString(codigoDoImovel, "C�digo do im�vel inv�lido");
		ValidationModule.analizeImmobileAlugar(bancoDeImoveisParaAlugar, codigoDoImovel);
		
		Imovel imovel = bancoDeImoveisParaAlugar.get(codigoDoImovel);
		
		ValidationModule.analizeCash(usuarioLogado.getSaldoNaConta(), imovel.getPreco());
		ValidationModule.analizeBoolean(imovel.isAlugado(), "Imovel j� alugado");
		ValidationModule.analizeNomeProprietario(imovel.getNomeDoProprietario(), usuarioLogado.getNome());
		
		usuarioLogado.efetuaPagamento(imovel.getPreco());
		usuarioLogado.alugaImovel(imovel);
		usuarioLogado.incrementaimoveisAlugadosInquilino();
		imovel.setAlugado(true);    
		
		for (Usuario usuario : bancoDeUsuarios.values()) {
			if (usuario.getNome().equals(imovel.getNomeDoProprietario())) {
				adicionaDinheiroAoUsuario(SENHA_DO_ADM, usuario.getLogin(), imovel.getPreco());
			}
		}
		
		removeImovelParaAlugar(codigoDoImovel);
		return true;
	}
	
	
	/**
	 * Remove um imovel por c�digo do im�vel.
	 * @param codigoDoImovel
	 */
	private void removeImovel(String codigoDoImovel) {
		bancoDeImoveisParaVenda.remove(codigoDoImovel);

	}
	
	/**
	 * Cria o objeto apartamento, adiciona apartamento ao Map(banco imovel para venda) do usuario logado.
	 * Adiciona o dinheiro da venda da casa na conta do usuario que est� logado e
	 * 	incrementa o n�mero de vendas do usu�rio logado.
	 * @param proprietario, coordenadaDeEndereco, andar, area, preco, numeroDeQuartos, vagasNaGaragem
	 * @return
	 * @throws Exception
	 */
	public boolean vendeApartamento(String coordenadaDeEndereco, int andar, double area, double preco,
			int numeroDeQuartos, int vagasNaGaragem) throws Exception {
		
		ValidationModule.analizeValue(area, "�rea do apartamento n�o pode ser menor ou igual a zero");
		ValidationModule.analizeValue(preco, "Pre�o do apartamento n�o pode ser menor ou igual a zero.");
		ValidationModule.analizeValue(numeroDeQuartos, "Numero de quartos do apartamento n�o pode ser menor ou igual a zero.");
		ValidationModule.analizeValue(andar, "O andar do apartamento n�o pode ser menor ou igual a zero.");
		ValidationModule.analyzeString(coordenadaDeEndereco, "Endere�o n�o pode ser vazio ou nulo");
		Imovel apartamento = fabricaDeImoveis.fabricaApartamento(usuarioLogado.getNome(), coordenadaDeEndereco, andar, area, preco, numeroDeQuartos, vagasNaGaragem);
		cadastraImovelParaVenda(apartamento);
		
		usuarioLogado.adicionaImovelParaVenda(apartamento);
		usuarioLogado.adicionaDinheiro(apartamento.getPreco());
		usuarioLogado.incrementaNumeroDeVendas();
		return true;
		
	}
	
	/**
	 * Adiciona o imovel para o Map bancoDeImoveisParaVenda.
	 * 
	 * @param imovel
	 * @throws Exception
	 */
	private void cadastraImovelParaVenda(Imovel imovel) throws Exception {
		ValidationModule.analizeObject(imovel, "Imovel nao pode ser null");
		ValidationModule.analizeBoolean(bancoDeImoveisParaVenda.containsKey(imovel.getCodigoUnico()), "Im�vel j� cadastrado");
		
		bancoDeImoveisParaVenda.put(imovel.getCodigoUnico(), imovel);
	}
	
	/**
	 * Cria o objeto casa, adiciona casa ao Map(banco imovel para venda) do usuario logado.
	 * Adiciona o dinheiro da venda da casa na conta do usuario que est� logado e
	 * 	incrementa o n�mero de vendas do usu�rio logado.
	 * 
	 * @param proprietario, coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem
	 * @return
	 * @throws Exception
	 */
	public boolean vendeCasa(String coordenadaDeEndereco, double area, double preco,
							int numeroDeQuartos, int vagasNaGaragem) throws Exception {
		

		ValidationModule.analizeValue(area, "�rea da casa n�o pode ser menor ou igual a zero");
		ValidationModule.analizeValue(preco, "Pre�o da casa n�o pode ser menor ou igual a zero.");
		ValidationModule.analizeValue(numeroDeQuartos, "Numero de quartos da casa n�o pode ser menor ou igual a zero.");
		ValidationModule.analyzeString(coordenadaDeEndereco, "Endere�o n�o pode ser vazio ou nulo.");
		
		Imovel casa = fabricaDeImoveis.fabricaCasa(usuarioLogado.getNome(), coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem);
		cadastraImovelParaVenda(casa);
		
		usuarioLogado.adicionaImovelParaVenda(casa);
		usuarioLogado.adicionaDinheiro(casa.getPreco());
		usuarioLogado.incrementaNumeroDeVendas();
		
		return true;
		
	}
	
	
	/**
	 * Recupera os imoveis que est�o no Map bancoDeImoveisParaVenda por faixa de pre�o.
	 * 
	 * @param precoMinimo
	 * @param precoMaximo
	 * @return
	 * @throws ValueInvalidException 
	 */
	public String getImovelParaVendaPorFaixaDePreco(double precoMinimo, double precoMaximo) throws ValueInvalidException {
		ValidationModule.analizeValue(precoMaximo, "Pre�o m�ximo n�o pode ser zero ou negativo!");
		ValidationModule.analizeValue(precoMinimo, "Pre�o m�nimo n�o pode ser zero ou negativo!");
		
		String imoveisEmString = "";
		for (Imovel imovel : bancoDeImoveisParaVenda.values()){
			
			if (imovel.getPreco() >= precoMinimo && imovel.getPreco() <= precoMaximo && !imovel.isAlugado()) {
				imoveisEmString += imovel.toString() + "\n";
			}
		}
		return imoveisEmString;
	}
	
	
	/**
	 * Recupera os imoveis que est�o no Map bancoDeImoveisParaVenda por n�mero de c�modos.
	 * 
	 * @param numeroMinimoDeComodos
	 * @param numeroMaximoDeComodos
	 * @return
	 * @throws ValueInvalidException 
	 */
	public String getImovelParaVendaPorNumeroDeComodos(int numeroMinimoDeComodos, int numeroMaximoDeComodos) throws ValueInvalidException {
		ValidationModule.analizeInteger(numeroMinimoDeComodos, "O n�mero m�nimo de c�modos � 1, tente novamente!");
		ValidationModule.analizeInteger(numeroMaximoDeComodos, "O n�mero m�ximo de c�modos n�o pode ser zero ou negativo, tente novamente!");
		
		String imoveisEmString = "";
		for (Imovel imovel : bancoDeImoveisParaVenda.values()) {
			
			if (imovel.getNumeroDeQuartos() >= numeroMinimoDeComodos && imovel.getNumeroDeQuartos() <= numeroMaximoDeComodos && !imovel.isAlugado()){
				imoveisEmString += imovel.toString() + "\n";
			}
		}
		return imoveisEmString;
	}
	
	
	/**
	 * Cria o objeto apartamento, verifica se j� existe este apartamento no banco de imoveis,
	 * 		adiciona o apartamento ao Map bancoDeImoveisParaAlugar, adiciona dinheiro ao
	 * 		proprietario, incrementa no hist�rico e adiciona o im�vel no Map do usuario logado. 
	 * 		
	 * @param proprietario, coordenadaDeEndereco, andar, area, preco, numeroDeQuartos, vagasNaGaragem
	 * @return
	 * @throws Exception
	 */
	public boolean ApParaAlugarDeProprietario(String coordenadaDeEndereco, int andar, double area, double preco,
				 int numeroDeQuartos, int vagasNaGaragem) throws Exception {
		
		ValidationModule.analizeValue(area, "�rea do apartamento n�o pode ser menor ou igual a zero");
		ValidationModule.analizeValue(preco, "Pre�o do apartamento n�o pode ser menor ou igual a zero.");
		ValidationModule.analizeValue(numeroDeQuartos, "Numero de quartos do apartamento n�o pode ser menor ou igual a zero.");
		ValidationModule.analizeValue(andar, "O andar do apartamento n�o pode ser menor ou igual a zero.");
		
		Imovel apartamento = fabricaDeImoveis.fabricaApartamento(usuarioLogado.getNome(), coordenadaDeEndereco, andar, area, preco, numeroDeQuartos, vagasNaGaragem);
		
		if (bancoDeImoveisParaAlugar.containsKey(apartamento.getCodigoUnico())){
			throw new Exception ("Imovel j� cadastrado nos im�veis para alugar");
		}
		cadastraImovelParaALugar(apartamento);
		usuarioLogado.adicionaImovelParaAlugar(apartamento);
		return true;
	}
	
	public String getUsuarioLogado() {
		return usuarioLogado.getNome();
	}
	

	/**
	 * Remove imovel por codigoDoImovel do Map bancoDeImoveisParaAlugar.
	 * @param codigoDoImovel
	 */
	private void removeImovelParaAlugar(String codigoDoImovel) {
		bancoDeImoveisParaAlugar.remove(codigoDoImovel);
	}
	
	
	/**
	 * Cria o objeto casa, verifica se j� existe esta casa no banco de imoveis,
	 * 		adiciona a casa ao Map bancoDeImoveisParaAlugar, adiciona dinheiro ao
	 * 		proprietario, incrementa no hist�rico e adiciona o im�vel no Map do usuario logado. 
	 * 
	 * @param proprietario, coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem
	 * @return
	 * @throws Exception
	 */
	public boolean casaParaAlugarDeProprietario(String coordenadaDeEndereco, double area, double preco,
			int numeroDeQuartos, int vagasNaGaragem) throws Exception {
		
		ValidationModule.analizeValue(area, "�rea da casa n�o pode ser menor ou igual a zero");
		ValidationModule.analizeValue(preco, "Pre�o da casa n�o pode ser menor ou igual a zero.");
		ValidationModule.analizeValue(numeroDeQuartos, "Numero de quartos da casa n�o pode ser menor ou igual a zero.");
		ValidationModule.analyzeString(coordenadaDeEndereco, "Endere�o n�o pode ser vazio ou nulo.");
		
		Imovel casa = fabricaDeImoveis.fabricaCasa(usuarioLogado.getNome(), coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem);
		
		ValidationModule.analizeBoolean(bancoDeImoveisParaAlugar.containsKey(casa.getCodigoUnico()), "Imovel j� cadastrado nos im�veis para alugar");
	
		cadastraImovelParaALugar(casa);
		usuarioLogado.adicionaImovelParaAlugar(casa);
		return true; 
	}
	
	
	/**
	 * ADICIONA IM�VEL PARA ALUGAR NO MAP bandoDeImoveisParaAlugar 
	 * @param imovel
	 * @throws Exception
	 */
	private void cadastraImovelParaALugar(Imovel imovel) throws Exception{
		ValidationModule.analizeObject(imovel, "Imovel nao pode ser null");
		ValidationModule.analizeBoolean(bancoDeImoveisParaAlugar.containsKey(imovel.getCodigoUnico()), "Imovel ja cadastrado");

		usuarioLogado.incrementaimoveisAlugadosLocador();
		bancoDeImoveisParaAlugar.put(imovel.getCodigoUnico(), imovel);
	}
	
	
	/**
	 * MOSTRA IM�VEL PARA ALUGAR POR FAIXA DE PRE�O
	 * @param precoMinimo
	 * @param precoMaximo
	 * @return
	 * @throws ValueInvalidException 
	 */
	public String getImovelParaAlugarPorFaixaDePreco(double precoMinimo, double precoMaximo) throws ValueInvalidException{
		ValidationModule.analizeValue(precoMaximo, "Pre�o m�ximo n�o pode ser zero ou negativo!");
		ValidationModule.analizeValue(precoMinimo, "Pre�o m�nimo n�o pode ser zero ou negativo!");
		String imoveisEmString = "";
		
		for (Imovel imovel : bancoDeImoveisParaAlugar.values()) {
			if (imovel.getPreco() >= precoMinimo && imovel.getPreco() <= precoMaximo && !imovel.isAlugado()){
				
				imoveisEmString += imovel.toString() + "\n";
			}
		
		}
		return imoveisEmString;
	}
	
	
	/**
	 * MOSTRA IM�VEL PARA ALUGAR POR N�MERO DE C�MODOS
	 * @param numeroMinimoDeComodos
	 * @param numeroMaximoDeComodos
	 * @return
	 * @throws ValueInvalidException 
	 */
	public String getImovelParaAlugarPorNumeroDeComodos(int numeroMinimoDeComodos, int numeroMaximoDeComodos ) throws ValueInvalidException{
		ValidationModule.analizeInteger(numeroMinimoDeComodos, "O n�mero m�nimo de c�modos � 1, tente novamente!");
		ValidationModule.analizeInteger(numeroMaximoDeComodos, "O n�mero m�ximo de c�modos n�o pode ser zero ou negativo, tente novamente!");
		String imoveisEmString = "";
		for (Imovel imovel : bancoDeImoveisParaAlugar.values()) {
			
			if (imovel.getNumeroDeQuartos() >= numeroMinimoDeComodos && imovel.getNumeroDeQuartos() <= numeroMaximoDeComodos && !imovel.isAlugado()){
				
				imoveisEmString += imovel.toString() + "\n";
			}
		}
		return imoveisEmString;
	}
	
	
	/**
	 * MOSTRA IM�VEL PARA ALUGAR POR �REA INTERNA
	 * @param areaMinima
	 * @param areaMaxima
	 * @return
	 * @throws ValueInvalidException 
	 */
	public String getImovelParaAlugarPorAreaInterna(double areaMinima, double areaMaxima) throws ValueInvalidException{
		ValidationModule.analizeValue(areaMaxima, "A �rea m�xima tem que ser maior que zero");
		ValidationModule.analizeValue(areaMinima, "A �rea m�nima tem que ser maior que zero");
		String imoveisEmString = "";
		for (Imovel imovel : bancoDeImoveisParaAlugar.values()){
			
			if (imovel.getArea() >= areaMinima && imovel.getArea() <= areaMaxima && !imovel.isAlugado()){
				imoveisEmString += imovel.toString() + "\n";
			}
		}
		return imoveisEmString;
	}
	
	
	/**
	 * M�TODO DO TIPO MAP (bancoDeImoveisParaVendaDefault) PRIVATE PARA DEIXAR ADICIONADOS OS IMOVEIS
	 * @return
	 */
	private Map<String, Imovel> bancoDeImoveisParaVendaDefault(){ 
		Map<String, Imovel> bancoDeImoveisDefault = new HashMap<>();
		
		Imovel imovel = fabricaDeImoveis.fabricaApartamento("Danilo", "Centro", 9, 12, 100000, 1, 0);
		bancoDeImoveisDefault.put(imovel.getCodigoUnico(), imovel);
		imovel = fabricaDeImoveis.fabricaApartamento("Android A", "Norte", 1, 100, 80000, 2, 2);
		bancoDeImoveisDefault.put(imovel.getCodigoUnico(), imovel);
		imovel = fabricaDeImoveis.fabricaApartamento("Android B", "Sul", 5, 150, 140000, 3, 2);
		bancoDeImoveisDefault.put(imovel.getCodigoUnico(), imovel);
		imovel = fabricaDeImoveis.fabricaCasa("Drone A", "Leste", 80, 50000, 1, 0);
		bancoDeImoveisDefault.put(imovel.getCodigoUnico(), imovel);
		imovel = fabricaDeImoveis.fabricaCasa("Drone B", "Oeste", 300, 210000, 4, 1);
		bancoDeImoveisDefault.put(imovel.getCodigoUnico(), imovel);
		
		return bancoDeImoveisDefault;
	}
	
	
	/**
	 * M�TODO DO TIPO MAP (bancoDeUsuariosDefault) PRIVATE PARA DEIXAR ADICIONADOS OS USU�RIOS
	 * @return
	 */
	private Map<String, Usuario> bancoDeUsuariosDefault() {
	
		Map<String, Usuario> bancoDeUsuariosDefault = new HashMap<>();
		
		Usuario usuario;
		
		usuario = new Usuario("Jelly Bean", 31, "jelly_bean", "12345", "android1");
		bancoDeUsuariosDefault.put(usuario.getLogin(), usuario);
		
		usuario = new Usuario("KitKat", 45, "kit_kat", "1234", "android2");
		bancoDeUsuariosDefault.put(usuario.getLogin(), usuario);
		
		usuario = new Usuario("Lollipop", 19, "lolli_pop", "123", "android3");
		bancoDeUsuariosDefault.put(usuario.getLogin(), usuario);
		
		return bancoDeUsuariosDefault;
		
	}
	
	
	/**
	 * M�TODO DO TIPO MAP (bancoDeSenhasDefault) PRIVATE PARA DEIXAR ADICIONADOS AS SENHAS E LOGIN DOS USU�RIOS ADICIONADOS
	 * @return
	 */
	private Map<String, String> bancoDeSenhasDefault(){
		Map<String, String> bancoDeSenhasDefault = new HashMap<>();
		
		bancoDeSenhasDefault.put("jelly_bean", "android1");
		bancoDeSenhasDefault.put("kit_kat", "android2");
		bancoDeSenhasDefault.put("lolli_pop", "android3");
		
		return bancoDeSenhasDefault;
	}
	
	
}
