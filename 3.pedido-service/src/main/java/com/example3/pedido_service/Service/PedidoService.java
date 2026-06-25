package com.example3.pedido_service.Service;

import java.util.List;

import com.example3.pedido_service.Model.PedidoModel;

public interface PedidoService {

    //listar todos los pedidos
    List<PedidoModel> findAll();

    //buscar pedido por id
    PedidoModel findById(Long id);

    //guardar pedido
    PedidoModel save(PedidoModel pedidoModel);

    //actulizar el pedido
    PedidoModel update(Long id, PedidoModel pedidoModel);

    //eliminar el pedido
    void delete(Long id);
}
