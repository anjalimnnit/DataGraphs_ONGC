/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Month;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
//import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author DELL
 */
public class DualAxis extends ApplicationFrame {

    ArrayList <Integer> x=new ArrayList <>();//date
    ArrayList <Integer> y=new ArrayList <>();//numeric value1
    ArrayList < Integer> z=new ArrayList <>();//numeric value2
    public DualAxis(final String title) {
        super(title);
        //---------------------------DATABASE CONNECTIVITY--------------------------------
        try{
            String host="jdbc:mysql://localhost:3306/sid";
            Connection conn=DriverManager.getConnection(host,"root","0945");
            String sql="select * from test1";
            PreparedStatement p=conn.prepareStatement(sql);
            ResultSet r=p.executeQuery();
            while(r.next())
            {
                String a=r.getString(1);
                String b=r.getString(2);
                int x1=Integer.parseInt(a);
                int y1=Integer.parseInt(b);
                x.add(x1);
                y.add(y1);
                z.add(Integer.parseInt(r.getString(3)));
                
            }
            
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"ERROR!:\n"+e);
        }
        //-------YET TO IMPLEMENT DATABASE INTO THIS---------
        //----------------------------------------GRAPH CREATION------------------------------------------------
        final String chartTitle="Heading";
        final XYDataset dataset=createDataset1();
        
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle, "x-axis", "y-axis",dataset, true,true,false);
        
        final XYPlot plot=chart.getXYPlot();
        final NumberAxis axis2=new NumberAxis("y'-axis");
        
        axis2.setAutoRangeIncludesZero(false);
        plot.setRangeAxis(1,axis2);
        plot.setDataset(1,createDataset2());
        plot.mapDatasetToRangeAxis(1, 1);
        final XYItemRenderer renderer=plot.getRenderer();
        renderer.setToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        if(renderer instanceof StandardXYItemRenderer)
        {
            final StandardXYItemRenderer rr=(StandardXYItemRenderer) renderer;
            rr.setPlotLines(true);
        }
        
        final StandardXYItemRenderer renderer2=new StandardXYItemRenderer();
        renderer2.setSeriesPaint(0,Color.BLACK);
        renderer.setToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
        plot.setRenderer(1, renderer2);
        
        final DateAxis axis=(DateAxis)plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        final ChartPanel chartpanel=new ChartPanel(chart){
          @Override
            public Dimension getPreferredSize() {
                return new Dimension(500, 270);
            }  
        };
//        chartpanel.setPreferredSize(new java.awt.Dimension(500,270));
//        setContentPane(chartpanel);
        
        chartpanel.setMouseWheelEnabled(true);
        add(chartpanel,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        initComponents();
    }

    
    private XYDataset createDataset1()
    {
        final TimeSeries s1=new TimeSeries("line type 1",Month.class);
        s1.add(new Month(2, 2001),181.8);
        s1.add(new Month(3, 2001),167.6);
        s1.add(new Month(4, 2001),153.8);
        s1.add(new Month(5, 2001),167.6);
        s1.add(new Month(6, 2001),158.8);
        
        final TimeSeriesCollection dataset=new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }
    
    
    private XYDataset createDataset2()// Similar to createDataset1()
    {
        final TimeSeries s2=new TimeSeries("line type 2",Month.class);
        s2.add(new Month(2, 2001),429.6);
        s2.add(new Month(3, 2001),323.2);
        s2.add(new Month(4, 2001),417.2);
        s2.add(new Month(5, 2001),624.1);
        s2.add(new Month(6, 2001),422.6);
        
        final TimeSeriesCollection dataset=new TimeSeriesCollection();
        dataset.addSeries(s2);
        return dataset;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(back)
                .addContainerGap(225, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(296, Short.MAX_VALUE)
                .addComponent(back)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.hide();
        new Select_Params().show();
    }//GEN-LAST:event_backActionPerformed

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
            java.util.logging.Logger.getLogger(DualAxis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DualAxis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DualAxis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DualAxis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DualAxis(null).setVisible(true);
            }
        });
//        final NewJFrame demo=new NewJFrame("Dual axis demo");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    // End of variables declaration//GEN-END:variables
}
