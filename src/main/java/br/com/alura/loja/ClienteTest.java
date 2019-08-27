package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;
import br.com.alura.loja.resource.CarrinhoResource;
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

		String conteudo = target.path("/carrinhos/1").request().get(String.class);

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

		String conteudo = target.path("/projetos/1").request().get(String.class);

		// Deserializa o objeto
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		Assert.assertEquals("Minha loja", projeto.getNome());
	}

	@Test
	public void testaURICarrinhoPost() {
		Client client = ClientBuilder.newClient();

		// URI base do servidor para fazer request
		WebTarget target = client.target("http://localhost:8080");

		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("Sao Paulo");

		CarrinhoResource carrinhoResource = new CarrinhoResource();
		String xml = carrinhoResource.toXml(carrinho);

		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);

		Response response = target.path("/carrinhos").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("Location");
		String conteudo = client.target(location).request().get(String.class);
		Assert.assertTrue(conteudo.contains("Tablet"));
	}

}
