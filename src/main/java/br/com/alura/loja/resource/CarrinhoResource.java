package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {

	/**
	 * 
	 * @return
	 */
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") long id) {
		CarrinhoDAO carrinho = new CarrinhoDAO();
		return toXml(carrinho.busca(id));
	}

	/**
	 * 
	 * @param conteudo
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(uri).build();
	}

	/**
	 * 
	 * @param carrinho
	 * @return
	 */
	public String toXml(Carrinho carrinho) {
		XStream xStream = new XStream();
		return xStream.toXML(carrinho);
	}

	/**
	 * 
	 * @param carrinho
	 * @return
	 */
	public String toJson(Carrinho carrinho) {
		Gson gson = new Gson();
		return gson.toJson(carrinho);
	}

}
