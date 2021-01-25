package project.view;

// IMPORTS
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase pública que representa la vista principal del programa.
 */
public class MainView {
  // UI components
  private JFrame frame;
  private JPanel panelContainer = new JPanel();
  private JPanel mainPanel = new JPanel();
  JPanel loginPanel = new JPanel();
  JPanel registerPanel = new JPanel();
  JPanel newQuestionPanel = new JPanel();
  JPanel questionDetailPanel = new JPanel();
  JPanel userQuestionsPanel = new JPanel();
  JButton backButton = new JButton("Volver");

  // Constantes
  String NOT_LOGGED_MESSAGE = "No puedes ir a esta vista, debes loguearte. Inténtalo nuevamente";

  // Variables
  QuestionsModelTable questionsModel;
  JTable table;
  CardLayout cl;

  /**
   * Constructor de la vista principal
   */
  public MainView() {
    frame = new JFrame("Stackoverflow");
    cl = new CardLayout();
    panelContainer.setLayout(cl);
    panelContainer.setPreferredSize(new Dimension(1200, 800));
    mainPanel.setPreferredSize(new Dimension(1200, 800));
    buildMainPanel();
    panelContainer.add(mainPanel, "2");
    cl.show(panelContainer, "1");
    buildBackButton();
    buildJFrame();
  }

  /**
   * Método de instacia privado que renderiza mensaje de bienvenida al usuario (Guest o usuario logueado)
   */
  private void setWelcomeMessage() {
    JPanel welcomePanel = new JPanel();
    if (Main.currentStack.getLoggedUser() != null) {
      JLabel title = new JLabel("Bienvenido: " + Main.currentStack.getLoggedUser().getName());
      title.setFont(new Font("Merlo", Font.BOLD, 25));
      welcomePanel.add(title, BorderLayout.CENTER);
    } else {
      JLabel title = new JLabel("Bienvenido Guest");
      title.setFont(new Font("Merlo", Font.BOLD, 25));
      welcomePanel.add(title, BorderLayout.CENTER);
    }
    welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    mainPanel.add(welcomePanel, BorderLayout.LINE_START);
  }

