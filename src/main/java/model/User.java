package model;

/**
 * Clase que representa un usuario que se situará dentro de un stack.
 * Cadda usuario queda determinado por un nombre (ID), contraseña, reputación y reputación temporal.
 * Tipo de relaciones:
 */
public class User {
  private final String name;
  private final String password;
  private int reputation;
  private int tempReputation;

  User(String name, String password) {
    this.name = name;
    this.password = password;
    this.reputation = 30; // Se entrega por defecto esta cantidad para operar sobre ella
    this.tempReputation = 0;
  }

  /**
   * Método de instancia (getter) que permite retornar el nombre de un usuario
   * @return String Nombre del usuario
   */
  public String getName() {
    return this.name;
  }

  /**
   * Método de instancia (getter) que permite retornar la contraseña de un usuario
   * @return String Contraseña del usuario
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Método de instancia que permite validar la reputación de un usuario antes de ofrecer una recompensa
   * @param rewardQuantity Cantidad de recompensa ofrecida
   * @return Boolean Que indica si la operación fue exitosa (true) o fallida (false).
   */
  public boolean validateReputation(int rewardQuantity) {
    return (reputation + tempReputation >= rewardQuantity);
  }

  /**
   * Método de instancia que permite restar o añadir un número a la reputación real del usuario
   * @param newReputation Cantidad de reputación
   */
  public void addOrSubstractReputation(int newReputation) {
    reputation += newReputation;
  }

  /**
   * Método de instancia que permite restar una cantidad de recompensa a la reputación temporal del usuario
   * @param rewardQuantity Cantidad de recompensa
   */
  public void addDebtReputation(int rewardQuantity) {
    tempReputation -= rewardQuantity;
  }

  /**
   * Método de instancia que permite descontar reputación y añadirla a la reputación temporal anteriormente fijada
   * @param rewardQuantity Cantidad de recompensa
   */
  public void discountReputation(int rewardQuantity) {
    reputation -= rewardQuantity;
    tempReputation += rewardQuantity;
  }

  /**
   * Método de instancia que efectúa una sobreescritura sobre el método toString().
   * para especificar los atributos de la clase User.
   * @return String con formato concatenado.
   */
  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", reputation=" + reputation +
            ", tempReputation=" + tempReputation +
            '}';
  }
}