package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que representa el menú interactivo disponible a través de consola
 */
public class Menu {
  private static final Scanner scanner = new Scanner(System.in);
  private static boolean exitProgram = false;
  private static Stack currentStack;

  public static void main(String[] args) {
    // Inicia menú
    mainMenu();
  }

  /**
   * Método de clase que imprime header de menú y stack elegido
   */
  private static void printMenuHeader() {
    System.out.print("\n### SISTEMA DE PREGUNTAS Y RESPUESTAS ###\n");
    if (currentStack != null) {
      System.out.printf(currentStack.toString());
    }
  }

  /**
   * Método de clase que muestra en consola el menú principal
   */
  public static void mainMenu() {
    while (!exitProgram) {
      printMenuHeader();
      System.out.print("1. Empezar con un Stack existente\n");
      System.out.print("2. Crear un Stack desde cero\n");
      System.out.print("3. Salir del programa\n");
      System.out.print("ESCOJA UNA OPCIÓN:\n");

      String selectedOptionOne = scanner.nextLine();

      switch (selectedOptionOne) {
        case "1":
          // Usar stack existente
          currentStack = buildStack();
          registerAndLoginMenu();
          break;
        case "2":
          // Usar nuevo stack
          currentStack = new Stack();
          registerAndLoginMenu();
          break;
        case "3":
          // Salir del programa
          exitProgram = true;
          scanner.close();
          break;
        default:
          // Opción inválida
          System.out.printf("Tu opción no es válida. Selecciona un número entre 1 a 3. \n");
      }
    }
  }

  /**
   * Método de clase que construye el stack de prueba
   *
   * @return Stack de prueba
   */
  private static Stack buildStack() {
    // Usuarios
    User user1 = new User("Pepsi", "pa$$");
    User user2 = new User("Coca Cola", "12345");
    User user3 = new User("Canada Dry", "myPass");
    User user4 = new User("Inca Cola", "mySuperPa$$");

    // Labels
    Label label1 = new Label("Java", "Lenguaje de programación");
    Label label2 = new Label("Computer science", "Ciencia que estudia a los computadores");
    Label label3 = new Label("C#", "Lenguaje de programación");

    // Preguntas
    Question question1 = new Question(user3, "¿Qué es un arreglo?",
            "Me gustaría saber qué es Cerrar sesión un arreglo en Java");
    Question question2 = new Question(user4, "¿Cómo hacer un for loop en Java?",
            "No sé cómo hacer un for loop en Java");
    Question question3 = new Question(user1, "¿Cuál es la diferencia entre un método y una función?",
            "No sé qué significa :(");
    Question question4 = new Question(user3, "¿De qué se trata el criterio de cohesión?",
            "Me gustaría entender cómo funciona este concepto en OOP");
    Question question5 = new Question(user2, "¿Cómo empezar en Unity?",
            "Quiero programar videojuegos.");

    // Añadir etiquetas a preguntas
    question1.addLabel(label1);
    question1.addLabel(label2);
    question3.addLabel(label1);
    question3.addLabel(label3);
    question4.addLabel(label2);

    // Respuestas
    Answer answer1 = new Answer(user1, "Un arreglo es una estructura de dato que se encarga de reservar espacio en memoria para una colección de elementos");
    Answer answer2 = new Answer(user4, "Una estructura de datos que manipula datos de una forma sensible.");
    Answer answer3 = new Answer(user3, "Un for loop en Java se puede hacer con la keyword for, se puede hacer de forma iterativa o sobre una colección de instancias");
    Answer answer4 = new Answer(user2, "Un bucle puede hacerse de 4 formas en Java, un for loop es una de ellas.");
    Answer answer5 = new Answer(user3, "Un método es parte de una clase mientras que las funciones están definidas por sí mismas y no pertenecen a ninguna clase");
    Answer answer6 = new Answer(user2, "No tengo idea jeje, suerte");
    Answer answer7 = new Answer(user1, "La cohesión se refiere al nivel de fortaleza y unidad con la que diferentes componentes de un programa están interrelacionados entre ellos");
    Answer answer8 = new Answer(user3, "Let Me Google That For You");
    Answer answer9 = new Answer(user1, "Puedes revisar la sección de tutoriales disponibles en la documentación de Unity.");
    Answer answer10 = new Answer(user4, "Hay buenos cursos en Coursera para beginners de Unity.");

    // Añadir respuestas a preguntas
    question1.addAnswer(answer1);
    question1.addAnswer(answer2);
    question2.addAnswer(answer3);
    question2.addAnswer(answer4);
    question3.addAnswer(answer5);
    question3.addAnswer(answer6);
    question4.addAnswer(answer7);
    question4.addAnswer(answer8);
    question5.addAnswer(answer9);
    question5.addAnswer(answer10);

    // Lista de preguntas, respuestas y etiquetas
    List<User> users = new ArrayList<User>();
    List<Question> questions = new ArrayList<Question>();
    List<Label> labels = new ArrayList<Label>();

    // Creando lista de usuarios
    users.add(user1);
    users.add(user2);
    users.add(user3);
    users.add(user4);

    // Creando lista de preguntas
    questions.add(question1);
    questions.add(question2);
    questions.add(question3);
    questions.add(question4);
    questions.add(question5);

    // Creando lista de etiquetas
    labels.add(label1);
    labels.add(label2);
    labels.add(label3);

    // Stack
    Stack stack1 = new Stack(users, questions, labels);
    return stack1;
  }

