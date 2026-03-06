package com.tienda.controller;

import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ConsultaController(ProductoService productoService,
                              CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        return "/consultas/listado";
    }

    @PostMapping("/consultaDerivada")
    public String consultaDerivada(@RequestParam() double precioInf,
            @RequestParam() double precioSup, Model model) {
        var lista = productoService.consultaDerivada(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }

    @PostMapping("/consultaJPQL")
    public String consultaJPQL(@RequestParam() double precioInf,
            @RequestParam() double precioSup,
            Model model) {
        var productos = productoService.consultaJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }

    @PostMapping("/consultaSQL")
    public String consultaSQL(@RequestParam() double precioInf,
            @RequestParam() double precioSup, Model model) {
        var lista = productoService.consultaSQL(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/consultas/listado";
    }
    
    
    //Consulta Avanzada Producto
    
    @PostMapping("/productoDerivada")
public String productoDerivada(
        @RequestParam double precioMin,
        @RequestParam double precioMax,
        @RequestParam int existenciasMin,
        @RequestParam String descripcionCategoria,
        Model model) {

    var lista = productoService.consultaAvanzadaDerivada(
            precioMin, precioMax, existenciasMin, descripcionCategoria);

    model.addAttribute("productos", lista);

    return "/consultas/listado";
}

@PostMapping("/productoJPQL")
public String productoJPQL(
        @RequestParam double precioMin,
        @RequestParam double precioMax,
        @RequestParam int existenciasMin,
        @RequestParam String descripcionCategoria,
        Model model) {

    var lista = productoService.consultaAvanzadaJPQL(
            precioMin, precioMax, existenciasMin, descripcionCategoria);

    model.addAttribute("productos", lista);

    return "/consultas/listado";
}

@PostMapping("/productoSQL")
public String productoSQL(
        @RequestParam double precioMin,
        @RequestParam double precioMax,
        @RequestParam int existenciasMin,
        @RequestParam String descripcionCategoria,
        Model model) {

    var lista = productoService.consultaAvanzadaSQL(
            precioMin, precioMax, existenciasMin, descripcionCategoria);

    model.addAttribute("productos", lista);

    return "/consultas/listado";
}

    //Consulta Avanzada Categoria

    @PostMapping("/categoriaDerivada")
    public String categoriaDerivada(
            @RequestParam String textoDescripcion,
            Model model) {

        var lista = categoriaService.consultaCategoriaDerivada(textoDescripcion);

        model.addAttribute("categorias", lista);

        return "/consultas/listadoCategorias";
    }

    @PostMapping("/categoriaJPQL")
    public String categoriaJPQL(
            @RequestParam int cantidadMinProductos,
            @RequestParam String textoDescripcion,
            Model model) {

        var lista = categoriaService.consultaCategoriaJPQL(
                cantidadMinProductos, textoDescripcion);

        model.addAttribute("categorias", lista);

        return "/consultas/listadoCategorias";
    }

    @PostMapping("/categoriaSQL")
    public String categoriaSQL(
            @RequestParam int cantidadMinProductos,
            @RequestParam String textoDescripcion,
            Model model) {

        var lista = categoriaService.consultaCategoriaSQL(
                cantidadMinProductos, textoDescripcion);

        model.addAttribute("categorias", lista);

        return "/consultas/listadoCategorias";
    }

}