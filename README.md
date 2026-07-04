# 🪙 Pikmin Piklopedia App

Aplicación móvil nativa para Android desarrollada en **Kotlin** como parte del caso práctico para **BK Programación**. La aplicación consiste en un catálogo interactivo basado en la Piklopedia del universo Pikmin, donde se exploran conceptos avanzados de componentes de interfaz (UI), navegación, eventos, estilos centralizados, soporte multiidioma y la mejora de la experiencia de usuario (UX).

A continuación se detalla la guía visual de referencia para el diseño de la aplicación (`image_8f9a28.png`):

![Wireframe de la aplicación](image_8f9a28.jpg)

**Autora:** Irene Condado Alcantarilla.

---

## 🏢 Caso Práctico & Contexto

Ada, responsable del equipo de desarrollo de **BK Programación**, ha solicitado la creación de una aplicación desde cero para dominar los componentes de una interfaz, la gestión de eventos y la navegación entre pantallas. María y Juan, integrantes del equipo y entusiastas de la saga, han propuesto esta temática interactiva de Pikmin para cumplir con los objetivos técnicos de manera lúdica y robusta.

---

## 🚀 Características y Requisitos del Proyecto

El desarrollo se desglosa en los siguientes módulos e hitos técnicos:

### 📌 Apartado A: Listado de Pikmin (Catálogo General)
* **RecyclerView & GridLayoutManager:** Configuración en forma de cuadrícula balanceada para mostrar de manera organizada los 9 tipos de Pikmin oficiales de la Piklopedia.
* **CardView Personalizados:** Cada celda de la cuadrícula consta de un contenedor con elevación que incluye un `ImageView` para el renderizado del personaje y un `TextView` con su tipo o color (ej: Pikmin Rojo, Pikmin Azul).
* **Toolbar Superior:** Sustitución completa de la clásica *ActionBar* por un componente `Toolbar` personalizado con el título principal "Pikmin".

### 📌 Apartado B: Pantalla de Detalles
* **Navegación Dinámica:** Al interactuar con cualquiera de las tarjetas del catálogo, se intercepta el evento y se abre una segunda pantalla enviando los argumentos del Pikmin seleccionado.
* **Componentes de Detalle:** Renderizado a gran escala del `ImageView`, título del tipo/color y descripción detallada de sus habilidades únicas (ej: resistencia al fuego, capacidad de volar).
* **Navegación Inversa:** La `Toolbar` superior de esta pantalla ("Detalle Pikmin") cuenta con un botón nativo de retroceso (`HomeAsUp`) para regresar de forma fluida a la cuadrícula.

### 📌 Apartado C: Temas y Estilos Centralizados
* **Uso estricto de `styles.xml` / `themes.xml`:** Toda la paleta de colores, tamaños de fuente y configuraciones visuales de los elementos (`RecyclerView`, `TextView`, botones) se definen de forma centralizada.
* *Nota:* Está estrictamente prohibido incluir atributos de color o dimensiones directamente en línea (*hardcoded*) en los layouts XML de las vistas.

### 📌 Apartado D: Menú Contextual y Ajustes
* Interfaz con un menú de opciones (*Overflow Menu*) en la barra superior dotado de dos secciones:
  * **Acerca de...:** Despliega un `AlertDialog` personalizado que incluye el logotipo de la aplicación y el mensaje aclaratorio: *“Aplicación desarrollada por [Tu Nombre]. Versión 1.0.”*
  * **Ajustes:** Transiciona a una nueva interfaz que incorpora selectores (`Spinner` o componentes equivalentes) para configurar los parámetros locales de **Idioma** (Español / Inglés) y **Tema** (Claro / Oscuro).

### 📌 E: Soporte Multiidioma (Internacionalización)
* Desacoplamiento total de cadenas de texto mediante el uso de recursos nativos:
  * `values/strings.xml`: Textos y traducciones por defecto en idioma Español.
  * `values-en/strings.xml`: Textos y traducciones mapeadas para el idioma Inglés.
* *Nota:* Ningún texto literal se encuentra incrustado directamente en el código fuente.

### 📌 F: Documentación del Código
* Todo el código fuente está documentado siguiendo el estándar **JavaDocs / KDoc**.
* Cada clase, método y función describe detalladamente su propósito, parámetros de entrada (`@param`) y valores de retorno (`@return`).

### 📌 G: Mensajes Interactivos y UX
* **Snackbar:** Al inicializar la pantalla del catálogo principal por primera vez, se asoma un componente Snackbar en la zona inferior con el mensaje: *“¡Bienvenidos al mundo Pikmin!”*.
* **Toast:** Al pulsar sobre una tarjeta y navegar, se lanza un aviso Toast flotante notificando al usuario: *“Se ha seleccionado el Pikmin [nombre_del_pikmin]”*.

### 📌 H: Pantalla de Carga Nativa (Splash Screen)
* Integración del flujo de inicio mediante la API nativa de **Splash Screen (Android 12+, API 31+)** configurada desde los temas, evitando retrasos manuales mediante hilos innecesarios. Muestra de forma limpia el logotipo personalizado y redirige inmediatamente a la cuadrícula principal.

---

## 🛠️ Requisitos del Entorno

* **IDE:** Android Studio (Ladybug / Jellyfish o superior)
* **Lenguaje:** Kotlin 1.9+
* **Build System:** Kotlin DSL (`.gradle.kts`)
* **SDK Mínimo:** API 24 (Android 7.0 Nougat)
* **SDK Objetivo:** API 34 o superior

---

## 🔧 Pasos para Compilación y Entrega

1. **Clonar o descargar** el proyecto en tu entorno local.
2. Abrir la carpeta raíz desde Android Studio para que se realice la sincronización inicial de dependencias de Gradle.
3. Para preparar el empaquetado final reduciendo significativamente el peso del archivo, ejecuta el comando de limpieza desde el menú del IDE:
   ```bash
   Build > Clean Project
