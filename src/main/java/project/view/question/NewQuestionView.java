
package project.view.question;

// IMPORTS
import project.model.Label;
import project.view.Main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que construye un JPanel e implementa la clase ListSelectionListener para seleccionar etiquetas
 */
public class NewQuestionView extends JPanel implements ListSelectionListener {
  String SUCCESS_MESSAGE = "La pregunta ha sido creada exitosamente.";
  String LOGIN_FAILURE_MESSAGE = "¡No te encuentras logueado!";
  String FAILURE_MESSAGE = "La pregunta no ha podido ser creada. Inténtalo nuevamente.";
  JTextField titleField = new JTextField();
  JTextField contentField = new JTextField();
  List<String> labels = new ArrayList<String>();
  JList labelsList;
  GridBagConstraints gbc = new GridBagConstraints();

  /**
   * Constructor de vista para un nueva pregunta
   */
  public NewQuestionView() {
    buildGrid();
    buildNewQuestionForm();
    buildAskButton();
  }

  /**
   * Método de instancia privado que construye la grilla principal del panel
   */
  private void buildGrid() {
    setLayout(new GridBagLayout());
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    setBounds(300, 90, 500, 300);
  }

  /**
   * Método de instancia privado que construye el formulario de pregunta
   */
  private void buildNewQuestionForm() {
    JLabel title = new JLabel("Nueva pregunta");
    title.setFont(new Font("Merlo", Font.BOLD, 15));
    title.setSize(300, 30);
    Border border = title.getBorder();
    Border margin = new EmptyBorder(0,0,30,0);
    title.setBorder(new CompoundBorder(border, margin));
    add(title, gbc);

    // Title
    JLabel titleLabel = new JLabel("Título");
    titleField.setPreferredSize(new Dimension(300, 35));
    add(titleLabel, gbc);
    add(titleField, gbc);

    // Content
    JLabel contentLabel = new JLabel("Contenido");
    contentField.setPreferredSize(new Dimension(300, 35));
    add(contentLabel, gbc);
    add(contentField, gbc);

    // Labels
    buildLabelsList();
  }

  /**
   * Métoodo de instancia privado que construye un selector de etiquetas para seleccionar
   */
  private void buildLabelsList() {
    JLabel labelsLabel = new JLabel("Etiquetas");
    for(Label label: Main.currentStack.getLabels()) {
      labels.add(label.getName());
    }

    labelsList = new JList(labels.toArray());
    labelsList.setVisibleRowCount(labels.size());
    labelsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    add(labelsLabel, gbc);
    add(new JScrollPane(labelsList), gbc);
  }

  /**
   * Método de instancia privado que permite construir el botón de pregunta
   */
  private void buildAskButton() {
    JButton newQuestionButton = new JButton("Crear");
    newQuestionButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setAskAction();
      }
    });
    add(newQuestionButton, gbc);
  }

  /**
   * Método de instancia privado que ejecuta el método ask del stack
   * y muestras mensajes de éxito o fracaso despendiendo de la respuesta desde el modelo
   */
  private void setAskAction() {
    String title = titleField.getText();
    String content = contentField.getText();
    List<Label> selectedLabels = new ArrayList<Label>();

    for(Label label : Main.currentStack.getLabels()) {
      if (labelsList.getSelectedValuesList().contains(label.getName())) {
        selectedLabels.add(label);
      }
    }

    boolean askSuccess = Main.currentStack.ask(title, content, selectedLabels);
    if (Main.currentStack.getLoggedUser() == null) {
      JOptionPane.showMessageDialog(null, LOGIN_FAILURE_MESSAGE, null, JOptionPane.WARNING_MESSAGE);
      return;
    }
    if (askSuccess) {
      JOptionPane.showMessageDialog(null, SUCCESS_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, FAILURE_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // Do something
  }
}
