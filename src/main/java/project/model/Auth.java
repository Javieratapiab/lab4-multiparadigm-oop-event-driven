package project.model;

/**
 * Interface que maneja la lógica de una abstracción de autenticación
 */
public interface Auth {
  boolean login(String name, String password);
  boolean register(String name, String password);
  boolean logout();
}
