/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaree;
import Backbone.WriteToFile;
import Backbone.*;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Sandjes1046
 */
public class Availability extends javax.swing.JFrame {
     //Takes the indices of what the user selects and converts them to a format our Backbone (Back end) functions accept
    int[] make_valid(int[] not_valid, String day) {
        int count;
        int[] valid = new int[not_valid.length];
        //if the day label is monday format the indices so they equal their equavalent in our availability array
        if ("monday".equals(day)) {
            for (int i = 0; i < not_valid.length; i++) {
                if (not_valid[i] == 0) {
                    valid[i] = 0;
                } else if (not_valid[i] > 0) {
                    valid[i] = not_valid[i] * 4;
                    if (i == not_valid.length - 1) {
                        valid[i] = not_valid[i] * 4 - 1;
                    }
                }
            }
        }
        //if the day label is tuesday format the indices so they equal their equavalent in our availability array
        if ("tuesday".equals(day)) {
            count = 0;
            for (int i = 0; i < not_valid.length; i++) {
                if (not_valid[i] == 0) {
                    valid[i] = 32;
                } else if (not_valid[i] > 0) {
                    count = not_valid[i] * 4 - 4;
                    valid[i] = 36 + not_valid[i] - not_valid[i] + count;
                    if (i == not_valid.length - 1) {
                        valid[i] = 36 + not_valid[i] - not_valid[i] + count - 1;
                    }
                }

            }
        }
        //if the day label is wednesday format the indices so they equal their equavalent in our availability array
        if ("wednesday".equals(day)) {
            count = 0;
            for (int i = 0; i < not_valid.length; i++) {
                if (not_valid[i] == 0) {
                    valid[i] = 64;
                } else if (not_valid[i] > 0) {
                    count = not_valid[i] * 4 - 4;
                    valid[i] = 68 + not_valid[i] - not_valid[i] + count;
                    if (i == not_valid.length - 1) {
                        valid[i] = 68 + not_valid[i] - not_valid[i] + count - 1;
                    }

                }
            }
        }
        //if the day label is thursday format the indices so they equal their equavalent in our availability array
        if ("thursday".equals(day)) {
            count = 0;
            for (int i = 0; i < not_valid.length; i++) {
                if (not_valid[i] == 0) {
                    valid[i] = 96;
                } else if (not_valid[i] > 0) {
                    count = not_valid[i] * 4 - 4;
                    valid[i] = 100 + not_valid[i] - not_valid[i] + count;
                    if (i == not_valid.length - 1) {
                        valid[i] = 100 + not_valid[i] - not_valid[i] + count - 1;
                    }
                }
            }
        }
        //if the day label is friday format the indices so they equal their equavalent in our availability array
        if ("friday".equals(day)) {
            count = 0;
            for (int i = 0; i < not_valid.length; i++) {
                if (not_valid[i] == 0) {
                    valid[i] = 128;
                } else if (not_valid[i] > 0) {
                    count = not_valid[i] * 4 - 4;
                    valid[i] = 132 + not_valid[i] - not_valid[i] + count;
                    if (i == not_valid.length - 1) {
                        valid[i] = 132 + not_valid[i] - not_valid[i] + count - 1;
                    }
                }

            }
        }

        return valid;
    }
    
    //this function makes a new 160 availability array from the array we made from the users selected indices
    //make_valid is called before this function
    int [] new_avail(int[] valid){
        int [] avail = new int[160];
        //makes a 160 array and if the indices equals the position in the 160 array it sets it equal to busy '0'
        for(int i = 0; i < 160; i++){
            for (int j = 0; j < valid.length;j++){
                if(i == valid[j]){
                    avail[i] = 0;
                }
            }
        }
        
        
        
        
    return avail;}
    /**
     * Creates new form Availability
     */
    public Availability() {
        initComponents();
        //jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Monday");

        jLabel2.setText("Tuesday");

        jLabel3.setText("Wednesday");

        jLabel4.setText("Thursday");

        jLabel5.setText("Friday");

        jButton1.setText("Change Availability");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList5.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList5);

        jList4.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList4);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setToolTipText("");
        jList1.setVisibleRowCount(10);
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jList2);

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jList3);

        jLabel6.setText("Hold CTRL or Commnad to select multiple times in the same day.");

        jLabel7.setText("Hold CTRL or Command");

        jButton2.setText("Clear All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Return to Calendar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(181, 181, 181)
                                .addComponent(jLabel3)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel4))
                            .addComponent(jButton3))
                        .addGap(60, 60, 60)
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel1)
                                                .addGap(41, 41, 41)
                                                .addComponent(jLabel2)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(78, 78, 78)
                                .addComponent(jButton2)))
                        .addGap(0, 43, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(24, 504, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                        .addGap(72, 72, 72)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton1))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int[] avail = new int[160];
        
        //makes an array of the indices seleted for the specific day and sends it to make_valid and the 
        //retun is asigned to another array to keep track of where in the process of formating I am
        int[] monday = jList1.getSelectedIndices();
        int[] valid_mon = make_valid(monday, "monday");
        //makes an array of the indices seleted for the specific day and sends it to make_valid and the 
        //retun is asigned to another array to keep track of where in the process of formating I am
        int[] tuesday = jList2.getSelectedIndices();
        int[] valid_tues = make_valid(tuesday, "tuesday");
        //makes an array of the indices seleted for the specific day and sends it to make_valid and the 
        //retun is asigned to another array to keep track of where in the process of formating I am
        int[] wednesday = jList3.getSelectedIndices();
        int[] valid_wed = make_valid(wednesday, "wednesday");
//makes an array of the indices seleted for the specific day and sends it to make_valid and the 
        //retun is asigned to another array to keep track of where in the process of formating I am
        int[] thursday = jList4.getSelectedIndices();
        int[] valid_thurs = make_valid(thursday, "thursday");
//makes an array of the indices seleted for the specific day and sends it to make_valid and the 
        //retun is asigned to another array to keep track of where in the process of formating I am
        int[] friday = jList5.getSelectedIndices();
        int[] valid_fri = make_valid(friday, "friday");

        //i save all the lengths of the arrays to copy the day arrays into a 160 array
        int len1 = valid_mon.length;
        int len2 = valid_tues.length + len1;
        int len3 = valid_wed.length + len2;
        int len4 = valid_thurs.length + len3;
        int len5 = valid_fri.length + len4;
        int[] valid = new int[len5];
        System.arraycopy(valid_mon, 0, valid, 0, len1);
        System.arraycopy(valid_tues, 0, valid, len1, valid_tues.length);
        System.arraycopy(valid_wed, 0, valid, len2, valid_wed.length);
        System.arraycopy(valid_thurs, 0, valid, len3, valid_thurs.length);
        System.arraycopy(valid_fri, 0, valid, len4, valid_fri.length);
        
        //formats the 160 array to our liking
        avail = new_avail(valid);
        //sends t he 160 array to replacec the users
       WriteToFile.replaceAvailability(Storage.idx, avail);
       JOptionPane.showMessageDialog(null,"Success!");
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //clears all users selections
        jList1.clearSelection();
        jList2.clearSelection();
        jList3.clearSelection();
        jList4.clearSelection();
        jList5.clearSelection();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new Calendar().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Availability.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Availability.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Availability.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Availability.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Availability().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JList<String> jList4;
    private javax.swing.JList<String> jList5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    // End of variables declaration//GEN-END:variables
}
