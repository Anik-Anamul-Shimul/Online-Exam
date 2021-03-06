package view.add_exam;

import model.MultipleChoiceQuestion;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class setQuestionDialog extends JDialog {
    private JLabel questionLabel;
    private JTextArea questionTextArea;
    private JLabel choice1Label;
    private JTextField choice1Field;
    private JLabel choice2Label;
    private JTextField choice2Field;
    private JLabel choice3Label;
    private JTextField choice3Field;
    private JLabel choice4Label;
    private JTextField choice4Field;
    private JLabel rightAnswerLabel;
    private JSpinner rightAnswerSpinner;
    private JLabel markLabel;
    private JSpinner markSpinner;
    private JLabel noteLabel;
    private JButton confirmButton;
    private QuestionFormListener questionFormListener;

    public setQuestionDialog(JDialog owner, String title, boolean model, MultipleChoiceQuestion initialQuestion) {
        super(owner, title, model);
        setSize(new Dimension(550,420));
        setResizable(false);
        setLocationRelativeTo(owner);

        initialization(initialQuestion);
        setComponents();

        confirmButton.addActionListener(e -> {
            String question = questionTextArea.getText();
            String choice1 = choice1Field.getText();
            String choice2 = choice2Field.getText();
            String choice3 = choice3Field.getText();
            String choice4 = choice4Field.getText();
            double mark = (double) markSpinner.getValue();
            String rightChoice = (String) rightAnswerSpinner.getValue();
            if(question.length() <= 1){
                noteLabel.setVisible(true);
            }else{
                noteLabel.setVisible(false);
                QuestionFormEvent event = new QuestionFormEvent(
                        this, question, choice1, choice2, choice3,
                        choice4, mark, rightChoice
                );
                if (questionFormListener != null){
                    questionFormListener.questionFormEventOccurred(event);
                }
            }
        });

        setVisible(true);
    }

    public void setQuestionFormListener(QuestionFormListener questionFormListener) {
        this.questionFormListener = questionFormListener;
    }

    private void initialization(MultipleChoiceQuestion initialQuestion) {
        questionLabel = new JLabel("Question :");

        questionTextArea = new JTextArea();
        questionTextArea.setColumns(25);
        questionTextArea.setRows(4);
        questionTextArea.setLineWrap(true);

        choice1Label = new JLabel("Choice A :");
        choice1Field = new JTextField(25);

        choice2Label = new JLabel("Choice B :");
        choice2Field = new JTextField(25);

        choice3Label = new JLabel("Choice C :");
        choice3Field = new JTextField(25);

        choice4Label = new JLabel("Choice D :");
        choice4Field = new JTextField(25);

        markLabel =  new JLabel("Q. Mark :");

        SpinnerModel markSpinnerModel = new SpinnerNumberModel(1.00,0.25,10.00,.25);
        markSpinner = new JSpinner(markSpinnerModel);
        // disabling editable mod
        JFormattedTextField spin = ((JSpinner.DefaultEditor)markSpinner.getEditor()).getTextField();
        spin.setEditable(false);

        rightAnswerLabel = new JLabel("Right one :");

        List<String> options = Arrays.asList("A", "B", "C", "D");
        SpinnerModel spinnerModel = new SpinnerListModel(options);
        rightAnswerSpinner = new JSpinner(spinnerModel);
        rightAnswerSpinner.setEditor(new JSpinner.DefaultEditor(rightAnswerSpinner));

        noteLabel = new JLabel("Question can't be blank");
        noteLabel.setForeground(Color.red);
        noteLabel.setVisible(false);

        confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial",Font.PLAIN,15));
        confirmButton.setFocusPainted(false);
        confirmButton.setBackground(new Color(0x0f4c75));
        confirmButton.setForeground(Color.WHITE);

        if(initialQuestion != null) {
            questionTextArea.setText(initialQuestion.getQuestion());
            choice1Field.setText(initialQuestion.getChoices().get(0));
            choice2Field.setText(initialQuestion.getChoices().get(1));
            choice3Field.setText(initialQuestion.getChoices().get(2));
            choice4Field.setText(initialQuestion.getChoices().get(3));
            markSpinner.setValue(initialQuestion.getMark());
            rightAnswerSpinner.setValue(options.get(initialQuestion.getRightIndex()));
        }
    }

    private void setComponents() {

        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createEmptyBorder(8,0,5,0));

        /*  Initialization  */
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 10;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;

        /* First Row */
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(questionLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        formPanel.add(new JScrollPane(questionTextArea), gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(choice1Label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(-10,0,-10,0);
        formPanel.add(choice1Field, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(choice2Label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        formPanel.add(choice2Field, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(choice3Label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        formPanel.add(choice3Field, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(choice4Label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        formPanel.add(choice4Field, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(markLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        formPanel.add(markSpinner, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(rightAnswerLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        formPanel.add(rightAnswerSpinner, gbc);

        /*  Next Row */
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(noteLabel, gbc);

        /* Last Row */
        gbc.gridy++;
        gbc.weighty = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,98);
        formPanel.add(confirmButton,gbc);

        setLayout(new BorderLayout());
        add(formPanel,BorderLayout.CENTER);
    }
}
