package teste;

public class Venda {

    private Cliente cliente;
    private Produtos produto;
    private int quantidade;
    private double valorTotal;

    public Venda(Cliente cliente, Produtos produto, int quantidade) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = calcularValorTotal();
    }

    // Método para calcular o valor total da venda
    private double calcularValorTotal() {
        return produto.getPreco() * quantidade;
    }

    public boolean realizarVenda(Estoque estoque) {
        if (produto.getQuantidade() >= quantidade) {
            produto.setQuantidade(produto.getQuantidade() - quantidade);
            estoque.atualizarEstoque(produto.getNome(), produto.getQuantidade()); // Atualiza o estoque
            System.out.println("Venda realizada para " + cliente.getNome() + " no valor de R$ " + valorTotal);
            return true; // Venda realizada com sucesso
        } else {
            System.out.println("Quantidade insuficiente em estoque!");
            return false; // Venda não realizada
        }
    }

    @Override
    public String toString() {
        return "Venda{" +
                "cliente=" + cliente.getNome() +
                ", produto=" + produto.getNome() +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                '}';
    }
}