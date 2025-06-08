Trabajo Práctico de Desarrollo
Descripción
En esta tarea, se deberá desarrollar una solución para el problema descripto, considerando todos los aspectos importantes de la programación orientada a objetos, casos de prueba, complejidad computacional, uso de estructuras aprendidas en la cursada, explicación del algoritmo utilizado, justificación de decisiones y demostración del trabajo realizado.
Grupos de 4 o 5 personas.

Consigna
En una colonia automatizada, los recursos son transportados por una red de robots logísticos que operan dentro del alcance de robopuertos. Estos robots manejan ítems entre diferentes cofres inteligentes, cada uno con un rol específico dentro del sistema logístico.

Tipos de Cofre
Cofre de Provisión Activa: empuja ítems automáticamente hacia cofres que los solicitan.
Cofre de Provisión Pasiva: almacena ítems disponibles para ser tomados, pero no los ofrece activamente.
Cofre de Solicitud: solicita ítems específicos; los recibe desde proveedores activos, búfer o pasivos, respetando ese orden de prioridad.
Cofre Intermedio/Búfer: solicita ítems como el de solicitud, pero también puede servir como proveedor pasivo para otras solicitudes.
Cofre de Almacenamiento: almacena excedentes que no tienen destino claro; se usa como último recurso.

Robopuertos
Un robopuerto define una zona de cobertura dentro de la cual los robots logísticos pueden operar. Cada robopuerto tiene una coordenada fija (x, y) y una distancia máxima de alcance. Los cofres conectados a un robopuerto están dentro de su área de cobertura.

Los robots logísticos solo pueden mover ítems entre cofres cubiertos por al menos un mismo robopuerto, o entre robopuertos conectados si se simula una red más compleja.
Los robots logísticos tienen una capacidad de carga limitada y deben priorizar las entregas más cercanas.

Ejemplo:

Como puede verse, en este caso hay tres robopuertos formando una única red (podría haber más redes), que abarca a todos los cofres salvo al cofre marcado con rojo. Éste último es inaccesible. Los otros, son accesibles y los robots pueden desplazarse entre ellos según sea necesario.
Ítems y Operaciones Logísticas
El sistema trabaja con ítems diferenciados por tipo, como si se tratara de distintos materiales, componentes o productos. Cada ítem tiene un nombre o identificador único (por ejemplo: "hierro", "circuito", "motor", etc.) y puede estar presente en distintas cantidades en los cofres.
Cada cofre puede realizar una de las siguientes funciones, por ítem y cantidad:
Ofrecer ítems: indicando que posee unidades disponibles para ser retiradas por robots. Esto puede implicar un comportamiento activo (empujar) o pasivo (esperar a que le retiren).
Solicitar ítems: especificando cuántas unidades necesita de un tipo determinado. La solicitud se considera satisfecha cuando alcanza o supera esa cantidad.
Almacenar ítems excedentes: especialmente en el caso de cofres de almacenamiento, que reciben ítems sin destino asignado.

No es posible que un mismo cofre tenga roles distintos para diferentes ítems. Por ejemplo, un cofre puede:
Solicitar "circuito" (20 unidades)
u ofrecer "hierro" (50 unidades)
o almacenar cualquier otro ítem restante

Los robots deben respetar estos pedidos específicos y transportar únicamente la cantidad necesaria de cada ítem, evitando entregas en exceso o innecesarias.

Los robots transportan una cantidad predefinida de ítems en cada viaje. Esta cantidad debe ser configurable a nivel global al momento de correr la simulación.

Especial atención debe prestarse a los cofres de tipo intermedio, ya que solicitan pero pueden proveer, a su vez, si el elemento fuera necesario en otro cofre. Una solicitud imposible de satisfacer en un cofre intermedio no es obstáculo para dar el sistema por terminado, es decir, en estado de equilibrio.
Apartado: Energía y Batería de los Robots Logísticos
Los robots logísticos utilizan baterías para desplazarse entre cofres dentro de la red logística. Cada movimiento consume energía en proporción a la distancia recorrida, por lo que es fundamental gestionar sus cargas para evitar interrupciones en las entregas.

Unidad de Energía: Se define una unidad abstracta de energía llamada "Célula". Cada robot tiene una capacidad máxima de batería medida en Células (por ejemplo, 100).

La energía consumida por cada movimiento se calcula con la fórmula:
Células necesarias = distanciaEuclídea(cofre_origen, cofre_destino) × factor_de_consumo

El factor de consumo puede ser, por ejemplo, 1 célula por unidad de distancia.
Robopuertos como Estaciones de Recarga
Los robopuertos también funcionan como estaciones de recarga. Un robot puede recargar su batería completamente al pasar por un robopuerto (requiere un turno/ciclo de carga).
Si un robot no tiene suficiente energía para alcanzar el destino, debe planificar una ruta intermedia pasando por un robopuerto para recargar.
Si no existe una ruta posible con recargas intermedias, el robot debe cancelar o posponer la entrega.
Reglas de operación con batería
Chequeo antes de salir: antes de cada entrega, el robot calcula si tiene batería suficiente para llegar al destino (y volver, si el sistema lo requiere).
Ruta planificada con recargas: si no tiene suficiente batería, debe trazar una ruta alternativa que incluya recarga en robopuertos intermedios.
Recarga automática: al pasar por un robopuerto, un robot recarga automáticamente su batería.
Planificación energética: la simulación debe considerar la batería como un recurso limitado al tomar decisiones logísticas.

