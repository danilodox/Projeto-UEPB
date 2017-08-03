package excecoes;


/**
 * Classe usada com exceções em Objetos.
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */
public class ObjectInvalidException extends Exception{
	private static final long serialVersionUID = 1L;

	public ObjectInvalidException(String message)  {
		super(message);
	}
}
