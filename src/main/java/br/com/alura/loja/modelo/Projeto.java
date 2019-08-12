package br.com.alura.loja.modelo;

public class Projeto {

    private String nome;

    private long id;

    private int anoDeInicio;

    /**
     * Create a new instance of Projeto
     * 
     * @param nome
     * @param id
     * @param anoDeInicio
     */
    public Projeto(String nome, long id, int anoDeInicio) {
        super();
        this.nome = nome;
        this.id = id;
        this.anoDeInicio = anoDeInicio;
    }

    /**
     * Create a new instance of Projeto
     */
    public Projeto() {
        super();
    }

    /**
     * Retrieve the value of nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retrieve the value of id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Retrieve the value of anoDeInicio.
     *
     * @return the anoDeInicio
     */
    public int getAnoDeInicio() {
        return anoDeInicio;
    }

    /**
     * Set a new value to nome.
     * 
     * @param nome
     *                 the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Set a new value to id.
     * 
     * @param id
     *               the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Set a new value to anoDeInicio.
     * 
     * @param anoDeInicio
     *                        the anoDeInicio to set
     */
    public void setAnoDeInicio(int anoDeInicio) {
        this.anoDeInicio = anoDeInicio;
    }

}
