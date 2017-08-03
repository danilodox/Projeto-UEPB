package validacao;

import java.util.Map;

import excecoes.AccountInvalidException;
import excecoes.BooleanInvalidException;
import excecoes.CashInvalidException;
import excecoes.ImmobileInvalidException;
import excecoes.ObjectInvalidException;
import excecoes.SenhaADMInvalidException;
import excecoes.StringInvalidException;
import excecoes.UserInvalidException;
import excecoes.ValueInvalidException;
import imovel.Imovel;

/**
 *  Classe representando um módulo de validação. Nesta classe há métodos
 * avaliativos que lançam exceções quando necessário.
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */
public class ValidationModule {
	
	public static void analizeInteger(int valor, String messagem) throws ValueInvalidException {
		if (valor <= 0) {
			throw new ValueInvalidException(messagem);
		}
	}
	
	public static void analyzeString(String string, String messagem) throws StringInvalidException {
		
		if (string == null || string.trim().isEmpty()) {
			throw new StringInvalidException(messagem);
		}
		
	}
	
	public static void analizeSenhaADM(String senhaADM) throws SenhaADMInvalidException {
		if (senhaADM == null || senhaADM.trim().isEmpty() || !senhaADM.equals("uepb")) {
			throw new SenhaADMInvalidException("Senha do Adm inválida");
		}
	}
	
	public static void analizeValue(double valor, String messagem) throws ValueInvalidException {
		if (valor <= 0) {
			throw new ValueInvalidException(messagem);
		}
	
	}
	
	public static void analizeAccount(String nome, String cpf, int idade) throws AccountInvalidException {
		if (nome == null | nome.trim().isEmpty()) {
			throw new AccountInvalidException("Nome de usuário não pode ser vazio ou nulo");
		}
		if (cpf == null | cpf.trim().isEmpty()) {
			throw new AccountInvalidException("CPF do usuário não pode ser vazio ou nulo");
		}
		if (idade < 18) {
			throw new AccountInvalidException("O usuário não pode ser menor de idade");
		}
		
	}
	
	public static void analizeUserPresente(Map<String, String> bancoDeSenhas, String login) throws UserInvalidException {
		if (bancoDeSenhas.containsKey(login)) {
			throw new UserInvalidException("Usuário inválido");
		}
	}
	
	public static void analizeUser(Map<String, String> bancoDeSenhas, String login) throws UserInvalidException {
		if (!bancoDeSenhas.containsKey(login)) {
			throw new UserInvalidException("Usuário inválido");
		}
	}
	
	public static void analizeImmobileVendaPresent(Map<String, Imovel> bancoDeImoveisParaVenda, String codigoDoImovel) throws ImmobileInvalidException {
		if (!bancoDeImoveisParaVenda.containsKey(codigoDoImovel)) {
			throw new ImmobileInvalidException("Imóvel não cadastrado.");
		}
	}
	
	public static void analizeCash(double saldo, double valor) throws CashInvalidException {
		if (saldo < valor) {
			throw new CashInvalidException("Você não tem saldo suficiente" + "\n" + "Seu saldo é: " + saldo);
		}
	}
	
	public static void analizeImmobileAlugar(Map<String, Imovel> bancoDeImoveisParaAlugar, String codigoDoImovel) throws ImmobileInvalidException {
		if(!bancoDeImoveisParaAlugar.containsKey(codigoDoImovel)) {
			throw new ImmobileInvalidException("Código inválido");
		}
	}
	
	public static void analizeBoolean(boolean sinal, String messagem) throws BooleanInvalidException {
		if (sinal == true) {
			throw new BooleanInvalidException(messagem);
		}
	}
	
	public static void analizeBooleanFalse(boolean sinal, String messagem) throws BooleanInvalidException {
		if (sinal == false) {
			throw new BooleanInvalidException(messagem);
		}
	}
	
	public static void analizeNomeProprietario(String proprietario, String nome) throws StringInvalidException {
		if (proprietario.equals(nome)) {
			throw new StringInvalidException("Você não pode alugar um imóvel próprio");
		}
	}
	
	public static void analizeObject(Object ob, String messagem) throws ObjectInvalidException {
		if (ob == null) {
			throw new ObjectInvalidException(messagem);
		}
	}
	
	
}
