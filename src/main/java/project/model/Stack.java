package project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un stack dentro de la aplicación. Implementa la interface Auth.
 * Tipo de relaciones: composición (usuarios, preguntas y etiquetas).
 */
public class Stack implements Auth {
  private User userLogged;
  private List<User> users;
  private List<Question> questions;
  private List<Label> labels;

  public Stack() {
    this.userLogged = null;
    this.users = new ArrayList<>();
    this.questions = new ArrayList<>();
    this.labels = new ArrayList<>();
  }

  public Stack(List<User> users,
               List<Question> questions,
               List<Label> labels) {
    this.userLogged = null;
    this.users = users;
    this.questions = questions;
    this.labels = labels;
  }

  /**
   * Método de instancia que retorna el usuario logueado dentro del stack
   * @return Usuario logueado
   */
  public User getLoggedUser() {
    return userLogged;
  }

  /**
   * Método de instancia (getter) que retorna una lista de etiquetas del stack
   * @return Lista de etiquetas del stack
   */
  public List<Label> getLabels() {
    return labels;
  }

  /**
   * Método de instancia (getter) que retorna una lista de preguntas del stack
   * @return Lista de preguntas del stack
   */
  public List<Question> getQuestions() {
    return questions;
  }

  /**
   * Método de instancia que añade una pregunta a lista de preguntas de un stack
   * @param question Pregunta a añadir en lista de preguntas
   */
  public void addQuestion(Question question) {
    questions = new ArrayList<>(questions);
    questions.add(question);
  }

  /**
   * Método de instancia que añade un usuario registrado a lista de preguntas de un stack
   * @param name Nombre de usuario a registrar
   * @param password Contraseña de usuario a registrar
   */
  public void addUser(String name, String password) {
    users = new ArrayList<>(users);
    users.add(new User (name, password));
  }

  /**
   * Método de instancia que añade una nueva etiqueta a lista de etiquetas de un stack
   * @param newLabel Etiqueta a añadir
   * @return boolean Retorna un booleano true si la operación fue realizada con éxito, de lo contrario, false.
   */
  public boolean addLabel(Label newLabel) {
    boolean unique = true;
    for (Label label : labels) {
      if (label.getName().equals(newLabel.getName())) {
        unique = false;
        break;
      }
    }
    if (unique) { labels.add(newLabel); }
    return unique;
  }

  /**
   * Método de instancia privado (setter) que asigna un usuario logueado
   * @param user Usuario a loguear
   */
  private void setLoggedUser(User user) {
    userLogged = user;
  }

  /**
   * Método de instancia público (setter) que setea usuarios dentro de un stack
   * @param users Lista de usuarios
   */
  public void setUsers(List<User> users) {
    this.users = users;
  }

  /**
   * Método de instancia público (setter) que setea preguntas dentro de un stack
   * @param questions Lista de preguntas
   */
  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  /**
   * Método de instancia público (setter) que setea etiquetas dentro del stack
   * @param labels Lista de etiquetas
   */
  public void setLabels(List<Label> labels) {
    this.labels = labels;
  }

  /**
   * Método de instancia privado y sobrecargado que permite si un usuario ya se encuentra registrado
   * @param name Nombre de usuario
   * @return boolean Retorna un booleano true si la operación fue realizada con éxito, de lo contrario, false.
   */
  private boolean validateUser(String name) {
    for(User user : users) {
      boolean equalName = user.getName().equals(name);
      if (equalName) { return true; }
    }
    return false;
  }

  /**
   * Método de instancia privado y sobrecargado que permite validar las credenciales de un usuario.
   * @param name Nombre de usuario
   * @param password Contraseña de usuario
   * @return boolean,
   */
  private boolean validateUser(String name, String password) {
    for(User user : users) {
      boolean equalName = user.getName().equals(name);
      boolean equalPassword = user.getPassword().equals(password);
      if (equalName && equalPassword) {
        return true;
      }
    }
    return false;
  }

  /**
   * Método de instancia que permite registrar a un usuario en el stack
   * @param name Nombre del usuario a registrar
   * @param password Contraseña del usuario a registrar
   * @return boolean Retorna un booleano true si la operación fue realizada con éxito, de lo contrario, false.
   */
  public boolean register(String name, String password) {
    if (validateUser(name)) return false;
    addUser(name, password);
    return true;
  }

  /**
   * Método de instancia que permite loguear a un usuario registrado en el stack
   * @param name Nombre del usuario registrado
   * @param password Contraseña del usuario registrado
   * @return boolean Retorna un booleano true si la operación fue realizada con éxito, de lo contrario, false.
   */
  public boolean login(String name, String password) {
    if (!validateUser(name, password)) return false;
    for (User user : users) {
      if (user.getName().equals(name) && user.getPassword().equals(password)) {
        setLoggedUser(user);
        break;
      }
    }
    return true;
  }

  /**
   * Método de instancia que permite desloguear a un usuario del stack.
   * @return boolean Retorna un booleano true si la operación fue realizada con éxito, de lo contrario, false.
   */
  public boolean logout() {
    if (userLogged == null) return false;
    userLogged = null;
    return true;
  }

