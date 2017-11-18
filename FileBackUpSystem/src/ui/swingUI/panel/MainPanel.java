package ui.swingUI.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.BeBackUpDirectory;
import ui.swingUI.button.ClearStyleButton;
import ui.swingUI.button.DoBackupButton;
import ui.swingUI.button.ExchangeButton;
import ui.swingUI.button.HtlButton;
import ui.swingUI.button.LthButton;
import ui.swingUI.button.NewPathButton;
import ui.swingUI.label.BBDLabel;
import ui.swingUI.label.HangOnLIstLabel;
import ui.swingUI.label.ListLabel;
import ui.swingUI.label.ToPathLabel;
import ui.swingUI.other.SpecialBorder;
import ui.swingUI.other.ToPathTextField;
import ui.swingUI.size.ComponentSize;
import ui.swingUI.size.IntervalSize;

public class MainPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private JFrame jf;
	private JTextField toPath;
	private MyListScrollPane2<BBDLabel> list;
	private MyListScrollPane2<BBDLabel> HnagOnList;
	
	private JLabel toPathLabel;
	private JLabel listLabel;
	private JLabel hangOnListLabel;
	
	private JLabel lthButton;
	private JLabel htlButton;
	private JLabel exchangeButton;
	private JLabel newPathButton;
	private JLabel clearStyleButton;
	private JLabel doBackupButton;
	
	
	public MainPanel(JFrame jf) {
		this.setLayout(null);
		this.jf=jf;
		
		//toPath=new JTextField();
		list=new MyListScrollPane2<>(jf);
		list.setBounds(IntervalSize.getInterval_1_Size_X()+ComponentSize.getNOW_STRING_LABEL_SIZE_X()+IntervalSize.getInterval_3_Size_X() - 10,
				IntervalSize.getInterval_2_Size_Y()+ComponentSize.getNOW_TO_PATH_SIZE_Y()+IntervalSize.getInterval_3_Size_Y(),
				ComponentSize.getNOW_LIST_PANEL_SIZE_X(), ComponentSize.getNOW_LIST_PANEL_SIZE_Y());
		HnagOnList=new MyListScrollPane2<>(jf);
		HnagOnList.setBounds(IntervalSize.getInterval_1_Size_X()+ComponentSize.getNOW_STRING_LABEL_SIZE_X()+IntervalSize.getInterval_3_Size_X() - 10,
				IntervalSize.getInterval_2_Size_Y()+ComponentSize.getNOW_TO_PATH_SIZE_Y()+3*IntervalSize.getInterval_3_Size_Y()
					+ComponentSize.getNOW_3_BUTTON_SIZE_Y()+ComponentSize.getNOW_LIST_PANEL_SIZE_Y(),
				ComponentSize.getNOW_HANG_ON_LIST_PANEL_SIZE_X(), ComponentSize.getNOW_HANG_ON_LIST_PANEL_SIZE_Y());
		
		toPathLabel=new ToPathLabel();
		listLabel=new ListLabel();
		hangOnListLabel=new HangOnLIstLabel();
		
		toPath=new ToPathTextField();
		
		lthButton=new LthButton();
		htlButton=new HtlButton();
		exchangeButton=new ExchangeButton();
		
		newPathButton=new NewPathButton();
		clearStyleButton=new ClearStyleButton();
		doBackupButton=new DoBackupButton();
		
		list.add(new BBDLabel(new BeBackUpDirectory("testpath6", 0,TimeUnit.DAYS, "d:\test", "Increment", 0)));
		
		add(toPathLabel);
		add(listLabel);
		add(hangOnListLabel);
		add(toPath);
		add(list.getJsp());
		add(HnagOnList.getJsp());
		
		add(lthButton);
		add(htlButton);
		add(exchangeButton);
		
		add(newPathButton);
		add(clearStyleButton);
		add(doBackupButton);
		
		this.setBackground(Color.white);
		//this.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		this.setBorder(new SpecialBorder(Color.blue,"mid"));
		this.setBounds(10, ComponentSize.getNOW_HEADLINE_SIZE_Y(), ComponentSize.getNOW_MAIN_PANEL_SIZE_X(), ComponentSize.getNOW_MAIN_PANEL_SIZE_Y());
	}
		
}
