import java.util.List;

public class Pedido {

    private List<ItemPedido> itens;
    private DescontoService descontoService;

    public Pedido(List<ItemPedido> itens, DescontoService descontoService) {
        this.itens = itens;
        this.descontoService = descontoService;
    }

    public double calcularValorTotal() {
        double valorTotal = 0.0;

        if (itens.isEmpty()) {
            return 0.0;
        }

        for (ItemPedido item : itens) {
            valorTotal += item.getSubtotal();
        }

        double result = valorTotal - descontoService.calcularDesconto(valorTotal);
        return Math.max(result, 0.0);
    }

    public double calcularValorTotalPorItem() {
        double valorTotal = 0.0;

        if (itens.isEmpty()) {
            return 0.0;
        }

        for (ItemPedido item : itens) {
            double itemSubtotal = item.getSubtotal();
            valorTotal += ( itemSubtotal - descontoService.calcularDesconto(itemSubtotal));
        }

        return valorTotal;
    }
}