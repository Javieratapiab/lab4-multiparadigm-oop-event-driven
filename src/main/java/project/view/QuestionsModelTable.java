package project.view;

import project.model.Question;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

class QuestionsModelTable extends AbstractTableModel {
  private final List<Question> questions;
  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
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

  public QuestionsModelTable() {
    questions = Main.currentStack.getQuestions();
  }

  @Override
  public int getRowCount() {
    return questions.size();
  }

  @Override
  public int getColumnCount() {
    return cols.length;
  }

  @Override
  public String getColumnName(int col) {
    return cols[col];
  }

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
      return format.format(questions.get(rowIndex).getPublicationDate());
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
