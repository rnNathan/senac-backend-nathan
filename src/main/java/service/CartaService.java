package service;

import exception.VemNoX1Exception;
import model.entity.Carta;
import model.repository.CartaRepository;

public class CartaService {
	
	
	private static final int MAXIMO_ATRIBUTOS_PERMITIDO = 10;
	private CartaRepository repository = new CartaRepository();
	
	
	public Carta salvar (Carta novaCarta) throws VemNoX1Exception {
		
		
		int totalPontosAtributos = novaCarta.getForca() + novaCarta.getVelocidade() + novaCarta.getInteligencia();
		if (totalPontosAtributos > MAXIMO_ATRIBUTOS_PERMITIDO) {
			
			throw new VemNoX1Exception("Excedeu o total de " + MAXIMO_ATRIBUTOS_PERMITIDO);
			
		}		
		return repository.salvar(novaCarta);
		
		
	}

}
