import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.Arrays;
import javax.swing.event.*;
import java.net.URL;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Hashtable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class DrawingDisplayer extends JPanel implements ListSelectionListener, ActionListener, Serializable
{
	int min = 0;
    int max = 100;
    int value = 100/2;
    
	JLabel title, colour, drawFile, progressLabel, fileOption;
	JButton play, pause, clear, fileOpen, lineColor, backgroundColor, fileClose;
	JList list;
	JSlider slider;
	JProgressBar progressBar;
	Drawing centerPanel, newColorPanel1, newColorPanel2;
	private DefaultListModel model;
	Timer timer;
    JColorChooser chooserColor1, chooserColor2;
    Color selectedColorBackground, selectedColorLines;  
	
	public DrawingDisplayer()
	{
		super(new BorderLayout());
		
		newColorPanel1 = new Drawing();
		newColorPanel2 = new Drawing();
		chooserColor1 = new JColorChooser(newColorPanel1.getForeground());
		chooserColor2 = new JColorChooser(newColorPanel2.getForeground());
		
		setPreferredSize(new Dimension(1000, 600));  
		title = new JLabel("Drawing Displayer");
		title.setFont(new Font("Serif", Font.BOLD, 25));
        JPanel topPanel = new JPanel();
        topPanel.add(title);
        add(topPanel,BorderLayout.NORTH);
        
        //LABELS
        drawFile = new JLabel("  Draw the File");
        play = new JButton("Play");
        pause = new JButton("Pause");
        clear = new JButton("Clear");
        fileOpen = new JButton("Open File");
        fileClose = new JButton("Clear File");
        fileOption = new JLabel("  File Options");
        colour = new JLabel("  Colour Options");
        lineColor = new JButton("Line Colour");
        backgroundColor = new JButton("Background Colour");
        
        //SLIDER
        slider = new JSlider(JSlider.HORIZONTAL, min, max, value);
        slider.setMinorTickSpacing(5);  
		slider.setMajorTickSpacing(50);  
		slider.setPaintTicks(true);  
		slider.setPaintLabels(true); 
		slider.setPreferredSize(new Dimension(20,20));
        
        //PROGRESS BAR
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
    	progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
      
        //LIST MODEL
        model = new DefaultListModel();
        list = new JList(model);
        list.setPreferredSize(new Dimension(250,130));
        list.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        list.addListSelectionListener(this);
        
        //ADD PANELS
        //PANEL FOR BUTTONS OF PLAY, PAUSE AND CLEAR
        JPanel buttonPanelOne = new JPanel();
        buttonPanelOne.add(play);        
        buttonPanelOne.add(pause);
        buttonPanelOne.add(clear);
        
        //PANEL FOR A PROGRESS BAR
        JPanel progressPanel = new JPanel();
        progressLabel = new JLabel("Progress Bar");
        progressPanel.add(progressLabel);
        progressPanel.add(progressBar);
        
        //PANEL FOR FILE OPTIONS BUTTONS
        JPanel buttonFileOpenClose = new JPanel();
        buttonFileOpenClose.add(fileOpen);
        buttonFileOpenClose.add(fileClose);
        
        //PANEL FOR ALL BUTTONS EXCLUDED COLOUR OPTIONS
        JPanel buttonPanelTwo = new JPanel();
        buttonPanelTwo.add(drawFile);
        buttonPanelTwo.add(buttonPanelOne);
        buttonPanelTwo.add(slider);
        buttonPanelTwo.add(progressPanel);
        buttonPanelTwo.add(fileOption);
        buttonPanelTwo.add(buttonFileOpenClose);
        buttonPanelTwo.setPreferredSize(new Dimension(250,250));
        buttonPanelTwo.setLayout(new GridLayout(6,1));
        
        //PANEL FOR COLOUR BUTTONS
        JPanel colourButtonPanel = new JPanel();
        colourButtonPanel.add(lineColor);
        colourButtonPanel.add(backgroundColor);
		
        //PANEL COMBINED FOR COLOUR OPTIONS
		JPanel colourButtonPanelOption = new JPanel();
		colourButtonPanelOption.add(colour);
		colourButtonPanelOption.add(colourButtonPanel);
		colourButtonPanelOption.setPreferredSize(new Dimension(265,50));
		colourButtonPanelOption.setLayout(new GridLayout(4,1));
		colourButtonPanelOption.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
        //COMBINED ALL PANELS INTO ONE
        JPanel panelCombined = new JPanel();
        panelCombined.add(buttonPanelTwo);
        panelCombined.add(list);
        panelCombined.add(colourButtonPanelOption);
        panelCombined.setPreferredSize(new Dimension(265,625));
        panelCombined.setLayout(new GridLayout(3,1));
        panelCombined.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        //ADD COMBINED PANEL TO A WEST PANEL
        JPanel westPanel = new JPanel();
        westPanel.add(panelCombined);
        add(westPanel,BorderLayout.WEST);
        
        //CREATE A NEW DRAWING FOR THE DISPLAY OF DRAWING
        centerPanel = new Drawing();       
        centerPanel.setPreferredSize(new Dimension(600,300));
        centerPanel.setBackground(Color.WHITE);
        add(centerPanel,BorderLayout.CENTER);
        
        //ADD ACTIONLISTENERS
        play.addActionListener(this);
        pause.addActionListener(this);
        clear.addActionListener(this);
        fileOpen.addActionListener(this);
        fileClose.addActionListener(this);
        lineColor.addActionListener(this);
        backgroundColor.addActionListener(this);
	}
	
	//draw PLAY
	public void startDraw() 
	{
		//DRAW HOUSE.DRW is yet to be completed...
		
		remove(centerPanel);
		add(centerPanel);
		centerPanel = (Drawing)model.elementAt(list.getSelectedIndex());
		timer = new Timer(0, this);
		
        int drawLine = centerPanel.getNumDraw();
		int start = 0;

   		for(int i = 0; i < centerPanel.getNumLines(); i++)
        {
	        
            drawLine = centerPanel.getNumLines() - 1 + 1;
   			centerPanel.setNumDraw(drawLine);
   			progressBar.setValue(start++);
        }

	   	if(progressBar.getValue() == 3)
	   	{
		   	progressBar.setValue(100);
		   	progressBar.setStringPainted(true);
	   	}
	   	
		System.out.println("START");	
		timer.start();
		repaint();
		revalidate();
    }
	
    //PAUSE DRAW
    public void pauseDraw() 
    {
        timer.stop();
    }
    
  	//JLIST SELECTION 	
	public void valueChanged(ListSelectionEvent event)
    {  
        if (!list.isSelectionEmpty())
		{  
	        remove(centerPanel);
            centerPanel = (Drawing)model.elementAt(list.getSelectedIndex());
            centerPanel.setNumDraw(centerPanel.getNumLines());
            
            add(centerPanel, BorderLayout.CENTER);
        	revalidate();
    	}
	}
   
	//ACTIONPERFORMED FOR BUTTONS
	public void actionPerformed(ActionEvent event)
	{  
	    JFileChooser chooser = new JFileChooser(new File("."));
	    Object source = event.getSource();
	    
	    if (source == fileOpen)
		{  
	        int status = chooser.showOpenDialog(this);
	        
	        if (status == JFileChooser.APPROVE_OPTION)
			{  
	            File fileToOpen = chooser.getSelectedFile();
	            
	            Drawing drawing = new Drawing(fileToOpen);
	            model.addElement(drawing);
	        }
		}
		
		if (source == play)
		{  
			startDraw();
			centerPanel.setColour(selectedColorLines);
			play.setEnabled(false);   
			pause.setEnabled(true);
			clear.setEnabled(true);
		}
	      
	    if (source == fileClose)
	    {
		  	if (!list.isSelectionEmpty()) 
		  	{
    			model.remove(list.getSelectedIndex());
			}  
	    }
	      
	    if (source == pause)
	    {  
		   	play.setEnabled(true);
		  	pause.setEnabled(false);
		  	clear.setEnabled(true);
		  	timer.stop();
	    }
	      
	    if (source == clear)
	    {
		    remove(centerPanel);
		    add(centerPanel); 	      
		    centerPanel.setColour(Color.WHITE);
		    centerPanel.setBackground(Color.WHITE);
			centerPanel.setPreferredSize(new Dimension(600,300));
			repaint();
			progressBar.setValue(0);
			play.setEnabled(true);
		  	pause.setEnabled(true);
	    }
	      
	    //Colour: For drawing and background
	    if (source == lineColor)
	    {
		   	selectedColorLines = JColorChooser.showDialog(centerPanel, "Choose a color" , chooserColor1.getColor());
		   	if(source == play)
	   		{
		   		centerPanel.setColour(selectedColorLines);
	   		}
	    }
	      
		if (source == backgroundColor)
	    {  
		   	selectedColorBackground = JColorChooser.showDialog(centerPanel, "Choose a color" , chooserColor2.getColor());
		   	centerPanel.setBackground(selectedColorBackground);
		   	centerPanel.setColour(selectedColorBackground);
	    }
	}
	
	//SLIDER ADJUSTED
	public void stateChanged(ChangeEvent event)
	{
		if(event.getSource() == slider)
		{
			int speed = slider.getValue();
			slider.addChangeListener(new ChangeListener() {
	      		public void stateChanged(ChangeEvent e) {
	        		JSlider source = (JSlider) e.getSource();
	        	if (!source.getValueIsAdjusting()) {
	          	int changeSpeed = (int) source.getValue();
	          	
	          	for(int i = 0; i < centerPanel.getNumDraw(); i++)
		        {
			        if(changeSpeed <50)
			        {
				        timer.setDelay(changeSpeed);
						int lines = centerPanel.getNumLines() - 1 + 1;
		   				centerPanel.setNumDraw(lines);
	   				}
		   			repaint();
				}
	        	}
	      		}
	    	});	
		}		
	}
	
	public static void main(String[] args)
    {
	    try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame("Drawing Displayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new DrawingDisplayer());
      	
        frame.pack();
        // position the frame in the middle of the screen
      
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width-frameDimension.width)/2,
           (screenDimension.height-frameDimension.height)/2);
        frame.setVisible(true);
	}
}
