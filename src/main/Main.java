package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import excecoes.ValueInvalidException;
import sistema.Sistema;

/**
 * Classe com o m�todo main.
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */

public class Main {
	static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
	static Scanner input = new Scanner(System.in);
    public static String opcao;
    public static String opcaoLogar;
    public static String opcaoComprar;
    public static String opcaoAlugar;
    public static Sistema sistema = new Sistema();
	
    
    // SENHA DO ADMINSTRADOR:  UEPB
    
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
	 * M�TODO PARA ESCOLHER ENTRE LOGAR, CRIAR UMA CONTA OU SAIR DO SISTEMA
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
				System.out.println("Op��o inv�lida!");
				break;
			}
			
		}
	
	}
	
	/**
	 * M�TODO PARA ESCOLHER ENTRE: ADICIONAR DINHERIO, COMPRAR IM�VEL, VENDER APARTAMENTO, VENDER CASA,
	 *       					   ALUGAR, CASA DE PROPRIETARIO PARA ALUGAR, APARTAMENTO DE PROPRIETARIO PARA ALUGAR, 
	 *      					   MOSTRAR HIST�RICO, DESLOGAR.
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
					operacoesComprarImovel(); // CONT�M OUTRO MENU, ONDE TEM: COMPRAR IMOVEL, MOSTRAR IMOVEL POR FAIXA DE PRE�O E MOSTRAR IMOVEL POR N� DE C�MODOS
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("3"):
				
				try {
					limparTela();
					if(vendeApartamento()) {
						System.out.println("Apartamento adicionado ao sistema com sucesso!!");
					}
					
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("4"):
				
				try {
					limparTela();
					if(vendeCasa()) {
						System.out.println("Casa cadastrado na lista de vendas!");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("5"):
				
				try {
					limparTela();
					operacoesAlugar(); //CONT�M OUTRO MENU, ONDE TEM: ALUGA IM�VEL PARA TERCEIROS, MOSTRAR IM�VEL PARA ALUGAR POR �REA INTERNA E POR N� DE C�MODOS
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("6"):
				try {
					limparTela();
					if(casaParaAlugarDeProprietario()) {
						System.out.println("Casa para alugar cadastrada no sistema com sucesso!!");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
				
			case("7"):
				try {
					limparTela();
					if(ApParaAlugarDeProprietario()) {
						System.out.println("Apartamento para alugar cadastrado no sistema com sucesso!!");
					}
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
				System.out.println("Voc� deslogou da sua conta!");
				return false;
				
			default:
					limparTela();
					System.out.println("Op��o inv�lida, tente novamente..");
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
				System.out.println("Opcao inv�lida, tente novamente..");
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
					System.out.println("Im�vel alugado com sucesso!!");
					break;
				}
				
				loopAlugar = false;
				break;
				
			case("2"):
				
				System.out.println(getImovelParaAlugarPorNComodos());
				break;
				
			case("3"):
				
				System.out.println(getImovelParaAlugarPorAreaInterna());
				break;
				
			case("4"):
				
				System.out.println(getImovelParaAlugarPorFaixaDePreco());
				break;
				
			case("5"):
				loopAlugar = false;
				break;
				
			default:
				System.out.println("Op��o incorreta, tente novamente...");
				break;
			}
		}
		return loopAlugar;
		
	}
	
	
	
	public static boolean comprarImovel() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o c�digo do im�vel: ");
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
		System.out.println("| 2) Comprar im�vel			|");
		System.out.println("|                                       |");
		System.out.println("| 3) Vender apartamento                 |");
		System.out.println("|                                       |");
		System.out.println("| 4) Vender casa                        |");
		System.out.println("|                                       |");
		System.out.println("| 5) Alugar im�vel de terceiros         |");
		System.out.println("|                                       |");
		System.out.println("| 6) Alugar casa pr�pria                |");
		System.out.println("|                                       |");
		System.out.println("| 7) Alugar apartamento pr�prio         |");
		System.out.println("|                                       |");
		System.out.println("| 8) Exibir hist�rico                   |");
		System.out.println("|                                       |");
		System.out.println("| 9) Sair                               |");
		System.out.println("|_______________________________________|");
	
	}
	
	
	public static void menuTipoDeBuscaParaAlugar() {
		mostrarCabecalho();
		System.out.println(" _______________________________________");
		System.out.println("|                                       |");
		System.out.println("| 1) Alugar im�vel de terceiros         |");
		System.out.println("|                                       |");
		System.out.println("| 2) Procurar im�vel por N� de c�modos  |");
		System.out.println("|                                       |");
		System.out.println("| 3) Procurar im�vel por �rea interna   |");
		System.out.println("|                                       |");
		System.out.println("| 4) Procurar im�vel por faixa de pre�o |");
		System.out.println("|                                       |");
		System.out.println("| 5) Sair                               |");
		System.out.println("|_______________________________________|");
	}
	
	public static boolean alugaImovelParaTerceiros() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o c�digo do im�vel: ");
		String codigoImovel = input.next();
		System.out.println("|__________________________________________________");
		
		return sistema.alugaImovelParaTerceiros(codigoImovel);
		
	}
		
	
	public static void menuTipoDeBuscaParaCompra() {
		mostrarCabecalho();
		System.out.println(" _______________________________________");
		System.out.println("|                                       |");
		System.out.println("| 1) Comprar um im�vel                  |");
		System.out.println("|                                       |");
		System.out.println("| 2) Procurar im�vel por faixa de pre�o |");
		System.out.println("|                                       |");
		System.out.println("| 3) Procurar im�vel por N� de c�modos  |");
		System.out.println("|                                       |");
		System.out.println("| 4) Sair                               |");
		System.out.println("|_______________________________________|");
	}
	
	public static String getImovelParaAlugarPorAreaInterna() throws ValueInvalidException {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o tamanho da �rea interna m�nima: ");
		double areaMinimo = input.nextDouble();
		System.out.println("|");
		System.out.print("| 2) Digite o tamanho da �rea interna m�xima: ");
		double areaMaximo = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		return sistema.getImovelParaAlugarPorAreaInterna(areaMinimo, areaMaximo);
	}
	
	public static String getImovelParaAlugarPorFaixaDePreco() throws ValueInvalidException {
			System.out.println(" __________________________________________________");
			System.out.print("| 1) Digite o pre�o m�nimo: ");
			double precoMinimo = input.nextDouble();
			System.out.println("|");
			System.out.print("| 2) Digite o pre�o m�ximo: ");
			double precoMaximo = input.nextDouble();
			System.out.println("|__________________________________________________");
			return sistema.getImovelParaAlugarPorFaixaDePreco(precoMinimo, precoMaximo);
	}
	
	public static String getImovelParaAlugarPorNComodos() throws ValueInvalidException {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o n�mero m�nimo de c�modos: ");
		int comodoMinimo = input.nextInt();
		System.out.println("|");
		System.out.print("| 2) Digite o n�mero m�ximo de c�modos: ");
		int comodoMaximo = input.nextInt();
		System.out.println("|__________________________________________________");
		
		return sistema.getImovelParaAlugarPorNumeroDeComodos(comodoMinimo, comodoMaximo);
	}
	
	public static String getImovelParaVendaPorNComodos() throws ValueInvalidException {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o n�mero m�nimo de c�modos: ");
		int comodoMinimo = input.nextInt();
		System.out.println("|");
		System.out.print("| 2) Digite o n�mero m�ximo de c�modos: ");
		int comodoMaximo = input.nextInt();
		System.out.println("|__________________________________________________");
		return sistema.getImovelParaVendaPorNumeroDeComodos(comodoMinimo, comodoMaximo);
	}
	
	public static String getImovelParaVendaPorFaixaDePreco() throws ValueInvalidException {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite o pre�o m�nimo: ");
		double precoMinimo = input.nextDouble();
		System.out.println("|");
		System.out.print("| 2) Digite o pre�o m�ximo: ");
		double precoMaximo = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		return sistema.getImovelParaVendaPorFaixaDePreco(precoMinimo, precoMaximo);
	}
	
	public static boolean ApParaAlugarDeProprietario () throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite um endere�o ou uma refer�ncia: ");
		String endereco = entrada.readLine();
		System.out.println("|");
		System.out.print("| 2) Digite quantos andares cont�m o apartamento: ");
		int andar = input.nextInt();
		System.out.println("|");
		System.out.print("| 3) Digite o tamanho da �rea interna do apartamento(m�): ");
		double area = input.nextDouble();
		System.out.println("|");
		System.out.print("| 4) N�mero de quarto(s) do apartamento: ");
		int quarto = input.nextInt();
		System.out.println("|");
		System.out.print("| 5) N�mero de vaga(s) na garagem: ");
		int garagem = input.nextInt();
		System.out.println("|");
		System.out.print("| 6) Digite o pre�o do apartamento: ");
		double preco = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		return sistema.ApParaAlugarDeProprietario(endereco, andar, area, preco, quarto, garagem);
	}
	
	public static boolean casaParaAlugarDeProprietario() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite um endere�o ou uma refer�ncia: ");
		String endereco = entrada.readLine();
		System.out.println("|");
		System.out.print("| 2) Digite o tamanho da �rea interna da casa(m�): ");
		double area = input.nextDouble();
		System.out.println("|");
		System.out.print("| 3) N�mero de quarto(s) da casa: ");
		int quarto = input.nextInt();
		System.out.println("|");
		System.out.print("| 4) N�mero de vaga(s) na garagem: ");
		int garagem = input.nextInt();
		System.out.println("|");
		System.out.print("| 5) Digite o pre�o da casa: ");
		double preco = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		return sistema.casaParaAlugarDeProprietario(endereco, area, preco, quarto, garagem);
	}
	
	public static boolean vendeCasa() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite um endere�o ou uma refer�ncia: ");
		String endereco = entrada.readLine();
		System.out.println("|");
		System.out.print("| 2) Digite o tamanho da �rea interna da casa(m�): ");
		double area = input.nextDouble();
		System.out.println("|");
		System.out.print("| 3) N�mero de quarto(s) da casa: ");
		int quarto = input.nextInt();
		System.out.println("|");
		System.out.print("| 4) N�mero de vaga(s) na garagem: ");
		int garagem = input.nextInt();
		System.out.println("|");
		System.out.print("| 5) Digite o pre�o da casa: ");
		double preco = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		
		return sistema.vendeCasa(endereco, area, preco, quarto, garagem);
	}
	
	public static boolean vendeApartamento() throws Exception {
		System.out.println(" __________________________________________________");
		System.out.print("| 1) Digite um endere�o ou uma refer�ncia: ");
		String endereco = entrada.readLine();
		System.out.println("|");
		System.out.print("| 2) Digite quantos andares cont�m o apartamento: ");
		int andar = input.nextInt();
		System.out.println("|");
		System.out.print("| 3) Digite o tamanho da �rea interna do apartamento(m�): ");
		double area = input.nextDouble();
		System.out.println("|");
		System.out.print("| 4) N�mero de quarto(s) do apartamento: ");
		int quarto = input.nextInt();
		System.out.println("|");
		System.out.print("| 5) N�mero de vaga(s) na garagem: ");
		int garagem = input.nextInt();
		System.out.println("|");
		System.out.print("| 6) Digite o pre�o do apartamento: ");
		double preco = input.nextDouble();
		System.out.println("|__________________________________________________");
		
		
		return sistema.vendeApartamento(endereco, andar, area, preco, quarto, garagem);
	}
	
	public static void adicionaDinheiro() throws Exception {
		System.out.print("|  Senha do administrador: ");
		String adm = input.next();
		System.out.print("\n| Login: ");
		String login = entrada.readLine();
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
		System.out.println("|  Bem vindo ao Mercado de Im�veis P2  |");
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
		String login = entrada.readLine();
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
		String login = entrada.readLine();
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
