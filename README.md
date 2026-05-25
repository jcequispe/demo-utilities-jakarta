# demo-utilities-jakarta

**Librería Wrapper** que proporciona métodos utilitarios para procesar datos serializados.

## Descripción

Este proyecto Maven es una librería que:
- 📦 **NO importa el BOM** (sin dependencias declaradas)
- 📚 **Proporciona métodos wrapper** (`DataProcessor`)
- 🔗 **Se registra en el BOM** para que otros proyectos la usen
- 🔓 **Usa commons-collections indirectamente** (proporcionada por el consumidor)

## Estructura

```
demo-utilities-jakarta/
├── pom.xml                          ← SIN dependencias
└── src/main/java/com/demo/utils/
    └── DataProcessor.java           ← Métodos wrapper
```

## Propósito

Esta librería wrapper:

1. **Encapsula métodos de serialización** - Proporciona métodos reutilizables
2. **Sin dependencias explícitas** - Confía en que el consumidor las proporciona
3. **Registrada en BOM** - El consumidor obtiene la versión del BOM
4. **Desacoplamiento** - No declara commons-collections, lo usa por inference

## Clase DataProcessor

```java
public class DataProcessor {
    // Deserializa datos binarios
    public static Object deserializeData(byte[] data) throws Exception { ... }
    
    // Procesa colecciones (usa commons-collections si está disponible)
    public static String processCollection(Object collection) { ... }
}
```

## Flujo de Dependencias

```
demo-bom-jakarta
    ├─ Registra: demo-utilities-jakarta:1.0.0
    └─ Define: commons-collections:3.2.1

demo-utilities-jakarta
    ├─ Registrado en: BOM
    └─ Proporciona: DataProcessor (wrapper methods)

demo-app-vulnerable
    ├─ Importa: BOM
    ├─ Declara: demo-utilities-jakarta (obtiene del BOM)
    ├─ Declara: commons-collections (obtiene del BOM)
    └─ Usa: DataProcessor + commons-collections en endpoint
```

## Compilar

```bash
# NO necesita que el BOM esté instalado
# Pero para probar con el proyecto vulnerable, instalar en orden:

cd ../demo-bom-jakarta
mvn clean install

cd ../demo-utilities-jakarta
mvn clean install
```

## Verificar dependencias

```bash
mvn dependency:tree
```

Salida esperada:

```
[INFO] com.demo:demo-utilities-jakarta:jar:1.0.0
[INFO] (sin dependencias - es una librería independiente)
```

## Características Clave

✅ **Sin dependencias declaradas** - No hereda commons-collections
✅ **Métodos wrapper** - `DataProcessor` proporciona funcionalidades
✅ **Registrada en BOM** - El BOM la controla, no ella al BOM
✅ **Versionado centralmente** - Version viene del BOM
✅ **Desacoplada** - Cambios en commons-collections no la afectan

## Uso en demo-app-vulnerable

```java
import com.demo.utils.DataProcessor;

// El vulnerable la usa así:
Object obj = DataProcessor.deserializeData(data);

// commons-collections viene del BOM del proyecto vulnerable
// utilities wrapper NO la declara
```

## Dependabot

Cuando esté en GitHub:
- 🔔 Dependabot detecta que está **registrada en el BOM**
- 🔍 Verifica su versión (definida en BOM)
- 📨 Si el BOM cambia la versión de utilities, abre PR
- 🔗 Los cambios en el BOM se propagan a todos los consumidores

## Actualizar versión

La versión de utilities está definida en `demo-bom-jakarta/pom.xml`:

```xml
<dependency>
    <groupId>com.demo</groupId>
    <artifactId>demo-utilities-jakarta</artifactId>
    <version>1.0.0</version>  ← Cambiar aquí
</dependency>
```

Los consumidores automáticamente obtienen la nueva versión.

## Referencias

- [Dependency Management - Maven](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
- [BOM Pattern - Maven](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
- [Wrapper/Adapter Pattern](https://refactoring.guru/design-patterns/adapter)

---

**Parte de:** Demo educativa de vulnerabilidades CVE-2015-6420
