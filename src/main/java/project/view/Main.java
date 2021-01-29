package project.view;

import project.model.Answer;
import project.model.Question;
import project.model.Stack;
import project.model.User;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static Stack currentStack = new Stack();

  public static void main(String[] args) throws Exception

  {
    try {
      UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
      // Do nothing
    }

    JFrame.setDefaultLookAndFeelDecorated(true);
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        stackBuilder();
        new MainView();
      }
    });
  }

  /**
   * Método de clase que construye un stack de prueba para operar sobre él
   */
  public static void stackBuilder() {
    User user1 = new User("Pepsi", "pa$$");
    User user2 = new User("Coca Cola", "12345");
    User user3 = new User("Canada Dry", "myPass");
    User user4 = new User("Inca Cola", "mySuperPa$$");

    // Labels
    project.model.Label label1 = new project.model.Label("Java", "Lenguaje de programación");
    project.model.Label label2 = new project.model.Label("Computer science", "Ciencia que estudia a los computadores");
    project.model.Label label3 = new project.model.Label("C#", "Lenguaje de programación");
    project.model.Label label4 = new project.model.Label("Game development", "Desarrollo de videojuegos");

    // Preguntas
    Question question1 = new Question(user3, "¿Qué es un arreglo?",
            "Me gustaría saber qué es un arreglo en Java");
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
    question5.addLabel(label4);

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
    List<User> users = new ArrayList<>();
    List<Question> questions = new ArrayList<>();
    List<project.model.Label> labels = new ArrayList<>();

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
    labels.add(label4);

    // Se añaden instancias a stack
    currentStack = new Stack(users, questions, labels);
  }
}
