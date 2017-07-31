package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


import sistema.Sistema;

//...
public class Main {
	static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
	static Scanner input = new Scanner(System.in);
    public static String opcao;
    public static String opcaoLogar;
    public static String opcaoComprar;
    public static String opcaoAlugar;
    public static Sistema sistema = new Sistema();
	
	public static void main(String[] args) throws Exception {
	
	
		
		while (true) {
			if (logar()) {
				operacoesDoSistema();
			}else {
				break;
			}
		}
		
		
	}
	
	/**
	 * MÉTODO PARA ESCOLHER ENTRE LOGAR, CRIAR UMA CONTA OU SAIR DO SISTEMA
	 * @return
	 */
	public static boolean logar() {
		
		while (true) {
			mostrarIndice(); //MOSTRA O MENU LOGAR, CRIAR CONTA OU SAIR
			opcao = input.next();
			switch (opcao) {
			case("1"):
				
				try {
					return efetuarLogin();
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("2"):
				
				try {
					return criaConta();
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("3"):
				return false;
				
			default:
				System.out.println("Opção inválida!");
				break;
			}
			
		}
	
	}
	
	/**
	 * MÉTODO PARA ESCOLHER ENTRE: ADICIONAR DINHERIO, COMPRAR IMÓVEL, VENDER APARTAMENTO, VENDER CASA,
	 *       					   ALUGAR, CASA DE PROPRIETARIO PARA ALUGAR, APARTAMENTO DE PROPRIETARIO PARA ALUGAR, 
	 *      					   MOSTRAR HISTÓRICO, DESLOGAR.
	 * 
	 * @return
	 */
	public static boolean operacoesDoSistema() {
			
		limparTela();
		while(true) {
			
			menuDepoisQueLogar();
			opcao = input.next();
			
			switch (opcao) {
			case("1"):
				
				try {
					limparTela();
					adicionaDinheiro();
					
				}	catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("2"):
				
				try {
					limparTela();
					operacoesComprarImovel(); // CONTÉM OUTRO MENU, ONDE TEM: COMPRAR IMOVEL, MOSTRAR IMOVEL POR FAIXA DE PREÇO E MOSTRAR IMOVEL POR Nº DE CÔMODOS
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("3"):
				
				try {
					limparTela();
					vendeApartamento();
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("4"):
				
				try {
					limparTela();
					vendeCasa();
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("5"):
				
				try {
					limparTela();
					operacoesAlugar(); //CONTÉM OUTRO MENU, ONDE TEM: ALUGA IMÓVEL PARA TERCEIROS, MOSTRAR IMÓVEL PARA ALUGAR POR ÁREA INTERNA E POR Nº DE CÔMODOS
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("6"):
				try {
					limparTela();
					casaParaAlugarDeProprietario();
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("7"):
				try {
					limparTela();
					ApParaAlugarDeProprietario();
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("8"):
				try {
					
					ExibirHistorico();
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
					
			case("9"):
				limparTela();
				sistema.deslogar();
				System.out.println("Você deslogou da sua conta!");
				return false;
				
			default:
					limparTela();
					System.out.println("Opção inválida, tente novamente..");
					break;
				
			}
		}
	}
	
	public static void operacoesComprarImovel() throws Exception {
		boolean loopCompra = true;
		
		limparTela();
		while (loopCompra) {
			
			menuTipoDeBuscaParaCompra();
			opcao = input.next();
			
			switch(opcao) {
			
			case("1"):
				
					if (comprarImovel()) {
						System.out.println("Compra realizada com sucesso!!");
						break;
					}
				loopCompra = false;
				break;
				
			case("2"):
				
					System.out.println(getImovelParaVendaPorFaixaDePreco());
					break;
				
			case("3"):
				
					System.out.println(getImovelParaVendaPorNComodos());
					break;
				
			case("4"):
				loopCompra = false;
				break;
				
			default:
				System.out.println("Opcao inválida, tente novamente..");
				break;
				
			}
		}
	}
	
	public static boolean operacoesAlugar() throws Exception {
		boolean loopAlugar = true;
		
		
		while(loopAlugar) {
			
			limparTela();
			menuTipoDeBuscaParaAlugar();
			opcao = input.next();
			
			switch (opcao) {
			case("1"):
				
				if (alugaImovelParaTerceiros()) {
					System.out.println("Imóvel alugado com sucesso!!");
					break;
				}
				
				loopAlugar = false;
				break;
				
			case("2"):
				
				getImovelParaAlugarPorNComodos();
				loopAlugar = false;
				break;
				
			case("3"):
				
				getImovelParaAlugarPorAreaInterna();
				loopAlugar = false;
				break;
				
			case("4"):
				
				getImovelParaAlugarPorFaixaDePreco();
				loopAlugar = false;
				break;
				
			case("5"):
				loopAlugar = false;
				break;
				
			default:
				System.out.println("Opção incorreta, tente novamente...");
				break;
			}
		}
		return loopAlugar;
		
	}
	
	
	
	public static boolean comprarImovel() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o código do imóvel: ");
		String codigoDoImovel = input.next();
		System.out.println("|__________________________________________________");
		
		return sistema.comprarImovel(codigoDoImovel);
	}
	
	public static void menuDepoisQueLogar() {
		
		mostrarCabecalho();
		System.out.println(" _______________________________________");
		System.out.println("|                                       |");
		System.out.println("| 1) Adicionar dinheiro		        |");
		System.out.println("|                                       |");
		System.out.println("| 2) Comprar imóvel			|");
		System.out.println("|                                       |");
		System.out.println("| 3) Vender apartamento                 |");
		System.out.println("|                                       |");
		System.out.println("| 4) Vender casa                        |");
		System.out.println("|                                       |");
		System.out.println("| 5) Alugar imóvel de terceiros         |");
		System.out.println("|                                       |");
		System.out.println("| 6) Alugar casa própria                |");
		System.out.println("|                                       |");
		System.out.println("| 7) Alugar apartamento próprio         |");
		System.out.println("|                                       |");
		System.out.println("| 8) Exibir histórico                   |");
		System.out.println("|                                       |");
		System.out.println("| 9) Sair                               |");
		System.out.println("|_______________________________________|");
	
	}
	
	
	public static void menuTipoDeBuscaParaAlugar() {
		mostrarCabecalho();
		System.out.println(" _______________________________________");
		System.out.println("|                                       |");
		System.out.println("| 1) Alugar imóvel de terceiros         |");
		System.out.println("|                                       |");
		System.out.println("| 2) Procurar imóvel por Nº de cômodos  |");
		System.out.println("|                                       |");
		System.out.println("| 3) Procurar imóvel por área interna   |");
		System.out.println("|                                       |");
		System.out.println("| 4) Procurar imóvel por faixa de preço |");
		System.out.println("|                                       |");
		System.out.println("| 5) Sair                               |");
		System.out.println("|_______________________________________|");
	}
	
	public static boolean alugaImovelParaTerceiros() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o código do imóvel: ");
		String codigoImovel = input.next();
		System.out.println("|__________________________________________________");
		
		return sistema.alugaImovelParaTerceiros(codigoImovel);
		
	}
		
	
	public static void menuTipoDeBuscaParaCompra() {
		mostrarCabecalho();
		System.out.println(" _______________________________________");
		System.out.println("|                                       |");
		System.out.println("| 1) Comprar um imóvel                  |");
		System.out.println("|                                       |");
		System.out.println("| 2) Procurar imóvel por faixa de preço |");
		System.out.println("|                                       |");
		System.out.println("| 3) Procurar imóvel por Nº de cômodos  |");
		System.out.println("|                                       |");
		System.out.println("| 4) Sair                               |");
		System.out.println("|_______________________________________|");
	}
	
	public static String getImovelParaAlugarPorAreaInterna() {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o tamanho da área interna mínima: ");
		double areaMinimo = input.nextDouble();
		System.out.println("|");
		System.out.print("| 2) Digite o tamanho da área interna máxima: ");
		double areaMaximo = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		return sistema.getImovelParaAlugarPorAreaInterna(areaMinimo, areaMaximo);
	}
	
	public static String getImovelParaAlugarPorFaixaDePreco() {
			System.out.println(" __________________________________________________");
			System.out.print("| 1) Digite o preço mínimo: ");
			double precoMinimo = input.nextDouble();
			System.out.println("|");
			System.out.print("| 2) Digite o preço máximo: ");
			double precoMaximo = input.nextDouble();
			System.out.println("|__________________________________________________");
			return sistema.getImovelParaAlugarPorFaixaDePreco(precoMinimo, precoMaximo);
	}
	
	public static String getImovelParaAlugarPorNComodos() {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o número mínimo de cômodos: ");
		int comodoMinimo = input.nextInt();
		System.out.println("|");
		System.out.print("| 2) Digite o número máximo de cômodos: ");
		int comodoMaximo = input.nextInt();
		System.out.println("|__________________________________________________");
		
		return sistema.getImovelParaAlugarPorNumeroDeComodos(comodoMinimo, comodoMaximo);
	}
	
	public static String getImovelParaVendaPorNComodos() {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o número mínimo de cômodos: ");
		int comodoMinimo = input.nextInt();
		System.out.println("|");
		System.out.print("| 2) Digite o número máximo de cômodos: ");
		int comodoMaximo = input.nextInt();
		System.out.println("|__________________________________________________");
		return sistema.getImovelParaVendaPorNumeroDeComodos(comodoMinimo, comodoMaximo);
	}
	
	public static String getImovelParaVendaPorFaixaDePreco() {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o preço mínimo: ");
		double precoMinimo = input.nextDouble();
		System.out.println("|");
		System.out.print("| 2) Digite o preço máximo: ");
		double precoMaximo = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		return sistema.getImovelParaVendaPorFaixaDePreco(precoMinimo, precoMaximo);
	}
	
	public static boolean ApParaAlugarDeProprietario () throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o nome do proprietario: ");
		String proprietario = input.next();
		System.out.println("|");
		System.out.print("| 2) Digite um endereço ou uma referência: ");
		String endereco = input.next();
		System.out.println("|");
		System.out.print("| 3) Digite quantos andares contém o apartamento: ");
		int andar = input.nextInt();
		System.out.println("|");
		System.out.print("| 4) Digite o tamanho da área interna do apartamento(m²): ");
		double area = input.nextDouble();
		System.out.println("|");
		System.out.print("| 5) Número de quarto(s) do apartamento: ");
		int quarto = input.nextInt();
		System.out.println("|");
		System.out.print("| 6) Número de vaga(s) na garagem: ");
		int garagem = input.nextInt();
		System.out.println("|");
		System.out.print("| 7) Digite o preço do apartamento: ");
		double preco = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		return sistema.ApParaAlugarDeProprietario(proprietario, endereco, andar, area, preco, quarto, garagem);
	}
	
	public static boolean casaParaAlugarDeProprietario() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o nome do proprietario: ");
		String proprietario = entrada.readLine();
		System.out.println("|");
		System.out.print("| 2) Digite um endereço ou uma referência: ");
		String endereco = entrada.readLine();
		System.out.println("|");
		System.out.print("| 3) Digite o tamanho da área interna da casa(m²): ");
		double area = input.nextDouble();
		System.out.println("|");
		System.out.print("| 4) Número de quarto(s) da casa: ");
		int quarto = input.nextInt();
		System.out.println("|");
		System.out.print("| 5) Número de vaga(s) na garagem: ");
		int garagem = input.nextInt();
		System.out.println("|");
		System.out.print("| 6) Digite o preço da casa: ");
		double preco = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		return sistema.casaParaAlugarDeProprietario(proprietario, endereco, area, preco, quarto, garagem);
	}
	
	public static boolean vendeCasa() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o nome do proprietario: ");
		String proprietario = entrada.readLine();
		System.out.println("|");
		System.out.print("| 2) Digite um endereço ou uma referência: ");
		String endereco = entrada.readLine();
		System.out.println("|");
		System.out.print("| 3) Digite o tamanho da área interna da casa(m²): ");
		double area = input.nextDouble();
		System.out.println("|");
		System.out.print("| 4) Número de quarto(s) da casa: ");
		int quarto = input.nextInt();
		System.out.println("|");
		System.out.print("| 5) Número de vaga(s) na garagem: ");
		int garagem = input.nextInt();
		System.out.println("|");
		System.out.print("| 6) Digite o preço da casa: ");
		double preco = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		return sistema.vendeCasa(proprietario, endereco, area, preco, quarto, garagem);
	}
	
	public static boolean vendeApartamento() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o nome do proprietario: ");
		String proprietario = entrada.readLine();
		System.out.println("|");
		System.out.print("| 2) Digite um endereço ou uma referência: ");
		String endereco = entrada.readLine();
		System.out.println("|");
		System.out.print("| 3) Digite quantos andares contém o apartamento: ");
		int andar = input.nextInt();
		System.out.println("|");
		System.out.print("| 4) Digite o tamanho da área interna do apartamento(m²): ");
		double area = input.nextDouble();
		System.out.println("|");
		System.out.print("| 5) Número de quarto(s) do apartamento: ");
		int quarto = input.nextInt();
		System.out.println("|");
		System.out.print("| 6) Número de vaga(s) na garagem: ");
		int garagem = input.nextInt();
		System.out.println("|");
		System.out.print("| 7) Digite o preço do apartamento: ");
		double preco = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		return sistema.vendeApartamento(proprietario, endereco, andar, area, preco, quarto, garagem);
	}
	
	public static void adicionaDinheiro() throws Exception {
		System.out.print("|  Senha do administrador: ");
		String adm = input.next();
		System.out.print("\n| Login: ");
		String login = input.next();
		System.out.println("Valor a ser adicionado: ");
		double valor = input.nextDouble();
		
		sistema.adicionaDinheiroAoUsuario(adm, login, valor);
		System.out.println("Dinheiro adicionado com sucesso!!");
	}
	
	public static void mostrarIndice() {
		mostrarCabecalho();
		System.out.println(" ______________________________________ ");
		System.out.println("|                                      |");
		System.out.println("| 1) Login                             |");
		System.out.println("|                                      |");
		System.out.println("| 2) Criar conta                       |");
		System.out.println("|                                      |");
		System.out.println("| 3) Sair                              |");
		System.out.println("|______________________________________|");
	}
	
	public static void mostrarCabecalho(){
		System.out.println(" ______________________________________ ");
		System.out.println("|                                      |");
		System.out.println("|  Bem vindo ao Mercado de Imóveis P2  |");
		System.out.println("|______________________________________|");
		
	}
	
	public static boolean criaConta() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite seu nome: ");
		String nome = entrada.readLine();
		System.out.println("|");
		System.out.print("| 3) Digite sua idade: ");
		int idade = input.nextInt();
		System.out.println("|");
		System.out.print("| 2) Digite seu login: ");
		String login = input.next();
		System.out.println("|");
		System.out.print("| 3) Digite seu cpf: ");
		String cpf = input.next();
		System.out.println("|");
		System.out.print("| 4) Digite uma senha: ");
		String senha = input.next();
		System.out.println("|__________________________________________________");
		
		return sistema.criaContaDeUsuario(nome, idade, login, cpf, senha);
	}
	
	public static boolean efetuarLogin() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) LOGIN: ");
		String login = input.next();
		System.out.println("|");
		System.out.print("| 2) SENHA: ");
		String senha = input.next();
		System.out.println("|__________________________________________________");
		
		return sistema.logar(login, senha);
	}
	
	public static void ExibirHistorico() throws Exception {
		System.out.println(sistema.getHistoricoDoUsuarioLogado());
	}
	
	public static void limparTela(){
		for (int i = 0; i < 50; i++){
			System.out.println("");
		}
	}

}
