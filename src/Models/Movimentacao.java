package Models;

public class Movimentacao {
    final String descricao;
    final double valor;

    public Movimentacao(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return descricao + ": R$ " + valor;
    }
}
