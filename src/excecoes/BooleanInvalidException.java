package excecoes;

/**
 * Classe usada com exce��es em Boolean
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */
public class BooleanInvalidException extends Exception{
	private static final long serialVersionUID = 1L;

	public BooleanInvalidException(String message)  {
		super(message);
	}
}
