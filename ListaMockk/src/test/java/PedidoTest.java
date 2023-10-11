
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoTest {

    @Mock
    DescontoService descontoService;

    Pedido pedido;

    private List<ItemPedido> itens = getFakeItens();
    private List<ItemPedido> itensVazio = new ArrayList<>();

    List<ItemPedido> getFakeItens() {
        ArrayList<ItemPedido> itens = new ArrayList<ItemPedido>();
        itens.add(new ItemPedido("item_1", 40.0));
        itens.add(new ItemPedido("item_2", 60.0));
        return itens;
    }

    // 1
    @Test
    public void testDescontoAplicadoDezPorcento() {
        // Arrange
        pedido = new Pedido(itens, descontoService);
        when(descontoService.calcularDesconto(100)).thenReturn(10.0);

        // Act
        double result = pedido.calcularValorTotal();

        // Assert
        Assert.assertEquals(90, result, 0.001);
    }

    // 2
    @Test
    public void testDescontoAplicadoZeroPorcento() {
        // Arrange
        pedido = new Pedido(itens, descontoService);
        when(descontoService.calcularDesconto(100)).thenReturn(0.0);

        // Act
        double result = pedido.calcularValorTotal();

        // Assert
        Assert.assertEquals(100, result, 0.001);
    }

    // 3
    @Test
    public void testDescontoAplicadoMaiorQueValorTotal() {
        // Arrange
        pedido = new Pedido(itens, descontoService);
        when(descontoService.calcularDesconto(100)).thenReturn(150.0);

        // Act
        double result = pedido.calcularValorTotal();

        // Assert
        Assert.assertEquals(0.0, result, 0.001);
    }

    // 4
    @Test
    public void testDescontoAplicadoThownException() {
        // Arrange
        pedido = new Pedido(itens, descontoService);
        when(descontoService.calcularDesconto(100)).thenThrow(new IllegalArgumentException());

        // Act Assert
        assertThrows(IllegalArgumentException.class, () -> pedido.calcularValorTotal());
    }

    // 5
    @Test
    public void testDescontoAplicadoItensListaVazia() {
        // Arrange
        pedido = new Pedido(itensVazio, descontoService);

        // Act
        double result = pedido.calcularValorTotal();

        // Assert
        verify(descontoService, times(0)).calcularDesconto(0.0);
        Assert.assertEquals(0.0, result, 0.001);
    }

    // 6
    @Test
    public void testDescontoAplicadoPorItem() {
        // Arrange
        pedido = new Pedido(itens, descontoService);

        when(descontoService.calcularDesconto(anyDouble())).thenReturn(10.0, 30.0);

        // Act
        double result = pedido.calcularValorTotalPorItem();

        // Assert
        verify(descontoService, times(2)).calcularDesconto(anyDouble());
        Assert.assertEquals(60.0, result, 0.001);
    }

    @Test
    public void testDescontoAplicadoNumeroDeChamadas() {
        // Arrange
        pedido = new Pedido(itens, descontoService);

        when(descontoService.calcularDesconto(100.0)).thenReturn(10.0);

        // Act
        double result = pedido.calcularValorTotal();

        // Assert
        verify(descontoService, times(1)).calcularDesconto(100.0);
        Assert.assertEquals(90.0, result, 0.001);
    }
}
