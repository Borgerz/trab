package teste;

import java.io.Serializable;

public class Produtos implements Serializable {
    private String nome;
    private String tipo;
    private int quantidade;
    private double preco;

    public Produtos(String nome, String tipo, int quantidade, double preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}