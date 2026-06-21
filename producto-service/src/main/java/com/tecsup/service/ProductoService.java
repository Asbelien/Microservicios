package com.tecsup.service;

import com.tecsup.client.CategoriaClient;
import com.tecsup.dto.CategoriaDTO;
import com.tecsup.model.Producto;
import com.tecsup.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaClient categoriaClient;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, Producto productoActualizado) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        producto.setNombre(productoActualizado.getNombre());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setCategoriaId(productoActualizado.getCategoriaId());
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return productoRepository.existsById(id);
    }

    // Construye la respuesta completa (producto + datos de categoría vía Feign)
    public Map<String, Object> obtenerProductoConCategoria(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        CategoriaDTO categoria = categoriaClient.obtenerCategoriaPorId(producto.getCategoriaId());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("id", producto.getId());
        respuesta.put("nombre", producto.getNombre());
        respuesta.put("precio", producto.getPrecio());
        respuesta.put("categoria", categoria);

        return respuesta;
    }
}