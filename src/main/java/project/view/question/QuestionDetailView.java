
package project.view.question;

import project.model.Label;
import project.model.Question;
import project.view.Main;
import project.view.answer.AnswersModelTable;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Clase que hereda de JPanel y que muestra un panel con el detalle de una pregunta y sus asociaciones
 */
public class QuestionDetailView extends JPanel {
  String SUCCESS_MESSAGE = "La respuesta ha sido creada exitosamente.";
  String LOGIN_FAILURE_MESSAGE = "¡No te encuentras logueado!";
  String FAILURE_MESSAGE = "La respuesta no ha podido ser creada. Inténtalo nuevamente.";
  Question selectedQuestion;
  AnswersModelTable answersModel;
  JTextField answerContentField = new JTextField();
  JTable answersTable;
  JPanel answerPanel = new JPanel();

  /**
   * Constructor de la vista de detalle de una pregunta en particular, mostrando sus respuestas asociadas
   * @param questionId ID que representa el ID de una pregunta
   */
  public QuestionDetailView(int questionId) {
    for(Question question : Main.currentStack.getQuestions()) {
      if (questionId == question.getId()) {
        selectedQuestion = question;
        break;
      }
    }
    buildView();
  }

  /**
   * Método de instancia privado que permite construir la vista de detalle de pregunta y sus componentes
   */
  private void buildView() {
    buildGrid();
    buildQuestionDetail();
    buildAnswersTable();
    buildNewAnswerPanel();
  }

  /**
   * Método de instancia privado que permite construir la grilla de la vista del detalle de una pregunta
   */
  private void buildGrid() {
    setLayout(new GridLayout(4,1));
    setBounds(300, 90, 1300, 800);
  }

  /**
   * Método de instancia privado que se encarga de construir la tabla de respuestas asociadas a una pregunta
   */
  private void buildAnswersTable() {
    answersModel = new AnswersModelTable(selectedQuestion);
    answersTable = new JTable();
    answersTable.setModel(answersModel);
    Border answerBorder = answersTable.getBorder();
    Border answerMargin = new EmptyBorder(20,20,10,20);
    answersTable.setBorder(new CompoundBorder(answerBorder, answerMargin));
    JScrollPane sp = new JScrollPane(answersTable);
    sp.setPreferredSize(new Dimension(800, 800));
    sp.setBorder(BorderFactory.createTitledBorder(null, "Respuestas", TitledBorder.CENTER,
      TitledBorder.TOP, new Font("Monospaced", Font.PLAIN, 22)));
    add(sp, BorderLayout.CENTER);
  }

  /**
   * Método de instancia privado que se encarga de construir los componentes de los detalles de una respuesta
   */
  private void buildNewAnswerPanel() {
    ImageIcon newAnswerIcon = new ImageIcon(this.getClass().getResource("/new-answer.png"));
    answerPanel = new JPanel();
    answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
    // Title
    JLabel title = new JLabel("Nueva respuesta");
    title.setIcon(newAnswerIcon);
    title.setFont(new Font(null, Font.BOLD, 15));
    Border border = title.getBorder();
    Border margin = new EmptyBorder(0,0,10,0);
    title.setBorder(new CompoundBorder(border, margin));
    title.setAlignmentX(Component.LEFT_ALIGNMENT);
    answerPanel.add(title);

    // Answer content
    JLabel contentLabel = new JLabel("Tu respuesta: ");
    contentLabel.setPreferredSize(new Dimension(30, 15));
    contentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    answerPanel.add(contentLabel);
    answerContentField.setAlignmentX(Component.LEFT_ALIGNMENT);
    answerPanel.add(answerContentField);

    // Answer button
    JButton newAnswerButton = new JButton("Crear respuesta");
    newAnswerButton.addActionListener(e -> setAnswerAction());

    newAnswerButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    answerPanel.add(newAnswerButton);
    Border answerBorder = answerPanel.getBorder();
    Border answerMargin = new EmptyBorder(20,20,10,20);
    answerPanel.setBorder(new CompoundBorder(answerBorder, answerMargin));
    answerPanel.setBorder(new CompoundBorder(answerBorder, answerMargin));
    add(answerPanel, BorderLayout.WEST);
  }

  /**
   * Método de instancia privado que se encarga de construir los componentes de los detalles de una pregunta
   */
  private void buildQuestionDetail() {
    // Crea componentes para pregunta
    JPanel questionDetailPanel = new JPanel();
    JPanel labelsPills = new JPanel();
    JLabel questionTitle = new JLabel(selectedQuestion.getTitle());
    JLabel questionAuthor = new JLabel(selectedQuestion.getAuthor().getName());
    JLabel questionPublicationDate = new JLabel(selectedQuestion.publicationDateFormat());
    JLabel questionContent = new JLabel(selectedQuestion.getContent());

    for(Label label : selectedQuestion.getLabels()) {
        JButton labelButton = new JButton(label.getName());
        labelButton.setFont(new Font(null, Font.BOLD, 18));
        labelButton.putClientProperty("JButton.buttonType","roundRect");
        labelButton.setForeground(Color.GREEN);
        labelButton.setBackground(Color.black);
        labelButton.setOpaque(false);
        labelsPills.add(labelButton, BorderLayout.WEST);
    }

    // Define estilos para textos
    questionTitle.setFont(new Font(null, Font.PLAIN, 30));
    questionAuthor.setFont(new Font(null, Font.ITALIC, 20));
    questionPublicationDate.setFont(new Font(null, Font.PLAIN, 20));
    questionContent.setFont(new Font(null, Font.PLAIN, 20));

    // Agrega componentes a panel de detalle de pregunta
    questionDetailPanel.add(questionTitle);
    questionDetailPanel.add(questionAuthor);
    questionDetailPanel.add(questionPublicationDate);
    questionDetailPanel.add(questionContent);
    questionDetailPanel.add(labelsPills, BorderLayout.WEST);
    Border border = questionDetailPanel.getBorder();
    Border margin = new EmptyBorder(20,20,10,20);
    questionDetailPanel.setBorder(new CompoundBorder(border, margin));
    questionDetailPanel.setLayout(new GridLayout(5, 1));
    add(questionDetailPanel, BorderLayout.CENTER);
  }

  /**
   * Método de instancia privado que setea la acción de answer y renderiza
   * una alerta de éxito o fracaso dependiendo del resultado
   */
  private void setAnswerAction() {
    String content = answerContentField.getText();
    if (Main.currentStack.getLoggedUser() == null) {
      JOptionPane.showMessageDialog(null, LOGIN_FAILURE_MESSAGE, null, JOptionPane.WARNING_MESSAGE);
      return;
    }
    boolean answerSuccess = Main.currentStack.answer(selectedQuestion, content);
    if (answerSuccess) {
      JOptionPane.showMessageDialog(null, SUCCESS_MESSAGE);
      answersModel.fireTableDataChanged();
    } else {
      JOptionPane.showMessageDialog(null, FAILURE_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
    }
  }
}
