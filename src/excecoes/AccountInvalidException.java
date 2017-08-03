package excecoes;

/**
 * Classe usada com exceções em String
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */
public class AccountInvalidException extends Exception{
	
private static final long serialVersionUID = 1L;
	
	public AccountInvalidException(String message)  {
		super(message);
	}
}
