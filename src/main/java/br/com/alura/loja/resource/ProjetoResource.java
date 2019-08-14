package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
     * @param projeto
     * @return
     */
    private String toXml(Projeto projeto) {
        XStream xStream = new XStream();
        return xStream.toXML(projeto);
    }

}
