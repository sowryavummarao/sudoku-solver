package com.SudokuSolver;

import com.example.helloworld.sudoku.SudokuBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;


/**
 * Created by sowryavummarao on 12/15/17.
 */
public class GUICreator extends JComponent implements ActionListener{
    private static JTextField[] textFields;
    private boolean valid;
    private static JFrame frame;
    private static JButton button;

    public GUICreator(){
        button.addActionListener(this);
        frame.add(this);
    }

    public static void main(String[] args) {
        frame = new JFrame("Sudoku Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11,10));

        panel.add(new JLabel());
        for (int i = 0;i < 9;i++){
            panel.add(new JLabel("         " + Integer.toString(i)));
        }

        textFields = new JTextField[81];
        for (int i = 0; i < 9;i++){
            panel.add(new JLabel("         " + Integer.toString(i)));
            for (int j = 0; j < 9;j++){
                textFields[9 * i + j] = new JTextField(1);
                panel.add(textFields[9 * i + j]);
            }
        }

        button = new JButton("Solve!");

        for (int i = 0; i < 5;i++) {
            panel.add(new JLabel());
        }
        panel.add(button);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public String getInputString(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9;i++){
            for ( int j = 0; j< 9; j++){
                if (textFields[9 * i + j].getText().length() == 1) {
                    builder.append(textFields[9 * i + j].getText());
                }else{
                    builder.append(0);
                }
            }
            builder.append("|");
        }
        return builder.toString();
    }
    @Override
    protected void paintComponent(Graphics g){
        g.drawLine(100,100,100,100);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(frame.getWidth());
        valid = true;
        for (int i = 0;i < textFields.length;i++){
            //
            //EXTRA ITERATIONS ARE OCCURRING BECAUSE ONCE VALID IS FALSE, IT'S STILL RUNNING THROUGH, BUT THIS IS
            //UNNECESSARY
            //
            if (textFields[i].getText().length() > 1){
                System.out.printf("The cell  on row %d column " +
                        "%d must be in the range [0,9]\n",i/9,i%9);
                valid = false;
            }else{
                if (textFields[i].getText().length() == 1) {
                    try {
                        Integer.parseInt(textFields[i].getText());
                    } catch (Exception e1) {
                        System.out.printf("The cell  on row %d column " +
                                "%d must be in the range [0,9]\n", i / 9, i % 9);
                        valid = false;
                    }
                }
            }
        }
        if (valid){
            SudokuBoard board = new SudokuBoard();
            String input = getInputString();
            int[][] inputArray = SudokuBoard.stringToInput(input);
            for (int[] element : inputArray) {
                board.setValue(element[0],element[1],element[2]);
            }
            board.solve(0,0,board);
        }
    }
}
