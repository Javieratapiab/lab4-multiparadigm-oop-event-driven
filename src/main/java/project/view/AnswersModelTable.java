package project.view;

import project.model.Answer;
import project.model.Question;

import javax.swing.table.AbstractTableModel;
import java.util.List;

class AnswersModelTable extends AbstractTableModel {
  private List<Answer> answers;
  private final String[] cols = {
          "ID",
          "Autor",
          "Fecha de publicación",
          "Estado",
          "Contenido",
          "Votos",

  };

  public AnswersModelTable(Question question) {
    answers = question.getAnswers();
  }

  @Override
  public int getRowCount() {
    return answers.size();
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
