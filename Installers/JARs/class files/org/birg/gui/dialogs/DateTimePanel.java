package org.birg.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;








public class DateTimePanel
  extends JPanel
{
  private String[] defaultYears = { "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013" };
  private String[] defaultMonths = {
    "January", "February", "March", "April", "May", "June", "July", "August", 
    "September", "October", "November", "December" };
  private String[] defaultDays = {
    "Sun", "Mon", "Tues", "Wed", "Thur", "Fri", 
    "Sat" };
  private String[] defaultHours = {
    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
  
  private String[] defaultMins;
  private String[] amPm = { "AM", "PM" };
  
  protected CalendarModel cm;
  
  private JComboBox monthList;
  
  private JComboBox yearList;
  private JComboBox hourList;
  
  public DateTimePanel(Date initialDate)
  {
    this.d = initialDate;
    this.cm = new CalendarModel(this.d, this.defaultMonths, this.defaultDays);
    
    this.defaultMins = new String[60];
    for (int i = 0; i < 60; i++) {
      if (i < 10) {
        this.defaultMins[i] = ("0" + i);
      } else {
        this.defaultMins[i] = i;
      }
    }
    
    this.table = new JTable(this.cm);
    this.table.setShowGrid(false);
    this.table.setRowSelectionAllowed(false);
    this.table.setCellSelectionEnabled(true);
    JScrollPane pane = new JScrollPane(this.table);
    pane.setPreferredSize(new Dimension(300, 120));
    
    this.monthList = new JComboBox(this.cm.getLocalizedMonthNames());
    this.monthList.setSelectedItem(this.cm.getLocalizedMonthName());
    this.yearList = new JComboBox(this.defaultYears);
    this.yearList.setSelectedItem(this.cm.getYear());
    JPanel topPanel = new JPanel(new FlowLayout());
    topPanel.add(this.monthList);
    topPanel.add(this.yearList);
    
    this.hourList = new JComboBox(this.defaultHours);
    this.hourList.setSelectedItem(this.cm.getHour());
    this.minuteList = new JComboBox(this.defaultMins);
    this.minuteList.setSelectedItem(this.cm.getMinute());
    this.amPmList = new JComboBox(this.amPm);
    this.amPmList.setSelectedItem(this.cm.getAmPm());
    
    JPanel bottomPanel = new JPanel(new FlowLayout());
    bottomPanel.add(this.hourList);
    bottomPanel.add(new JLabel(":"));
    bottomPanel.add(this.minuteList);
    bottomPanel.add(this.amPmList);
    

    setLayout(new BorderLayout());
    add(topPanel, "North");
    add(pane, "Center");
    add(bottomPanel, "South");
    

    this.monthList.addActionListener(new DateTimePanel.1(this));
    




    this.yearList.addActionListener(new DateTimePanel.2(this));
    





    this.hourList.addActionListener(new DateTimePanel.3(this));
    





    this.minuteList.addActionListener(new DateTimePanel.4(this));
    





    this.amPmList.addActionListener(new DateTimePanel.5(this));
  }
  

  private JComboBox minuteList;
  
  private JComboBox amPmList;
  
  JLabel timeZone;
  
  private JTable table;
  
  private Date d;
  
  public Date getDate()
  {
    return this.cm.getSelectedDate();
  }
}