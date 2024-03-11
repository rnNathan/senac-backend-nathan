package model.dto;

public class JogadaDTO {
	
	private int idPartida;
	private int idCartaNaPartidaSelecionada;
	private String atributoSelecionado;
	public JogadaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JogadaDTO(int idPartida, int idCartaNaPartidaSelecionada, String atributoSelecionado) {
		super();
		this.idPartida = idPartida;
		this.idCartaNaPartidaSelecionada = idCartaNaPartidaSelecionada;
		this.atributoSelecionado = atributoSelecionado;
	}
	public int getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}
	public int getIdCartaNaPartidaSelecionada() {
		return idCartaNaPartidaSelecionada;
	}
	public void setIdCartaNaPartidaSelecionada(int idCartaNaPartidaSelecionada) {
		this.idCartaNaPartidaSelecionada = idCartaNaPartidaSelecionada;
	}
	public String getAtributoSelecionado() {
		return atributoSelecionado;
	}
	public void setAtributoSelecionado(String atributoSelecionado) {
		this.atributoSelecionado = atributoSelecionado;
	}
	
	
	

}
