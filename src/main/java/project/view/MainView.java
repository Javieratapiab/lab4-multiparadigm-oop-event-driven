package project.view;

import project.view.auth.LoginView;
import project.view.auth.LogoutDialog;
import project.view.auth.RegisterView;
import project.view.question.NewQuestionView;
import project.view.question.QuestionDetailView;
import project.view.question.QuestionsModelTable;
import project.view.question.VoteQuestionView;
import project.view.user.UserQuestionsDetailView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.Objects;

/**
 * Clase que representa la vista principal del programa
 */
public class MainView {
  private final String NOT_LOGGED_MESSAGE = "No puedes ir a esta vista, debes loguearte. Inténtalo nuevamente";
  private final CardLayout cardLayout = new CardLayout();
  private final JFrame frame = new JFrame("Stackoverflow");
  private final JPanel panelContainer = new JPanel();
  private JPanel mainPanel = new JPanel();
  private JPanel mainSection = new JPanel();
  private JPanel loginPanel = new JPanel();
  private JPanel registerPanel = new JPanel();
  private JPanel newQuestionPanel = new JPanel();
  private JPanel questionDetailPanel = new JPanel();
  private JPanel userQuestionsPanel = new JPanel();
  private JPanel voteQuestionPanel = new JPanel();

  /**
   * Constructor de la vista principal
   */
  public MainView() {
    buildPanelContainer();
    buildMainPanel();
    buildBackButton();
    buildJFrame();
    showNextPanel("1");
  }

  /**
   * Método de instancia privado que construye el panel contenedor
   */
  private void buildPanelContainer() {
    panelContainer.setLayout(cardLayout);
    panelContainer.setPreferredSize(new Dimension(1200, 800));
    panelContainer.add(mainPanel, "2");
  }

  /**
   * Método de instancia privado que construye secciones de la vista principales
   */
  private void buildMainPanel() {
    mainPanel.setLayout(new GridLayout(2, 1));
    buildMainSection();
    buildQuestionsTable();
  }