  /**
   * Método de instancia que permite crear una pregunta en el stack.
   * @param title Título de una pregunta.
   * @param content Contenido de una pregunta.
   * @param labels Etiquetas que serán asociadas a una pregunta
   * @return boolean Retorna un booleano true si la operación fue realizada con éxito, de lo contrario, false.
   */
  public boolean ask(String title, String content, List<Label> labels) {
    if (userLogged == null) return false;
    Question question = new Question(userLogged, title, content);
    for(Label label: labels) {
      question.addLabel(label);
    }
    addQuestion(question);
    return true;
  }

  /**
   * Método de instancia que permite crear una respuesta que será asociada a una pregunta en el stack.
   * @param question Pregunta a la que se le asociará una nueva respuesta.
   * @param content Contenido de la respuesta a crear.
   * @return boolean Retorna un booleano true si la operación fue realizada con éxito, de lo contrario, false.
   */
  public boolean answer(Question question, String content) {
    if (userLogged == null) return false;
    Answer answer = new Answer(userLogged, content);
    question.addAnswer(answer);
    return true;
  }

  /**
   * Método de instancia que permite aceptar una respuesta, cambiar estados de pregunta y respuesta y, adicionalmente,
   * resolver las recompensas asociadas.
   * @param question Pregunta que cambiará su estado a cerrada puesto que una respuesta es aceptada.
   * @param answer Respuesta que será aceptada.
   * @return boolean Retorna un booleano true si la operación fue realizada con éxito, de lo contrario, false.
   */
  public boolean accept(Question question, Answer answer) {
    if (userLogged == null) return false;
    if (question.getStatus() == "Cerrada") return false;
    question.setStatus("Cerrada");
    answer.setAcceptationStatus("Sí");
    userLogged.addOrSubstractReputation(2);
    answer.getAuthor().addOrSubstractReputation(15);
    return true;
  }

  /**
   * Método de instancia que permite votar por una pregunta dentro del stack.
   * @param question Pregunta a la que se le asignará el voto (positivo o negativo)
   * @param voteType Tipo de voto, positivo (UP) o negativo (DOWN)
   * @return boolean Retorna un booleano true si la operación fue realizada con éxito, de lo contrario, false.
   */
  public boolean vote(Question question, String voteType) {
    if (userLogged == null) return false;
    boolean result;

    switch (voteType) {
      case "UP" -> {
        question.addOrSubstractVotes(1);
        question.getAuthor().addOrSubstractReputation(10);
        result = true;
      }
      case "DOWN" -> {
        question.addOrSubstractVotes(-1);
        question.getAuthor().addOrSubstractReputation(-2);
        result = true;
      }
      default -> result = false;
    }

    return result;
  }

  /**
   * Método de instancia (sobrecarga) que permite votar por una respuesta asociada a una pregunta dentro del stack
   * @param answer Respuesta a la que se le asignará el voto (positivo o negativo).
   * @param voteType Tipo de voto, positivo (UP) o negativo (DOWN).
   * @return boolean Retorna un booleano true si la operación fue realizada con éxito, de lo contrario, false.
   */
  public boolean vote(Answer answer, String voteType) {
    if (userLogged == null) return false;
    boolean result;

    switch (voteType) {
      case "UP" -> {
        answer.addOrSubstractVotes(1);
        answer.getAuthor().addOrSubstractReputation(10);
        result = true;
      }
      case "DOWN" -> {
        answer.addOrSubstractVotes(-1);
        answer.getAuthor().addOrSubstractReputation(-2);
        userLogged.addOrSubstractReputation(-1);
        result = true;
      }
      default -> result = false;
    }

    return result;
  }

  /**
   * Método de instancia que retorna una lista de preguntas filtradas del stack.
   * @param user Usuario con el cual se filtrarán las preguntas del stack.
   * @return Lista de preguntas filtradas.
   */
  public List<Question> filterQuestionsByUser(User user) {
    List <Question> result = new ArrayList<>();

    for(Question question : questions) {
      boolean matchAuthor = user.getName().equals(question.getAuthor().getName());
      if (matchAuthor) {
        result.add(question);
      }
    }
    return result;
  }

  /**
   * Método de instancia que retorna una pregunta de la lista de preguntas en el stack filtrada por id
   * @param questionId ID de pregunta a buscar
   * @return Pregunta filtrada
   */
  public Question filterQuestionById(int questionId) {
    Question result = null;
    for(Question question : questions) {
      if (question.getId() == questionId) {
        result = question;
        break;
      }
    }
    return result;
  }

  /**
   * Método de instancia que efectúa una sobreescritura sobre el método toString().
   * para especificar los atributos de la clase Stack.
   * @return String con formato concatenado.
   */
  @Override
  public String toString() {
    return "Stack{" +
            "userLogged=" + userLogged +
            ", users=" + users +
            ", questions=" + questions +
            ", labels=" + labels +
            '}' + "\n\n";
  }
}
