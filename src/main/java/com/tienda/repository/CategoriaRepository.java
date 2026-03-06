package com.tienda.repository;

import com.tienda.domain.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    public List<Categoria> findByActivoTrue();
    
    //Derivada
    public List<Categoria> findByActivoTrueAndDescripcionContaining(String textoDescripcion);
    
    //JPQL
    @Query("""
       SELECT c
       FROM Categoria c JOIN c.productos p
       WHERE c.activo = true
       AND p.activo = true
       AND c.descripcion LIKE %:textoDescripcion%
       GROUP BY c
       HAVING COUNT(p) >= :cantidadMinProductos
       ORDER BY COUNT(p) DESC
       """)
public List<Categoria> consultaCategoriaJPQL(
        @Param("cantidadMinProductos") int cantidadMinProductos,
        @Param("textoDescripcion") String textoDescripcion);

        //SQL Nativa
        @Query(value = """
       SELECT c.*
       FROM categoria c
       JOIN producto p ON p.id_categoria = c.id_categoria
       WHERE c.activo = true
       AND p.activo = true
       AND c.descripcion LIKE %:textoDescripcion%
       GROUP BY c.id_categoria
       HAVING COUNT(p.id_producto) >= :cantidadMinProductos
       ORDER BY COUNT(p.id_producto) DESC
       """, nativeQuery = true)
public List<Categoria> consultaCategoriaSQL(
        @Param("cantidadMinProductos") int cantidadMinProductos,
        @Param("textoDescripcion") String textoDescripcion);
    
}