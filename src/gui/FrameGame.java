/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import gameobjects.abstracts.AbstractMovingObject;
import enums.ActionResult;
import enums.GameObjectType;
import enums.MovingDirection;
import gamemap.interfaces.TimeMap;
import gameobjects.impls.Goldman;
import listeners.interfaces.MoveResultListener;
import sound.interfaces.SoundPlayer;
import user.AbstractUserManager;
import utils.MessageManager;

public class FrameGame extends BaseChildFrame implements MoveResultListener {
    private TimeMap map; // передаем объект карты, которая умеет себя рисовать
    private SoundPlayer soundPlayer;
    private AbstractUserManager userManager;

    /**
     * Creates new form FrameGame
     */
    public FrameGame(AbstractUserManager userManager) {
        this.userManager = userManager;
        initComponents();
    }

    public void setMap(TimeMap gameMap, SoundPlayer soundPlayer) {
        this.map = gameMap;
        gameMap.drawMap();

        this.soundPlayer = soundPlayer;
        this.soundPlayer.startBackgroundMusic("background.wav");

        gameMap.getGameMap().getGameCollection().addMoveListener(this);

        jlabelTurnsLeft.setText(String.valueOf(gameMap.getGameMap().getTimeLimit()));
        jPanelMap.removeAll();
        jPanelMap.add(gameMap.getMap());
    }


    private void moveGameObject(MovingDirection direction, GameObjectType gameObjectType) {
        map.getGameMap().getGameCollection().moveObject(direction, gameObjectType);
    }

    private void gameFinished(String message) {
        MessageManager.showInformMessage(null, message);
        closeFrame();
    }

    private static final String DIE_MESSAGE = "Вы проиграли!";
    private static final String WIN_MESSAGE = "Вы выиграли! Количество очков: ";

    @Override
    protected void closeFrame() {
        super.closeFrame();
        soundPlayer.stopBackgroundMusic();
        map.stop();
    }

    @Override
    public void notifyActionResult(ActionResult actionResult, AbstractMovingObject movingObject) {
        if (movingObject.getType().equals(GameObjectType.GOLDMAN)) {
            Goldman goldMan = (Goldman) movingObject;
            checkGoldmanActions(actionResult, goldMan);
        }

        checkCommonActions(actionResult);

        map.drawMap();
    }

    private void checkGoldmanActions(ActionResult actionResult, Goldman goldman) {
        switch (actionResult) {
            case MOVE -> {
                jlabelTurnsLeft.setText(String.valueOf(map.getGameMap().getTimeLimit() - goldman.getTurnsNumber()));

                if (goldman.getTurnsNumber() >= map.getGameMap().getTimeLimit()) {
                    soundPlayer.stopBackgroundMusic();
                    //map.getGameMap().getGameCollection().notifyMoveListeners(ActionResult.DIE, goldman);
                    gameFinished(DIE_MESSAGE);
                }
            }
            case COLLECT_TREASURE -> jlabelScore.setText(String.valueOf(goldman.getTotalScore()));
            case WIN -> {
                jlabelScore.setText(String.valueOf(goldman.getTotalScore()));
                soundPlayer.stopBackgroundMusic();
                gameFinished(WIN_MESSAGE + goldman.getTotalScore());
            }
        }
    }

