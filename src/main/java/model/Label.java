package model;

/**
 * Clase que representa una etiqueta que se situará dentro de un stack.
 * Cada etiqueta queda determinada por nombre (único) y una descripción.
 * Tipo de relaciones: agregación (respuestas, etiquetas y recompensas) y asociación (usuario).
 */
class Label {
  private final String name;
  private final String description;

  Label(String name, String description) {
    this.name = name;
    this.description = description;
  }

  /**
   * Método de instancia (getter) que permite retornar el nombre de una etiqueta
   * @return String Nombre de una etiqueta
   */
  public String getName() {
    return this.name;
  }

  /**
   * Método de instancia (getter) que permite retornar la descripción de una etiqueta
   * @return String Descripción de una etiqueta
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Método de instancia que efectúa una sobreescritura sobre el método toString().
   * para especificar los atributos de la clase Label.
   * @return String con formato concatenado.
   */
  @Override
  public String toString() {
    return "Label{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}