package excecoes;


/**
 * Classe usada com exceções em Imóvel.
 * 
 * @author Danilo Medeiros Dantas, danilomedeiros.dox@gmail.com
 * @version 1.0 <br>
 *          Copyright (c) 2017 Universidade Estadual de Campina Grande.
 */
public class ImmobileInvalidException extends Exception{
private static final long serialVersionUID = 1L;
	
	public ImmobileInvalidException(String message)  {
		super(message);
	}
}
