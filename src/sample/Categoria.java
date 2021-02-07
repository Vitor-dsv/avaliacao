package sample;

public enum Categoria {
    EMPREGADO(1),
    EMPREGADOR(2),
    DESEMPREGADO(3);

    private final int codigoEmpregado;

    Categoria(int codigoEmpregado) {
        this.codigoEmpregado = codigoEmpregado;
    }

    public static Categoria valueOf(int codigoEmpregado) {
        switch (codigoEmpregado) {
            case 1:
                return Categoria.EMPREGADO;
            case 2:
                return Categoria.EMPREGADOR;
            case 3:
                return Categoria.DESEMPREGADO;
            default:
                return null;
        }
    }
}
