package sistema;

import java.util.HashMap;
import java.util.Map;

import imovel.Imovel;
import imovel.ImovelFactory;
import usuario.Usuario;

//CLASSE QUE ENCAPSULA OS MÉTODOS DO USUARIO E IMOVEL
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
	 * Método get
	 * @return
	 */
	public boolean isLogado(){
		return this.isLogado;
	}
	
	/**
	 * Recebe como parâmetro a senha do adm (apenas com o acesso dessa senha que pode adicionar algum valor), 
	 * 		o login (para adicionar o dinheiro no usuario com o nome do login) e 
	 * 		o valor (a quantidade do dinheiro a ser adicionado).
	 * 
	 * @param senhaDoADM, login, valor
	 * @throws Exception
	 */
	public void adicionaDinheiroAoUsuario(String senhaDoADM, String login, double valor) throws Exception{
		if (senhaDoADM == null || senhaDoADM.trim().isEmpty() || !senhaDoADM.equals(SENHA_DO_ADM)) {
			throw new Exception("Senha inválida");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new Exception("Login inválido");
		}
		
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
		if (nome == null | nome.trim().isEmpty()){
			throw new Exception("Nome de usuario não pode ser vazio ou nulo");
		}
		if (login == null | login.trim().isEmpty()) {
			throw new Exception("Login de usuario não pode ser vazio ou nulo");
		}
		if (cpf == null | cpf.trim().isEmpty()) {
			throw new Exception("CPF de usuario não pode ser vazio ou nulo");
		}
		if (senha == null | senha.trim().isEmpty()) {
			throw new Exception("Senha de usuario não pode ser vazio ou nulo");
		}
		
		if (idade < 18) {
			throw new Exception("O usuário não pode ser menor de idade");
		}
		
		Usuario usuario = new Usuario(nome,idade, login, cpf, senha);
		
		if (bancoDeSenhas.containsKey(usuario.getLogin())){
			throw new Exception("Usuario já cadastrado.");
		}
		
		this.bancoDeSenhas.put(login, senha);
		this.bancoDeUsuarios.put(login, usuario);
		logar(login, senha);
		return true;
	}
	
	/**
	 * Mostra o histórico do usuário que estiver logado.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHistoricoDoUsuarioLogado() throws Exception {
		if (loginDoUsuarioLogado == null) {
			throw new Exception("Não há usuário logado.");
		}
		
		return usuarioLogado.toString();    
	}
	
	/**
	 * Mostra o login do usuário logado.
	 * @return
	 * @throws Exception
	 */
	public String getLoginDoUsuarioLogado() throws Exception{
		if (isLogado) {
			return this.loginDoUsuarioLogado;
		}
		
		throw new Exception ("Não há usuário logado");
	}
	
	/**
	 * Verifica se o login e senha estão no banco de senhas e então, (caso seja true) modifica o
	 * 		atributo isLogado para true, o atributo loginDoUsuarioLogado recebe o login do usuario, e 
	 * 		usuarioLogado recebe como tipo usuario o usuario que logou. 
	 * 
	 * @param login, senha
	 * @return
	 * @throws Exception
	 */
	public boolean logar(String login, String senha) throws Exception {
		if (login == null || login.trim().isEmpty()) {
			throw new Exception("Login inválido");
		}
		if (senha == null || senha.trim().isEmpty()) {
			throw new Exception("Senha inválida");
		}
		
		if (bancoDeSenhas.containsKey(login)) {
			if (autenticaSenha(login, senha)) {
				this.isLogado = true;
				this.loginDoUsuarioLogado = login;
				this.usuarioLogado = bancoDeUsuarios.get(login);
				return true;
			}
			throw new Exception("Login ou senha incorreta!!");
		}
		throw new Exception("Usuario não cadastrado.");
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
	  * 	como parâmetro é correspondente com a chave do usuário. 
	  * @param login
	  * @param senha
	  * @return
	  * @throws Exception
	  */
	private boolean autenticaSenha(String login, String senha) throws Exception{
		if (login == null || login.trim().isEmpty() || !bancoDeSenhas.containsKey(login)){
			throw new Exception ("Login inválido");
		}
		if (senha == null || senha.trim().isEmpty()) {
			throw new Exception("Senha inválida");
		}
		
		return bancoDeSenhas.get(login).equals(senha);
	}
	
	/**
	 * Cria o objeto imovel, verifica se o usuario tem saldo suficiente para fazer a compra,
	 * 		efetua o pagamento adiquindo o valor com um getPreco,
	 * 		guarda o imovel comprado no Map bancoDeImoveisParaVenda do novo usuario que comprou o imovel.
	 * 
	 * Modifica o valores do imovel comprado para o novo dono do imóvel,
	 * 		adiciona o dinheiro ao antigo dono do imovel e
	 * 		remove o imovel do usuario que era dono antes.
	 * 
	 * @param codigoDoImovel
	 * @return
	 * @throws Exception
	 */
	public boolean comprarImovel(String codigoDoImovel) throws Exception {
		if (codigoDoImovel == null || codigoDoImovel.trim().isEmpty()) {
			throw new Exception("O código do imóvel não pode ser vazio ou nulo");
		}
		if (!bancoDeImoveisParaVenda.containsKey(codigoDoImovel)) {
			throw new Exception("Imóvel não cadastrado.");
		}
		
		Imovel imovel = bancoDeImoveisParaVenda.get(codigoDoImovel);
		if (usuarioLogado.getSaldoNaConta() < imovel.getPreco()) {
			throw new Exception("Você não tem saldo suficiente para comprar este imóvel"
					+ "\n" + "Seu saldo é: " + usuarioLogado.getSaldoNaConta());
		}
		
		usuarioLogado.efetuaPagamento(imovel.getPreco());
		usuarioLogado.adicionaImovelParaVenda(imovel); //GUARDA O IMOVEL COMPRADO NO MAP bancoDeImoveisParaVenda DO NOV USUARIO QUE ESTÁ COMPRANDO
		usuarioLogado.incrementaNumeroDeCompras(); 
		
		String nomeDoProprietario = imovel.getNomeDoProprietario();
		for (Usuario usuario : bancoDeUsuarios.values()){
			
			if (usuario.getNome().equals(nomeDoProprietario)){//PROCURA O USUARIO DONO DO IMOVEL Q ACABOU SENDO VENDIDO
				
				imovel.setProprietario(usuarioLogado.getNome());
				adicionaDinheiroAoUsuario(SENHA_DO_ADM, usuario.getLogin(), imovel.getPreco());//ADICIONA DINHEIRO AO USUARIO Q FOI ACHADO(DONO DO IMOVEL Q ACABOU DE VENDER)
				usuario.removeImovelParaVenda(codigoDoImovel);//JÁ QUE FOI COMPRADO O IMOVEL, REMOVE O IMOVEL DO MAP
				removeImovel(codigoDoImovel);
				return true;
			}
		}
		removeImovel(codigoDoImovel);
		return true;
	}
	
	/**
	 * Remove um imovel por código do imóvel.
	 * @param codigoDoImovel
	 */
	private void removeImovel(String codigoDoImovel) {
		bancoDeImoveisParaVenda.remove(codigoDoImovel);

	}
	
	/**
	 * Cria o objeto apartamento, adiciona apartamento ao Map(banco imovel para venda) do usuario logado.
	 * Adiciona o dinheiro da venda da casa na conta do usuario que está logado e
	 * 	incrementa o número de vendas do usuário logado.
	 * @param proprietario, coordenadaDeEndereco, andar, area, preco, numeroDeQuartos, vagasNaGaragem
	 * @return
	 * @throws Exception
	 */
	public boolean vendeApartamento(String proprietario, String coordenadaDeEndereco, int andar, double area, double preco,
			int numeroDeQuartos, int vagasNaGaragem) throws Exception {
		if (proprietario == null || proprietario.trim().isEmpty()) {
			throw new Exception("Nome do proprietario do apartamento não pode ser vazio ou nulo.");
		}
		if (area <= 0) {
			throw new Exception("Area do apartamento não pode ser menor ou igual a zero.");
		}
		if (preco <= 0) {
			throw new Exception("Preço do apartamento não pode ser menor ou igual a zero.");
		}
		if (numeroDeQuartos <= 0) {
			throw new Exception("Numero de quartos do apartamento não pode ser menor ou igual a zero.");
		}
		if (andar <= 0) {
			throw new Exception("O andar do apartamento não pode ser menor ou igual a zero.");
		}
		
		Imovel apartamento = fabricaDeImoveis.fabricaApartamento(proprietario, coordenadaDeEndereco, andar, area, preco, numeroDeQuartos, vagasNaGaragem);
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
		if (imovel == null) {
			throw new Exception("Imovel nao pode ser null");
		}

		if (bancoDeImoveisParaVenda.containsKey(imovel.getCodigoUnico())) {
			throw new Exception("Imovel ja cadastrado");
		}
		
		bancoDeImoveisParaVenda.put(imovel.getCodigoUnico(), imovel);
	}
	
	/**
	 * Cria o objeto casa, adiciona casa ao Map(banco imovel para venda) do usuario logado.
	 * Adiciona o dinheiro da venda da casa na conta do usuario que está logado e
	 * 	incrementa o número de vendas do usuário logado.
	 * 
	 * @param proprietario, coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem
	 * @return
	 * @throws Exception
	 */
	public boolean vendeCasa(String proprietario, String coordenadaDeEndereco, double area, double preco,
							int numeroDeQuartos, int vagasNaGaragem) throws Exception {
		if (proprietario == null || proprietario.trim().isEmpty()) {
			throw new Exception("Nome do proprietario da casa não pode ser vazio ou nulo.");
		}
		if (area <= 0) {
			throw new Exception("Area da casa não pode ser menor ou igual a zero.");
		}
		if (preco <= 0) {
			throw new Exception("Preço da casa não pode ser menor ou igual a zero.");
		}
		if (numeroDeQuartos <= 0) {
			throw new Exception("Numero de quartos da casa não pode ser menor ou igual a zero.");
		}
		if (coordenadaDeEndereco == null || coordenadaDeEndereco.trim().isEmpty()) {
			throw new Exception ("Endereço não pode ser vazio ou nulo.");
		}
		
		Imovel casa = fabricaDeImoveis.fabricaCasa(proprietario, coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem);
		cadastraImovelParaVenda(casa);
		
		usuarioLogado.adicionaImovelParaVenda(casa);
		usuarioLogado.adicionaDinheiro(casa.getPreco());
		usuarioLogado.incrementaNumeroDeVendas();
		
		return true;
		
	}
	
	
	/**
	 * Recupera os imoveis que estão no Map bancoDeImoveisParaVenda por faixa de preço.
	 * 
	 * @param precoMinimo
	 * @param precoMaximo
	 * @return
	 */
	public String getImovelParaVendaPorFaixaDePreco(double precoMinimo, double precoMaximo) {
		
		String imoveisEmString = "";
		for (Imovel imovel : bancoDeImoveisParaVenda.values()){
			
			if (imovel.getPreco() >= precoMinimo && imovel.getPreco() <= precoMaximo && !imovel.isAlugado()) {
				imoveisEmString += imovel.toString() + "\n";
			}
		}
		return imoveisEmString;
	}
	
	
	/**
	 * Recupera os imoveis que estão no Map bancoDeImoveisParaVenda por número de cômodos.
	 * 
	 * @param numeroMinimoDeComodos
	 * @param numeroMaximoDeComodos
	 * @return
	 */
	public String getImovelParaVendaPorNumeroDeComodos(int numeroMinimoDeComodos, int numeroMaximoDeComodos) {
		
		String imoveisEmString = "";
		for (Imovel imovel : bancoDeImoveisParaVenda.values()) {
			
			if (imovel.getNumeroDeQuartos() >= numeroMinimoDeComodos && imovel.getNumeroDeQuartos() <= numeroMaximoDeComodos && !imovel.isAlugado()){
				imoveisEmString += imovel.toString() + "\n";
			}
		}
		return imoveisEmString;
	}
	
	
	/**
	 * FALTA CORRIGIR...
	 * 		
	 * @param proprietario, coordenadaDeEndereco, andar, area, preco, numeroDeQuartos, vagasNaGaragem
	 * @return
	 * @throws Exception
	 */
	public boolean ApParaAlugarDeProprietario(String proprietario, String coordenadaDeEndereco, int andar, double area, double preco,
				 int numeroDeQuartos, int vagasNaGaragem) throws Exception {
		if (proprietario == null || proprietario.trim().isEmpty()) {
			throw new Exception("Nome do proprietario do apartamento não pode ser vazio ou nulo.");
		}
		if (area <= 0) {
			throw new Exception("Area do apartamento não pode ser menor ou igual a zero.");
		}
		if (preco <= 0) {
			throw new Exception("Preço do apartamento não pode ser menor ou igual a zero.");
		}
		if (numeroDeQuartos <= 0) {
			throw new Exception("Numero de quartos do apartamento não pode ser menor ou igual a zero.");
		}
		if (andar <= 0) {
			throw new Exception("O andar do apartamento não pode ser menor ou igual a zero.");
		}
		
		Imovel apartamento = fabricaDeImoveis.fabricaApartamento(proprietario, coordenadaDeEndereco, andar, area, preco, numeroDeQuartos, vagasNaGaragem);
		
		if (bancoDeImoveisParaAlugar.containsKey(apartamento.getCodigoUnico())){
			throw new Exception ("Imovel já cadastrado nos imóveis para alugar");
		}
		
		usuarioLogado.incrementaimoveisAlugadosLocador();
		usuarioLogado.alugaImovel(apartamento);
		removeImovelParaAlugar(apartamento.getCodigoUnico());
		return true;
	}
	
	
	/**
	 * Remove imovel por codigoDoImovel do Map bancoDeImoveisParaAlugar.
	 * @param codigoDoImovel
	 */
	private void removeImovelParaAlugar(String codigoDoImovel) {
		bancoDeImoveisParaAlugar.remove(codigoDoImovel);
	}
	
	
	/**
	 * Falta CORRIGIR...
	 * @param proprietario, coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem
	 * @return
	 * @throws Exception
	 */
	public boolean casaParaAlugarDeProprietario(String proprietario, String coordenadaDeEndereco, double area, double preco,
			int numeroDeQuartos, int vagasNaGaragem) throws Exception {
		if (proprietario == null || proprietario.trim().isEmpty()) {
			throw new Exception("Nome do proprietario da casa não pode ser vazio ou nulo.");
		}
		if (area <= 0) {
			throw new Exception("Area da casa não pode ser menor ou igual a zero.");
		}
		if (preco <= 0) {
			throw new Exception("Preço da casa não pode ser menor ou igual a zero.");
		}
		if (numeroDeQuartos <= 0) {
			throw new Exception("Numero de quartos da casa não pode ser menor ou igual a zero.");
		}
		
		Imovel casa = fabricaDeImoveis.fabricaCasa(proprietario, coordenadaDeEndereco, area, preco, numeroDeQuartos, vagasNaGaragem);
		
		if (bancoDeImoveisParaAlugar.containsKey(casa.getCodigoUnico())) {
			throw new Exception ("Imovel já cadastrado nos imóveis para alugar");
		}
	
		
		usuarioLogado.alugaImovel(casa);
		removeImovelParaAlugar(casa.getCodigoUnico());
		usuarioLogado.incrementaimoveisAlugadosLocador();
		
		return true; 
	}
	
	
	/**
	 * Aluga imóvel de terceiros, armazena o imovel no bancoDeImoveisParaAlugar do usuario logado e
	 * 		cadastra o imovel.
	 * 
	 * @param codigoDoImovel
	 * @return
	 * @throws Exception
	 */
	public boolean alugaImovelParaTerceiros(String codigoDoImovel) throws Exception {
		if (codigoDoImovel == null || codigoDoImovel.trim().isEmpty()) {
			throw new Exception("Código do imóvel não pode ser vazio ou nulo.");
		}
		if (!bancoDeImoveisParaVenda.containsKey(codigoDoImovel)) {
			throw new Exception("Código inválido");
		}

		Imovel imovel = bancoDeImoveisParaVenda.get(codigoDoImovel);
		if (imovel.isAlugado()) {
			throw new Exception("Imóvel já alugado");
		}
		if (imovel.getNomeDoProprietario().equals(usuarioLogado.getNome())) {
			throw new Exception("Você não pode alugar um imóvel próprio");
		}
		
		usuarioLogado.incrementaimoveisAlugadosInquilino();
		usuarioLogado.adicionaImovelParaAlugar(imovel);
		cadastraImovelParaALugar(imovel);
		return true;
	}
	
	
	/**
	 * ADICIONA IMÓVEL PARA ALUGAR NO MAP bandoDeImoveisParaAlugar 
	 * @param imovel
	 * @throws Exception
	 */
	private void cadastraImovelParaALugar(Imovel imovel) throws Exception{
		if (imovel == null){
			throw new Exception("Imovel nao pode ser null");
		}
		
		if (bancoDeImoveisParaAlugar.containsKey(imovel.getCodigoUnico())) {
			throw new Exception("Imovel ja cadastrado");
		}
		
		imovel.setAlugado(true);    //MUDA O STATUS PARA ALUGADO TRUE
		usuarioLogado.incrementaimoveisAlugadosLocador();
		bancoDeImoveisParaAlugar.put(imovel.getCodigoUnico(), imovel);
	}
	
	
	/**
	 * MOSTRA IMÓVEL PARA ALUGAR POR FAIXA DE PREÇO
	 * @param precoMinimo
	 * @param precoMaximo
	 * @return
	 */
	public String getImovelParaAlugarPorFaixaDePreco(double precoMinimo, double precoMaximo){
		
		String imoveisEmString = "";
		
		for (Imovel imovel : bancoDeImoveisParaAlugar.values()) {
			if (imovel.getPreco() >= precoMinimo && imovel.getPreco() <= precoMaximo && !imovel.isAlugado()){
				
				imoveisEmString += imovel.toString() + "\n";
			}
		
		}
		return imoveisEmString;
	}
	
	
	/**
	 * MOSTRA IMÓVEL PARA ALUGAR POR NÚMERO DE CÔMODOS
	 * @param numeroMinimoDeComodos
	 * @param numeroMaximoDeComodos
	 * @return
	 */
	public String getImovelParaAlugarPorNumeroDeComodos(int numeroMinimoDeComodos, int numeroMaximoDeComodos ){
		String imoveisEmString = "";
		for (Imovel imovel : bancoDeImoveisParaAlugar.values()) {
			
			if (imovel.getNumeroDeQuartos() >= numeroMinimoDeComodos && imovel.getNumeroDeQuartos() <= numeroMaximoDeComodos && !imovel.isAlugado()){
				
				imoveisEmString += imovel.toString() + "\n";
			}
		}
		return imoveisEmString;
	}
	
	
	/**
	 * MOSTRA IMÓVEL PARA ALUGAR POR ÁREA INTERNA
	 * @param areaMinima
	 * @param areaMaxima
	 * @return
	 */
	public String getImovelParaAlugarPorAreaInterna(double areaMinima, double areaMaxima){
		String imoveisEmString = "";
		for (Imovel imovel : bancoDeImoveisParaAlugar.values()){
			
			if (imovel.getArea() >= areaMinima && imovel.getArea() <= areaMaxima && !imovel.isAlugado()){
				imoveisEmString += imovel.toString() + "\n";
			}
		}
		return imoveisEmString;
	}
	
	
	/**
	 * MÉTODO DO TIPO MAP (bancoDeImoveisParaVendaDefault) PRIVATE PARA DEIXAR ADICIONADOS OS IMOVEIS
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
	 * MÉTODO DO TIPO MAP (bancoDeUsuariosDefault) PRIVATE PARA DEIXAR ADICIONADOS OS USUÁRIOS
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
	 * MÉTODO DO TIPO MAP (bancoDeSenhasDefault) PRIVATE PARA DEIXAR ADICIONADOS AS SENHAS E LOGIN DOS USUÁRIOS ADICIONADOS
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
