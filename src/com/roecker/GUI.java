package com.roecker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    boolean tour = true;
    JFrame frame;
    JPanel tab;
    JPanel UI;
    JPanel territoire;
    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton fin;
    private JTextField Joueur;

    public GUI() {
        String nbJoueurs;


        frame = new JFrame();
        tab = new JPanel();
        UI = new JPanel();
        territoire = new JPanel();
        Joueur = new JTextField("Joueur 1");

        territoire.setLayout(new GridLayout(3, 3));

        button0 = new JButton("1");
        button1 = new JButton("2");
        button2 = new JButton("3");
        button3 = new JButton("4");
        button4 = new JButton("5");
        button5 = new JButton("6");
        button6 = new JButton("7");
        button7 = new JButton("8");
        button8 = new JButton("9");
        fin = new JButton("Fin du tour");

        button0.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        fin.addActionListener(this);

        territoire.add(button0);
        territoire.add(button1);
        territoire.add(button2);
        territoire.add(button3);
        territoire.add(button4);
        territoire.add(button5);
        territoire.add(button6);
        territoire.add(button7);
        territoire.add(button8);
        UI.add(Joueur);
        UI.add(fin);

        do {
            nbJoueurs = JOptionPane.showInputDialog(frame, "Combien de joueurs ?");
        }while (0 > Integer.parseInt(nbJoueurs) && Integer.parseInt(nbJoueurs) > 10);

        frame.setSize(300,300);
        frame.add(tab, BorderLayout.EAST);
        frame.add(territoire, BorderLayout.CENTER);
        frame.add(UI, BorderLayout.AFTER_LAST_LINE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Dice War");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fin && tour){
            Joueur.setText("Joueur 2");
            tour = false;
        }else {
            Joueur.setText("Joueur 1");
            tour = true;
        }
    }
}
