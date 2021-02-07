package sample;

import java.util.Date;

public class Pessoa {

    private String nomeCompleto;
    private Date dataNascimento;
    private Categoria categoria;
    private String estadoUF;

    static final String UF_TOCANTINS = "TO";
    static final String UF_PARA = "PA";

    public Pessoa(String nomeCompleto, Date dataNascimento, Categoria categoria, String estadoUF) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.categoria = categoria;
        this.estadoUF = estadoUF;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEstadoUF() {
        return estadoUF;
    }

    public void setEstadoUF(String estadoUF) {
        this.estadoUF = estadoUF;
    }

    public double retornarBeneficio() {
        return 0.0;
    }

    public int retornarQuantidadeMesesBeneficio() {
        return 0;
    }


}