    private void checkCommonActions(ActionResult actionResult) {
        switch (actionResult) {
            case DIE -> {
                soundPlayer.stopBackgroundMusic();
                gameFinished(DIE_MESSAGE);
            }
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
        jPanelMap = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jbtnUp = new javax.swing.JButton();
        jbtnDown = new javax.swing.JButton();
        jbtnRight = new javax.swing.JButton();
        jbtnLeft = new javax.swing.JButton();
        jlabelScoreText = new javax.swing.JLabel();
        jlabelScore = new javax.swing.JLabel();
        jlabelTurnsLeftText = new javax.swing.JLabel();
        jlabelTurnsLeft = new javax.swing.JLabel();
        jbtnSave = new javax.swing.JButton();
        jbtnExit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmenuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jmenuService = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Game");
        setName("FrameGame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(550, 400));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(312, 437));

        jPanelMap.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelMap.setPreferredSize(new java.awt.Dimension(305, 305));

        javax.swing.GroupLayout jPanelMapLayout = new javax.swing.GroupLayout(jPanelMap);
        jPanelMap.setLayout(jPanelMapLayout);
        jPanelMapLayout.setHorizontalGroup(
            jPanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        jPanelMapLayout.setVerticalGroup(
            jPanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(203, 305));

        jbtnUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/up.png"))); // NOI18N
        jbtnUp.setToolTipText("Вверх");
        jbtnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpActionPerformed(evt);
            }
        });

        jbtnDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/down.png"))); // NOI18N
        jbtnDown.setToolTipText("Вниз");
        jbtnDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDownActionPerformed(evt);
            }
        });

        jbtnRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right.png"))); // NOI18N
        jbtnRight.setToolTipText("Вправо");
        jbtnRight.setMaximumSize(new java.awt.Dimension(63, 31));
        jbtnRight.setMinimumSize(new java.awt.Dimension(63, 31));
        jbtnRight.setPreferredSize(new java.awt.Dimension(63, 31));
        jbtnRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRightActionPerformed(evt);
            }
        });

        jbtnLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left.png"))); // NOI18N
        jbtnLeft.setToolTipText("Влево");
        jbtnLeft.setMaximumSize(new java.awt.Dimension(63, 31));
        jbtnLeft.setMinimumSize(new java.awt.Dimension(63, 31));
        jbtnLeft.setPreferredSize(new java.awt.Dimension(63, 31));
        jbtnLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLeftActionPerformed(evt);
            }
        });

        jlabelScoreText.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jlabelScoreText.setText("Количество очков: ");

        jlabelScore.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jlabelScore.setText("0");

        jlabelTurnsLeftText.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jlabelTurnsLeftText.setText("Осталось ходов: ");

        jlabelTurnsLeft.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jlabelTurnsLeft.setText("0");

        jbtnSave.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jbtnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jbtnSave.setText("Сохранить");
        jbtnSave.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnSave.setIconTextGap(10);
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });

        jbtnExit.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jbtnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jbtnExit.setText("Выйти из игры");
        jbtnExit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnExit.setIconTextGap(10);
        jbtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jbtnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnDown, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jbtnRight, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jbtnUp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlabelTurnsLeftText)
                            .addComponent(jlabelScoreText))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlabelScore, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabelTurnsLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnUp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnRight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jbtnDown, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabelScoreText, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelScore))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabelTurnsLeftText)
                    .addComponent(jlabelTurnsLeft))
                .addGap(18, 18, 18)
                .addComponent(jbtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMap, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelMap, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jmenuFile.setText("Файл");

        jMenuItem1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jMenuItem1.setText("Сохранить игру");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmenuFile.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jMenuItem2.setText("Выйти из игры");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jmenuFile.add(jMenuItem2);

        jMenuBar1.add(jmenuFile);

        jmenuService.setText("Сервис");

        jMenuItem3.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stat.png"))); // NOI18N
        jMenuItem3.setText("Статистика");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jmenuService.add(jMenuItem3);

        jMenuBar1.add(jmenuService);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        System.out.println(evt.getKeyCode());
    }//GEN-LAST:event_formKeyPressed

    private void jbtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnExitActionPerformed

    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnSaveActionPerformed

    private void jbtnLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLeftActionPerformed
        moveGameObject(MovingDirection.LEFT, GameObjectType.GOLDMAN);

    }//GEN-LAST:event_jbtnLeftActionPerformed

    private void jbtnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRightActionPerformed
        moveGameObject(MovingDirection.RIGHT, GameObjectType.GOLDMAN);

    }//GEN-LAST:event_jbtnRightActionPerformed

    private void jbtnDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDownActionPerformed
        moveGameObject(MovingDirection.DOWN, GameObjectType.GOLDMAN);

    }//GEN-LAST:event_jbtnDownActionPerformed

    private void jbtnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpActionPerformed
        moveGameObject(MovingDirection.UP, GameObjectType.GOLDMAN);
    }//GEN-LAST:event_jbtnUpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelMap;
    private javax.swing.JButton jbtnDown;
    private javax.swing.JButton jbtnExit;
    private javax.swing.JButton jbtnLeft;
    private javax.swing.JButton jbtnRight;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnUp;
    private javax.swing.JLabel jlabelScore;
    private javax.swing.JLabel jlabelScoreText;
    private javax.swing.JLabel jlabelTurnsLeft;
    private javax.swing.JLabel jlabelTurnsLeftText;
    private javax.swing.JMenu jmenuFile;
    private javax.swing.JMenu jmenuService;
    // End of variables declaration//GEN-END:variables

}
