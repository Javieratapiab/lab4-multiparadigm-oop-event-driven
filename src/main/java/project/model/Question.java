package project.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
  Random rnd;
  Date    dt;
  long    ms;

  public Question(User author, String title, String content) {
    this.id = count.incrementAndGet();
    this.author = author;
    this.title = title;
    this.content = content;
    this.publicationDate = generateRandomDate();
    this.votes = 0;
    this.status = "Abierta";
    this.answers = new ArrayList<>();
    this.labels = new ArrayList<>();
  }

  /**
   * Método de instancia privado que permite generar una fecha de publicación aleatoria
   * @return Retorna una fecha aleatoria
   */
  private Date generateRandomDate() {
    rnd = new Random();
    ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
    return (new Date(ms));
  }

  /**
   * Método de instancia (getter) que retorna el id de una pregunta
   * @return Id de una pregunta
   */
  public int getId() {
    return id;
  }

  /**
   * Método de instancia (getter) que retorna un autor (User) asociado a una pregunta
   * @return User autor de una pregunta.
   */
  public User getAuthor() {
    return author;
  }

  /**
   * Método de instancia (getter) que retorna título de una pregunta
   * @return Título de una pregunta
   */
  public String getTitle() {
    return title;
  }

  /**
   * Método de instancia (getter) que retorna un contenido de una pregunta
   * @return Contenido de una pregunta
   */
  public String getContent() {
    return content;
  }

  /**
   * Método de instancia (getter) que retorna fecha de publicación de una pregunta
   * @return Fecha de publicación de una pregunta
   */
  public Date getPublicationDate() {
    return publicationDate;
  }

  /**
   * Método de instancia (getter) que retorna cantidad de votos de una pregunta
   * @return Cantidad de votos de una pregunta
   */
  public int getVotes() {
    return votes;
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
   * Método de instancia (getter) que retorna lista de respuestas asociadas a una pregunta
   * @return Retorna una lista de respuestas asociadas
   */
  public List<Answer> getAnswers() {
    return answers;
  }

  /**
   * Método de instancia (getter) que retorna lista de etiquetas asociadas a una pregunta
   * @return Retorna una lista de etiquetas asociadas
   */
  public List<Label> getLabels() {
    return labels;
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
   * Método de instancia que permite añadir o restar un voto
   * @param vote Voto positivo (+1) o negativo (-1).
   */
  public void addOrSubstractVotes(int vote) {
    votes += vote;
  }

  /**
   * Método de instancia que permite retornar fecha de publicación con formato
   * @return DateFormat
   **/
  public String publicationDateFormat() {
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    return(format.format(publicationDate));
  }

  /**
   * Método de instancia que retorna una respuesta de la lista de respuesta en el stack filtrada por id
   * @param answerId ID de respuesta a buscar
   * @return Respuesta filtrada
   */
  public Answer filterAnswerById(int answerId) {
    Answer result = null;
    for(Answer answer : answers) {
      if (answer.getId() == answerId) {
        result = answer;
        break;
      }
    }
    return result;
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
            '}';
  }
}