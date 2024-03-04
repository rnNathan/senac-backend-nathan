package controller;

import java.util.ArrayList;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Carta;
import model.repository.CartaRepository;

@Path("/carta")
public class CartaController {
	
	private CartaRepository repository = new CartaRepository();
	
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Carta salvar (Carta novaCarta) {
		return repository.salvar(novaCarta);
		
	}
	
	@GET
	@Path("/sortear")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Carta> sortear (){
		return repository.sortearSeisCartas();
		
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean excluir (int id) {
		return repository.excluir(id);
		
	}
	

}