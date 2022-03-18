/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import enums.LocationType;
import gamemap.impls.JTableGameMap;
import collections.impls.MapCollection;
import sound.impls.WavPlayer;
import user.AbstractUserManager;
import user.DBUserManager;
import user.User;

import javax.swing.*;

/**
 *
 * @author vojtech
 */
public class FrameMainMenu extends JFrame {

    private FrameGame frameGame;
    private FrameStat frameStat = new FrameStat();
    private FrameSavedGames frameLoadGame = new FrameSavedGames();

    private AbstractUserManager userManager = new DBUserManager();
    private CustomDialog usernameDialog;

    /**
     * Creates new form FrameMainMenu
     */
    public FrameMainMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlMainMenu = new javax.swing.JPanel();
        jbtnNewGame = new javax.swing.JButton();
        jbtnLoadgame = new javax.swing.JButton();
        jbtnStatistics = new javax.swing.JButton();
        jbtnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Игра");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("FrameMainMenu"); // NOI18N
        setResizable(false);

        jpnlMainMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jpnlMainMenu.setToolTipText("");

        jbtnNewGame.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jbtnNewGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new.png"))); // NOI18N
        jbtnNewGame.setText("Новая игра");
        jbtnNewGame.setToolTipText("Создать новую игру");
        jbtnNewGame.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnNewGame.setIconTextGap(10);
        jbtnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNewGameActionPerformed(evt);
            }
        });

        jbtnLoadgame.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jbtnLoadgame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/load.png"))); // NOI18N
        jbtnLoadgame.setText("Загрузить игру");
        jbtnLoadgame.setToolTipText("Загрузить сохраненную игру");
        jbtnLoadgame.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnLoadgame.setIconTextGap(10);
        jbtnLoadgame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoadgameActionPerformed(evt);
            }
        });

        jbtnStatistics.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jbtnStatistics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stat.png"))); // NOI18N
        jbtnStatistics.setText("Статистика");
        jbtnStatistics.setToolTipText("Посмотреть статистику по игрокам");
        jbtnStatistics.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnStatistics.setIconTextGap(10);
        jbtnStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnStatisticsActionPerformed(evt);
            }
        });

        jbtnExit.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        jbtnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jbtnExit.setText("Выход");
        jbtnExit.setToolTipText("Выйти из игры");
        jbtnExit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnExit.setIconTextGap(10);
        jbtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnlMainMenuLayout = new javax.swing.GroupLayout(jpnlMainMenu);
        jpnlMainMenu.setLayout(jpnlMainMenuLayout);
        jpnlMainMenuLayout.setHorizontalGroup(
            jpnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlMainMenuLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jpnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnLoadgame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnNewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jpnlMainMenuLayout.setVerticalGroup(
            jpnlMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlMainMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnLoadgame, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jpnlMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnlMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNewGameActionPerformed
        if (createNewUser() == null) {
            return;
        }

        if (frameGame == null) {
            frameGame = new FrameGame(userManager);
        }
        frameGame.setMap(new JTableGameMap(LocationType.FS, "game.map", new MapCollection()), new WavPlayer());
        frameGame.showFrame(this);
    }//GEN-LAST:event_jbtnNewGameActionPerformed

    private void jbtnLoadgameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLoadgameActionPerformed
        frameLoadGame.showFrame(this);
    }//GEN-LAST:event_jbtnLoadgameActionPerformed

    private void jbtnStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnStatisticsActionPerformed
        frameStat.showFrame(this);
    }//GEN-LAST:event_jbtnStatisticsActionPerformed

    private void jbtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExitActionPerformed
        System.exit(0);// не рекомендуется так делать, т.к. могут быть другие процессы

    }//GEN-LAST:event_jbtnExitActionPerformed

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
            java.util.logging.Logger.getLogger(FrameMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnExit;
    private javax.swing.JButton jbtnLoadgame;
    private javax.swing.JButton jbtnNewGame;
    private javax.swing.JButton jbtnStatistics;
    private javax.swing.JPanel jpnlMainMenu;
    // End of variables declaration//GEN-END:variables

    private User createNewUser() {
        if (usernameDialog == null) {
            usernameDialog = new CustomDialog(this, "Имя пользователя", "Введите имя: ", true);
        }

        usernameDialog.setVisible(true);

        if (usernameDialog.getValidatedText() != null) {
            userManager.createNewUser(usernameDialog.getValidatedText());
            return userManager.getUser();
        }

        return null;
    }



}
