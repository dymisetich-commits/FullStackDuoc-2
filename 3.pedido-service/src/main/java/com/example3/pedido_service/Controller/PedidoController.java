package com.example3.pedido_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example3.pedido_service.Model.PedidoModel;
import com.example3.pedido_service.Service.PedidoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//hacemolos lo mismo que el restaurante service anterior
@Slf4j //por los logs

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    //listar todos los pedidos 
    @GetMapping
    public ResponseEntity<List<PedidoModel>> listar(){
        log.info("GET -> listar pedidos");

        return ResponseEntity.ok(pedidoService.findAll());
    }

    //porcar por id

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> buscar(@PathVariable Long id){
        log.info("GET -> buscar pedido {}", id);

        return ResponseEntity.ok(pedidoService.findById(id));
    }

    //crear el pedido
    @PostMapping
    public ResponseEntity<PedidoModel> guardar(@Valid @RequestBody PedidoModel pedidoModel){
        log.info("POST -> guardar pedido");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoService.save(pedidoModel));
    }


    //actualizar pedido 
    @PutMapping("/{id}")
    public ResponseEntity<PedidoModel> actualizar(@PathVariable Long id, @Valid @RequestBody PedidoModel pedidoModel){

        log.info("PUT -> actualizar pedido{}", id);

        return ResponseEntity.ok(pedidoService.update(id, pedidoModel));

    }

    //eliminar el pedido 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable long id){
        log.info("DELETE -> eliminar pedido {}", id);

        pedidoService.delete(id);
        
        return ResponseEntity.ok("Pedido eliminado");
    }

}


/*
CREATE DATABASE pedido_db;
*/


/*
GET http://localhost:8083/api/pedidos
*/


/*
GET http://localhost:8083/api/pedidos/1
*/

/*
{ post
  "usuarioId": 1,
  "restauranteId": 1,
  "total": 25000
}
*/

/* put 
{
  "usuarioId": 1,
  "restauranteId": 1,
  "estado": "PAGADO",
  "total": 25000
}
*/
