/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import paint.PaintState;
import shape.*;

public class ReplayPanel extends JPanel implements Runnable {

         private BufferedImage buff_img, org_img;
         private PaintState paintState;
         private Line line;
         private Rectangle rect;
         private Oval oval;
         private Pencil pencil;
         private Bucket bucket;
         private Eraser eraser;
         private int delay = 30;
         private boolean isPlaying;
         private JToggleButton bPlay;
         private Thread thread = null;
         private int currentState = 0;
         private int currentStep = 0;
         private int cStateElement = 0;
         private ArrayList<Point> listPoint;
         private ArrayList<DrawType> listState;
         private ArrayList<Integer> listDrawStep;
         private Graphics2D g2d, g2;

         public void setDelay(int delay) {
                  this.delay = (105 - delay) / 2;
         }

         public void setButton(JToggleButton bPlay) {
                  this.bPlay = bPlay;
         }

         public ReplayPanel() {
                  listState = new ArrayList<>();
                  listDrawStep = new ArrayList<>();
                  line = new Line();
                  rect = new Rectangle();
                  oval = new Oval();
                  pencil = new Pencil();
                  bucket = new Bucket();
                  eraser = new Eraser();
                  isPlaying = false;

                  initComponents();
                  paintState = new PaintState();
                  this.setSize(909, 439);
                  //Khoi tao anh
                  org_img = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
                  g2 = (Graphics2D) org_img.getGraphics();
                  g2.setColor(Color.WHITE);
                  g2.fillRect(0, 0, getSize().width, getSize().height);
                  g2.dispose();
                  paintState.setData(org_img);
                  buff_img = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
                  g2 = (Graphics2D) org_img.getGraphics();
                  g2.drawImage(org_img, 0, 0, null);
                  g2.dispose();
                  readState();

         }

         public void startReplay() {
                  isPlaying = true;
                  if (thread == null) {
                           refresh();
                           thread = new Thread(this);
                           isPlaying = true;
                           thread.start();
                           //thread.resume();
                  } else {
                           thread.resume();
                  }

         }

         public void pauseReplay() {
                  isPlaying = false;
                  //       thread.suspend();
         }

         public void flush() {
                  if (isPlaying) {
                           bPlay.setIcon(new ImageIcon(getClass().getResource("/icon/pause.png")));
                           isPlaying = false;
                  }
                  //cấp phát một vùng nhớ mới để lấy trạng thái mới mà không tác động đé padpaint
                  paintState = new PaintState();
                  org_img = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
                  g2 = (Graphics2D) org_img.getGraphics();
                  g2.setColor(Color.WHITE);
                  g2.fillRect(0, 0, getSize().width, getSize().height);
                  g2.dispose();
                  paintState.setData(org_img);
         }

         public boolean isPlaying() {
                  return isPlaying;
         }

