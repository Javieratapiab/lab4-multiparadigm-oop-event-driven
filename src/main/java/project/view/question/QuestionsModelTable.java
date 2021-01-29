package project.view.question;

import project.model.Question;
import project.view.Main;
import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Clase que hereda de la clase abstracta Table model y permite la creación
 * de una tabla referenciada a un modelo u clase.
 */
public class QuestionsModelTable extends AbstractTableModel {
  private final List<Question> questions;
  private final String[] cols = {
          "ID",
          "Autor",
          "Título",
          "Contenido",
          "Fecha de publicación",
          "Votos", "Estado",
          "Respuestas",
          "Etiquetas"
  };

  /**
   * Constructor de ModelTable del modelo Question
   */
  public QuestionsModelTable() {
    questions = Main.currentStack.getQuestions();
  }

  public QuestionsModelTable(List<Question> filteredQuestions) {
    questions = filteredQuestions;
  }

  /**
   * Método de instancia público (sobreescritura) que retorna el número de filas del ModelTable
   * @return Entero que representa el número de filas de la tabla
   */
  @Override
  public int getRowCount() {
    return questions.size();
  }

  /**
   * Método de instancia público (sobreescritura) que retorna el largo de columnas del ModelTable
   * @return Entero que representa el número de columnas de la tabla
   */
  @Override
  public int getColumnCount() {
    return cols.length;
  }

  /**
   * Método de instancia público (sobreescritura) que permite acceder al nombre de
   * una columna del ModelTable a través un índice.
   * @param col Índice de columnas
   * @return Nombre de columna
   */
  @Override
  public String getColumnName(int col) {
    return cols[col];
  }

  /**
   * Método de instancia público que permite realizar ordenamiento ascendente por fecha de publicación
   */
  public void sortByPublicationDateAsc() {
    questions.sort(Comparator.comparing(q -> q.getPublicationDate()));
  }

  /**
   * Método de instancia público que permite realizar ordenamiento descedente por fecha de publicación
   */
  public void sortByPublicationDateDesc() {
    questions.sort(Comparator.comparing(question -> question.getPublicationDate()));
    Collections.reverse(questions);
  }

  /**
   * Método de instancia que permite obtener el valor de un elemento en la tabla a partir de la columna y fila
   * en la que se encuentre posicionado.
   * @param rowIndex Índice de fila
   * @param columnIndex Indica de columna
   * @return Objeto ubicado en la fila y columna dadas
   */
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    if (columnIndex == 0) {
      return questions.get(rowIndex).getId();
    } else if (columnIndex == 1) {
      return questions.get(rowIndex).getAuthor().getName();
    } else if (columnIndex == 2) {
      return questions.get(rowIndex).getTitle();
    } else if (columnIndex == 3) {
      return questions.get(rowIndex).getContent();
    } else if (columnIndex == 4) {
      return questions.get(rowIndex).publicationDateFormat();
    } else if (columnIndex == 5) {
      return questions.get(rowIndex).getVotes();
    } else if (columnIndex == 6) {
      return questions.get(rowIndex).getStatus();
    } else if (columnIndex == 7) {
      return questions.get(rowIndex).getAnswers().size();
    } else if (columnIndex == 8) {
      return questions.get(rowIndex).getLabels().size();
    }
    return null;
  }
}
