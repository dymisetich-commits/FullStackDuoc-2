package com.example3.pedido_service.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example3.pedido_service.Model.PedidoModel;
import com.example3.pedido_service.Repository.PedidoRepository;
import com.example3.pedido_service.Service.PedidoService;
import com.example3.pedido_service.feign.RestauranteFeignClient;
import com.example3.pedido_service.feign.UsuarioFeignClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//habilitamos los logs
@Slf4j

@RequiredArgsConstructor //reemplaza el autowired
@Service
public class PedidoServiceImpl implements PedidoService { //implementamos el pedidoservice que la interface

    //pedidoserviceimpl sale en rojo por que tenemos que llenar las cosas con lo de pedido service dandole que sale epico jsjsjsjsj ta bien 

    
    private final PedidoRepository pedidoRepository;

    private final UsuarioFeignClient usuarioFeignClient;

    private final RestauranteFeignClient restauranteFeignClient;

    //listar todos los pedidos
    @Override
    public List<PedidoModel> findAll(){
        log.info("Listando todos los pedidos");

        return pedidoRepository.findAll();
    }

    //buscar por id
    @Override
    public PedidoModel findById(Long id){
        log.info("uscando pedido con id: {}", id);

        return pedidoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Pedido no encontradood"));
    }

    //guardar pedido
    @Override
    public PedidoModel save(PedidoModel pedidoModel){

        log.info("Guardando el nuevo pedido");

        // validar usuario
        var usuario =
                usuarioFeignClient.obtenerUsuario(
                        pedidoModel.getUsuarioId());

        log.info("Usuario encontrado: {}",
                usuario.getNombre());

        // validar restaurante
        var restaurante =
                restauranteFeignClient.obtenerRestaurante(
                        pedidoModel.getRestauranteId());

        log.info("Restaurante encontrado: {}",
                restaurante.getNombre());

        // fecha automática
        pedidoModel.setFecha(LocalDateTime.now());

        // estado inicial
        pedidoModel.setEstado("PENDIENTE");

        return pedidoRepository.save(pedidoModel);
        }

    //actualiazr pedido
    @Override
    public PedidoModel update(Long id, PedidoModel pedidoModel){
        log.info("Actulizando");

        PedidoModel existente = findById(id);

        existente.setUsuarioId(pedidoModel.getUsuarioId());
        existente.setRestauranteId(pedidoModel.getRestauranteId());
        existente.setEstado(pedidoModel.getEstado());
        existente.setTotal(pedidoModel.getTotal());

        return pedidoRepository.save(existente);
    }

    //eliminar pedido
    @Override
    public void delete(Long id){
        log.info("Eliminando pedido: {}", id);

        PedidoModel pedidoModel = findById(id);

        pedidoRepository.delete(pedidoModel);
    }
}