  /**
   * Método de instancia privado que setea el botón Volver
   */
  private JButton buildBackButton() {
    ImageIcon backIcon = new ImageIcon(this.getClass().getResource("/return.png"));
    JButton backButton = new JButton("Volver", backIcon);
    backButton.addActionListener(e -> rebuildMainPanel());
    backButton.setSize(100, 30);
    return backButton;
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
   * Método de instancia privado que muestra el siguiente panel del panel contenedor
   *
   * @param panelId String que representa el panel siguiente a mostrar del panel contenedor
   */
  private void showNextPanel(String panelId) {
    cardLayout.show(panelContainer, panelId);
  }

  /**
   * Método de instancia privado que permite reconstruir la vista principal
   */
  private void rebuildMainPanel() {
    cardLayout.removeLayoutComponent(mainPanel);
    mainPanel = new JPanel();
    buildMainPanel();
    panelContainer.add(mainPanel, "2");
    showNextPanel("2");
  }

  /**
   * Método de instancia privado que crea un botón de login
   *
   * @return Retorna JButton de Login, se agrega listener para mostrar panel de login
   */
  private JButton createLoginButton() {
    ImageIcon iconLogin = new ImageIcon(this.getClass().getResource("/login.png"));
    JButton loginButton = new JButton("Login", iconLogin);
    loginButton.setFont(new Font(null, Font.PLAIN, 16));
    loginButton.addActionListener(e -> {
      loginPanel = new LoginView();
      loginPanel.add(buildBackButton());
      panelContainer.add(loginPanel, "3");
      showNextPanel("3");
    });
    return loginButton;
  }

  /**
   * Método de instancia privado que crea un botón de registro
   *
   * @return Retorna JButton de Register, se agrega listener para mostrar panel de registro
   */
  private JButton createRegisterButton() {
    ImageIcon iconRegister = new ImageIcon(this.getClass().getResource("/add-user.png"));
    JButton registerButton = new JButton("Registrarse", iconRegister);
    registerButton.setFont(new Font(null, Font.PLAIN, 16));
    registerButton.addActionListener(e -> {
      registerPanel = new RegisterView();
      registerPanel.add(buildBackButton());
      panelContainer.add(registerPanel, "4");
      showNextPanel("4");
    });
    return registerButton;
  }

  /**
   * Método de instancia privado que crea un botón de logout
   *
   * @return Retorna JButton de Logout, se agrega listener para mostrar diálogo (modal) para desloguearse
   */
  private JButton createLogoutButton() {
    ImageIcon iconLogout = new ImageIcon(this.getClass().getResource("/logout.png"));
    JButton logoutButton = new JButton("Logout", iconLogout);
    logoutButton.setFont(new Font(null, Font.PLAIN, 16));
    logoutButton.addActionListener(e -> {
      LogoutDialog logout = new LogoutDialog();
      logout.setModal(true);
      rebuildMainPanel();
    });
    return logoutButton;
  }

  /**
   * Método de instancia privado que crea un botón de aceptar
   *
   * @return Retorna JButton de Accept, se agrega listener para mostrar panel de preguntas.
   */
  private JButton setAcceptButton() {
    ImageIcon acceptIcon = new ImageIcon(this.getClass().getResource("/check-icon.png"));
    JButton acceptButton = new JButton("Aceptar respuesta", acceptIcon);
    acceptButton.setFont(new Font(null, Font.PLAIN, 16));
    acceptButton.addActionListener(e -> {
      if (Main.currentStack.getLoggedUser() == null) {
        JOptionPane.showMessageDialog(null, NOT_LOGGED_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
      } else {
        userQuestionsPanel = new UserQuestionsDetailView();
        userQuestionsPanel.add(buildBackButton());
        panelContainer.add(userQuestionsPanel, "7");
        showNextPanel("7");
      }
    });
    return acceptButton;
  }

  /**
   * Método de instancia privado que crea un botón de nueva pregunta
   *
   * @return Retorna JButton de Nueva pregunta, se agrega listener para mostrar panel (form) de una nueva pregunta
   */
  private JButton setNewQuestionButton() {
    ImageIcon newQuestionIcon = new ImageIcon(this.getClass().getResource("/new-question.png"));
    JButton newQuestionButton = new JButton("Nueva pregunta", newQuestionIcon);
    newQuestionButton.setFont(new Font(null, Font.PLAIN, 16));
    newQuestionButton.addActionListener(e -> {
      if (Main.currentStack.getLoggedUser() == null) {
        JOptionPane.showMessageDialog(null, NOT_LOGGED_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
      } else {
        newQuestionPanel = new NewQuestionView();
        newQuestionPanel.add(buildBackButton());
        panelContainer.add(newQuestionPanel, "5");
        showNextPanel("5");
      }
    });
    return newQuestionButton;
  }

  /**
   * Método de instancia privado que crea un botón que permite ir a votar
   * @return Botón para realizar votación
   */
  private JButton setVoteButton() {
    ImageIcon voteButtonIcon = new ImageIcon(this.getClass().getResource("/vote-up.png"));
    JButton voteButton = new JButton("Votar", voteButtonIcon);
    voteButton.setFont(new Font(null, Font.PLAIN, 16));
    voteButton.addActionListener(e -> {
      if (Main.currentStack.getLoggedUser() == null) {
        JOptionPane.showMessageDialog(null, NOT_LOGGED_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
      } else {
        voteQuestionPanel = new VoteQuestionView();
        voteQuestionPanel.add(buildBackButton(), 0,0);
        panelContainer.add(voteQuestionPanel, "8");
        showNextPanel("8");
      }
    });
    return voteButton;
  }

  /**
   * Método de instancia privado que crea filtros ASC y DESC para fecha de publicación de preguntas
   * @param questionsModelTable Modelo de tabla de preguntas
   * @return JCombobox de filtros de fecha de publicación
   */
  private JComboBox<String> buildFiltersSection(QuestionsModelTable questionsModelTable) {
    String[] filterOptions = { "ASC", "DESC" };
    JComboBox<String> filtersList = new JComboBox<>(filterOptions);
    filtersList.setSelectedIndex(-1);
    filtersList.addActionListener(e -> {
      String typeOfFilter = Objects.requireNonNull(filtersList.getSelectedItem()).toString();
      switch(typeOfFilter) {
        case "ASC":
          questionsModelTable.sortByPublicationDateAsc();
          questionsModelTable.fireTableDataChanged();
          break;
        case "DESC":
          questionsModelTable.sortByPublicationDateDesc();
          questionsModelTable.fireTableDataChanged();
          break;
      }
    });
    filtersList.setMaximumSize(new Dimension(80, 20));
    filtersList.setAlignmentX(Component.RIGHT_ALIGNMENT);
    return filtersList;
  }

  /**
   * Método de instancia privado que setea estilos para header de tabla de preguntas
   * @param questionsTable Tabla de preguntas
   */
  private void buildQuestionsTableHeader(JTable questionsTable) {
    JTableHeader header = questionsTable.getTableHeader();
    header.setBackground(Color.black);
    header.setForeground(Color.WHITE);
    header.setFont(new Font(null, Font.BOLD, 12));
  }

  /**
   * Método de instancia privado que agrega mouse listener a tabla de preguntas
   * @param questionsTable Tabla de preguntas
   */
  private void buildQuestionsTableEvent(JTable questionsTable) {
    questionsTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        int row = questionsTable.rowAtPoint(e.getPoint());
        int col = questionsTable.columnAtPoint(e.getPoint());
        if (row >= 0 && col >= 0) {
          if (Main.currentStack.getLoggedUser() == null) {
            JOptionPane.showMessageDialog(null, NOT_LOGGED_MESSAGE, null, JOptionPane.ERROR_MESSAGE);
          } else {
            int questionId = (int) questionsTable.getValueAt(row, 0);
            questionDetailPanel = new QuestionDetailView(questionId);
            questionDetailPanel.add(buildBackButton(), BorderLayout.LINE_START);
            panelContainer.add(questionDetailPanel, "6");
            showNextPanel("6");
          }
        }
      }
    });
  }

  /**
   * Método de instancia privado que construye la tabla de preguntas de la vista principal
   */
  private void buildQuestionsTable() {
    QuestionsModelTable questionsModelTable = new QuestionsModelTable();
    JTable questionsTable = new JTable();
    JPanel questionTablePanel = new JPanel();
    questionTablePanel.setLayout(new BoxLayout(questionTablePanel, BoxLayout.Y_AXIS));
    questionsTable.setModel(questionsModelTable);
    buildQuestionsTableEvent(questionsTable);
    buildQuestionsTableHeader(questionsTable);
    JScrollPane sp = new JScrollPane(questionsTable);
    sp.setBorder(BorderFactory.createTitledBorder(null, "Todas las preguntas", TitledBorder.CENTER,
                  TitledBorder.TOP, new Font("Monospaced", Font.PLAIN, 22)));
    questionTablePanel.add(buildFiltersSection(questionsModelTable), BorderLayout.EAST);
    questionTablePanel.add(sp, BorderLayout.CENTER);
    mainPanel.add(questionTablePanel, BorderLayout.CENTER);
  }

  /**
   * Método de instancia privado que construye el título principal con logo
   */
  private JLabel buildMainTitle() {
    ImageIcon stackIcon = new ImageIcon(this.getClass().getResource("/stackoverflow.png"));
    JLabel mainTitle = new JLabel("Stack overflow");
    mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
    mainTitle.setIcon(stackIcon);
    mainTitle.setBorder(new EmptyBorder(0, 0, 80, 0));
    return mainTitle;
  }

  /**
   * Método de instancia privado que construye mensaje de bienvenida a usuario
   * @return JLabel que muestra el mensaje de bienvenida para usuario logueado y guest
   */
  private JLabel buildWelcomeText() {
    JLabel userLabel;
    if (Main.currentStack.getLoggedUser() != null) {
      userLabel = new JLabel("Bienvenido: " + Main.currentStack.getLoggedUser().getName());
    } else {
      userLabel = new JLabel("Bienvenido: GUEST");
    }
    userLabel.setFont(new Font(null, Font.PLAIN, 16));
    userLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
    return userLabel;
  }

  /**
   * Método de instancia privado que construye las acciones de un usuario con sesión activa
   */
  private void buildActionsForLoggedUser() {
    JButton logoutButton = createLogoutButton();
    mainSection.add(logoutButton, BorderLayout.WEST);
    JButton acceptButton = setAcceptButton();
    mainSection.add(Box.createRigidArea(new Dimension(0, 3)));
    mainSection.add(acceptButton, BorderLayout.WEST);
    JButton newQuestionButton = setNewQuestionButton();
    mainSection.add(Box.createRigidArea(new Dimension(0, 3)));
    mainSection.add(newQuestionButton, BorderLayout.WEST);
    JButton voteButton = setVoteButton();
    mainSection.add(Box.createRigidArea(new Dimension(0, 3)));
    mainSection.add(voteButton, BorderLayout.WEST);
  }

  /**
   * Método de instancia privado que construye las acciones de un usuario sin sesión activa
   */
  private void buildActionsForGuestUser() {
    JButton loginButton = createLoginButton();
    JButton registerButton = createRegisterButton();
    mainSection.add(loginButton, BorderLayout.WEST);
    mainSection.add(Box.createRigidArea(new Dimension(0, 5)));
    mainSection.add(registerButton, BorderLayout.WEST);
  }

  /**
   * Método de instancia privado que construye la sección principal del menú principal
   * (Auth, button actions, mensajes de bienvenida, etc).
   */
  private void buildMainSection() {
    mainSection = new JPanel();
    mainSection.setLayout(new BoxLayout(mainSection, BoxLayout.Y_AXIS));
    mainSection.add(buildMainTitle(), BorderLayout.CENTER);
    mainSection.add(buildWelcomeText(), BorderLayout.WEST);
    if (Main.currentStack.getLoggedUser() != null) {
      buildActionsForLoggedUser();
    } else {
      buildActionsForGuestUser();
    }
    mainSection.setBorder(new EmptyBorder(0, 10, 0, 0));
    mainPanel.add(mainSection, BorderLayout.WEST);
  }
}
