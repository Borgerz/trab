package teste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FACE extends JFrame implements ActionListener {
    private JTextArea displayArea;
    private JTextField inputField;
    private Estoque estoque;

    public FACE(Estoque estoque) {
        this.estoque = estoque;
        setTitle("Controle de Estoque");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        inputField = new JTextField();
        add(inputField, BorderLayout.SOUTH);

        JButton enterButton = new JButton("Enviar");
        enterButton.addActionListener(this);
        add(enterButton, BorderLayout.EAST);

        setVisible(true);
        updateDisplay("Bem-vindo ao Controle de Estoque!");
        showMenu();
    }

    public void updateDisplay(String text) {
        displayArea.append(text + "\n");
        displayArea.setCaretPosition(displayArea.getDocument().getLength()); // Scroll to bottom
    }

    private void showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("\nControle de Estoque\n");
        menu.append("1. Adicionar Produto\n");
        menu.append("2. Remover Produto\n");
        menu.append("3. Atualizar Estoque\n");
        menu.append("4. Listar Produtos\n");
        menu.append("5. Sair\n");
        updateDisplay(menu.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();
        processInput(input);
        inputField.setText(""); // Limpa o campo de entrada
    }

    private void processInput(String input) {
        try {
            int opcao = Integer.parseInt(input);
            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                    atualizarEstoque();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    updateDisplay("Saindo do sistema...");
                    System.exit(0);
                    break;
                default:
                    updateDisplay("Opção inválida! Tente novamente.");
                    showMenu();
            }
        } catch (NumberFormatException e) {
            updateDisplay("Por favor, insira um número entre 1 e 5.");
            showMenu();
        }
    }

    private void adicionarProduto() {
        try {
            String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
            if (nome == null) return; // Se cancelar, sai da operação
            
            String tipo = "";
            while (true) {
                tipo = JOptionPane.showInputDialog("Digite o tipo de bebida:");
                if (tipo == null) return; // Se cancelar, sai da operação
                if (!tipo.matches(".*\\d.*")) { // Verifica se não contém dígitos
                    break;
                } else {
                    updateDisplay("O tipo de bebida não pode conter números. Tente novamente.");
                }
            }

            int quantidade = -1;
            while (quantidade < 0) {
                String quantidadeInput = JOptionPane.showInputDialog("Digite a quantidade em estoque:");
                if (quantidadeInput == null) return; // Se cancelar, sai da operação
                try {
                    quantidade = Integer.parseInt(quantidadeInput);
                    if (quantidade < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    updateDisplay("Quantidade deve ser um número válido e não negativo.");
                }
            }

            double preco = -1;
            while (preco < 0) {
                String precoInput = JOptionPane.showInputDialog("Digite o preço do produto:");
                if (precoInput == null) return; // Se cancelar, sai da operação
                try {
                    preco = Double.parseDouble(precoInput);
                    if (preco < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    updateDisplay("Preço deve ser um número válido e não negativo.");
                }
            }

            Produtos produto = new Produtos(nome, tipo, quantidade, preco);
            estoque.adicionarProduto(produto);
            updateDisplay("Produto adicionado: " + nome);
        } catch (Exception e) {
            updateDisplay("Erro ao adicionar produto.");
        }
    }

    private void removerProduto() {
        String nomeRemover = JOptionPane.showInputDialog("Digite o nome do produto a ser removido:");
        if (nomeRemover == null) return; // Se cancelar, sai da operação
        
        if (!estoque.produtoExistente(nomeRemover)) {
            updateDisplay("Produto não encontrado no estoque. Verifique o nome e tente novamente.");
            return;
        }

        estoque.removerProduto(nomeRemover);
        updateDisplay("Produto removido: " + nomeRemover);
    }

    private void atualizarEstoque() {
        String nomeAtualizar = JOptionPane.showInputDialog("Digite o nome do produto a ser atualizado:");
        if (nomeAtualizar == null) return; // Se cancelar, sai da operação
        
        if (!estoque.produtoExistente(nomeAtualizar)) {
            updateDisplay("Produto não encontrado no estoque. Verifique o nome e tente novamente.");
            return;
        }
        
        int novaQuantidade = -1;
        while (novaQuantidade < 0) {
            String quantidadeInput = JOptionPane.showInputDialog("Digite a nova quantidade em estoque:");
            if (quantidadeInput == null) return; // Se cancelar, sai da operação
            try {
                novaQuantidade = Integer.parseInt(quantidadeInput);
                if (novaQuantidade < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                updateDisplay("Quantidade deve ser um número válido e não negativo.");
            }
        }

        estoque.atualizarEstoque(nomeAtualizar, novaQuantidade);
        updateDisplay("Estoque atualizado para: " + nomeAtualizar);
    }

    private void listarProdutos() {
        StringBuilder listaProdutos = new StringBuilder("Produtos em estoque:\n");
        for (Produtos p : estoque.getProdutos()) {
            listaProdutos.append(p).append("\n");
        }
        updateDisplay(listaProdutos.toString());
    }
}