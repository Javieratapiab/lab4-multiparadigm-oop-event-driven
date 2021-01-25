
package project.view;

import project.model.Answer;
import project.model.Question;
import project.model.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class UserQuestionsDetailView extends JPanel {
  // Variables
  Question filteredQuestion;
  Answer filteredAnswer;
  User loggedUser;

  // Componentes
  JTable questionsTable;
  JTable answersTable;

  // Constantes
  String SUCCESS_MESSAGE = "La respuesta ha sido aceptada exitosamente.";
  String LOGIN_FAILURE_MESSAGE = "¡No te encuentras logueado!";
  String FAILURE_MESSAGE = "La respuesta no ha podido ser aceptada. Inténtalo nuevamente.";

  public UserQuestionsDetailView() {
    loggedUser = Main.currentStack.getLoggedUser();
    buildView();
    setBackground(Color.white);
  }

  private void buildView() {
    buildGrid();
    buildQuestionsTable();
  }

  private void buildGrid() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBounds(300, 90, 1400, 700);
  }

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

    JScrollPane tableSp = new JScrollPane(answersTable);
    tableSp.setPreferredSize(new Dimension(400, 400));
    add(tableSp, BorderLayout.WEST);
  }

  private void setTableStyles(JTable table) {
    table.setFont(new Font("Merlo",Font.PLAIN,14));
    table.setBounds(30, 250, 1300, 700);
    Border border = table.getBorder();
    Border margin = new EmptyBorder(20,20,10,20);
    table.setBorder(new CompoundBorder(border, margin));
    table.setBorder(new CompoundBorder(border, margin));
  }

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
          if (answersTable == null) {
            buildAnswersTable(questionId);
          }
          revalidate();
          repaint();
        }
      }
    });
    JScrollPane sp = new JScrollPane(questionsTable);
    sp.setPreferredSize(new Dimension(500, 300));
    add(sp, BorderLayout.EAST);
  }
}
