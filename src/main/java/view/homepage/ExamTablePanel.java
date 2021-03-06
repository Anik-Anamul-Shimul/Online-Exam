package view.homepage;

import model.Exam;
import view.TableStyle;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ExamTablePanel extends JPanel{
    private JTable examTable;
    private ExamTableModel examTableModel;

    private List<Exam> exams;

    public ExamTablePanel(List<Exam> exams, HomePage home) {
        this.exams = exams;
        examTableModel = new ExamTableModel(exams);
        examTable = new JTable(examTableModel);

        setLayout(new BorderLayout());
        add(new JScrollPane(examTable),BorderLayout.CENTER);
        setBorder(BorderFactory.createMatteBorder(1,1,1,1,new Color(0x726a95)));

        TableStyle tableStyle = new TableStyle(examTable);
        tableStyle.StyleTheTable();

        examTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    String id = (String) table.getValueAt(table.getSelectedRow(), 0);
                    home.attemptToGoExamRoom(id);
                }
            }
        });


        refresh();
    }

    public void refresh() {
        examTableModel.setExamList(exams);
        examTableModel.fireTableDataChanged();
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
