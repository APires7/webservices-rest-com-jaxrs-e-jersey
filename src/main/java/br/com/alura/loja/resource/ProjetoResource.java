package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String busca() {
        ProjetoDAO projeto = new ProjetoDAO();
        return toXml(projeto.busca(1l));
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
