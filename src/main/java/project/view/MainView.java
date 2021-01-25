package project.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {
  // UI components
  private JFrame frame;
  private JPanel panelContainer = new JPanel();
  private JPanel mainPanel = new JPanel();
  JPanel loginPanel = new JPanel();
  JPanel registerPanel = new JPanel();
  JDialog logoutDialog = new JDialog();
  JButton backButton = new JButton("Volver");

  // Card layout
  CardLayout cl;

  // Variables
  QuestionsModelTable questionsModel;
  JTable table;

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

  public void setWelcomeMessage() {
    JPanel welcomePanel = new JPanel();
    if (Main.currentStack.getLoggedUser() != null) {
      JLabel title = new JLabel("Bienvenido: " + Main.currentStack.getLoggedUser().getName());
      title.setFont(new Font("Inconsolata", Font.BOLD, 25));
      welcomePanel.add(title, BorderLayout.CENTER);
    } else {
      JLabel title = new JLabel("Bienvenido Usuario Guest ");
      title.setFont(new Font("Inconsolata", Font.BOLD, 25));
      welcomePanel.add(title, BorderLayout.CENTER);
    }
    welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    welcomePanel.setBackground(Color.white);
    mainPanel.add(welcomePanel, BorderLayout.LINE_START);
  }

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

  private JButton setLoginButton() {
    JButton loginButton = new JButton("Login");
    loginButton.setFont(new Font("Merlo", Font.BOLD, 15));
    loginButton.setSize(new Dimension(80, 50));
    loginButton.setBackground(Color.blue);
    loginButton.setOpaque(false);
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

  private JButton setRegisterButton() {
    JButton registerButton = new JButton("Registrarse");
    registerButton.setFont(new Font("Merlo", Font.PLAIN, 16));
    registerButton.setSize(new Dimension(100, 50));
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

  private JButton setLogoutButton() {
    JButton logoutButton = new JButton("Logout");
    logoutButton.setFont(new Font("Merlo", Font.PLAIN, 16));
    logoutButton.setSize(new Dimension(100, 50));
    logoutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        LogoutDialog logout = new LogoutDialog();

        logoutPanel = logout;
        logoutPanel.add(backButton);
        panelContainer.add(logoutPanel, "5");
        cl.show(panelContainer, "5");
      }
    });

    return logoutButton;
  }

  public void buildMainPanel() {
    mainPanel.setLayout(new GridLayout(3, 2));
    setWelcomeMessage();
    setAuthPanel();
    setQuestionsTable();
  }

  public void buildBackButton() {
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        cl.removeLayoutComponent(mainPanel);
        mainPanel = new JPanel();
        buildMainPanel();
        panelContainer.add(mainPanel, "2");
        cl.show(panelContainer, "2");
      }
    });
  }

  public void setAuthPanel() {
    JPanel tempPanel = new JPanel();
    tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
    if (Main.currentStack.getLoggedUser() != null) {
      JButton logoutButton = setLogoutButton();
      tempPanel.add(logoutButton, BorderLayout.WEST);
    } else {
      JButton loginButton = setLoginButton();
      JButton registerButton = setRegisterButton();
      loginButton.setSize(30, 60);
      registerButton.setSize(80, 60);
      tempPanel.add(loginButton, BorderLayout.WEST);
      tempPanel.add(registerButton, BorderLayout.WEST);
    }
    tempPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    tempPanel.setBackground(Color.white);
    mainPanel.add(tempPanel, BorderLayout.WEST);
  }

  public void setQuestionsTable() {
    questionsModel = new QuestionsModelTable();
    table = new JTable();
    table.setModel(questionsModel);
    table.setFont(new Font("Merlo",Font.PLAIN,14));
    table.setBounds(30, 250, 1300, 700);
    JScrollPane sp = new JScrollPane(table);
    sp.setPreferredSize(new Dimension(800, 800));
    mainPanel.add(sp, BorderLayout.EAST);
  }
}