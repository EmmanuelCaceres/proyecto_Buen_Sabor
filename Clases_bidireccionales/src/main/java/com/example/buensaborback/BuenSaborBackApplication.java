package com.example.buensaborback;

import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.domain.entities.enums.FormaPago;
import com.example.buensaborback.domain.entities.enums.TipoEnvio;
import com.example.buensaborback.domain.entities.enums.TipoPromocion;
import com.example.buensaborback.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BuenSaborBackApplication {
// Aca tiene que inyectar todos los repositorios
// Es por ello que deben crear el paquete reositorio

// Ejemplo  @Autowired
//	private ClienteRepository clienteRepository;

	private static final Logger logger = LoggerFactory.getLogger(BuenSaborBackApplication.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ProvinciaRepository provinciaRepository;

	@Autowired
	private LocalidadRepository localidadRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private SucursalRepository	sucursalRepository;

	@Autowired
	private DomicilioRepository domicilioRepository;

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ArticuloInsumoRepository articuloInsumoRepository;

	@Autowired
	private ArticuloManufacturadoRepository articuloManufacturadoRepository;

	@Autowired
	private ImagenRepository imagenRepository;

	@Autowired
	private PromocionRepository promocionRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private  FacturaRepository facturaRepository;

	@Autowired
	private  PedidoRepository pedidoRepository;

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;

	@Autowired
	private ImagenArticuloRepository imagenArticuloRepository;

	public static void main(String[] args) {
		SpringApplication.run(BuenSaborBackApplication.class, args);
		logger.info("Estoy activo en el main");
	}


	@Bean
	CommandLineRunner init() {
		return args -> {
			logger.info("----------------ESTOY----FUNCIONANDO---------------------");


			Categoria categoriaBebidas = Categoria.builder().denominacion("Bebidas").
					build();
			categoriaRepository.save(categoriaBebidas);

			Categoria categoriaGaseosas = Categoria.builder().denominacion("Gaseosas").
					build();
			categoriaRepository.save(categoriaGaseosas);

			Categoria categoriaTragos = Categoria.builder().denominacion("Tragos").
					build();
			categoriaRepository.save(categoriaTragos);

			Categoria categoriaPizzas = Categoria.builder().denominacion("Pizzas").
					build();

			Categoria categoriaInsumos = Categoria.builder().denominacion("Insumos").
					build();

			// Grabo la categoria de insumos y de Manufacturados
			categoriaRepository.save(categoriaPizzas);
			categoriaRepository.save(categoriaInsumos);
			// Asigno subCategorias

			categoriaBebidas.getSubCategorias().add(categoriaGaseosas);
			categoriaBebidas.getSubCategorias().add(categoriaTragos);
			// Grabo las Subcategorias
			categoriaRepository.save(categoriaBebidas);

			// Crear Unidades de medida
			UnidadMedida unidadMedidaLitros = UnidadMedida.builder().denominacion("Litros").build();
			UnidadMedida unidadMedidaGramos = UnidadMedida.builder().denominacion("Gramos").build();
			UnidadMedida unidadMedidaCantidad = UnidadMedida.builder().denominacion("Cantidad").build();
			UnidadMedida unidadMedidaPorciones = UnidadMedida.builder().denominacion("Porciones").build();
			unidadMedidaRepository.save(unidadMedidaLitros);
			unidadMedidaRepository.save(unidadMedidaGramos);
			unidadMedidaRepository.save(unidadMedidaCantidad);
			unidadMedidaRepository.save(unidadMedidaPorciones);

			// Crear Insumos , coca cola , harina , etc
			ArticuloInsumo cocaCola = ArticuloInsumo.builder().
					denominacion("Coca cola").
					esParaElaborar(false).
					stockActual(5).
					stockMaximo(50).
					precioCompra(50.0).
					precioVenta(70.0).
					build();
			ArticuloInsumo harina = ArticuloInsumo.builder().denominacion("Harina").esParaElaborar(true).stockActual(4).stockMaximo(40).precioCompra(40.0).precioVenta(60.5).build();
			ArticuloInsumo queso = ArticuloInsumo.builder().denominacion("Queso").esParaElaborar(true).stockActual(20).stockMaximo(50).precioCompra(23.6).precioVenta(66.6).build();
			ArticuloInsumo tomate = ArticuloInsumo.builder().denominacion("Tomate").esParaElaborar(true).stockActual(20).stockMaximo(50).precioCompra(23.6).precioVenta(66.6).build();
			// Grabamos los Articulos
			articuloInsumoRepository.save(cocaCola);
			articuloInsumoRepository.save(harina);
			articuloInsumoRepository.save(queso);
			articuloInsumoRepository.save(tomate);

			cocaCola.setUnidadMedida(unidadMedidaLitros);
			harina.setUnidadMedida(unidadMedidaGramos);
			queso.setUnidadMedida(unidadMedidaGramos);
			tomate.setUnidadMedida(unidadMedidaCantidad);

			articuloInsumoRepository.save(cocaCola);
			articuloInsumoRepository.save(harina);
			articuloInsumoRepository.save(queso);
			articuloInsumoRepository.save(tomate);

			unidadMedidaLitros.getArticulos().add(cocaCola);
			unidadMedidaGramos.getArticulos().add(harina);
			unidadMedidaGramos.getArticulos().add(queso);
			unidadMedidaCantidad.getArticulos().add(tomate);

			unidadMedidaRepository.save(unidadMedidaLitros);
			unidadMedidaRepository.save(unidadMedidaGramos);
			unidadMedidaRepository.save(unidadMedidaCantidad);
			unidadMedidaRepository.save(unidadMedidaPorciones);



//			// crear fotos para cada insumo
//			ImagenArticulo imagenArticuloCoca = ImagenArticulo.builder().
//					url("https://m.media-amazon.com/images/I/51v8nyxSOYL._SL1500_.jpg").
//					build();
//			ImagenArticulo imagenArticuloHarina = ImagenArticulo.builder().url("https://mandolina.co/wp-content/uploads/2023/03/648366622-1024x683.jpg").build();
//			ImagenArticulo imagenArticuloQueso = ImagenArticulo.builder().url("https://superdepaso.com.ar/wp-content/uploads/2021/06/SANTAROSA-PATEGRAS-04.jpg").build();
//			ImagenArticulo imagenArticuloTomate = ImagenArticulo.builder().url("https://thefoodtech.com/wp-content/uploads/2020/06/Componentes-de-calidad-en-el-tomate-828x548.jpg").build();
//
//			imagenArticuloRepository.save(imagenArticuloCoca);
//			imagenArticuloRepository.save(imagenArticuloHarina);
//			imagenArticuloRepository.save(imagenArticuloQueso);
//			imagenArticuloRepository.save(imagenArticuloTomate);
//
//			//ASOCIAMOS IMAGEN CON INSUMOS
//			cocaCola.getImagenes().add(imagenArticuloCoca);
//			harina.getImagenes().add(imagenArticuloHarina);
//			queso.getImagenes().add(imagenArticuloQueso);
//			tomate.getImagenes().add(imagenArticuloTomate);
//
//			articuloInsumoRepository.save(cocaCola);
//			articuloInsumoRepository.save(harina);
//			articuloInsumoRepository.save(queso);
//			articuloInsumoRepository.save(tomate);


			logger.info("Hasta aca llego");
//
//

			harina.setCategoria(categoriaInsumos);
			queso.setCategoria(categoriaInsumos);
			tomate.setCategoria(categoriaInsumos);
			cocaCola.setCategoria(categoriaBebidas);

			articuloInsumoRepository.save(harina);
			articuloInsumoRepository.save(queso);
			articuloInsumoRepository.save(tomate);
			articuloInsumoRepository.save(cocaCola);


//			//ASOCIAMOS CATEGORIA CON INSUMOS
			categoriaInsumos.getArticulos().add(harina);
			categoriaInsumos.getArticulos().add(queso);
			categoriaInsumos.getArticulos().add(tomate);
			categoriaGaseosas.getArticulos().add(cocaCola);
			categoriaRepository.save(categoriaInsumos);
			categoriaRepository.save(categoriaGaseosas);

//			// Crear Articulos Manufacturados
			ArticuloManufacturado pizzaMuzarella = ArticuloManufacturado.builder().
					denominacion("Pizza Muzarella").
					descripcion("Una pizza clasica").
					precioVenta(130.0).
					tiempoEstimadoMinutos(15).
					preparacion("Pasos de preparacion de una muzza de toda la vida").
					build();
			ArticuloManufacturado pizzaNapolitana = ArticuloManufacturado.builder().denominacion("Pizza Napolitana").descripcion("Una pizza clasica").precioVenta(150.0).tiempoEstimadoMinutos(15).preparacion("Pasos de preparacion de una pizza napolitana italiana").build();

			articuloManufacturadoRepository.save(pizzaMuzarella);
			articuloManufacturadoRepository.save(pizzaNapolitana);

			// Crear fotos para los articulos manufacturados
			ImagenArticulo imagenArticuloPizzaMuzarella = ImagenArticulo.builder().
					url("https://storage.googleapis.com/fitia-api-bucket/media/images/recipe_images/1002846.jpg").
					build();
			ImagenArticulo imagenArticuloPizzaNapolitana = ImagenArticulo.builder().url("https://assets.elgourmet.com/wp-content/uploads/2023/03/8metlvp345_portada-pizza-1024x686.jpg.webp").build();
			imagenArticuloRepository.save(imagenArticuloPizzaMuzarella);
			imagenArticuloRepository.save(imagenArticuloPizzaNapolitana);



			//ASOCIAMOS IMAGEN CON ARTICULO MANUFACTURADO
			pizzaMuzarella.getImagenes().add(imagenArticuloPizzaMuzarella);
			pizzaNapolitana.getImagenes().add(imagenArticuloPizzaNapolitana);

			pizzaMuzarella.setUnidadMedida(unidadMedidaPorciones);
			pizzaNapolitana.setUnidadMedida(unidadMedidaPorciones);

			pizzaMuzarella.setCategoria(categoriaPizzas);
			pizzaNapolitana.setCategoria(categoriaPizzas);

			articuloManufacturadoRepository.save(pizzaMuzarella);
			articuloManufacturadoRepository.save(pizzaNapolitana);








//			DetallePedido detallePedidoNuevo = DetallePedido.builder()
//					.cantidad(2)
//					.subTotal(245d)
//					.articulo(ArticuloManufacturado.builder()
//							.unidadMedida(UnidadMedida.builder()
//									.denominacion("Porcion")
//									.build())
//							.precioVenta(32.1)
//							.denominacion("Pizza Lomo")
//							.descripcion("sanguche de lomo pero en vez de pan, pizzas")
//							.preparacion("Preparacion de prueba")
//							.tiempoEstimadoMinutos(20)
//							.categoria(Categoria.builder()
//									.denominacion("Categoria de prueba")
//									.build())
//							.build())
//					.pedido(facturaNueva.getPedido())
//					.build();
//			facturaNueva.getPedido().getDetallePedidos().add(detallePedidoNuevo);
//
//			facturaRepository.save(facturaNueva);

//			Domicilio domicilioprueba = Domicilio.builder()
//					.localidad(Localidad.builder()
//							.provincia(Provincia.builder()
//									.nombre("Nombre de provincia").pais(Pais.builder()
//											.nombre("Nombre de pais")
//											.build())
//									.build())
//							.nombre("nombre de localidad")
//							.build())
//					.calle("Calle de prueba")
//					.numero(32)
//					.cp(5533)
//					.build();
//
//			domicilioRepository.save(domicilioprueba);


//			ArticuloManufacturado articuloManufacturadoNuevo = ArticuloManufacturado.builder()
//					.unidadMedida(UnidadMedida.builder()
//							.denominacion("Porcion")
//							.build())
//					.precioVenta(32.1)
//					.denominacion("Prueba de articulo")
//					.descripcion("Esto es una descripcion")
//					.preparacion("Preparacion de prueba")
//					.tiempoEstimadoMinutos(20)
//					.categoria(Categoria.builder()
//							.denominacion("Categoria de prueba")
//							.build())
//					.build();
//
//			DetallePedido detallePedido = DetallePedido.builder()
//					.cantidad(4)
//					.subTotal(34.5)
//					.articulo(articuloManufacturadoNuevo)
//					.pedido(Pedido.builder()
//							.total(21d)
//							.totalCosto(40d)
//							.estado(Estado.Pendiente)
//							.tipoEnvio(TipoEnvio.Delivery)
//							.formaPago(FormaPago.Efectivo)
//							.fechaPedido(LocalDate.now())
//							.horaEstimadaFinalizacion(LocalTime.now())
//							.cliente(Cliente.builder()
//									.nombre("Emmanuel")
//									.apellido("Caceres")
//									.email("emmanuel@gmail.com")
//									.telefono("2615120063")
//									.fechaNacimiento(LocalDate.of(1998,11,7))
//									.usuario(Usuario.builder()
//											.auth0Id("Auth0")
//											.username("nombre de usuario prueba")
//											.build())
//									.build())
//							.factura(Factura.builder()
//									.fechaFacturacion(LocalDate.now())
//									.formaPago(FormaPago.Efectivo)
//									.mpMerchantOrderId(32131)
//									.mpPaymentId(212)
//									.mpPaymentType("Prueba tipo mp")
//									.mpPreferenceId("Prueba preference mp")
//									.build())
//							.build())
//					.build();
//
//			articuloManufacturadoNuevo.getDetallesPedido().add(detallePedido);
//
//			articuloManufacturadoRepository.save(articuloManufacturadoNuevo);

		};
	}
}







