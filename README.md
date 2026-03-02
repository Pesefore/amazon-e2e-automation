# Amazon E2E Automation - Selenium + Cucumber + Maven

## Descripción

Proyecto de automatización E2E desarrollado con Selenium WebDriver,
Cucumber y Maven.

El flujo automatizado realiza:

- Búsqueda de producto
- Filtro por marca
- Aplicación de rango de precio
- Ordenamientos por precio, novedad y calificación
- Validación de orden descendente por precio
- Ejecución en Jenkins (CI/CD) y registro de resultados en consola.

## Tecnologías

- Java 17
- Selenium WebDriver
- Cucumber
- Maven
- Jenkins
- GitHub

##  Requisitos previos

- Java 17 instalado
- Maven instalado
- Google Chrome
- ChromeDriver compatible

## Instalación y ejecución local

1. Clonar el repositorio: git clone https://github.com/Pesefore/amazon-e2e-automation.git
2.  Ingresar al proyecto: cd amazon-e2e-automation
3.  Ejecutar pruebas: mvn clean test
4.  Verás en la consola la ejecución de los tests y la impresión de los resultados de los primeros 5 productos según precio, novedad y calificación.

## Ejecución en Jenkins

El proyecto está preparado para correr en Jenkins con los siguientes pasos:
1. Configurar un Job que ejecute el comando: mvn clean test
2. Los resultados se registrarán directamente en la consola de Jenkins.
3. El log de Jenkins muestra claramente:
- Inicio y fin del escenario.
- Steps ejecutados con éxito.
- Primeros 5 productos según precio, novedad y calificación.
Estado final de la ejecución: BUILD SUCCESS.
⚠️ Por el momento no se ha configurado un reporte visual tipo Allure, pero la evidencia de ejecución completa está en la consola de Jenkins.


## Flujo automatizado

- Ingresar a www.amazon.com y buscar “zapatos”.
- Filtrar por la marca “Skechers”.
- Aplicar rango de precio de $100 a $200.
- Imprimir el número total de resultados encontrados.
- Ordenar por “Precio: De más alto a más bajo” y mostrar los primeros 5 productos.
- Ordenar por “Novedades” y mostrar los primeros 5 productos.
- Ordenar por “Promedio de comentarios de clientes” y mostrar los primeros 5 productos.

## Evidencia de Ejecución
- Al ejecutar los tests, Jenkins imprime en consola lo siguiente:
--- Primeros 5 productos ---
Producto 1: Skechers Tenis para caminar para hombre - Precio: $670
Producto 2: Skechers para mujer Max Cushioning Glide Step Sapphire Slip-ins sin manos - Precio: $648
Producto 3: Skechers Women's Max Cushioning Arch Fit 2.0 Antilles Trainers - Precio: $442
Producto 4: Skechers Women's Go Golf Elite 5 Arch Fit Waterproof Slip in Golf Shoe - Precio: $268
Producto 5: Skechers Zapatillas bajas para hombre - Precio: $620
  And se imprimen los primeros 5 productos con sus precios                                  # stepDefinitions.AmazonStep.imprimirCincoPrimerosProductos()
Los productos están ordenados descendientemente por precio.
  And se verifica que los primeros 5 productos estÃ©n ordenados descendientemente por precio # stepDefinitions.AmazonStep.verificarProductosOrdenados()
  And se ordenan los productos por Llegadas mÃ¡s recientes                                   # stepDefinitions.AmazonStep.ordenarPorNuevosLanzamientos()
--- Primeros 5 productos ---
Producto 1: Skechers Aero Spark para mujer - Precio: $436
Producto 2: Skechers Tenis Aero Burst para mujer - Precio: $503
Producto 3: Skechers Tenis de correr Aero Burst para hombre - Precio: $503
Producto 4: Skechers Zapatillas bajas para hombre - Precio: $619
Producto 5: Skechers Flex Appeal 5.0 Easy Breezy para mujer - Precio: $358
  And se imprimen los primeros 5 productos ordenados por novedad                            # stepDefinitions.AmazonStep.ordenarProductosPorNovedad()
  And se ordenan los productos por Promedio OpiniÃ³n del cliente                             # stepDefinitions.AmazonStep.ordenarProductosPorPromedio()
--- Primeros 5 productos ---
Producto 1: Skechers Hombres Aero Burst Hands Free Slip-ins - Precio: $455
Producto 2: Skechers para mujer Aero Burst Hands Free Slip-ins - Precio: $476
Producto 3: Skechers Flex Appeal 5.0 Easy Breezy para mujer - Precio: $358
Producto 4: Skechers para mujer Max Cushioning Glide Step Sapphire Slip-ins sin manos - Precio: $642
Producto 5: Skechers Tenis Max Cushioning Slip-ins para hombre - Zapatos deportivos sin cordones para correr y caminar con Memory Foam - Precio: $352

- Todos los steps ejecutados aparecen en el log de Jenkins
- La ejecución final muestra BUILD SUCCESS
 ----> Esto permite validar que el proyecto corre correctamente en CI/CD, aunque no haya un reporte visual aún.

## Justificación de la entrega sin reportes visuales
- Los tests fueron desarrollados y ejecutados correctamente.
- La evidencia de ejecución está disponible en Jenkins y en la consola local.
- Debido a restricciones de tiempo, se priorizó completar la automatización y el flujo E2E, dejando la generación de reportes visuales como mejora futura.