  /**
   * Método de clase que muestra el menú de registro y login
   */
  private static void registerAndLoginMenu() {
    while (!exitProgram) {
      String username;
      String password;

      printMenuHeader();
      System.out.print("1. Registrar un usuario\n");
      System.out.print("2. Loguear un usuario\n");
      System.out.print("3. Cerrar sesión\n");
      System.out.print("4. Salir del programa\n");
      System.out.print("ESCOJA UNA OPCIÓN:\n");

      String selectedOptionTwo = scanner.nextLine();

      switch (selectedOptionTwo) {
        case "1":
          // Registrar usuario (REGISTER)
          System.out.println("Ingresa nombre de usuario:");
          username = scanner.nextLine();
          System.out.println("Ingresa password:");
          password = scanner.nextLine();
          boolean registerSuccess = currentStack.register(username, password);
          if (registerSuccess) {
            System.out.printf("RESULTADO: El usuario %s ha sido registrado correctamente\n", username);
          } else {
            System.out.printf("RESULTADO: El usuario %s ya existe. Intenta con otro nombre\n", username);
          }
          break;
        case "2":
          // Loguear usuario (LOGIN)
          System.out.println("Ingresa nombre de usuario:");
          username = scanner.nextLine();
          System.out.println("Ingresa password:");
          password = scanner.nextLine();
          boolean loginSuccess = currentStack.login(username, password);
          if (loginSuccess) {
            System.out.printf("RESULTADO: El usuario %s ha sido logueado correctamente\n", username);
            loggedUserMenu();
          } else {
            System.out.printf("RESULTADO: Credenciales incorrectas. Intenta nuevamente\n");
          }
          break;
        case "3":
          // Cerrar sesión (LOGOUT)
          boolean logoutSuccess = currentStack.logout();
          if (logoutSuccess) {
            System.out.printf("RESULTADO: Has sido deslogueado exitosamente\n");
          } else {
            System.out.printf("RESULTADO: Cierre de sesión inválido, no hay usuarios logueados.\n");
          }
          mainMenu();
          break;
        case "4":
          // Salir del programa
          exitProgram = true;
          scanner.close();
          break;
        default:
          // Opción no válida
          System.out.printf("Tu opción no es válida. Selecciona un número entre 1 y 4\n");
          break;
      }
    }
  }

