package sample;

import java.util.Date;

public class Empregado extends Pessoa {

    private Boolean ehAposentado;
    private static final double BENEFICIO_BASE = 1777.77;
    private static final int QUANTIDADE_MESES_BENEFICIO = 12;

    public Empregado(String nomeCompleto, Date dataNascimento, String estadoUF, Boolean ehAposentado) {
        super(nomeCompleto, dataNascimento, Categoria.EMPREGADO, estadoUF);
        this.ehAposentado = ehAposentado;
    }

    public Boolean getEhAposentado() {
        return ehAposentado;
    }

    public void setEhAposentado(Boolean ehAposentado) {
        this.ehAposentado = ehAposentado;
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

