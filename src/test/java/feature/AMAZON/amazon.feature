@FlujoE2E
 Feature: Búsqueda y filtrado de productos en Amazon

  Scenario: Buscar zapatos Skechers y validar ordenamientos de precios y comentarios
    Given que el usuario navega a Amazon
    When busca el producto "zapatos"
    And filtra la búsqueda por la marca Skechers
    And aplica un rango de precio de $100 a $200
    Then se imprime el número total de resultados encontrados
    And se ordenan los productos del precio del más alto al más bajo
    And se imprimen los primeros 5 productos con sus precios
    And se verifica que los primeros 5 productos estén ordenados descendientemente por precio
    And se ordenan los productos por Llegadas más recientes
    And se imprimen los primeros 5 productos ordenados por novedad
    And se ordenan los productos por Promedio Opinión del cliente
    And se imprimen los primeros 5 productos ordenados por calificación

