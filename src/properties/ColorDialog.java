/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class ColorDialog extends javax.swing.JPanel {

    private ImageIcon strokeIcon;
    private ImageIcon fillIcon;
    private Color strokeColor;
    private Color fillColor;
    
    public ColorDialog() {
        strokeIcon = new ColorCell(Color.BLACK);
        fillIcon = new ColorCell(Color.WHITE);
        strokeColor = Color.BLACK;
        fillColor = Color.WHITE;
        initComponents();
        
        //init mau cho nut
        // neu la anh thi init luon trong generated cx dc
        bStrokeColor.setIcon(strokeIcon);
        bFillColor.setIcon(fillIcon);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
         // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
         private void initComponents() {

                  buttonGroup1 = new javax.swing.ButtonGroup();
                  buttonGroup2 = new javax.swing.ButtonGroup();
                  bFillColor = new javax.swing.JToggleButton();
                  jLabel2 = new javax.swing.JLabel();
                  bStrokeColor = new javax.swing.JToggleButton();
                  jLabel1 = new javax.swing.JLabel();
                  colorPane = new properties.ColorPane();

                  buttonGroup1.add(bFillColor);
                  bFillColor.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    bFillColorActionPerformed(evt);
                           }
                  });

                  jLabel2.setText("      Fill");

                  buttonGroup1.add(bStrokeColor);
                  bStrokeColor.setSelected(true);

                  jLabel1.setText("  Stroke");

                  colorPane.addMouseListener(new java.awt.event.MouseAdapter() {
                           public void mousePressed(java.awt.event.MouseEvent evt) {
                                    colorPaneMousePressed(evt);
                           }
                  });

                  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                  this.setLayout(layout);
                  layout.setHorizontalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                                    .addContainerGap(10, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(bStrokeColor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(jLabel1))
                                    .addGap(6, 6, 6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(jLabel2)
                                             .addComponent(bFillColor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(colorPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                  );
                  layout.setVerticalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(layout.createSequentialGroup()
                                                      .addComponent(bStrokeColor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addGap(11, 11, 11)
                                                      .addComponent(jLabel1))
                                             .addGroup(layout.createSequentialGroup()
                                                      .addComponent(bFillColor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addGap(13, 13, 13)
                                                      .addComponent(jLabel2))
                                             .addGroup(layout.createSequentialGroup()
                                                      .addContainerGap()
                                                      .addComponent(colorPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  );
         }// </editor-fold>//GEN-END:initComponents

    private void bFillColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFillColorActionPerformed
    }//GEN-LAST:event_bFillColorActionPerformed

    private void colorPaneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorPaneMousePressed
        if (bStrokeColor.isSelected()) {
            strokeIcon = colorPane.getImage();
            strokeColor = colorPane.getColor();
            bStrokeColor.setIcon(strokeIcon);
        } else if (bFillColor.isSelected()) {
            fillIcon = colorPane.getImage();
            fillColor = colorPane.getColor();
            bFillColor.setIcon(fillIcon);
        }
    }//GEN-LAST:event_colorPaneMousePressed


         // Variables declaration - do not modify//GEN-BEGIN:variables
         private javax.swing.JToggleButton bFillColor;
         private javax.swing.JToggleButton bStrokeColor;
         private javax.swing.ButtonGroup buttonGroup1;
         private javax.swing.ButtonGroup buttonGroup2;
         private properties.ColorPane colorPane;
         private javax.swing.JLabel jLabel1;
         private javax.swing.JLabel jLabel2;
         // End of variables declaration//GEN-END:variables

    public Color getStrokeColor() {
        return strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

 
  

}
