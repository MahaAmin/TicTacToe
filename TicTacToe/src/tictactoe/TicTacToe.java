/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author maha
 */
public class TicTacToe extends javax.swing.JFrame {

    private String startGame = "X";
    private int xPlayerScore = 0;
    private int oPlayerScore = 0;

    /**
     * Creates new form TicTacToe
     */
    public TicTacToe() {
        initComponents();
        setSize(1200, 600);
        setLocationRelativeTo(null);
        gameScore();
    }

    /* This method sets game score: 
        PlayerX: xPlayerScore 
        PlayerO: oPlayerScore 
     */
    private void gameScore() {
        jlbPlayerX.setText(String.valueOf(xPlayerScore));
        jlbPlayerO.setText(String.valueOf(oPlayerScore));
    }

    private void choosePlayer() {
        if (startGame.equalsIgnoreCase("X")) {
            startGame = "O";
        } else {
            startGame = "X";
        }
    }

    
    private void winner() {
        /*   cell1    cell2   cell3 
             cell4    cell5   cell6
             cell7    cell8   cell9
         */

         /* x x x
           * * *
           * * *
         */
        if (cell1.getText().equalsIgnoreCase("X") && cell2.getText().equalsIgnoreCase("X") && cell3.getText().equalsIgnoreCase("X")) {
            cell1.setBackground(Color.yellow);
            cell2.setBackground(Color.yellow);
            cell3.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player X Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            xPlayerScore++;
            gameScore();
        }
        /* * * *
           x x x
           * * *
         */ 
        else if (cell4.getText().equalsIgnoreCase("X") && cell5.getText().equalsIgnoreCase("X") && cell6.getText().equalsIgnoreCase("X")) {
            cell4.setBackground(Color.yellow);
            cell5.setBackground(Color.yellow);
            cell6.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player X Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            xPlayerScore++;
            gameScore();
        } /* * * *
           * * *
           x x x
         */ else if (cell7.getText().equalsIgnoreCase("X") && cell8.getText().equalsIgnoreCase("X") && cell9.getText().equalsIgnoreCase("X")) {
            cell7.setBackground(Color.yellow);
            cell8.setBackground(Color.yellow);
            cell9.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player X Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            xPlayerScore++;
            gameScore();
        } /* x * *
           x * *
           x * *
         */ else if (cell1.getText().equalsIgnoreCase("X") && cell4.getText().equalsIgnoreCase("X") && cell7.getText().equalsIgnoreCase("X")) {
            cell1.setBackground(Color.yellow);
            cell4.setBackground(Color.yellow);
            cell7.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player X Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            xPlayerScore++;
            gameScore();
        } /* * x *
           * x *
           * x *
         */ else if (cell2.getText().equalsIgnoreCase("X") && cell5.getText().equalsIgnoreCase("X") && cell8.getText().equalsIgnoreCase("X")) {
            cell2.setBackground(Color.yellow);
            cell5.setBackground(Color.yellow);
            cell8.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player X Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            xPlayerScore++;
            gameScore();
        } /* * * x
           * * x
           * * x
         */ else if (cell3.getText().equalsIgnoreCase("X") && cell6.getText().equalsIgnoreCase("X") && cell9.getText().equalsIgnoreCase("X")) {
            cell3.setBackground(Color.yellow);
            cell6.setBackground(Color.yellow);
            cell9.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player X Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            xPlayerScore++;
            gameScore();
        } /* x * *
           * x *
           * * x
         */ else if (cell1.getText().equalsIgnoreCase("X") && cell5.getText().equalsIgnoreCase("X") && cell9.getText().equalsIgnoreCase("X")) {
            cell1.setBackground(Color.yellow);
            cell5.setBackground(Color.yellow);
            cell9.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player X Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            xPlayerScore++;
            gameScore();
        } /* * * x
           * x *
           x * *
         */ else if (cell3.getText().equalsIgnoreCase("X") && cell5.getText().equalsIgnoreCase("X") && cell7.getText().equalsIgnoreCase("X")) {
            cell3.setBackground(Color.yellow);
            cell5.setBackground(Color.yellow);
            cell7.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player X Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            xPlayerScore++;
            gameScore();
        } /* o o o
           * * *
           * * *
         */ else if (cell1.getText().equalsIgnoreCase("O") && cell2.getText().equalsIgnoreCase("O") && cell3.getText().equalsIgnoreCase("O")) {
            cell1.setBackground(Color.yellow);
            cell2.setBackground(Color.yellow);
            cell3.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player O Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            oPlayerScore++;
            gameScore();
        } /* * * *
           o o o
           * * *
         */ else if (cell4.getText().equalsIgnoreCase("O") && cell5.getText().equalsIgnoreCase("O") && cell6.getText().equalsIgnoreCase("O")) {
            cell4.setBackground(Color.yellow);
            cell5.setBackground(Color.yellow);
            cell6.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player O Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            oPlayerScore++;
            gameScore();
        } /* * * *
           * * *
           o o o
         */ else if (cell7.getText().equalsIgnoreCase("O") && cell8.getText().equalsIgnoreCase("O") && cell9.getText().equalsIgnoreCase("O")) {
            cell7.setBackground(Color.yellow);
            cell8.setBackground(Color.yellow);
            cell9.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player O Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            oPlayerScore++;
            gameScore();
        } /* o * *
           o * *
           o * *
         */ else if (cell1.getText().equalsIgnoreCase("O") && cell4.getText().equalsIgnoreCase("O") && cell7.getText().equalsIgnoreCase("O")) {
            cell1.setBackground(Color.yellow);
            cell4.setBackground(Color.yellow);
            cell7.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player O Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            oPlayerScore++;
            gameScore();
        } /* * o *
           * o *
           * o *
         */ else if (cell2.getText().equalsIgnoreCase("O") && cell5.getText().equalsIgnoreCase("O") && cell8.getText().equalsIgnoreCase("O")) {
            cell2.setBackground(Color.yellow);
            cell5.setBackground(Color.yellow);
            cell8.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player O Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            oPlayerScore++;
            gameScore();
        } /* * * o
           * * o
           * * o
         */ else if (cell3.getText().equalsIgnoreCase("O") && cell6.getText().equalsIgnoreCase("O") && cell9.getText().equalsIgnoreCase("O")) {
            cell3.setBackground(Color.yellow);
            cell6.setBackground(Color.yellow);
            cell9.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player O Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            oPlayerScore++;
            gameScore();
        } /* o * *
           * o *
           * * o
         */ else if (cell1.getText().equalsIgnoreCase("O") && cell5.getText().equalsIgnoreCase("O") && cell9.getText().equalsIgnoreCase("O")) {
            cell1.setBackground(Color.yellow);
            cell5.setBackground(Color.yellow);
            cell9.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player O Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            oPlayerScore++;
            gameScore();
        } /* * * o
           * o *
           o * *
         */ else if (cell3.getText().equalsIgnoreCase("O") && cell5.getText().equalsIgnoreCase("O") && cell7.getText().equalsIgnoreCase("O")) {
            cell3.setBackground(Color.yellow);
            cell5.setBackground(Color.yellow);
            cell7.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Player O Wins!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            oPlayerScore++;
            gameScore();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cell1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        cell2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        cell3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jlbPlayerX = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        cell4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        cell5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        cell6 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jlbPlayerO = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        cell7 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        cell8 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        cell9 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jbtnReset = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jbtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTacToe");

        jPanel1.setBackground(new java.awt.Color(189, 178, 168));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 100)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(17, 9, 9));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tic Tac Toe");
        jPanel1.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(61, 57, 54));
        jPanel2.setLayout(new java.awt.GridLayout(3, 5, 2, 2));

        jPanel3.setBackground(new java.awt.Color(236, 224, 224));
        jPanel3.setLayout(new java.awt.BorderLayout());

        cell1.setFont(new java.awt.Font("Ubuntu", 1, 96)); // NOI18N
        cell1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cell1ActionPerformed(evt);
            }
        });
        jPanel3.add(cell1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(236, 224, 224));
        jPanel4.setLayout(new java.awt.BorderLayout());

        cell2.setFont(new java.awt.Font("Ubuntu", 1, 96)); // NOI18N
        cell2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cell2ActionPerformed(evt);
            }
        });
        jPanel4.add(cell2, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(236, 224, 224));
        jPanel5.setLayout(new java.awt.BorderLayout());

        cell3.setFont(new java.awt.Font("Ubuntu", 1, 96)); // NOI18N
        cell3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cell3ActionPerformed(evt);
            }
        });
        jPanel5.add(cell3, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(236, 224, 224));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Player X :");
        jPanel6.add(jLabel2, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(236, 224, 224));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jlbPlayerX.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jlbPlayerX.setForeground(new java.awt.Color(0, 0, 0));
        jlbPlayerX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPlayerX.setText("jLabel4");
        jPanel7.add(jlbPlayerX, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(236, 224, 224));
        jPanel8.setLayout(new java.awt.BorderLayout());

        cell4.setFont(new java.awt.Font("Ubuntu", 1, 96)); // NOI18N
        cell4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cell4ActionPerformed(evt);
            }
        });
        jPanel8.add(cell4, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(236, 224, 224));
        jPanel9.setLayout(new java.awt.BorderLayout());

        cell5.setFont(new java.awt.Font("Ubuntu", 1, 96)); // NOI18N
        cell5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cell5ActionPerformed(evt);
            }
        });
        jPanel9.add(cell5, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(236, 224, 224));
        jPanel10.setLayout(new java.awt.BorderLayout());

        cell6.setFont(new java.awt.Font("Ubuntu", 1, 96)); // NOI18N
        cell6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cell6ActionPerformed(evt);
            }
        });
        jPanel10.add(cell6, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(236, 224, 224));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Player O :");
        jPanel11.add(jLabel3, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel11);

        jPanel12.setBackground(new java.awt.Color(236, 224, 224));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jlbPlayerO.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jlbPlayerO.setForeground(new java.awt.Color(0, 0, 0));
        jlbPlayerO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPlayerO.setText("jLabel5");
        jPanel12.add(jlbPlayerO, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(236, 224, 224));
        jPanel13.setLayout(new java.awt.BorderLayout());

        cell7.setFont(new java.awt.Font("Ubuntu", 1, 96)); // NOI18N
        cell7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cell7ActionPerformed(evt);
            }
        });
        jPanel13.add(cell7, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(236, 224, 224));
        jPanel14.setLayout(new java.awt.BorderLayout());

        cell8.setFont(new java.awt.Font("Ubuntu", 1, 96)); // NOI18N
        cell8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cell8ActionPerformed(evt);
            }
        });
        jPanel14.add(cell8, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel14);

        jPanel15.setBackground(new java.awt.Color(236, 224, 224));
        jPanel15.setLayout(new java.awt.BorderLayout());

        cell9.setFont(new java.awt.Font("Ubuntu", 1, 96)); // NOI18N
        cell9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cell9ActionPerformed(evt);
            }
        });
        jPanel15.add(cell9, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel15);

        jPanel16.setBackground(new java.awt.Color(236, 224, 224));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jbtnReset.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jbtnReset.setForeground(new java.awt.Color(0, 0, 0));
        jbtnReset.setText("Reset");
        jbtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnResetActionPerformed(evt);
            }
        });
        jPanel16.add(jbtnReset, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel16);

        jPanel17.setBackground(new java.awt.Color(236, 224, 224));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jbtnExit.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jbtnExit.setForeground(new java.awt.Color(0, 0, 0));
        jbtnExit.setText("Exit");
        jbtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExitActionPerformed(evt);
            }
        });
        jPanel17.add(jbtnExit, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel17);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cell1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cell1ActionPerformed

        cell1.setText(startGame);
        if (startGame.equalsIgnoreCase("X")) {
            cell1.setForeground(Color.green);
        } else {
            cell1.setForeground(Color.blue);
        }

        choosePlayer();
        winner();

    }//GEN-LAST:event_cell1ActionPerformed

    private void cell2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cell2ActionPerformed
        cell2.setText(startGame);
        if (startGame.equalsIgnoreCase("X")) {
            cell2.setForeground(Color.green);
        } else {
            cell2.setForeground(Color.blue);
        }

        choosePlayer();
        winner();
    }//GEN-LAST:event_cell2ActionPerformed

    private void cell3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cell3ActionPerformed
        cell3.setText(startGame);
        if (startGame.equalsIgnoreCase("X")) {
            cell3.setForeground(Color.green);
        } else {
            cell3.setForeground(Color.blue);
        }

        choosePlayer();
        winner();
    }//GEN-LAST:event_cell3ActionPerformed

    private void cell9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cell9ActionPerformed
        cell9.setText(startGame);
        if (startGame.equalsIgnoreCase("X")) {
            cell9.setForeground(Color.green);
        } else {
            cell9.setForeground(Color.blue);
        }

        choosePlayer();
        winner();
    }//GEN-LAST:event_cell9ActionPerformed

    private void cell4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cell4ActionPerformed
        cell4.setText(startGame);
        if (startGame.equalsIgnoreCase("X")) {
            cell4.setForeground(Color.green);
        } else {
            cell4.setForeground(Color.blue);
        }

        choosePlayer();
        winner();
    }//GEN-LAST:event_cell4ActionPerformed

    private void cell5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cell5ActionPerformed
        cell5.setText(startGame);
        if (startGame.equalsIgnoreCase("X")) {
            cell5.setForeground(Color.green);
        } else {
            cell5.setForeground(Color.blue);
        }

        choosePlayer();
        winner();
    }//GEN-LAST:event_cell5ActionPerformed

    private void cell6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cell6ActionPerformed
        cell6.setText(startGame);
        if (startGame.equalsIgnoreCase("X")) {
            cell6.setForeground(Color.green);
        } else {
            cell6.setForeground(Color.blue);
        }

        choosePlayer();
        winner();
    }//GEN-LAST:event_cell6ActionPerformed

    private void cell7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cell7ActionPerformed
        cell7.setText(startGame);
        if (startGame.equalsIgnoreCase("X")) {
            cell7.setForeground(Color.green);
        } else {
            cell7.setForeground(Color.blue);
        }

        choosePlayer();
        winner();
    }//GEN-LAST:event_cell7ActionPerformed

    private void cell8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cell8ActionPerformed
        cell8.setText(startGame);
        if (startGame.equalsIgnoreCase("X")) {
            cell8.setForeground(Color.green);
        } else {
            cell8.setForeground(Color.blue);
        }

        choosePlayer();
        winner();
    }//GEN-LAST:event_cell8ActionPerformed

    private JFrame frame;

    private void jbtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExitActionPerformed
        frame = new JFrame("Exit");
        if (JOptionPane.showConfirmDialog(frame, "Exit game?", "Tic Tac Toe",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jbtnExitActionPerformed


    private void reset() {
        cell1.setText("");
        cell2.setText("");
        cell3.setText("");
        cell4.setText("");
        cell5.setText("");
        cell6.setText("");
        cell7.setText("");
        cell8.setText("");
        cell9.setText("");

        cell1.setBackground(Color.LIGHT_GRAY);
        cell2.setBackground(Color.LIGHT_GRAY);
        cell3.setBackground(Color.LIGHT_GRAY);
        cell4.setBackground(Color.LIGHT_GRAY);
        cell5.setBackground(Color.LIGHT_GRAY);
        cell6.setBackground(Color.LIGHT_GRAY);
        cell7.setBackground(Color.LIGHT_GRAY);
        cell8.setBackground(Color.LIGHT_GRAY);
        cell9.setBackground(Color.LIGHT_GRAY);
    }
    
    private void jbtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnResetActionPerformed
            reset();
    }//GEN-LAST:event_jbtnResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cell1;
    private javax.swing.JButton cell2;
    private javax.swing.JButton cell3;
    private javax.swing.JButton cell4;
    private javax.swing.JButton cell5;
    private javax.swing.JButton cell6;
    private javax.swing.JButton cell7;
    private javax.swing.JButton cell8;
    private javax.swing.JButton cell9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbtnExit;
    private javax.swing.JButton jbtnReset;
    private javax.swing.JLabel jlbPlayerO;
    private javax.swing.JLabel jlbPlayerX;
    // End of variables declaration//GEN-END:variables
}
