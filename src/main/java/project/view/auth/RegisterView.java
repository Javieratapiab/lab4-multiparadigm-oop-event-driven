
package project.view.auth;

import project.view.Main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que genera un JPanel con un form de register embebido
 */
public class RegisterView extends JPanel {
  String SUCCESS_MESSAGE = "El usuario ha sido registrado exitosamente.";
  String FAILURE_MESSAGE = "El usuario que tratas de registrar ya existe";
  JTextField usernameField = new JTextField();
  JTextField passwordField = new JTextField();
  GridBagConstraints gbc = new GridBagConstraints();

  /**
   * Constructor de la vista (JPanel) de RegisterView
   */
  public RegisterView() {
    buildGrid();
    buildRegisterForm();
    buildRegisterButton();
  }

  /**
   * Método de instancia privado que genera la grilla del JPanel register
   */
  private void buildGrid() {
    setLayout(new GridBagLayout());
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    setBounds(300, 90, 500, 300);
  }

  /**
   * Método de instancia privado que construye el formulario de register
   */
  private void buildRegisterForm() {
    JLabel title = new JLabel("Registro de usuario");
    title.setFont(new Font(null, Font.BOLD, 15));
    title.setSize(300, 30);
    Border border = title.getBorder();
    Border margin = new EmptyBorder(0,0,30,0);
    title.setBorder(new CompoundBorder(border, margin));
    add(title, gbc);

    // Username
    JLabel usernameLabel = new JLabel("Username");
    usernameField.setPreferredSize(new Dimension(300, 35));
    add(usernameLabel, gbc);
    add(usernameField, gbc);
    // Password
    JLabel passwordLabel = new JLabel("Password");
    passwordField.setPreferredSize(new Dimension(300, 35));
    add(passwordLabel, gbc);
    add(passwordField, gbc);
  }

  /**
   * Método de instancia privado que genera un JButton de register, agregando un listener
   */
  private void buildRegisterButton() {
    JButton registerButton = new JButton("Registrarse");
    registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setRegisterAction();
      }
    });
    add(registerButton, gbc);
  }

  /**
   * Método de instancia privado que registra un usuario con credenciales,
   * muestra diálogos de error o éxito a partir del resultado de register
   */
  private void setRegisterAction() {
    String username = usernameField.getText();
    String password = passwordField.getText();
    boolean registerSuccess = Main.currentStack.register(username, password);
    if (registerSuccess) {
      JOptionPane.showMessageDialog(null, SUCCESS_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, FAILURE_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
    }
  }
}
