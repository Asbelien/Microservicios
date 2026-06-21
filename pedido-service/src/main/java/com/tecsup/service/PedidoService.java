package com.tecsup.service;

import com.tecsup.client.ClienteClient;
import com.tecsup.client.ProductoClient;
import com.tecsup.dto.ClienteResponseDTO;
import com.tecsup.dto.PedidoDTO;
import com.tecsup.dto.ProductoResponseDTO;
import com.tecsup.model.Pedido;
import com.tecsup.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private ProductoClient productoClient;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido guardar(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setClienteId(dto.getClienteId());
        pedido.setProductoId(dto.getProductoId());
        pedido.setCantidad(dto.getCantidad());
        pedido.setEstado("pendiente");
        return pedidoRepository.save(pedido);
    }

    public Pedido cambiarEstado(Long id, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));

        List<String> estadosValidos = List.of("pendiente", "atendido", "anulado");
        if (!estadosValidos.contains(nuevoEstado)) {
            throw new RuntimeException("Estado inválido. Use: pendiente, atendido o anulado");
        }

        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    public boolean existe(Long id) {
        return pedidoRepository.existsById(id);
    }

    // Construye la respuesta completa combinando datos vía Feign
    public Map<String, Object> obtenerPedidoCompleto(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));

        ClienteResponseDTO cliente = clienteClient.obtenerClientePorId(pedido.getClienteId());
        ProductoResponseDTO producto = productoClient.obtenerProductoPorId(pedido.getProductoId());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("id", pedido.getId());
        respuesta.put("cantidad", pedido.getCantidad());
        respuesta.put("estado", pedido.getEstado());
        respuesta.put("cliente", cliente);
        respuesta.put("producto", producto);

        return respuesta;
    }
}