  /**
   * Método de clase que despliega menú de selección de etiquetas
   *
   * @return Lista de etiquetas seleccionadas que serán añadidas a una pregunta
   */
  private static List<Label> selectLabels() {
    List<Label> selectedLabels = new ArrayList<Label>();

    System.out.print("Elija las etiquetas relacionadas a su pregunta: \n");

    for (Label label : currentStack.getLabels()) {
      System.out.printf("Etiqueta: %s, descripción: %s \n", label.getName(), label.getDescription());
      System.out.printf("1. Agregar\n");
      System.out.printf("2. No agregar\n");
      System.out.print("ESCOJA UNA OPCIÓN:\n");

      String acceptedLabel = scanner.nextLine();

      switch (acceptedLabel) {
        case "1":
          // Agrega etiqueta
          selectedLabels.add(label);
          System.out.printf("Etiqueta: %s ha sido agregada a la pregunta\n", label.getName());
          continue;
        case "2":
          // No agrega etiqueta
          System.out.printf("Etiqueta: %s no se agrega a la pregunta\n", label.getName());
          continue;
        default:
          // Opción inválida
          System.out.printf("Tu opción no es válida. Selecciona un número entre 1 y 2\n");
          break;
      }
    }
    return selectedLabels;
  }

  /**
   * Método de clase que despliega un menú para selección de preguntas
   *
   * @param questions Lista de preguntas
   * @return Pregunta seleccionada
   */
  private static Question selectQuestion(List<Question> questions) {
    Question selectedQuestion = null;

    System.out.print("Elija una de las siguientes preguntas: \n");

    for (Question question : questions) {
      if (selectedQuestion == null) {
        System.out.printf("Pregunta: %s\n", question.toString());
        System.out.print("1. Seleccionar\n");
        System.out.print("2. Continuar\n");
        System.out.print("ESCOJA UNA OPCIÓN:\n");

        String acceptedQuestion = scanner.nextLine();

        switch (acceptedQuestion) {
          case "1":
            // Selecciona pregunta
            selectedQuestion = question;
            System.out.printf("Has seleccionado la siguiente pregunta: %s\n", question.toString());
            break;
          case "2":
            // No selecciona pregunta
            continue;
          default:
            // Opción inválida
            System.out.printf("Tu opción no es válida. Selecciona un número entre 1 y 2\n");
            break;
        }
      }
    }
    return selectedQuestion;
  }

  /**
   * Método de clase que despliega un menú para seleccionar una respuesta
   *
   * @param answers Lista de respuestas
   * @return Respuesta seleccionada
   */
  private static Answer selectAnswer(List<Answer> answers) {
    Answer selectedAnswer = null;

    System.out.print("\nElija una de las siguientes respuestas:\n");

    for (Answer answer : answers) {
      if (selectedAnswer == null) {
        System.out.printf("Respuesta: %s\n", answer.toString());
        System.out.print("1. Seleccionar\n");
        System.out.print("2. Continuar\n");
        System.out.print("ESCOJA UNA OPCIÓN:\n");

        String acceptedAnswer = scanner.nextLine();

        switch (acceptedAnswer) {
          case "1":
            // Selecciona respuesta
            selectedAnswer = answer;
            System.out.printf("\nHas seleccionado la siguiente respuesta: %s\n", answer.toString());
            break;
          case "2":
            // No selecciona respuesta
            continue;
          default:
            // Opción inválida
            System.out.printf("\nTu opción no es válida. Selecciona un número entre 1 y 2\n");
            break;
        }
      }
    }
    return selectedAnswer;
  }

