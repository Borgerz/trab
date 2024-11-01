package teste;

public class TESTE { 

    public static void main(String[] args) {
        Estoque estoque = new Estoque(); // Cria uma nova instância do estoque
        FACE face = new FACE(estoque); // Passa a instância do estoque para a interface

        // Inicializa a interface
        face.setVisible(true);
    }
}