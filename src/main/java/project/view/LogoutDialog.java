
package project.view;

// Imports
import javax.swing.*;

/**
 * Clase que genera un JDialog al desloguear un usuario
 */
class LogoutDialog extends JDialog {
  String SUCCESS_MESSAGE = "El usuario ha sido deslogueado exitosamente.";
  String FAILURE_MESSAGE = "El usuario no ha podido ser deslogueado. Inténtalo nuevamente.";

  /**
   * Constructor de diálogo (modal) de Logout
   */
  public LogoutDialog() {
    boolean logoutSuccess = Main.currentStack.logout();
    if (logoutSuccess) {
      JOptionPane.showMessageDialog(null, SUCCESS_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, FAILURE_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
    }
  }
}
