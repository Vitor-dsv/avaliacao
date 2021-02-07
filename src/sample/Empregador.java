package sample;

import java.util.Date;

public class Empregador extends Pessoa {
    private int qtdFuncionarios;
    static final double VALOR_POR_FUNCIONARIO = 200.00;

    public Empregador(String nomeCompleto, Date dataNascimento, String estadoUF, int qtdFuncionarios) {
        super(nomeCompleto, dataNascimento, Categoria.EMPREGADOR, estadoUF);
        this.qtdFuncionarios = qtdFuncionarios;
    }

    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public void setQtdFuncionarios(int qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    @Override
    public double retornarBeneficio() {
        double beneficio = VALOR_POR_FUNCIONARIO * this.getQtdFuncionarios();

        if (this.getEstadoUF().equalsIgnoreCase(UF_TOCANTINS) || this.getEstadoUF().equalsIgnoreCase(UF_PARA)) {
            beneficio += (beneficio * 0.09);
        }

        return beneficio;
    }

    @Override
    public int retornarQuantidadeMesesBeneficio() {
        if (this.getQtdFuncionarios() >= 10) {
            return 10;
        }

        return 7;
    }

    public void exbirRegrasMesesBeneficio() {
        if (this.getQtdFuncionarios() >= 10) {
            System.out.println("Você contém até 10 funcionários, com isso seu beneficío irá ser de 10 meses.\n");
        } else {
            System.out.println("Você terá 7 meses de benefício por ser empregador.\n");
        }
    }
}
