package model;

/**
 * Clase que representa una recompensa que se situará dentro de una pregunta en un stack.
 * Cada pregunta queda determinada por un id (secuencial), título, contenido, autor, fecha de publicación, votos, status.
 * Tipo de relaciones: asociación (User).
 */
public class Reward {
  private final int quantity;
  private final User user;

  Reward(int quantity, User user) {
    this.quantity = quantity;
    this.user = user;
  }

  /**
   * Método de instancia (getter) que retorna un usuario
   * @return Cantidad de recompensa
   */
  public int getQuantity() {
    return quantity;
  }


  /**
   * Método de instancia (getter) que retorna un usuario
   * @return User
   */
  public User getUser() {
    return user;
  }

  /**
   * Método de instancia que efectúa una sobreescritura sobre el método toString()
   * para especificar los atributos de la clase Reward.
   * @return String con formato concatenado.
   */
  @Override
  public String toString() {
    return "Reward{" +
            "quantity=" + quantity +
            ", user=" + user +
            '}';
  }
}