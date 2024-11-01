package teste;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Estoque implements Serializable {
    private List<Produtos> produtos;

    public Estoque() {
        produtos = new ArrayList<>();
        loadEstoque(); // Carrega o estoque ao inicializar
    }

    public void adicionarProduto(Produtos produto) {
        produtos.add(produto);
        saveEstoque(); // Salva o estoque após adicionar um produto
    }

    public void removerProduto(String nome) {
        produtos.removeIf(produto -> produto.getNome().equalsIgnoreCase(nome));
        saveEstoque(); // Salva o estoque após remover um produto
    }

    public void atualizarEstoque(String nome, int novaQuantidade) {
        for (Produtos produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                produto.setQuantidade(novaQuantidade);
                break;
            }
        }
        saveEstoque(); // Salva o estoque após atualizar
    }

    public boolean produtoExistente(String nome) {
        return produtos.stream().anyMatch(produto -> produto.getNome().equalsIgnoreCase(nome));
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    // Método para salvar o estoque em um arquivo
    private void saveEstoque() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estoque.dat"))) {
            oos.writeObject(produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar o estoque de um arquivo
    @SuppressWarnings("unchecked")
    private void loadEstoque() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("estoque.dat"))) {
            produtos = (List<Produtos>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Se o arquivo não existe ou erro na leitura, inicializa uma nova lista
            produtos = new ArrayList<>();
        }
    }
}