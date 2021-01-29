package project.view.answer;

// IMPORTS
import project.model.Answer;
import project.model.Question;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Clase que hereda de la clase abstracta Table model y permite la creación
 * de una tabla referenciada a un modelo u clase.
 */
public class AnswersModelTable extends AbstractTableModel {
  private final List<Answer> answers;
  private final String[] cols = {
          "ID",
          "Autor",
          "Fecha de publicación",
          "Estado",
          "Contenido",
          "Votos",

  };

  /**
   * Constructor de ModelTable del modelo Answer
   * @param question Pregunta de la cual se extraerán las respuestas para la creación de table
   */
  public AnswersModelTable(Question question) {
    answers = question.getAnswers();
  }

  /**
   * Método de instancia público que retorna el número de filas del ModelTable
   * @return Entero que representa el número de filas de la tabla
   */
  @Override
  public int getRowCount() {
    return answers.size();
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
   * Método de instancia que permite obtener el valor de un elemento en la tabla a partir de la columna y fila
   * en la que se encuentre posicionado.
   * @param rowIndex Índice de fila
   * @param columnIndex Indica de columna
   * @return Objeto ubicado en la fila y columna dadas
   */
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    if (columnIndex == 0) {
      return answers.get(rowIndex).getId();
    } else if (columnIndex == 1) {
      return answers.get(rowIndex).getAuthor().getName();
    } else if (columnIndex == 2) {
      return answers.get(rowIndex).publicationDateFormat();
    } else if (columnIndex == 3) {
      return answers.get(rowIndex).getStatus();
    } else if (columnIndex == 4) {
      return answers.get(rowIndex).getContent();
    } else if (columnIndex == 5) {
      return answers.get(rowIndex).getVotes();
    }
    return null;
  }
}
