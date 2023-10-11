public class ItemPedido {

    private String nome;
    private double subtotal;

    public ItemPedido(String nome, double subtotal) {
        this.nome = nome;
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
