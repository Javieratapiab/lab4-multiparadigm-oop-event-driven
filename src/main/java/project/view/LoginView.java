
package project.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que genera un JPanel con un form de login embebido
 */
class LoginView extends JPanel {
  String SUCCESS_MESSAGE = "El usuario ha sido logueado exitosamente.";
  String FAILURE_MESSAGE = "El usuario que tratas de loguear no se encuentra registrado";
  JTextField usernameField = new JTextField();
  JTextField passwordField = new JTextField();
  GridBagConstraints gbc = new GridBagConstraints();

  /**
   * Constructor de la vista (JPanel) de LoginView
   */
  public LoginView() {
    buildGrid();
    buildLoginForm();
    buildLoginButton();
    setBackground(Color.white);
  }

  /**
   * Método de instancia privado que genera la grilla del JPanel Login
   */
  private void buildGrid() {
    setLayout(new GridBagLayout());
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    setBounds(300, 90, 500, 300);
  }

  /**
   * Método de instancia privado que construye el formulario de login
   */
  private void buildLoginForm() {
    // Title
    JLabel title = new JLabel("Login de usuario");
    title.setFont(new Font("Merlo", Font.BOLD, 15));
    title.setSize(300, 30);
    Border border = title.getBorder();
    Border margin = new EmptyBorder(0,0,30,0);
    title.setBorder(new CompoundBorder(border, margin));
    add(title, gbc);

    // Username
    JLabel usernameLabel = new JLabel("Username");
    usernameField.setPreferredSize(new Dimension(280, 35));
    add(usernameLabel, gbc);
    add(usernameField, gbc);

    // Password
    JLabel passwordLabel = new JLabel("Password");
    passwordField.setPreferredSize(new Dimension(280, 35));
    add(passwordLabel, gbc);
    add(passwordField, gbc);
  }

  /**
   * Método de instancia privado que genera un JButton de login
   * y le agrega un listener a la acción sobre él.
   */
  private void buildLoginButton() {
    JButton loginButton = new JButton("Login");
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setLoginAction();
      }
    });
    add(loginButton, gbc);
  }

  /**
   * Método de instancia privado que loguea un usuario con credenciales,
   * muestra diálogos de error o éxito a partir del resultado de login
   */
  private void setLoginAction() {
    String username = usernameField.getText();
    String password = passwordField.getText();
    boolean loginSuccess = Main.currentStack.login(username, password);
    if (loginSuccess) {
      JOptionPane.showMessageDialog(null, SUCCESS_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, FAILURE_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
    }
  }
}
