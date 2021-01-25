package project.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que representa una respuesta que se situará dentro de una stack a través de una pregunta.
 * Cada respuesta queda determinada por un id (secuencial), autor, contenido, votos, fecha de publicación
 * y estado de aceptación.
 */
public class Answer {
  private static final AtomicInteger count = new AtomicInteger(0);
  private final int id;
  private final Date publicationDate;
  private String acceptationStatus;
  private final String content;
  private int votes;
  private final User author;

  public Answer(User user, String content) {
    this.id = count.incrementAndGet();
    this.author = user;
    this.content = content;
    this.votes = 0;
    this.publicationDate = new Date();
    this.acceptationStatus = "No";
  }

  /**
   * Método de instancia (getter) que retorna el id de una respuesta
   * @return Id de una respuesta
   */
  public int getId() {
    return id;
  }

  /**
   * Método de instancia (getter) que retorna el autor de una respuesta
   * @return User, autor de una respuesta
   */
  public User getAuthor() {
    return author;
  }

  /**
   * Método de instancia (getter) que retorna un contenido de una respuesta
   * @return Contenido de una respuesta
   */
  public String getContent() {
    return content;
  }

  /**
   * Método de instancia (getter) que retorna cantidad de votos de una respuesta
   * @return Cantidad de votos de una respuesta
   */
  public int getVotes() {
    return votes;
  }

  /**
   * Método de instancia (setter) que setea un nuevo estado de aceptación de una respuesta
   * @param newStatus Nuevo estado de una respuesta
   */
  public void setAcceptationStatus(String newStatus) {
    acceptationStatus = newStatus;
  }

  /**
   * Método de instancia que suma un voto positivo o negativo a los votos de una instancia de answer.
   * @param vote Número positivo (1) o negativo (-1) que se sumará a los votos de la respuesta
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
   * Método de instancia (getter) que retorna el estado de aceptación de una respuesta
   * @return Estado de una respuesta
   */
  public String getStatus() {
    return acceptationStatus;
  }

  /**
   * Método de instancia que efectúa una sobreescritura sobre el método toString()
   * para especificar los atributos de la clase Answer.
   * @return String con formato concatenado.
   */
  @Override
  public String toString() {
    return "Answer{" +
            "id=" + id +
            ", publicationDate=" + publicationDate +
            ", acceptationStatus='" + acceptationStatus + '\'' +
            ", content='" + content + '\'' +
            ", votes=" + votes +
            ", author=" + author +
            '}';
  }
}