Objetivos del sistema
Modelar un sistema logístico automatizado que permita:

Definir una red de cofres inteligentes, robopuertos, y robots logísticos.
Simular los ciclos de operación en los que los ítems se transfieren de acuerdo con las reglas de prioridad y alcance.
Registrar los movimientos, distancias y decisiones tomadas en cada ciclo.
Requisitos
Diseñar las clases que considere necesarias.
Implementar una grilla espacial o sistema de coordenadas para ubicar cofres y robopuertos.
Los robots deben:
Buscar cofres fuente y destino válidos dentro del alcance.
Seleccionar el mejor proveedor según prioridad y distancia.
Planificar movimientos dentro de su capacidad de carga.
La red logística debe ser capaz de:
Registrar qué cofres están cubiertos por qué robopuertos.
Validar si un movimiento es posible según la cobertura.
Priorizar solicitudes más urgentes o accesibles.
Permitir ejecutar ciclos de simulación con cambios visibles en la red.
Estado Inicial y Objetivo del Sistema
El sistema parte de un estado inicial en el que existen múltiples pedidos de entrega y recepción de ítems sin satisfacer. Cada cofre de solicitud o intermedio puede tener cantidades deseadas de ciertos ítems, mientras que los cofres proveedores (activos, pasivos o búfer) contienen ítems disponibles para ser distribuidos.
El objetivo es que el sistema logístico ejecute ciclos de transferencia hasta alcanzar un estado estable, donde todos los pedidos hayan sido completamente satisfechos y no existan ítems en tránsito pendientes. Si esto no es posible (por falta de ítems, cobertura de robopuertos, rutas inviables por batería, etc.), el sistema debe detectar la imposibilidad de resolver el estado y reportar claramente los pedidos no satisfechos.
Inicio de Operaciones y Red de Cobertura
Todos los robots logísticos comienzan sus operaciones desde uno o más robopuertos iniciales, totalmente cargados. Cada robot sólo puede operar dentro del alcance de su robopuerto o de aquellos con los que comparta zona de cobertura. Los cofres forman parte de la red logística únicamente si están dentro del área de cobertura de al menos un robopuerto. Un cofre fuera de toda cobertura se considera inaccesible, y por lo tanto no puede participar en la logística.
Los movimientos entre cofres solo son válidos si existe al menos un camino viable entre ellos, respetando tanto las zonas de cobertura como las limitaciones de batería del robot.
Glosario
Robot logístico: unidad móvil que transporta ítems entre cofres. Tiene una batería limitada (medida en Células) y siempre parte (inicialmente) desde un robopuerto. Planifica rutas eficientes considerando cobertura, consumo de energía y prioridades logísticas.
Robopuerto: infraestructura fija que define una zona de cobertura dentro de la cual pueden operar los robots. También funciona como estación de recarga. Todo cofre debe estar dentro del alcance de al menos un robopuerto para ser considerado parte de la red.
Cofre logístico: contenedor inteligente de ítems. Existen distintos tipos con comportamientos y prioridades específicos.
Zona de cobertura: área definida por un robopuerto. Los cofres dentro de esta zona pueden enviar y recibir ítems mediante robots logísticos. Puede haber zonas superpuestas.
Célula (de energía): unidad abstracta que mide la batería de los robots. Cada movimiento consume células proporcionalmente a la distancia recorrida.
Estado estable: situación en la que todos los pedidos han sido satisfechos y no hay ítems en tránsito. El objetivo de la simulación es alcanzar este estado o informar que no es posible.
Carga de Datos desde Archivo
Para iniciar la simulación, toda la configuración inicial debe ser cargada desde archivo. El formato del archivo queda a criterio de cada equipo (puede ser JSON, CSV, YAML, texto plano estructurado, etc.), pero debe contener claramente la información necesaria para construir el sistema logístico:

La red logística:
Ubicación de cada robopuerto, necesaria para calcular zonas de cobertura
Posición y tipo de cada cofre logístico

Contenido de cada cofre:
Ítems disponibles para ofrecer
Ítems solicitados y sus cantidades
Ítems almacenados (si corresponde)

Robots logísticos:
Punto de inicio (normalmente en un robopuerto, puede variar según robot)
Capacidad total de batería (si se permite que varíe entre robots)
Capacidad de traslado (si se permite que varíe entre robots)

El sistema debe poder procesar este archivo y construir internamente toda la red para luego comenzar con las entregas. El diseño del archivo es libre, pero debe estar bien documentado y permitir replicar fácilmente la situación inicial.
Entrega
La entrega deberá realizarse en formato PDF, incluyendo los casos de prueba, el informe final y cualquier otro documento relevante. El código fuente de la solución se entregará en un archivo comprimido adjunto.
Fecha de Entrega: 3 de Julio de 2025
Presentación
Se realizará una breve presentación de la solución desarrollada, mostrando su funcionamiento en diferentes situaciones y destacando sus características principales. Duración estimada: 10 minutos.

Observaciones
Si se tuvieran dudas durante el desarrollo de la tarea, consultar con sus docentes para recibir orientación adicional.
