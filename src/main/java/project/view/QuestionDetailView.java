
package project.view;

import project.model.Label;
import project.model.Question;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class QuestionDetailView extends JPanel {
  // Componentes
  JTextField answerContentField = new JTextField();
  GridBagConstraints gbc = new GridBagConstraints();
  QuestionsModelTable questionsModel;
  JTable table;

  // Variables
  Question selectedQuestion;

  // Constantes
  String SUCCESS_MESSAGE = "La respuesta ha sido creada exitosamente.";
  String LOGIN_FAILURE_MESSAGE = "¡No te encuentras logueado!";
  String FAILURE_MESSAGE = "La respuesta no ha podido ser creada. Inténtalo nuevamente.";

  public QuestionDetailView(int questionId) {
    for(Question question : Main.currentStack.getQuestions()) {
      if (questionId == question.getId()) {
        selectedQuestion = question;
        break;
      }
    }
    buildGrid();
    buildQuestionDetail();
    buildAnswersTable();
    buildNewAnswerPanel();
    setBackground(Color.white);
  }

  private void buildGrid() {
    setLayout(new GridLayout(4, 1));
    setBounds(300, 90, 1300, 700);
  }

  private void buildAnswersTable() {
    AnswersModelTable answersModel = new AnswersModelTable(selectedQuestion);
    table = new JTable();
    table.setModel(answersModel);
    table.setFont(new Font("Merlo",Font.PLAIN,14));
    table.setBounds(30, 250, 1300, 700);
    Border answerBorder = table.getBorder();
    Border answerMargin = new EmptyBorder(20,20,10,20);
    table.setBorder(new CompoundBorder(answerBorder, answerMargin));
    table.setBorder(new CompoundBorder(answerBorder, answerMargin));
    JScrollPane sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(800, 800));
    add(sp, BorderLayout.CENTER);
  }

  private void buildNewAnswerPanel() {
    JPanel answerPanel = new JPanel();
    JLabel title = new JLabel("Nueva respuesta");
    title.setFont(new Font("Merlo", Font.BOLD, 15));
    title.setSize(20, 30);
    Border border = title.getBorder();
    Border margin = new EmptyBorder(0,0,10,0);
    title.setBorder(new CompoundBorder(border, margin));
    answerPanel.add(title);

    // Content
    JLabel contentLabel = new JLabel("Tu respuesta");
    contentLabel.setPreferredSize(new Dimension(30, 15));
    answerContentField.setPreferredSize(new Dimension(30, 15));
    answerPanel.add(contentLabel);
    answerPanel.add(answerContentField);

    // Answer button
    JButton newAnswerButton = new JButton("Crear respuesta");
    newAnswerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setAnswerAction();
      }
    });

    answerPanel.add(newAnswerButton);
    answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
    Border answerBorder = answerPanel.getBorder();
    Border answerMargin = new EmptyBorder(20,20,10,20);
    answerPanel.setBorder(new CompoundBorder(answerBorder, answerMargin));
    answerPanel.setBorder(new CompoundBorder(answerBorder, answerMargin));
    add(answerPanel, BorderLayout.CENTER);
  }

  private void buildQuestionDetail() {
    // Crea componentes para pregunta
    JPanel questionDetailPanel = new JPanel();
    JPanel labelsPills = new JPanel();
    JLabel questionTitle = new JLabel(selectedQuestion.getTitle());
    JLabel questionAuthor = new JLabel(selectedQuestion.getAuthor().getName());
    JLabel questionPublicationDate = new JLabel(selectedQuestion.publicationDateFormat());
    JLabel questionContent = new JLabel(selectedQuestion.getContent());

    /*labelsPills.setOpaque(false);*/
    for(Label label : selectedQuestion.getLabels()) {
        JButton labelButton = new JButton(label.getName());
        labelButton.setFont(new Font("Consolas", Font.PLAIN, 20));
        labelButton.setContentAreaFilled(false);
        labelButton.setOpaque(false);
        labelButton.setForeground(Color.magenta);
        labelButton.setBackground(Color.lightGray);
        labelsPills.add(labelButton);
    }

    // Define estilos para textos
    questionTitle.setFont(new Font("Merlo", Font.PLAIN, 30));
    questionAuthor.setFont(new Font("Merlo", Font.ITALIC, 20));
    questionPublicationDate.setFont(new Font("Merlo", Font.PLAIN, 20));
    questionContent.setFont(new Font("Merlo", Font.PLAIN, 20));

    // Agrega componentes a panel de detalle de pregunta
    questionDetailPanel.add(questionTitle);
    questionDetailPanel.add(questionAuthor);
    questionDetailPanel.add(questionPublicationDate);
    questionDetailPanel.add(questionContent);
    questionDetailPanel.add(labelsPills);
    Border border = questionDetailPanel.getBorder();
    Border margin = new EmptyBorder(20,20,10,20);
    questionDetailPanel.setBorder(new CompoundBorder(border, margin));
    questionDetailPanel.setLayout(new GridLayout(5, 1));
    add(questionDetailPanel, BorderLayout.CENTER);
  }

  private void setAnswerAction() {
    String content = answerContentField.getText();
    if (Main.currentStack.getLoggedUser() == null) {
      JOptionPane.showMessageDialog(null, LOGIN_FAILURE_MESSAGE, null, JOptionPane.WARNING_MESSAGE);
      return;
    }

    boolean answerSuccess = Main.currentStack.answer(selectedQuestion, content);

    if (answerSuccess) {
      JOptionPane.showMessageDialog(null, SUCCESS_MESSAGE);
      buildAnswersTable();
    } else {
      JOptionPane.showMessageDialog(null, FAILURE_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
    }
  }
}