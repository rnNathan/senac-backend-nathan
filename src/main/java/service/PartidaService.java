package service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.dto.JogadaDTO;
import model.dto.PartidaDTO;
import model.entity.Carta;
import model.entity.CartaNaPartida;
import model.entity.Partida;
import model.entity.enums.Resultado;
import model.repository.CartaPartida;
import model.repository.CartaRepository;
import model.repository.JogadorRepository;
import model.repository.PartidaRepository;

public class PartidaService {
	
	private JogadorRepository jogadorRepository = new JogadorRepository();
	private PartidaRepository partidaRepository = new PartidaRepository();
	private CartaRepository cartaRepository = new CartaRepository();
	private CartaPartida cartaPartida = new CartaPartida();

	
	public PartidaDTO iniciarPartida(int idJogador) {
		PartidaDTO dto = new PartidaDTO();
		
		Partida novaPartida = new Partida();
		
		novaPartida.setResultado(Resultado.EM_ANDAMENTO);
		novaPartida.setData(LocalDateTime.now());
		novaPartida.setJogador(jogadorRepository.consultarPorId(idJogador));
		novaPartida = partidaRepository.salvar(novaPartida);
		
		ArrayList<String> atributos = new ArrayList<String>();
		atributos.add("Forçã");
		atributos.add("Inteligencia");
		atributos.add("Velocidade");
		
		ArrayList<Carta> seisCartas = cartaRepository.sortearSeisCartas();
		ArrayList<CartaNaPartida>cartasDoJogador = new ArrayList<CartaNaPartida>();
		
		
		boolean ehDoJogador = true;
		
		for (Carta carta : seisCartas) {
			CartaNaPartida cartaDaPartida = new CartaNaPartida();
			cartaDaPartida.setIdPartida(novaPartida.getId());
			cartaDaPartida.setCarta(carta);
			cartaDaPartida.setPertenceAoJogador(ehDoJogador);
			cartaDaPartida = cartaPartida.salvar(cartaDaPartida);
			
			if (ehDoJogador) {
				cartasDoJogador.add(cartaDaPartida);
			}
			
			ehDoJogador = !ehDoJogador;
			
		}
		
		dto.setIdPartida(novaPartida.getId());
		dto.setResultadoUltimaJogada(null);
		dto.setAtributosDisponiveis(atributos);
		dto.setCartasJogador(cartasDoJogador);
		return dto;
		
	}
	
	public PartidaDTO jogar (JogadaDTO jogada) {
		PartidaDTO partidaAtualizada = new PartidaDTO();
		Partida partida = partidaRepository.consultarPorId(jogada.getIdPartida());
		CartaNaPartida cartaJogada = cartaPartida.consultarPorId(jogada.getIdCartaNaPartidaSelecionada());
		
		if (jogada.getAtributoSelecionado() == "Força") {
			int valorForca = cartaJogada.getCarta().getForca();
			
		}
		
		return partidaAtualizada;
		
				
		
		
	}
	
}