  /**
   * Método de clase que despliega menú para usuario logueado
   */
  private static void loggedUserMenu() {
    while (!exitProgram) {
      Question selectedQuestion;
      Answer selectedAnswer;

      printMenuHeader();
      if (currentStack.getLoggedUser() != null) {
        System.out.printf("## Logueado como: %s ##\n", currentStack.getLoggedUser().getName());
      }
      System.out.print("1. Agregar nueva pregunta\n");
      System.out.print("2. Responder pregunta\n");
      System.out.print("3. Dar recompensa\n");
      System.out.print("4. Aceptar respuesta\n");
      System.out.print("5. Votar pregunta/respuesta\n");
      System.out.print("6. Crear etiqueta\n");
      System.out.print("7. Cerrar sesión\n");
      System.out.print("8. Salir del programa\n");
      System.out.print("ESCOJA UNA OPCIÓN:\n");

      String selectedOption = scanner.nextLine();

      switch (selectedOption) {
        case "1":
          // Crear pregunta (ASK)
          System.out.print("Título de la pregunta:\n");
          String title = scanner.nextLine();
          System.out.print("Contenido de la pregunta:\n");
          String content = scanner.nextLine();
          List<Label> selectedLabels = selectLabels();
          boolean askSuccess = currentStack.ask(title, content, selectedLabels);
          if (askSuccess) {
            System.out.printf("RESULTADO: La pregunta ha sido creada exitosamente\n");
          } else {
            System.out.printf("RESULTADO: La creación de pregunta ha fallado. Inténtalo nuevamente\n");
          }
          break;
        case "2":
          // Crear respuesta (ANSWER)
          selectedQuestion = selectQuestion(currentStack.getQuestions());
          if (selectedQuestion == null) {
            System.out.print("No has seleccionado ninguna pregunta. Inténtalo nuevamente\n");
          } else {
            System.out.printf("Contenido de la respuesta:\n");
            String answerContent = scanner.nextLine();
            boolean answerSuccess = currentStack.answer(selectedQuestion, answerContent);
            if (answerSuccess) {
              System.out.print("RESULTADO: La respuesta ha sido creada exitosamente\n");
            } else {
              System.out.printf("RESULTADO: La creación de respuesta ha fallado. Inténtalo nuevamente\n");
            }
          }
          break;
        case "3":
          // Crear recompensa (REWARD)
          selectedQuestion = selectQuestion(currentStack.getQuestions());
          System.out.printf("Ingrese una recompensa (puntos): \n");
          int rewardQuantity = scanner.nextInt();
          scanner.nextLine();
          boolean rewardSuccess = currentStack.reward(selectedQuestion, rewardQuantity);
          if (rewardSuccess) {
            System.out.print("RESULTADO: La recompensa ha sido creada exitosamente\n");
          } else {
            System.out.print("RESULTADO: La entrega de recompensa ha fallado. Inténtalo nuevamente\n");
          }
          break;
        case "4":
          // Aceptar respuesta (ACCEPT)
          List<Question> filteredQuestions = currentStack.filterQuestionsByUser(currentStack.getLoggedUser());
          selectedQuestion = selectQuestion(filteredQuestions);
          if (selectedQuestion == null) {
            System.out.print("\nNo has seleccionado ninguna pregunta. Inténtalo nuevamente\n");
          } else {
            selectedAnswer = selectAnswer(selectedQuestion.getAnswers());
            if (selectedAnswer == null) {
              System.out.print("\nNo has seleccionado ninguna respuesta. Inténtalo nuevamente\n");
            } else {
              boolean acceptSuccess = currentStack.accept(selectedQuestion, selectedAnswer);
              if (acceptSuccess) {
                System.out.print("RESULTADO: La respuesta ha sido aceptada exitosamente\n");
              } else {
                System.out.print("RESULTADO: No se ha podido aceptar la respuesta. Inténtalo nuevamente\n");
              }
            }
          }
          break;
        case "5":
          // Votar por pregunta o respuesta (VOTE)
          questionsLoop:
          for (Question question : currentStack.getQuestions()) {
            if (!question.getAuthor().getName().equals(currentStack.getLoggedUser().getName())) {
              System.out.print("PREGUNTA:\n");
              System.out.print(question.toString() + "\n");
              System.out.print("1. Votar por pregunta\n");
              System.out.print("2. Votar por respuesta\n");
              System.out.print("3. Continuar\n");
              System.out.print("ESCOJA UNA OPCIÓN: ");

              selectedOption = scanner.nextLine();

              switch (selectedOption) {
                case "1":
                  // Vota por pregunta
                  selectedQuestion = question;
                  System.out.print("SELECCIONA TIPO DE VOTO\n");
                  System.out.print("1. Voto a favor\n");
                  System.out.print("2. Voto en contra\n");
                  System.out.print("ESCOJA UNA OPCIÓN: ");

                  String typeOfVote = scanner.nextLine();
                  if (typeOfVote.equals("1") || typeOfVote.equals("2")) {
                    boolean voteSuccess = currentStack.vote(selectedQuestion, (typeOfVote.equals("1")) ? "UP" : "DOWN");
                    if (voteSuccess) {
                      System.out.print("RESULTADO: Tu voto ha sido creado exitosamente\n");
                    } else {
                      System.out.print("RESULTADO: Tu voto no ha podido ser creado. Inténtalo nuevamente.\n");
                    }
                  } else {
                    // Opción inválida
                    System.out.print("\nLa opción ingresada no es válida.\n");
                  }
                  break questionsLoop;
                case "2":
                  // Vota por respuesta
                  for (Answer answer : question.getAnswers()) {
                    System.out.print(answer.toString() + "\n");
                    System.out.print("1. Seleccionar\n");
                    System.out.print("2. Continuar\n");
                    System.out.print("ESCOJA UNA OPCIÓN:\n");
                    selectedOption = scanner.nextLine();
                    switch (selectedOption) {
                      case "1":
                        // Selecciona respuesta
                        selectedAnswer = answer;
                        System.out.print("SELECCIONA TIPO DE VOTO\n");
                        System.out.print("1. Voto a favor\n");
                        System.out.print("2. Voto en contra\n");
                        System.out.print("ESCOJA UNA OPCIÓN: ");

                        typeOfVote = scanner.nextLine();

                        if (typeOfVote.equals("1") || typeOfVote.equals("2")) {
                          boolean voteSuccess = currentStack.vote(selectedAnswer, (typeOfVote.equals("1")) ? "UP" : "DOWN");
                          if (voteSuccess) {
                            System.out.print("RESULTADO: Tu voto ha sido creado exitosamente\n");
                          } else {
                            System.out.print("RESULTADO: Tu voto no ha podido ser creado. Inténtalo nuevamente.\n");
                          }
                        } else {
                          // Opción inválida
                          System.out.print("\nLa opción ingresada no es válida.\n");
                        }
                        break questionsLoop;
                      case "2":
                        // No selecciona respuesta
                        continue;
                      default:
                        // Opción inválida
                        System.out.print("\nLa opción ingresada no es válida.\n");
                    }
                  }
                  break;
                case "3":
                  continue;
                default:
                  // Opción inválida
                  System.out.print("\nLa opción ingresada no es válida\n");
                  break questionsLoop;
              }
            }
          }
          break;
        case "6":
          // Agregar una etiqueta
          System.out.print("Nombre de la etiqueta:\n");
          String name = scanner.nextLine();
          System.out.print("Descripción de la etiqueta:\n");
          String description = scanner.nextLine();
          Label label = new Label(name, description);
          boolean addLabel = currentStack.addLabel(label);
          if (addLabel) {
            System.out.printf("RESULTADO: La etiqueta ha sido creada exitosamente\n");
          } else {
            System.out.printf("RESULTADO: La etiqueta ya existe. Inténtalo nuevamente.\n");
          }
          break;
        case "7":
          // Cerrar sesión (LOGOUT)
          boolean logoutSuccess = currentStack.logout();
          if (logoutSuccess) {
            System.out.printf("RESULTADO: Has sido deslogueado exitosamente\n");
          } else {
            System.out.printf("RESULTADO: Cierre de sesión inválido, no hay usuarios logueados.\n");
          }
          registerAndLoginMenu();
          break;
        case "8":
          // Salir del programa
          exitProgram = true;
          scanner.close();
          break;
        default:
          System.out.printf("\nLa opción no es válida. Selecciona un número entre 1 y 8\n");
          break;
      }
    }
  }
}