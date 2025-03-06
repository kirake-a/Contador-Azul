# Compilar y generar el `.jar` de Contador Azul

Este documento describe los pasos para compilar y empaquetar el proyecto **Contador Azul** en un archivo `.jar` ejecutable.

---

## **1. Compilar los archivos Java**
Ejecuta el siguiente comando en la raíz del proyecto para compilar todos los archivos `.java` y colocarlos en la carpeta `out`:

```sh
javac -d out $(find src/main/java -name "*.java")
```

---

## **2. Crear el archivo `MANIFEST.MF`**
Crea un archivo llamado `MANIFEST.MF` en la raíz del proyecto con el siguiente contenido:

```
Main-Class: com.mantenimiento.azul.Main
```

📌 **Nota:** Asegúrate de que haya una línea en blanco al final del archivo.

---

## **3. Generar el `.jar`**
Ejecuta el siguiente comando para empaquetar el `.jar` con la versión `1.1`:

```sh
jar cfm ContadorAzul-1.1.jar MANIFEST.MF -C out .
```

Esto generará el archivo `ContadorAzul-1.1.jar` en la raíz del proyecto.

---

## **4. Ejecutar el `.jar`**
Para probar el `.jar` generado, usa el siguiente comando:

```sh
java -jar ContadorAzul-1.1.jar
```