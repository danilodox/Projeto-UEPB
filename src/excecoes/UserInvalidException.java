package excecoes;

/**
 * Classe usada com exceções para dados do usuário.
 * 
* @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */
public class UserInvalidException extends Exception{

private static final long serialVersionUID = 1L;
	
	public UserInvalidException(String message)  {
		super(message);
	}
}
