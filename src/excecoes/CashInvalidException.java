package excecoes;

/**
 * Classe usada com exce��es em dinheiro.
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */
public class CashInvalidException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CashInvalidException(String message)  {
		super(message);
	}
}