         @SuppressWarnings("unchecked")
         // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
         private void initComponents() {

                  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                  this.setLayout(layout);
                  layout.setHorizontalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGap(0, 400, Short.MAX_VALUE)
                  );
                  layout.setVerticalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGap(0, 300, Short.MAX_VALUE)
                  );
         }// </editor-fold>//GEN-END:initComponents


         // Variables declaration - do not modify//GEN-BEGIN:variables
         // End of variables declaration//GEN-END:variables
    public void setPaintState(PaintState paintState) {
                  this.paintState = paintState;
                  readState();
         }

         public void readState() {
                  listPoint = new ArrayList<>();
                  listState = paintState.getListState();
                  listDrawStep = paintState.getDrawStepList();
                  org_img.flush();
                  buff_img.flush();
                  System.gc();
                  org_img = null;
                  buff_img = null;
                  int w = paintState.getWidth();
                  int h = paintState.getHeight();
                  int[] data = paintState.getData();
                  org_img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                  org_img.getRaster().setPixels(0, 0, w, h, data);
                  buff_img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                  g2d = (Graphics2D) buff_img.getGraphics();
                  g2d.drawImage(org_img, 0, 0, null);
                  this.setSize(new Dimension(w, h));
                  this.setPreferredSize(new Dimension(w, h));
                  this.setMinimumSize(new Dimension(w, h));
                  this.revalidate();

         }

         public void refresh() {
                  g2 = (Graphics2D) buff_img.getGraphics();
                  g2.drawImage(org_img, 0, 0, null);
                  g2.dispose();
                  currentState = 0;
                  currentStep = 0;
                  cStateElement = 0;
                  repaint();
         }

         public void dispose() {
                  org_img = null;
                  buff_img = null;
                  line = null;
                  rect = null;
                  oval = null;
                  eraser = null;
                  pencil = null;
                  bucket = null;

                  g2.dispose();
                  g2d.dispose();
                  System.gc();
         }

         public void paintComponent(Graphics g) {
                  super.paintComponent(g);
                  g2 = (Graphics2D) g;
                  g.drawImage(buff_img, 0, 0, null);
                  // day chinh la cai luong anh chinh buff_img 
                  Graphics2D g2dBuffer = (Graphics2D) buff_img.getGraphics();

                  if (isPlaying) {

                           if (currentState < listState.size() && listPoint != null && cStateElement < listPoint.size()) {

                                    DrawType drawType = listState.get(currentState);

                                    if (drawType instanceof Line) {
                                             line.setPoint(listPoint.get(0), listPoint.get(cStateElement));
                                             line.draw(g2);
                                             //Neu da dat trang thai cuoi thi ve len bufer
                                             if (cStateElement == listPoint.size() - 1) {
                                                      line.draw(g2dBuffer);
                                             }
                                    } else if (drawType instanceof Rectangle) {
                                             rect.setPoint(listPoint.get(0), listPoint.get(cStateElement));
                                             rect.draw(g2);
                                             System.out.println("currentState: " + currentState);
                                             System.out.println("currentStep: " + currentStep);
                                             System.out.println("currentStateElement: " + cStateElement);
                                             System.out.println("\n");
                                             if (cStateElement == listPoint.size() - 1) {
                                                      rect.draw(g2dBuffer);
                                             }

                                    } else if (drawType instanceof Oval) {
                                             oval.setPoint(listPoint.get(0), listPoint.get(cStateElement));
                                             oval.draw(g2);
                                             if (cStateElement == listPoint.size() - 1) {
                                                      oval.draw(g2dBuffer);
                                             }

                                    } else if (drawType instanceof Pencil) {
                                             if (cStateElement < listPoint.size() - 1) {
                                                      System.out.println("currentState: " + currentState);
                                                      System.out.println("currentStep: " + currentStep);
                                                      System.out.println("currentStateElement: " + cStateElement);
                                                      System.out.println("\n");
                                                      pencil.setPoint(listPoint.get(cStateElement), listPoint.get(cStateElement + 1));
                                                      pencil.draw(g2dBuffer);
                                             }
                                    } else if (drawType instanceof Eraser) {
                                             if (cStateElement < listPoint.size() - 1) {
                                                      eraser.setPoint(listPoint.get(cStateElement), listPoint.get(cStateElement + 1));
                                                      eraser.draw(g2dBuffer);
                                             }
                                    }
                                    //   g2dBuffer.dispose();
                           }
                  }
         }

         @Override
         public void run() {

                  while (currentStep < paintState.getDrawStepList().size()) {
                           if (isPlaying == false) {
                                    thread.suspend();
                           }

                           int inStepState = listDrawStep.get(currentStep);
                           //Lay tung trang thia cua buoc ve
                           switch (inStepState) {
                                    case PaintState.PAINTTING:
                                             if (listPoint == null) {
                                                      DrawType inDrawType = listState.get(currentState);
                                                      if (inDrawType instanceof Line) {
                                                               line = (Line) inDrawType;
                                                               listPoint = line.getDraggedPoint();

                                                      } else if (inDrawType instanceof Rectangle) {
                                                               rect = (Rectangle) inDrawType;
                                                               listPoint = rect.getDraggedPoint();

                                                      } else if (inDrawType instanceof Oval) {
                                                               oval = (Oval) inDrawType;
                                                               listPoint = oval.getDraggedPoint();

                                                      } else if (inDrawType instanceof Pencil) {
                                                               pencil = (Pencil) inDrawType;
                                                               listPoint = pencil.getDraggedPoint();

                                                      } else if (inDrawType instanceof Bucket) {
                                                               Bucket inBucket = (Bucket) inDrawType;
                                                               inBucket.draw(buff_img);
                                                      }
                                             } else {   //Neu diem da duoc khoi tao
                                                      //Kiem tra xem hinh hien tai da dat den trang thai cuoi cung cua hinh chua

                                                      if (cStateElement == listPoint.size()) {
                                                               listPoint = null;
                                                               cStateElement = 0;
                                                               currentState++;
                                                               currentStep++;
                                                      } else {   //Neu van con trang thai cho viec ve hinh va trong danh sach cac diem cua hinh hien tai
                                                               //Diem con chua phai la diem cuoi cung thi se tang trang thai cua diem hien tai len mot
                                                               ++cStateElement;

                                                      }
                                             }
                                             break;
                           }

                           if (inStepState != PaintState.PAINTTING) {
                                    currentStep++;
                           }
                           try {
                                    Thread.sleep(delay);
                           } catch (InterruptedException ex) {
                                    System.out.println("replayPanel in run() method err");
                           }
                           repaint();
                  }

                  System.gc();
                  isPlaying = false;
                  thread = null;
                  bPlay.setIcon(new ImageIcon(getClass().getResource("/icon/pause.png")));

         }

         public BufferedImage getBuffer() {
                  return buff_img;
         }

}
