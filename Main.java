/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.RoutingData;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import waypoint.EventWaypoint;
import waypoint.MyWaypoint;
import waypoint.WaypointRender;


public class Main extends javax.swing.JFrame {

    private final Set<MyWaypoint> waypoints = new HashSet<>();
    private  Set<MyWaypoint> busStationWaypoints = new HashSet<>();
    private List<RoutingData> routingData = new ArrayList<>();
    private EventWaypoint event;
    private Point mousePosition;
    private double a=0,b=0;
    
    public Main() {
        initComponents();
        init("Beirut");
    }
    
    private void init(String city){
        TileFactoryInfo info=new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
        GeoPosition geo=new GeoPosition(33.888630,35.495480);
        jXMapViewer.setAddressLocation(geo);
        jXMapViewer.setZoom(8);
        
        
        //create event mouse move
        MouseInputListener mm=new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mm);
        jXMapViewer.addMouseMotionListener(mm);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer));
        

        event=getEvent();
    }
    

    
    private void initBusWaypoint() {
          WaypointPainter<MyWaypoint> wp = new WaypointRender();
          wp.setWaypoints(busStationWaypoints);
          jXMapViewer.setOverlayPainter(wp);
          for (MyWaypoint d : busStationWaypoints) {
              jXMapViewer.add(d.getButton());
          }
         
      }
    
    private void clearBusWaypoint() {
    	for (MyWaypoint d : busStationWaypoints) {
            jXMapViewer.remove(d.getButton());
        }      
    }
    
    private void initWaypoint() {
        WaypointPainter<MyWaypoint> wp = new WaypointRender();
        wp.setWaypoints(waypoints);
        jXMapViewer.setOverlayPainter(wp);
        for (MyWaypoint d : waypoints) {
            jXMapViewer.add(d.getButton());
        }
        //  Routing Data
        if (waypoints.size() == 2) {
            GeoPosition start = null;
            GeoPosition end = null;
            for (MyWaypoint w : waypoints) {
                if (w.getPointType() == MyWaypoint.PointType.START) {
                    start = w.getPosition();
                } else if (w.getPointType() == MyWaypoint.PointType.END) {
                    end = w.getPosition();
                }
            }
            if (start != null && end != null) {
//                routingData = RoutingService.getInstance().routing(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());

            } else {
                routingData.clear();
            }
            jXMapViewer.setRoutingData(routingData);
        }
    }

    private void addWaypoint(MyWaypoint waypoint) {
        for (MyWaypoint d : waypoints) {
            jXMapViewer.remove(d.getButton());
        }
        Iterator<MyWaypoint> iter = waypoints.iterator();
        while (iter.hasNext()) {
            if (iter.next().getPointType() == waypoint.getPointType()) {
                iter.remove();
            }
        }
        waypoints.add(waypoint);
        initWaypoint();
    }
    
 
    
  
    private EventWaypoint getEvent(){
        return new EventWaypoint(){
            @Override
            public void selected(MyWaypoint waypoints){
              JOptionPane.showMessageDialog(Main.this, waypoints.getName());
            }
        };
    }
    /**
     * This method is called from within the constructor to initialize the form.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
        jPopupMenu1 = new javax.swing.JPopupMenu();
        menuStart = new javax.swing.JMenuItem();
        menuEnd = new javax.swing.JMenuItem();
        jXMapViewer = new data.JXMapViewerCustom();
        ComboMapType = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        JButton1 = new javax.swing.JButton();
        JButton2 = new javax.swing.JButton();
        JButton3 = new javax.swing.JButton();
        
        
      JButtonClear = new javax.swing.JButton();
      JButtonClear.setText("Clear");
      
      JButtonClear.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
        	  for (MyWaypoint d : waypoints) {
                  jXMapViewer.remove(d.getButton());
              }
              waypoints.clear();
              initWaypoint();
              }});
        JButton1.setText("Beirut");
        
        JButton1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	 clearBusWaypoint();
	        	 busStationWaypoints.clear();
	        	 BusSelector.SetBusLocation(busStationWaypoints, event,"Beirut");
	             initBusWaypoint();
	        }
	    });
        
        JButton2.setText("Jnoub");
        
        JButton2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	 clearBusWaypoint();
	        	 busStationWaypoints.clear();
	        	 BusSelector.SetBusLocation(busStationWaypoints, event,"Jnoub");
	             initBusWaypoint();
	        }
	    });
        
        
        JButton3.setText("Baalbek");
        
        JButton3.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	             clearBusWaypoint();
	        	 busStationWaypoints.clear();
	        	 BusSelector.SetBusLocation(busStationWaypoints, event,"Baalbek");
	             initBusWaypoint();
	        }
	    });
        

        menuStart.setText("Start");
        menuStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStartActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuStart);

        menuEnd.setText("End");
        menuEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEndActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuEnd);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jXMapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXMapViewerMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jXMapViewerMouseReleased(evt);
            }
        });

        ComboMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open Stree", "Virtual Earth", "Hybrid", "Satellite" }));
        ComboMapType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboMapTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXMapViewerLayout = new javax.swing.GroupLayout(jXMapViewer);
        jXMapViewer.setLayout(jXMapViewerLayout);
        jXMapViewerLayout.setHorizontalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXMapViewerLayout.createSequentialGroup()
                .addContainerGap()
              .addComponent(JButtonClear)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addComponent(ComboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
            	.addComponent(JButton1)
            	 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
        		.addComponent(JButton2)
        		 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
				.addComponent(JButton3))
        );
        jXMapViewerLayout.setVerticalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addGroup(jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JButtonClear,javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JButton1,javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            		.addComponent(JButton2 ,javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
    				.addComponent(JButton3 ,javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 482, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void ComboMapTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboMapTypeActionPerformed
        TileFactoryInfo info;
        int index=ComboMapType.getSelectedIndex();
        if(index==0){
            info=new OSMTileFactoryInfo();
        }else if(index ==1){
            info=new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        }else if(index==2){
            info=new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
        }else{
            info=new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
        }
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
    }//GEN-LAST:event_ComboMapTypeActionPerformed

    private void menuStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStartActionPerformed
      GeoPosition geo =jXMapViewer.convertPointToGeoPosition(mousePosition);
      MyWaypoint waypoint=new MyWaypoint("Start Location",MyWaypoint.PointType.START,event,new GeoPosition(geo.getLatitude(),geo.getLongitude()));
      addWaypoint(waypoint);
    }//GEN-LAST:event_menuStartActionPerformed

    private void menuEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEndActionPerformed
        GeoPosition geo=jXMapViewer.convertPointToGeoPosition(mousePosition);
        MyWaypoint waypoint=new MyWaypoint("End Location",MyWaypoint.PointType.END,event,new GeoPosition(geo.getLatitude(),geo.getLongitude()));
        addWaypoint(waypoint);
    }//GEN-LAST:event_menuEndActionPerformed

    private void jXMapViewerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXMapViewerMouseReleased
        if(SwingUtilities.isRightMouseButton(evt)){
            mousePosition=evt.getPoint();
            jPopupMenu1.show(jXMapViewer,evt.getX(),evt.getY());
            
        }
    }//GEN-LAST:event_jXMapViewerMouseReleased

    
    private void jXMapViewerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXMapViewerMouseClicked
 
        GeoPosition geo=jXMapViewer.convertPointToGeoPosition(evt.getPoint());
        MyWaypoint waypoint=new MyWaypoint("Start Location",MyWaypoint.PointType.START,event,new GeoPosition(geo.getLatitude(),geo.getLongitude()));
        addWaypoint(waypoint);
        a=geo.getLatitude();
        b=geo.getLongitude();
        
        int counter = 0;
       
        double min = 0;
        String closestStation = "";
        
        for (MyWaypoint location : busStationWaypoints) {
        	double temp;
        	location.getButton().setIcon(new ImageIcon(getClass().getResource("/icon/bus-stop-pointer.png")));
        	temp = distance_Between_LatLong(location.getPosition().getLatitude(),location.getPosition().getLongitude(),a,b);
        	
        	if(counter == 0) {
        		min = temp;
        		closestStation = location.getName();
        		
        	}
        	counter+=1;
        	if(temp < min) {
        		min = temp;
        		closestStation = location.getName();
        		
        	}
        	
        		
	        }
        

        for (MyWaypoint location : busStationWaypoints) {
        		if(location.getName() == closestStation) {
        			location.getButton().setIcon(new ImageIcon(getClass().getResource("/icon/busred.png")));
        		}
        	
            		
	        }
       
//        ArrayList<String> values =  BusSelector.ClosestStation(busStationWaypoints, a, b);
       
        jLabel1.setText("The closest sation is = "+closestStation +" and the distance to it is = " + new DecimalFormat("##.##").format(min));
          

      
    }//GEN-LAST:event_jXMapViewerMouseClicked
    public static double distance_Between_LatLong(double lat1, double lon1, double lat2, double lon2) {
        lon1 = Math.toRadians(lon1);
      lon2 = Math.toRadians(lon2);
      lat1 = Math.toRadians(lat1);
      lat2 = Math.toRadians(lat2);

      // Haversine formula
      double dlon = lon2 - lon1;
      double dlat = lat2 - lat1;
      double a = Math.pow(Math.sin(dlat / 2), 2)
               + Math.cos(lat1) * Math.cos(lat2)
               * Math.pow(Math.sin(dlon / 2),2);
           
      double c = 2 * Math.asin(Math.sqrt(a));

      // Radius of earth in kilometers. Use 3956
      // for miles
      double r = 6371;

      // calculate the result
      return(c * r);
  }
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboMapType;
    private javax.swing.JLabel jLabel1;
    
    
    private javax.swing.JPopupMenu jPopupMenu1;
    private data.JXMapViewerCustom jXMapViewer;
    private javax.swing.JMenuItem menuEnd;
    private javax.swing.JMenuItem menuStart;

  private javax.swing.JButton JButtonClear;
    private javax.swing.JButton JButton1;
    private javax.swing.JButton JButton2;
    private javax.swing.JButton JButton3;
    // End of variables declaration//GEN-END:variables
}
