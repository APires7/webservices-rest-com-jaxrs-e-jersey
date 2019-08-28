package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {

	/**
	 * 
	 * @return
	 */
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") long id) {
		ProjetoDAO projeto = new ProjetoDAO();
		return toXml(projeto.busca(id));
	}

	/**
	 * 
	 * @param conteudo
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);
		URI uri = URI.create("/projetos/" + projeto.getId());
		return Response.created(uri).build();
	}

	/**
	 * 
	 * @param projeto
	 * @return
	 */
	public String toXml(Projeto projeto) {
		XStream xStream = new XStream();
		return xStream.toXML(projeto);
	}

	/**
	 * 
	 * @param projeto
	 * @return
	 */
	public String toJson(Projeto projeto) {
		Gson gson = new Gson();
		return gson.toJson(projeto);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Path("{id}")
	@DELETE
	public Response removeProjeto(@PathParam("id") long id) {
		new ProjetoDAO().remove(id);
		return Response.ok().build();
	}

}
