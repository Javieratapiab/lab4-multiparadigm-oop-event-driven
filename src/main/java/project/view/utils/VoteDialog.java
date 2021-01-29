
package project.view.utils;

import javax.swing.*;
import java.util.Enumeration;

/**
 * Clase que genera un JDialog para realizar votación
 */
public class VoteDialog extends JDialog {
  private JPanel votePanel = new JPanel();
  private ButtonGroup checkBoxGroup = new ButtonGroup();
  private String selectedOption;
  /**
   * Constructor de diálogo (modal) para votar
   */
  public VoteDialog() {
    buildCheckboxGroup();
    buildDialog();
  }

  /**
   * Método de instancia público que retorna la opción seleccionada por usuario
   * @return String con nombre de opción seleccionada por usuario
   */
  public String getSelectedOption() {
    return selectedOption;
  }

  /**
   * Método de instancia privado que construye un group de checkboxes para manejar la votación
   * @return ButtonGroup con votación positiva y negativa
   */
  private ButtonGroup buildCheckboxGroup() {
    JRadioButton positiveCheckbox = new JRadioButton("Voto positivo", true);
    JRadioButton negativeCheckbox = new JRadioButton("Voto negativo", false);
    positiveCheckbox.setName("UP");
    negativeCheckbox.setName("DOWN");
    checkBoxGroup.add(positiveCheckbox);
    checkBoxGroup.add(negativeCheckbox);
    votePanel.add(positiveCheckbox);
    votePanel.add(negativeCheckbox);
    return checkBoxGroup;
  }

  /**
   * Método de instancia privado que se encarga de guarda la opción seleccionada por el usuario
   */
  private void buildDialog() {
    int confirmed = JOptionPane.showConfirmDialog(null, votePanel);
    if (confirmed == JOptionPane.YES_OPTION) {
      Enumeration<AbstractButton> buttonGroupElem = checkBoxGroup.getElements();
      while(buttonGroupElem.hasMoreElements()) {
        JRadioButton jrd = (JRadioButton) buttonGroupElem.nextElement();
        if (jrd.isSelected()) {
          selectedOption = jrd.getName();
        }
      }
    }
  }
}
