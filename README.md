# Laboratorio paradigmas: [Stackoverflow](https://stackoverflow.com/)

Se presenta proyecto semestral de laboratorio de la asignatura **"Paradigmas de programación"** de la Universidad Santiago de Chile, que consta en la creación de un clon de la aplicación Stackoverflow en el lenguaje Java,
que consolida los conocimientos del paradigma orientado a objetos y orientado a eventos (multiparadigma)

## Autora
- [Javiera Tapia Bobadilla](https://github.com/javieratapiab)

## Características

- Paradigma: Orientado a objetos y Orientado a eventos (Multipardigma)
- Lenguage: Java (https://docs.oracle.com/en/java/), OpenJDK versión 11
- Intérprete: IntelliJ IDEA Community 2020.3
- Compilador: Gradle

## Ambiente de desarrollo:
- El ambiente de desarrollo donde se ejecutó el entregable corresponde a **macOS 10.15 Catalina**
- Versión exacta de JDK: OpenJDK versión 11

## Construcción de TDAs (modelos)

| Abstracción         	| Contexto                                                                                                                                                     	| Características (Qué)                                                                                                                                                                                                                                                                                                                                                                                                                	|
|---------------------	|--------------------------------------------------------------------------------------------------------------------------------------------------------------	|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|
| Stack               	| Estructura base que funciona como contenedor de usuarios, preguntas, y etiquetas.                                                 	| Lista de listas que se encarga de contener una serie de estructuras (del tipo lista), <br>tales como: usuarios, preguntas, recompensas, respuestas y búsqueda dentro de la plataforma.                                                                                                                                                                                                                                               	|
| Usuario (User)      	| Persona que publica, edita y elimina tanto preguntas como respuestas,<br>ofrece recompensas, vota, reporta, entre otros.                                     	| - Lista que posee credenciales de acceso y reputación<br>- Ofrece reputación, pregunta y responde, da ranking, etc. dentro de la plataforma.                                                                                                                                                                                                                                                                                         	|
| Pregunta (Question) 	| Enunciado interrogativo realizado por un usuario sobre diversas<br>tecnologías relacionadas a la ingeniería de software en la plataforma.                    	| - Lista que puede ser editada, modificada y eliminada.<br>- Es creada por un usuario y recibe ranking por medio del mismo.<br>- Posee textos con formato (negrita, cursiva) imágenes, enlaces y código<br>- Posee un ID, votaciones (positivas y negativas), visualizaciones,<br>  etiquetas, título, contenido, fecha de publicación, fecha de última modificación , etc.<br>- Es referenciada por una respuesta a través de su ID. 	|
| Respuesta (Answer)  	| Enunciado que responde a una pregunta realizada por un usuario<br>sobre diversas tecnologías relacionadas a la ingeniería de software en la plataforma       	| - Lista que recibe ranking y referencia una pregunta.<br>- Es creada por un usuario, quien puede recibir una bonificación (reputación) si esta es aceptada.<br>- Posee un autor (usuario), fecha de publicación, votos (favor y contra), estado de aceptación (sí/no), <br>  reportes de ofensa y categorías (mejor respuesta, peor respuesta, etc).                                                                                 	|
| Recompensa (Reward) 	| Bonificación que genera un usuario y que compensa<br>la utilidad de una pregunta dentro de la plataforma.                                                    	| - Lista que posee una descripción, id de pregunta y usuario que la otorgó.<br>- Bonifica una pregunta y posee diversas reglas en su aplicación.                                                                                                                                                                                                                                                                                      	|
| Auth  	            | Interface que setea las principales operaciones de auth como register, login y logout.                                                                        | - Directiva que fija comportamientos de autenticación en la declaración de sus métodos.                                                                                                                                                                                                                                   	|

## Instrucciones de compilación

- Comando para correr compilador Gradle en terminal: `./gradlew run`

## Características del laboratorio

- Más sobre los requerimientos y especificaciones: [Link a la actividad](https://docs.google.com/document/d/1TwFzL2nr5yJ24qKY3V4Z-iSBFnZuGbB_tgJ2ov_UtJs)
