package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {

    /**
     * 
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String busca() {
        CarrinhoDAO carrinho = new CarrinhoDAO();
        return toXml(carrinho.busca(1l));
    }

    /**
     * 
     * @param carrinho
     * @return
     */
    private String toXml(Carrinho carrinho) {
        XStream xStream = new XStream();
        return xStream.toXML(carrinho);
    }

}
