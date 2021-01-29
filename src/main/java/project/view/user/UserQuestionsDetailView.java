package project.view.user;

import project.model.Answer;
import project.model.Question;
import project.model.User;
import project.view.Main;
import project.view.answer.AnswersModelTable;
import project.view.question.QuestionsModelTable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Clase que provee el detalle de preguntas filtradas por usuario
 */
public class UserQuestionsDetailView extends JPanel {
  String SUCCESS_MESSAGE = "La respuesta ha sido aceptada exitosamente.";
  String LOGIN_FAILURE_MESSAGE = "¡No te encuentras logueado!";
  String FAILURE_MESSAGE = "La respuesta no ha podido ser aceptada. Inténtalo nuevamente.";
  Question filteredQuestion;
  Answer filteredAnswer;
  User loggedUser;
  JTable questionsTable;
  JTable answersTable;
  JScrollPane tableSp;

  /**
   * Constructor de vista de detalle de preguntas filtradas por usuario
   */
  public UserQuestionsDetailView() {
    loggedUser = Main.currentStack.getLoggedUser();
    buildView();
  }

  /**
   * Método de instancia privado que construye la grilla principal y la tabla de preguntas de un usuario
   */
  private void buildView() {
    buildGrid();
    buildQuestionsTable();
  }

  /**
   * Método de instancia que setea estilos para grilla principal
   */
  private void buildGrid() {
    setLayout(new GridLayout(3,1));
    setBounds(300, 90, 1400, 700);
  }

  /**
   * Método de instancia privado que construye la tabla de respuestas con el fin de aceptar una de ellas
   * @param questionId Id de pregunta de la que se aceptará una de sus respuestas
   */
  private void buildAnswersTable(int questionId) {
    filteredQuestion = Main.currentStack.filterQuestionById(questionId);
    AnswersModelTable answersModelTable = new AnswersModelTable(filteredQuestion);
    answersTable = new JTable();
    answersTable.setModel(answersModelTable);
    setTableStyles(answersTable);

    answersTable.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
      int row = answersTable.rowAtPoint(e.getPoint());
      int col = answersTable.columnAtPoint(e.getPoint());
      if (row >= 0 && col >= 0) {
        int answerId = (int) answersModelTable.getValueAt(row, 0); // Id de respuesta
        filteredAnswer = Main.currentStack.filterQuestionById(questionId).filterAnswerById(answerId);
        int confirmed = JOptionPane.showConfirmDialog(null,
          "¿Seguro que quieres aceptar esta respuesta?", "Aceptar respuesta",
          JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
          if (Main.currentStack.getLoggedUser() == null) {
            JOptionPane.showMessageDialog(null, LOGIN_FAILURE_MESSAGE, null, JOptionPane.WARNING_MESSAGE);
            return;
          }
          boolean acceptSuccess = Main.currentStack.accept(filteredQuestion, filteredAnswer);
          if (acceptSuccess) {
            JOptionPane.showMessageDialog(null, SUCCESS_MESSAGE);
            answersModelTable.fireTableDataChanged();
          } else {
            JOptionPane.showMessageDialog(null, FAILURE_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
          }
        }
      }
      }
    });
    if (tableSp != null) {
      remove(tableSp);
    }
    tableSp = new JScrollPane(answersTable);
    tableSp.setBorder(BorderFactory.createTitledBorder(null, "Respuestas", TitledBorder.CENTER,
      TitledBorder.TOP, new Font("Monospaced", Font.PLAIN, 22)));
    tableSp.setPreferredSize(new Dimension(400, 400));
    add(tableSp, BorderLayout.WEST);
  }

  /**
   * Método de instancia que añade bordes y márgenes a cualquier tabla que reciba
   * @param table Tabla a cual se le setearán estilos.
   */
  private void setTableStyles(JTable table) {
    table.setBounds(30, 250, 1300, 700);
    Border border = table.getBorder();
    Border margin = new EmptyBorder(20, 20, 10, 20);
    table.setBorder(new CompoundBorder(border, margin));
    table.setBorder(new CompoundBorder(border, margin));
  }

  /**
   * Método de instancia privado que construye la tabla de preguntas que mostrará las respuestas asociadas
   * una vez clickeada una de las filas.
   */
  private void buildQuestionsTable() {
    QuestionsModelTable questionsModelTable = new QuestionsModelTable(Main.currentStack.filterQuestionsByUser(loggedUser));
    questionsTable = new JTable();
    questionsTable.setModel(questionsModelTable);
    setTableStyles(questionsTable);
    questionsTable.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        int row = questionsTable.rowAtPoint(e.getPoint());
        int col = questionsTable.columnAtPoint(e.getPoint());
        if (row >= 0 && col >= 0) {
          int questionId = (int) questionsTable.getValueAt(row, 0); // Id de pregunta
          buildAnswersTable(questionId);
          revalidate();
          repaint();
        }
      }
    });
    JScrollPane sp = new JScrollPane(questionsTable);
    sp.setBorder(BorderFactory.createTitledBorder(null, "Todas tus preguntas", TitledBorder.CENTER,
      TitledBorder.TOP, new Font("Monospaced", Font.PLAIN, 22)));
    sp.setPreferredSize(new Dimension(500, 300));
    add(sp, BorderLayout.EAST);
  }
}
