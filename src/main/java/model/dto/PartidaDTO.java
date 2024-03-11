package model.dto;

import java.util.List;

import model.entity.CartaNaPartida;
import model.repository.CartaPartida;

public class PartidaDTO {
	
	private int idPartida;
	private List<CartaNaPartida> cartasJogador;
	private List<String> atributosDisponiveis;
	private String resultadoUltimaJogada;
	public PartidaDTO(int idPartida, List<CartaNaPartida> cartasJogador, List<String> atributosDisponiveis,
			String resultadoUltimaJogada) {
		super();
		this.idPartida = idPartida;
		this.cartasJogador = cartasJogador;
		this.atributosDisponiveis = atributosDisponiveis;
		this.resultadoUltimaJogada = resultadoUltimaJogada;
	}
	public PartidaDTO() {
		super();
	}
	public int getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}
	public List<CartaNaPartida> getCartasJogador() {
		return cartasJogador;
	}
	public void setCartasJogador(List<CartaNaPartida> cartasJogador) {
		this.cartasJogador = cartasJogador;
	}
	public List<String> getAtributosDisponiveis() {
		return atributosDisponiveis;
	}
	public void setAtributosDisponiveis(List<String> atributosDisponiveis) {
		this.atributosDisponiveis = atributosDisponiveis;
	}
	public String getResultadoUltimaJogada() {
		return resultadoUltimaJogada;
	}
	public void setResultadoUltimaJogada(String resultadoUltimaJogada) {
		this.resultadoUltimaJogada = resultadoUltimaJogada;
	}
	
	

	
}
