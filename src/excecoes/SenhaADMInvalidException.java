package excecoes;


/**
 * Classe usada com exceções para a senha do administrador.
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */
public class SenhaADMInvalidException extends Exception{
	
private static final long serialVersionUID = 1L;
	
	public SenhaADMInvalidException(String message)  {
		super(message);
	}
}
