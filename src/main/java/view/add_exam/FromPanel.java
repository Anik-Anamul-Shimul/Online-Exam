package view.add_exam;

import model.Exam;

import javax.swing.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FromPanel extends JPanel {

    private JPanel labelPanel;
    private JPanel formPanel;
    private JPanel notePanel;

    private JLabel examNameLabel;
    private JLabel examPassLabel;
    private JLabel durationLabel;
    private JLabel startTimeLabel;
    private JLabel dateLabel;
    private JLabel invalidInfoLabel;
    private JLabel invalidDateLabel;
    private JLabel tooEarlyLabel;
    private JLabel penaltyLabel;
    private JLabel passingLabel;
    private JTextField examNameField;
    private JTextField examPassField;
    private JSpinner penaltySpinner;
    private JSpinner passingSpinner;
    private JSpinner durationSpinner;
    private JSpinner dateSpinner;
    private JSpinner startTimeSpinner;
    private JButton createButton;

    private ExamFormListener examFormListener;
    private static final Font SMALL_FONT = new Font("FUTURA",Font.BOLD,12);

    public FromPanel() {
        initializeComponent();
        setComponent();

        createButton.addActionListener(e -> {
            String examName = examNameField.getText();
            String examPass = examPassField.getText();
            int passPercent = (int) passingSpinner.getValue();
            int penalty = (int) penaltySpinner.getValue();
            int examDuration = (int) durationSpinner.getValue();
            Date startDate = (Date) dateSpinner.getValue();
            Date startsIn = (Date) startTimeSpinner.getValue();

            // warning understood
            startDate.setHours(startsIn.getHours());
            startDate.setMinutes(startsIn.getMinutes());
            startDate.setSeconds(0);

            long diffInMiniSec = Math.abs(startDate.getTime() - new Date().getTime());
            long diffInMinute = TimeUnit.MINUTES.convert(diffInMiniSec, TimeUnit.MILLISECONDS);
            long diffInDay = TimeUnit.DAYS.convert(diffInMiniSec, TimeUnit.MILLISECONDS);


            if (examName.length() <= 3 && examPass.length() <= 4){
                invalidInfoLabel.setVisible(true);
                invalidDateLabel.setVisible(false);
                tooEarlyLabel.setVisible(false);
            } else if (diffInDay > 15) {
                invalidDateLabel.setVisible(true);
                invalidInfoLabel.setVisible(false);
                tooEarlyLabel.setVisible(false);
            } else if(new Date().getTime() >= startDate.getTime()
                    || diffInMinute < 10) {
                tooEarlyLabel.setVisible(true);
                invalidInfoLabel.setVisible(false);
                invalidDateLabel.setVisible(false);
            } else {
                invalidInfoLabel.setVisible(false);
                invalidDateLabel.setVisible(false);
                tooEarlyLabel.setVisible(false);
                ExamFormEvent event = new ExamFormEvent(
                        this,examName,examPass,penalty,passPercent,examDuration,startDate
                );

                if(examFormListener != null){
                    examFormListener.examFormEventOccurred(event);
                }
            }
        });
    }

    private void initializeComponent() {
        labelPanel = new JPanel();
        setLabelPanel();
        formPanel = new JPanel();
        setFormPanel();
        notePanel = new JPanel();
        setNotePanel();
    }

    private void setNotePanel() {
        invalidInfoLabel = new JLabel("Too short exam name or password");
        invalidInfoLabel.setFont(SMALL_FONT);
        invalidInfoLabel.setForeground(Color.RED);
        invalidInfoLabel.setVisible(false);

        invalidDateLabel = new JLabel("Please start the exam in 15 days");
        invalidDateLabel.setFont(SMALL_FONT);
        invalidDateLabel.setForeground(Color.RED);
        invalidDateLabel.setVisible(false);

        tooEarlyLabel = new JLabel("At least give 10 minute to start the exam");
        tooEarlyLabel.setFont(SMALL_FONT);
        tooEarlyLabel.setForeground(Color.RED);
        tooEarlyLabel.setVisible(false);

        notePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        notePanel.add(invalidDateLabel);
        notePanel.add(invalidInfoLabel);
        notePanel.add(tooEarlyLabel);
    }

    private void setLabelPanel() {
        JLabel requestLabel = new JLabel("Please Fill the form");
        requestLabel.setForeground(new Color(0xFFAA1455, true));
        requestLabel.setFont(new Font("Arial",Font.BOLD,20));

        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelPanel.add(requestLabel);
    }

    private void setFormPanel() {
        initializeFormComponent();
        setFormComponent();
    }

    private void setFormComponent() {
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
        formPanel.add(examNameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        formPanel.add(examNameField, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(examPassLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(-10,0,-10,0);
        formPanel.add(examPassField, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(passingLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(-10,0,-10,0);
        formPanel.add(passingSpinner, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(penaltyLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(-10,0,-10,0);
        formPanel.add(penaltySpinner, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(durationLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        formPanel.add(durationSpinner, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        formPanel.add(dateSpinner, gbc);

        /*  Next Row */
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0,0,0,5);
        formPanel.add(startTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,0,0,0);
        formPanel.add(startTimeSpinner, gbc);

        /* Last Row */
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.weighty = 4;
        gbc.insets = new Insets(0,0,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(createButton,gbc);
    }

    private void initializeFormComponent() {
        examNameLabel = new JLabel("Exam Name :");

        examPassLabel = new JLabel("Password  :");

        durationLabel = new JLabel("Time (min) :");

        dateLabel = new JLabel("Exam Date :");

        startTimeLabel = new JLabel("Start Time :");

        penaltyLabel = new JLabel("Penalty (%) :");

        passingLabel = new JLabel("Pass at (%) :");

        examNameField = new JTextField(12);

        examPassField = new JTextField(12);

        SpinnerModel durationSinnerModel =
                new SpinnerNumberModel(10,2,180,1);
        durationSpinner = new JSpinner(durationSinnerModel);
        // disabling editable mod
        JFormattedTextField durationSpin = ((JSpinner.DefaultEditor)durationSpinner.getEditor()).getTextField();
        durationSpin.setEditable(false);

        SpinnerModel penaltySpinnerModel =
                new SpinnerNumberModel(0,0,100,1);
        penaltySpinner = new JSpinner(penaltySpinnerModel);
        // disabling editable mod
        JFormattedTextField penaltySpin = ((JSpinner.DefaultEditor)penaltySpinner.getEditor()).getTextField();
        penaltySpin.setEditable(false);

        SpinnerModel passingSpinnerModel =
                new SpinnerNumberModel(0,0,100,1);
        passingSpinner = new JSpinner(passingSpinnerModel);
        // disabling editable mod
        JFormattedTextField passingSpin = ((JSpinner.DefaultEditor)passingSpinner.getEditor()).getTextField();
        passingSpin.setEditable(false);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "EEE, d MMM yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setValue(new Date()); // Now

        startTimeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH: mm");
        startTimeSpinner.setEditor(timeEditor);
        startTimeSpinner.setValue(new Date());

        createButton = new JButton("Create");
        createButton.setFont(new Font("Arial",Font.PLAIN,15));
        createButton.setFocusPainted(false);
        createButton.setBackground(new Color(0x0f4c75));
        createButton.setForeground(Color.WHITE);
    }

    public void setExamFormListener(ExamFormListener examFormListener) {
        this.examFormListener = examFormListener;
    }

    private void setComponent() {
        Dimension dimension = getPreferredSize();
        dimension.width = 300;
        this.setPreferredSize(dimension);
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(
                        1,1,1,1,new Color(0x03506f))
                        ,"Form Panel")
        );
        this.setLayout(new BorderLayout());
        this.add(labelPanel,BorderLayout.NORTH);
        this.add(formPanel,BorderLayout.CENTER);
        this.add(notePanel,BorderLayout.SOUTH);
    }

    public void setFields(Exam exam) {
        examNameField.setText(exam.getExamName());
        examPassField.setText(exam.getExamPassword());
        durationSpinner.setValue(exam.getExamDuration());
        penaltySpinner.setValue(exam.getPenalty());
        passingSpinner.setValue(exam.getPassingPercent());
        startTimeSpinner.setValue(exam.getExamStartingTime());
        dateSpinner.setValue(exam.getExamStartingTime());
        createButton.setText("Confirm");
    }
}