  /**
   * Método de instancia privado que construye el JFrame principal (vista principal)
   */
  private void buildJFrame() {
    frame.add(panelContainer);
    frame.setTitle("StackOverflow");
    frame.setBounds(300, 90, 1200, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  /**
   * Método de instancia privado que permite reconstruir la vista principal (refresh)
   */
  private void rebuildMainPanel() {
    cl.removeLayoutComponent(mainPanel);
    mainPanel = new JPanel();
    buildMainPanel();
    panelContainer.add(mainPanel, "2");
    cl.show(panelContainer, "2");
  }

  /**
   * Método de instancia privado que crea un botón de login
   * @return Retorna JButton de Login, se agrega listener para mostrar panel de login
   */
  private JButton setLoginButton() {
    JButton loginButton = new JButton("Login");
    loginButton.setFont(new Font("Merlo", Font.BOLD, 15));
    loginButton.setSize(new Dimension(60, 50));
    loginButton.setBackground(Color.green);
    loginButton.setBorderPainted(false);
    loginButton.setOpaque(true);
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        LoginView login = new LoginView();
        loginPanel = login;
        loginPanel.add(backButton);
        panelContainer.add(loginPanel, "3");
        cl.show(panelContainer, "3");
      }
    });
    return loginButton;
  }

  /**
   * Método de instancia privado que crea un botón de registro
   * @return Retorna JButton de Register, se agrega listener para mostrar panel de registro
   */
  private JButton setRegisterButton() {
    JButton registerButton = new JButton("Registrarse");
    registerButton.setFont(new Font("Merlo", Font.PLAIN, 16));
    registerButton.setSize(new Dimension(80, 50));
    registerButton.setBackground(Color.LIGHT_GRAY);
    registerButton.setBorderPainted(false);
    registerButton.setOpaque(true);
    registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        RegisterView register = new RegisterView();
        registerPanel = register;
        registerPanel.add(backButton);
        panelContainer.add(registerPanel, "4");
        cl.show(panelContainer, "4");
      }
    });

    return registerButton;
  }

  /**
   * Método de instancia privado que crea un botón de logout
   * @return Retorna JButton de Logout, se agrega listener para mostrar diálogo (modal) para desloguearse
   */
  private JButton setLogoutButton() {
    JButton logoutButton = new JButton("Logout");
    logoutButton.setFont(new Font("Merlo", Font.PLAIN, 16));
    logoutButton.setSize(new Dimension(100, 50));
    logoutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        LogoutDialog logout = new LogoutDialog();
        logout.setModal(true);
        rebuildMainPanel();
      }
    });

    return logoutButton;
  }


  /**
   * Método de instancia privado que crea un botón de aceptar
   * @return Retorna JButton de Accept, se agrega listener para mostrar panel de preguntas.
   */
  private JButton setAcceptButton() {
    JButton acceptButton = new JButton("Aceptar pregunta");
    acceptButton.setFont(new Font("Merlo", Font.PLAIN, 16));
    acceptButton.setSize(new Dimension(100, 50));
    acceptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (Main.currentStack.getLoggedUser() == null) {
          JOptionPane.showMessageDialog(null, NOT_LOGGED_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
          return;
        } else {
          UserQuestionsDetailView userQuestionsView = new UserQuestionsDetailView();
          userQuestionsPanel = userQuestionsView;
          userQuestionsPanel.add(backButton, BorderLayout.AFTER_LAST_LINE);
          panelContainer.add(userQuestionsPanel, "7");
          cl.show(panelContainer, "7");
        }
      }
    });

    return acceptButton;
  }

  /**
   * Método de instancia privado que crea un botón de nueva pregunta
   * @return Retorna JButton de Nueva pregunta, se agrega listener para mostrar panel (form) de una nueva pregunta
   */
  private JButton setNewQuestionButton() {
    JButton newQuestionButton = new JButton("Nueva pregunta (+)");
    newQuestionButton.setFont(new Font("Merlo", Font.BOLD, 15));
    newQuestionButton.setSize(new Dimension(80, 50));
    newQuestionButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (Main.currentStack.getLoggedUser() == null) {
          JOptionPane.showMessageDialog(null, NOT_LOGGED_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
          return;
        } else {
          NewQuestionView newQuestionView = new NewQuestionView();
          newQuestionPanel = newQuestionView;
          newQuestionPanel.add(backButton);
          panelContainer.add(newQuestionPanel, "5");
          cl.show(panelContainer, "5");
        }
      }
    });
    return newQuestionButton;
  }

  /**
   * Método de instancia privado que construye secciones de la vista principales
   */
  private void buildMainPanel() {
    mainPanel.setLayout(new GridLayout(3, 2));
    setWelcomeMessage();
    setAuthPanel();
    setQuestionsTable();
  }

  /**
   * Método de instancia privado que setea el botón Volver
   */
  private void buildBackButton() {
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        rebuildMainPanel();
      }
    });
  }

  /**
   * Método de instancia privado que construye la sección de botones para usuario logueado y no logueado
   */
  private void setAuthPanel() {
    JPanel tempPanel = new JPanel();
    tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
    if (Main.currentStack.getLoggedUser() != null) {
      JButton logoutButton = setLogoutButton();
      tempPanel.add(logoutButton, BorderLayout.WEST);
      JButton acceptButton = setAcceptButton();
      tempPanel.add(acceptButton, BorderLayout.WEST);
      JButton newQuestionButton = setNewQuestionButton();
      tempPanel.add(newQuestionButton, BorderLayout.WEST);
    } else {
      JButton loginButton = setLoginButton();
      JButton registerButton = setRegisterButton();
      loginButton.setSize(30, 60);
      registerButton.setSize(80, 60);
      tempPanel.add(loginButton, BorderLayout.WEST);
      tempPanel.add(registerButton, BorderLayout.WEST);
    }
    tempPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    mainPanel.add(tempPanel, BorderLayout.WEST);
  }

  /**
   * Método de instancia privado que construye la tabla de preguntas de la vista principal
   */
  private void setQuestionsTable() {
    questionsModel = new QuestionsModelTable();
    table = new JTable();
    table.setModel(questionsModel);
    table.setFont(new Font("Merlo",Font.PLAIN,14));
    table.setBounds(30, 250, 1300, 700);
    table.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        int col = table.columnAtPoint(e.getPoint());
        if (row >= 0 && col >= 0) {
          int questionId = (int) table.getValueAt(row, 0); // Id de pregunta
          QuestionDetailView questionDetailView = new QuestionDetailView(questionId);
          questionDetailPanel = questionDetailView;
          questionDetailPanel.add(backButton);
          panelContainer.add(questionDetailPanel, "6");
          cl.show(panelContainer, "6");
        }
      }
    });
    JScrollPane sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(800, 800));
    mainPanel.add(sp, BorderLayout.EAST);
  }
}
