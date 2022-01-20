/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

/**
 *
 * @author Mario
 */
public class Click_JList_Listener extends MouseAdapter{

    public void mouseClicked(MouseEvent evt) {
        JList list = (JList)evt.getSource();
        if (evt.getClickCount() == 2) {

            // Double-click detected
            int index = list.locationToIndex(evt.getPoint());
        } else if (evt.getClickCount() == 3) {

            // Triple-click detected
            int index = list.locationToIndex(evt.getPoint());
        }
    }
    
}
