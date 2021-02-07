package sample;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static final String UF_TOCANTINS = "TO";
    static final String UF_PARA = "PA";

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);

        boolean continuarPedindoDados = Boolean.TRUE;
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        while (continuarPedindoDados) {
            // Informações para cadastro do Beneficiário.
            Pessoa pessoa = null;
            String nome = "";
            Date dataNascimento = null;
            Categoria categoria = null;
            String estadoUF = "";

            // Informações especificas para tipo de Categoria.
            Boolean ehAposentado = Boolean.FALSE;
            int qtdFuncionarios = 0;
            int qtdMesesDesempregado = 0;

            // Nome Completo.
            System.out.println("Digite seu nome completo: ");
            nome = s.nextLine();

            // Data de nascimento.
            System.out.println("Digite sua data de nascimento: ");
            String dataNascimentoTexto = s.nextLine();

            // Validar formato da data de nascimento.
            dataNascimento = new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimentoTexto);

            // Categoria.
            System.out.println("Digite sua categoria a partir da lista: ");
            System.out.println("1 - Empregado.");
            System.out.println("2 - Empregador.");
            System.out.println("3 - Desempregado.");

            categoria = Categoria.valueOf(s.nextInt());

            // Estado (UF).
            System.out.println("Digite o seu Estado(UF): ");
            estadoUF = s.next();

            if (Objects.isNull(nome) || Objects.isNull(dataNascimento) || Objects.isNull(categoria) || Objects.isNull(estadoUF)) {
                throw new Exception("Você inseriu as informações de forma incorreta.");

            } else {
                if (categoria.equals(Categoria.EMPREGADO)) {
                    // Aposentado.
                    System.out.println("Digite se você é aposentado conforme a lista: ");
                    System.out.println("S - Sim");
                    System.out.println("N - Não");
                    ehAposentado = s.nextLine().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE;

                } else if (categoria.equals(Categoria.EMPREGADOR)) {
                    // Quantidade de funcionários.
                    System.out.println("Digite a quantidade de funcionários que você tem: ");
                    qtdFuncionarios = s.nextInt();

                } else if (categoria.equals(Categoria.DESEMPREGADO)) {
                    // Quantidade de meses desempregado.
                    System.out.println("Digite a quantidade de meses que você está desempregado: ");
                    qtdMesesDesempregado = s.nextInt();

                }

                if (categoria.equals(Categoria.EMPREGADO)) {
                    // Instanciado Empregado.
                    pessoa = new Empregado(nome, dataNascimento, estadoUF, ehAposentado);

                } else if (categoria.equals(Categoria.EMPREGADOR)) {
                    // Instanciado Empregador.
                    pessoa = new Empregador(nome, dataNascimento, estadoUF, qtdFuncionarios);

                } else if (categoria.equals(Categoria.DESEMPREGADO)) {
                    // Instanciado Desempregado.
                    pessoa = new Desempregado(nome, dataNascimento, estadoUF, qtdMesesDesempregado);

                }

                // Adicionado Beneficiado a lista.
                pessoas.add(pessoa);

                System.out.println("\n\n");

                // Exibir Dados Beneficiário:
                exibirDados(pessoa);

                // Exibir regras atentidas.
                System.out.println("Regras atendidas: \n");
                exibirRegraEstado(pessoa.getEstadoUF());
                exibirRegraSabadoDiaUtil();

                if (categoria.equals(Categoria.EMPREGADOR)) {
                    ((Empregador) pessoa).exbirRegrasMesesBeneficio();
                }

                // Exibir quantidade de meses e o valor do benefício.
                exibirQuantidadeTempoBeneficio(pessoa);
                exibirValorBeneficio(pessoa);

                // Questionar usuário se terá um novo beneficiário.
                System.out.println("Digite se você quer informar um novo beneficiário conforme a lista: ");
                System.out.println("S - Sim");
                System.out.println("N - Não");

                continuarPedindoDados = s.next().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE;

                s.nextLine();
            }
        }

        System.out.println("\n\n");

        if (pessoas.size() > 0) {
            System.out.println("Total de usuários lidos: " + pessoas.size());
            System.out.println("Total de beneficiários: " + pessoas.size());
            System.out.println("Total de valor concedido: " + retornarBeneficioConcedido(pessoas));
            System.out.println("O nome dos 2 beneficiários que irão receber os maiores valor de todos: " + retornarNomeDosBeneficiariosComMaiorValor(pessoas));
            System.out.println("O nome dos 2 beneficiários que irão receber os benefícios por mais tempo: " + retornarNomeDosBeneficiariosComMaiorTempo(pessoas));
        } else {
            System.out.println("Não foi cadastrado nenhum usuário.");
        }
    }

    private static void exibirDados(Pessoa pessoa) {
        StringBuilder str = new StringBuilder();
        str
                .append("Dados pessoais: \n")
                .append("Nome completo: ").append(pessoa.getNomeCompleto()).append(".\n")
                .append("Data de nascimento: ").append(new SimpleDateFormat("dd/mm/yyyy").format(pessoa.getDataNascimento())).append(".\n")
                .append("Categoria: ").append(pessoa.getCategoria()).append(".\n\n");

        // Exibir dados.
        System.out.println(str.toString());
    }

    private static void exibirRegraEstado(String estado) {
        if (estado.equalsIgnoreCase(UF_TOCANTINS) || estado.equalsIgnoreCase(UF_PARA)) {
            StringBuilder str = new StringBuilder();

            str
                    .append("Você recebeu um acréscimo de 9% por morar no estado de ")
                    .append(estado.equalsIgnoreCase(UF_PARA) ? "Pará." : "Tocantins")
                    .append("\n");

            // Exibir dados.
            System.out.println(str.toString());
        }
    }

    private static void exibirRegraSabadoDiaUtil() {
        System.out.println("Sábado será considerado dia útil para o pagamento.\n");
    }

    private static void exibirQuantidadeTempoBeneficio(Pessoa pessoa) {
        StringBuilder str = new StringBuilder();

        str
                .append("Quantidade de meses de benefício: ")
                .append(pessoa.retornarQuantidadeMesesBeneficio())
                .append("\n");

        System.out.println(str.toString());
    }

    private static void exibirValorBeneficio(Pessoa pessoa) {
        StringBuilder str = new StringBuilder();

        str
                .append("Valor Benefício: ")
                .append(pessoa.retornarBeneficio())
                .append("\n");

        System.out.println(str.toString());
    }

    private static double retornarBeneficioConcedido(ArrayList<Pessoa> pessoas) {
        double valorBeneficioCalculado = 0.0;

        for (Pessoa pessoa : pessoas) {
            valorBeneficioCalculado += pessoa.retornarBeneficio() * pessoa.retornarQuantidadeMesesBeneficio();
        }

        return valorBeneficioCalculado;
    }

    private static String retornarNomeDosBeneficiariosComMaiorValor(ArrayList<Pessoa> pessoas) {
        // Maior beneficiado.
        String nomeMaiorBeneficiado = "";
        double maiorValorBeneficado = 0.0;

        // Segundo maior beneficiado.
        String nomeSegundoMaiorBeneficiado = "";
        double segundoMaiorValorBeneficado = 0.0;

        for (Pessoa pessoa : pessoas) {
            String nome = pessoa.getNomeCompleto();
            double beneficio = pessoa.retornarBeneficio();

            if (beneficio > maiorValorBeneficado) {
                if (nomeMaiorBeneficiado != null) {
                    nomeSegundoMaiorBeneficiado = nomeMaiorBeneficiado;
                    segundoMaiorValorBeneficado = maiorValorBeneficado;
                }

                nomeMaiorBeneficiado = nome;
                maiorValorBeneficado = beneficio;

            } else if (beneficio > segundoMaiorValorBeneficado) {
                nomeSegundoMaiorBeneficiado = nome;
                segundoMaiorValorBeneficado = maiorValorBeneficado;
            }
        }

        if (!nomeSegundoMaiorBeneficiado.isEmpty()) {
            return nomeMaiorBeneficiado.concat(" e ").concat(nomeSegundoMaiorBeneficiado);
        } else {
            return nomeMaiorBeneficiado;
        }
    }

    private static String retornarNomeDosBeneficiariosComMaiorTempo(ArrayList<Pessoa> pessoas) {
        // Maior beneficiado.
        String nomeMaiorBeneficiado = "";
        int qtdMesesMaiorBeneficiado = 0;

        // Segundo maior beneficiado.
        String nomeSegundoMaiorBeneficiado = "";
        int qtdMesesSegundoMaiorBeneficiado = 0;

        for (Pessoa pessoa : pessoas) {
            String nome = pessoa.getNomeCompleto();
            int qtdMeses = pessoa.retornarQuantidadeMesesBeneficio();

            if (qtdMeses > qtdMesesMaiorBeneficiado) {
                if (nomeMaiorBeneficiado != null) {
                    nomeSegundoMaiorBeneficiado = nomeMaiorBeneficiado;
                    qtdMesesSegundoMaiorBeneficiado = qtdMesesMaiorBeneficiado;
                }

                nomeMaiorBeneficiado = nome;
                qtdMesesMaiorBeneficiado = qtdMeses;

            } else if (qtdMeses > qtdMesesSegundoMaiorBeneficiado) {
                nomeSegundoMaiorBeneficiado = nome;
                qtdMesesSegundoMaiorBeneficiado = qtdMeses;
            }
        }

        if (!nomeSegundoMaiorBeneficiado.isEmpty()) {
            return nomeMaiorBeneficiado.concat(" e ").concat(nomeSegundoMaiorBeneficiado);
        } else {
            return nomeMaiorBeneficiado;
        }
    }
}