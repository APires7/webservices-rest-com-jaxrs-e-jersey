package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ClienteTest {

    private HttpServer server;

    @Before
    public void startServidor() {
        this.server = new Servidor().inicializaServidor();
    }

    @After
    public void stopServidor() {
        this.server.stop();
    }

    @Test
    public void testaURICarrinho() {
        Client client = ClientBuilder.newClient();

        // URI base do servidor para fazer request
        WebTarget target = client.target("http://localhost:8080");

        String conteudo = target.path("/carrinhos").request().get(String.class);

        // Deserializa o objeto
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());

        // Sem deserializar o objeto
        // Assert.assertTrue(conteudo.contains("<rua> Rua Vergueiro 3185"));
    }

    @Test
    public void testaURIProjeto() {
        Client client = ClientBuilder.newClient();

        // URI base do servidor para fazer request
        WebTarget target = client.target("http://localhost:8080");

        String conteudo = target.path("/projetos").request().get(String.class);

        // Deserializa o objeto
        Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
        Assert.assertEquals("Minha loja", projeto.getNome());
    }

}
