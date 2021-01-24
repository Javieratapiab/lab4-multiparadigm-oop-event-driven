package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que representa una pregunta que se situará dentro de un stack.
 * Cada pregunta queda determinada por un id (secuencial), título, contenido, autor, fecha de publicación, votos, status.
 * Tipo de relaciones: agregación (respuestas, etiquetas y recompensas) y asociación (usuario).
 */
public class Question {
  private static final AtomicInteger count = new AtomicInteger(0);
  private final int id;
  private final String title;
  private final String content;
  private final Date publicationDate;
  private final User author;
  private int votes;
  private String status;
  private final List<Answer> answers;
  private final List<Label> labels;
  private List<Reward> rewards;

  Question(User author, String title, String content) {
    this.id = count.incrementAndGet();
    this.author = author;
    this.title = title;
    this.content = content;
    this.publicationDate = new Date();
    this.votes = 0;
    this.status = "Abierta";
    this.answers = new ArrayList<>();
    this.rewards = new ArrayList<>();
    this.labels = new ArrayList<>();
  }

  /**
   * Método de instancia (getter) que retorna un autor (User) asociado a una pregunta
   * @return User autor de una pregunta.
   */
  public User getAuthor() {
    return author;
  }

  /**
   * Método de instancia (getter) que retorna status de una pregunta
   * @return String Estado de una pregunta
   */
  public String getStatus() {
    return status;
  }

  /**
   * Método de instancia (setter) que modifica un estado de una pregunta
   * @param newStatus Nuevo estado de una pregunta
   */
  public void setStatus(String newStatus) {
    status = newStatus;
  }

  /**
   * Método de instancia (setter) que permite modificar lista de recompensas asociada
   * @param newRewards Nuevas recompensas asociadas a una pregunta.
   */
  public void setRewards(List<Reward> newRewards) {
    rewards = newRewards;
  }

  /**
   * Método de instancia (getter) que retorna lista de recompensas asociadas a una pregunta
   * @return Lista de recompensas.
   */
  public List<Reward> getRewards() {
    return rewards;
  }

  /**
   * Método de instancia (getter) que retorna lista de respuestas asociadas a una pregunta
   * @return Retorna una lista de respuestas asociadas
   */
  public List<Answer> getAnswers() {
    return answers;
  }

  /**
   * Método de instancia que agrega una etiqueta a lista de etiquetas asociadas a una pregunta
   * @param label Etiqueta a agregar en lista de etiquetas
   */
  public void addLabel(Label label) {
    labels.add(label);
  }

  /**
   * Método de instancia que agrega una respuesta a una lista de respuestas asociadas a una pregunta
   * @param answer Respuesta a agregar en lista de respuestas
   */
  public void addAnswer(Answer answer) {
    answers.add(answer);
  }

  /**
   * Método de instancia que agrega una recompensa a una lista de recompensas asociadas a una pregunta
   * @param reward Recompensa a agrega en lista de recompensas
   */
  public void addReward(Reward reward) {
    rewards.add(reward);
  }

  /**
   * Método de instancia que permite añadir o restar un voto
   * @param vote Voto positivo (+1) o negativo (-1).
   */
  public void addOrSubstractVotes(int vote) {
    votes += vote;
  }

  /**
   * Método de instancia que efectúa una sobreescritura sobre el método toString().
   * para especificar los atributos de la clase Pregunta.
   * @return String con formato concatenado.
   */
  @Override
  public String toString() {
    return "Question{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", publicationDate=" + publicationDate +
            ", author=" + author +
            ", votes=" + votes +
            ", status='" + status + '\'' +
            ", answers=" + answers +
            ", labels=" + labels +
            ", rewards=" + rewards +
            '}';
  }
}