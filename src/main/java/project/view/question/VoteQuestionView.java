package project.view.question;

import project.model.Answer;
import project.model.Question;
import project.model.User;
import project.view.Main;
import project.view.answer.AnswersModelTable;
import project.view.utils.VoteDialog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Clase que hereda de JPanel y que muestra un panel con el detalle de una pregunta y sus respuestas
 * para realizar una votación (positiva o negativa).
 */
public class VoteQuestionView extends JPanel {
  String LOGIN_FAILURE_MESSAGE = "¡No te encuentras logueado!";
  String QUESTION_SUCCESS_MESSAGE = "La pregunta ha sido votada exitosamente.";
  String ANSWER_SUCCESS_MESSAGE = "La respuesta ha sido votada exitosamente.";
  String QUESTION_FAILURE_MESSAGE = "La pregunta no ha podido ser votada. Inténtalo nuevamente.";
  String ANSWER_FAILURE_MESSAGE = "La respuesta no ha podido ser votada. Inténtalo nuevamente.";
  User loggedUser;
  JTable questionsTable;
  Question filteredQuestion;
  Answer filteredAnswer;
  JTable answersTable;
  JScrollPane tableSp;

  public VoteQuestionView() {
    loggedUser = Main.currentStack.getLoggedUser();
    buildView();
  }

  /**
   * Método de instancia privado que permite construir los componentes del panel
   */
  private void buildView() {
    buildGrid();
    buildQuestionsTable();
  }

  /**
   * Método de instancia privado que construye la grilla principal
   */
  private void buildGrid() {
    setLayout(new GridLayout(3,1,1,1));
    setBounds(300, 90, 1300, 800);
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
   * Método de instancia privado que construye la tabla de respuestas con el fin de aceptar una de ellas
   * @param questionId Id de pregunta de la que se aceptará una de sus respuestas
   */
  private void buildAnswersTable(int questionId) {
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
            "¿Quieres votar por esta respuesta?", "Votar respuesta",
            JOptionPane.YES_NO_OPTION);

          if (confirmed == JOptionPane.YES_OPTION) {
            VoteDialog voteDialog = new VoteDialog();
            voteDialog.setModal(true);
            String typeOfVote = voteDialog.getSelectedOption();
            if (typeOfVote != null) {
              createVote(filteredAnswer, typeOfVote);
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
   * Método de instancia privado que construye tabla de preguntas disponibles y seleccionables para votar
   */
  private void buildQuestionsTable() {
    QuestionsModelTable questionsModelTable = new QuestionsModelTable(Main.currentStack.filterQuestionsForVoting(loggedUser));
    questionsTable = new JTable();
    questionsTable.setModel(questionsModelTable);
    setTableStyles(questionsTable);
    questionsTable.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        int row = questionsTable.rowAtPoint(e.getPoint());
        int col = questionsTable.columnAtPoint(e.getPoint());
        if (row >= 0 && col >= 0) {
          int questionId = (int) questionsTable.getValueAt(row, 0);
          filteredQuestion = Main.currentStack.filterQuestionById(questionId);
          buildAnswersTable(questionId);
          int confirmed = JOptionPane.showConfirmDialog(null,
            "¿Quieres votar por esta pregunta?", "Votar por pregunta",
            JOptionPane.YES_NO_OPTION);

          if (confirmed == JOptionPane.YES_OPTION) {
            VoteDialog voteDialog = new VoteDialog();
            voteDialog.setModal(true);
            String typeOfVote = voteDialog.getSelectedOption();
            if (typeOfVote != null) {
              createVote(filteredQuestion, typeOfVote);
            }
          }
          revalidate();
          repaint();
        }
      }
    });
    JScrollPane sp = new JScrollPane(questionsTable);
    sp.setBorder(BorderFactory.createTitledBorder(null, "Preguntas", TitledBorder.CENTER,
      TitledBorder.TOP, new Font("Monospaced", Font.PLAIN, 22)));
    sp.setPreferredSize(new Dimension(500, 300));
    add(sp, BorderLayout.EAST);
  }

  /**
   * Método de instancia privado que verifica si un usuario se encuentra logueado
   * @return Booleano que hace validación client side si el usuario no se encuentra logueado
   */
  private boolean invalidLoggedUser() {
    if (Main.currentStack.getLoggedUser() == null) {
      JOptionPane.showMessageDialog(null, LOGIN_FAILURE_MESSAGE, null, JOptionPane.WARNING_MESSAGE);
      return true;
    }
    return false;
  }

  /**
   * Método de instancia privado que realiza votación por una pregunta
   * @param filteredQuestion Pregunta por la cual se desea votar.
   * @param typeOfVote Tipo de voto que se realizará.
   */
  private void createVote(Question filteredQuestion, String typeOfVote) {
    if (invalidLoggedUser()) return;
    boolean voteSuccess = Main.currentStack.vote(filteredQuestion, typeOfVote);
    if (voteSuccess) {
      JOptionPane.showMessageDialog(null, QUESTION_SUCCESS_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, QUESTION_FAILURE_MESSAGE);
    }
  }

  /**
   * Método de instancia privado (sobrecarga) que realiza votación por una respuesta
   * @param filteredAnswer Respuesta por la cual se desea votar.
   * @param typeOfVote Tipo de voto que se realizará.
   */
  private void createVote(Answer filteredAnswer, String typeOfVote) {
    if (invalidLoggedUser()) return;
    boolean voteSuccess = Main.currentStack.vote(filteredAnswer, typeOfVote);
    if (voteSuccess) {
      JOptionPane.showMessageDialog(null, ANSWER_SUCCESS_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, ANSWER_FAILURE_MESSAGE);
    }
  }
}
