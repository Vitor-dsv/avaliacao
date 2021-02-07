package sample;

import java.util.Date;

public class Desempregado extends Pessoa {
    private int qtdMesesDesempregado;
    private static final double BENEFICIO_BASE = 1999.99;
    private static final int QUANTIDADE_MESES_BENEFICIO = 12;

    public Desempregado(String nomeCompleto, Date dataNascimento, String estadoUF, int qtdMesesDesempregado) {
        super(nomeCompleto, dataNascimento, Categoria.DESEMPREGADO, estadoUF);
        this.qtdMesesDesempregado = qtdMesesDesempregado;
    }

    public int getQtdMesesDesempregado() {
        return qtdMesesDesempregado;
    }

    public void setQtdMesesDesempregado(int qtdMesesDesempregado) {
        this.qtdMesesDesempregado = qtdMesesDesempregado;
    }

    @Override
    public double retornarBeneficio() {
        double beneficio = BENEFICIO_BASE;

        if (this.getEstadoUF().equalsIgnoreCase(UF_TOCANTINS) || this.getEstadoUF().equalsIgnoreCase(UF_PARA)) {
            beneficio += (beneficio * 0.09);
        }
        return beneficio;
    }

    @Override
    public int retornarQuantidadeMesesBeneficio() {
        return QUANTIDADE_MESES_BENEFICIO;
    }

}
