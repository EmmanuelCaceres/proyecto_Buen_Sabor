package com.example.buensaborback.Services.impl;

import com.example.buensaborback.Services.IPedidoService;
import com.example.buensaborback.domain.DTOs.*;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.domain.entities.enums.FormaPago;
import com.example.buensaborback.domain.entities.enums.TipoEnvio;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PedidoServiceImpl implements IPedidoService {

    private static final Logger logger = LoggerFactory.getLogger(PedidoServiceImpl.class);

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public List<Pedido> findAll() throws Exception {

        return null;
    }

    @Override
    public Pedido findById(Long id) throws Exception {

        return null;
    }

    @Override
    @Transactional
    public Pedido save(Pedido entity) throws Exception {
        logger.info("Guardando pedido: {}", entity);
        return pedidoRepository.save(entity);
    }

    @Override
    public Pedido update(Long id, Pedido entity) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return false;
    }

    @Transactional
    public Pedido convertirDtoAEntidad(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setEliminado(pedidoDTO.isEliminado());
        pedido.setHoraEstimadaFinalizacion(pedidoDTO.getHoraEstimadaFinalizacion());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setTotalCosto(pedidoDTO.getTotalCosto());
        pedido.setEstado(Estado.valueOf(pedidoDTO.getEstado()));
        pedido.setTipoEnvio(TipoEnvio.valueOf(pedidoDTO.getTipoEnvio()));
        pedido.setFormaPago(FormaPago.valueOf(pedidoDTO.getFormaPago()));
        pedido.setFechaPedido(pedidoDTO.getFechaPedido());

        Set<DetallePedido> detallesPedido = new HashSet<>();
        for (DetallePedidoDTO detalleDTO : pedidoDTO.getDetallePedidos()) {
            DetallePedido detalle = new DetallePedido();
            detalle.setId(detalleDTO.getId());
            detalle.setEliminado(detalleDTO.isEliminado());
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setSubTotal(detalleDTO.getSubTotal());
            ArticuloDTO articuloDTO = detalleDTO.getArticulo();
            if (articuloDTO != null) {
                logger.info("Articulo en el pedido: {}", articuloDTO);
            } else {
                logger.warn("ArticuloDTO es nulo en detallePedido con id: {}", detalleDTO.getId());
            }

            Articulo articulo = convertirArticuloDtoAEntidad(detalleDTO.getArticulo());
            if (articulo != null) {
                logger.info("Articulo convertido correctamente: {}", articulo);
                detalle.setArticulo(articulo);
            }else{
                logger.info("Articulo con error: {}", articulo);
            }
            detalle.setPedido(pedido);
            detallesPedido.add(detalle);
        }

        pedido.setDetallePedidos(detallesPedido);
        return pedido;
    }

    private Articulo convertirArticuloDtoAEntidad(ArticuloDTO articuloDTO) {
        if (articuloDTO == null) {
            logger.warn("ArticuloDTO es nulo");
            return null;
        }

        logger.info("Clase del DTO recibido: {}", articuloDTO.getClass().getName());

        if (articuloDTO instanceof ArticuloDTO) {
            logger.info("Procesando ArticuloInsumoDTO");
            ArticuloInsumo insumo = new ArticuloInsumo();
            BeanUtils.copyProperties(articuloDTO, insumo);
            logger.info("ArticuloInsumo convertido: {}", insumo);
            return insumo;
        } else if (articuloDTO instanceof ArticuloDTO) {
            logger.info("Procesando ArticuloManufacturadoDTO");
            ArticuloManufacturado manufacturado = new ArticuloManufacturado();
            BeanUtils.copyProperties(articuloDTO, manufacturado);
            logger.info("ArticuloManufacturado convertido: {}", manufacturado);
            return manufacturado;
        }

        logger.warn("No se pudo identificar el tipo de ArticuloDTO");
        return null;
    }

}
