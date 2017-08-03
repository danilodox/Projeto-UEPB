package excecoes;

/**
 * Classe usada com exceções em números.
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */
public class ValueInvalidException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ValueInvalidException(String message) {
		super(message);
	}
}
