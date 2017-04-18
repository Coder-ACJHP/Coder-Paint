package com.coder.image;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ImageProps extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JPanel panel_1, panel;
	private JLabel lblImageSize, lblAspect, lblCreationDate, lblTransparency;
	protected JRadioButton blackWhite, colored;
	protected JLabel lblsizeResult, lblAspectResult, lblCreationResult, lblTransparencyResult;
	private JRadioButton fivePointFour;
	private JRadioButton fourPointThree;
	private JRadioButton sixteenPointNine;
	private double AspectRatio = 0.0;
	private JLabel lblX;
	private JLabel lblWideScreen;
	private JLabel lblOldScreens;

	/**
	 * Create the frame.
	 */
	public ImageProps() {
		setFont(new Font("Dialog", Font.PLAIN, 13));
		setType(Type.UTILITY);
		setTitle("Image Properties");
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ImageProps.class.getResource("/com/coder/icons/Info Squared-15.png")));
		setBounds(100, 100, 284, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblImageSize = new JLabel("Image size : ");
		lblImageSize.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImageSize.setAutoscrolls(true);
		lblImageSize.setBounds(10, 156, 90, 33);
		lblImageSize.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(lblImageSize);
		
		lblAspect = new JLabel("Aspect ratio : ");
		lblAspect.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAspect.setAutoscrolls(true);
		lblAspect.setBounds(10, 200, 90, 33);
		lblAspect.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(lblAspect);
		
		lblCreationDate = new JLabel("Creation date : ");
		lblCreationDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCreationDate.setAutoscrolls(true);
		lblCreationDate.setBounds(11, 283, 96, 33);
		lblCreationDate.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(lblCreationDate);
		
		lblTransparency = new JLabel("Image transperancy : ");
		lblTransparency.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTransparency.setAutoscrolls(true);
		lblTransparency.setBounds(10, 242, 142, 33);
		lblTransparency.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(lblTransparency);
		
		blackWhite = new JRadioButton("Black white");
		blackWhite.setAlignmentX(Component.CENTER_ALIGNMENT);
		blackWhite.setAutoscrolls(true);
		blackWhite.setBounds(68, 353, 109, 23);
		blackWhite.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(blackWhite);
		
		colored = new JRadioButton("Colored");
		colored.setAlignmentX(Component.CENTER_ALIGNMENT);
		colored.setAutoscrolls(true);
		colored.setBounds(175, 353, 83, 23);
		colored.setSelected(true);
		colored.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(colored);
		
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(blackWhite);
		buttonGroup1.add(colored);
		
		lblsizeResult = new JLabel("");
		lblsizeResult.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblsizeResult.setAutoscrolls(true);
		lblsizeResult.setBounds(121, 156, 109, 33);
		lblsizeResult.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(lblsizeResult);
		
		lblAspectResult = new JLabel("");
		lblAspectResult.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAspectResult.setAutoscrolls(true);
		lblAspectResult.setBounds(121, 200, 109, 33);
		lblAspectResult.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(lblAspectResult);
		
		lblCreationResult = new JLabel("");
		lblCreationResult.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCreationResult.setAutoscrolls(true);
		lblCreationResult.setBounds(126, 282, 132, 33);
		lblCreationResult.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(lblCreationResult);
		
		lblTransparencyResult = new JLabel("");
		lblTransparencyResult.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTransparencyResult.setAutoscrolls(true);
		lblTransparencyResult.setBounds(159, 242, 99, 34);
		lblTransparencyResult.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(lblTransparencyResult);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, 274, 150);
		panel.setBackground(Color.WHITE);
		panel.setAutoscrolls(true);
		panel_1.add(panel);
		
		JRadioButton rdbtnPixel = new JRadioButton("Pixel");
		rdbtnPixel.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnPixel.setAutoscrolls(true);
		rdbtnPixel.setSelected(true);
		rdbtnPixel.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnPixel.setBounds(68, 323, 84, 23);
		panel_1.add(rdbtnPixel);
		
		JRadioButton rdbtnInc = new JRadioButton("Inc");
		rdbtnInc.setAlignmentX(Component.CENTER_ALIGNMENT);
		rdbtnInc.setAutoscrolls(true);
		rdbtnInc.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnInc.setBounds(175, 322, 53, 23);
		panel_1.add(rdbtnInc);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnInc);
		buttonGroup.add(rdbtnPixel);
		
		JLabel lblUnit = new JLabel("Unit : ");
		lblUnit.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUnit.setAutoscrolls(true);
		lblUnit.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblUnit.setBounds(13, 323, 46, 24);
		panel_1.add(lblUnit);
		
		JLabel lblColor = new JLabel("Color : ");
		lblColor.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblColor.setAutoscrolls(true);
		lblColor.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblColor.setBounds(14, 358, 46, 14);
		panel_1.add(lblColor);
		
		JPanel panel_2 = new JPanel();
		panel_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Choose your screen size : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_2.setAutoscrolls(true);
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBounds(0, 383, 274, 115);
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		fourPointThree = new JRadioButton("4:3");
		fourPointThree.setPreferredSize(new Dimension(70, 23));
		fourPointThree.setAlignmentX(Component.CENTER_ALIGNMENT);
		fourPointThree.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(fourPointThree.isSelected()) {
					AspectRatio = 1.3;
				}
			}
		});
		fourPointThree.setAutoscrolls(true);
		fourPointThree.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_2.add(fourPointThree);
		
		sixteenPointNine = new JRadioButton("16:9");
		sixteenPointNine.setPreferredSize(new Dimension(70, 23));
		sixteenPointNine.setAlignmentX(Component.CENTER_ALIGNMENT);
		sixteenPointNine.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(sixteenPointNine.isSelected()) {
					AspectRatio = 1.7;
				}
			}
		});
		
		fivePointFour = new JRadioButton("5:4");
		fivePointFour.setPreferredSize(new Dimension(70, 23));
		fivePointFour.setMaximumSize(new Dimension(60, 23));
		fivePointFour.setAlignmentX(Component.CENTER_ALIGNMENT);
		fivePointFour.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(fivePointFour.isSelected()) {
					AspectRatio = 1.2;
				}
			}
		});
		fivePointFour.setAutoscrolls(true);
		fivePointFour.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_2.add(fivePointFour);
		sixteenPointNine.setAutoscrolls(true);
		sixteenPointNine.setFont(new Font("Dialog", Font.PLAIN, 13));
		sixteenPointNine.setSelected(true);
		panel_2.add(sixteenPointNine);
		
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(fourPointThree);
		buttonGroup2.add(fivePointFour);
		buttonGroup2.add(sixteenPointNine);
		
		lblOldScreens = new JLabel("(Old screens)");
		lblOldScreens.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblOldScreens.setForeground(new Color(255, 0, 0));
		lblOldScreens.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel_2.add(lblOldScreens);
		
		lblX = new JLabel("(1280 x 1024)");
		lblX.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblX.setForeground(new Color(255, 0, 0));
		lblX.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel_2.add(lblX);
		
		lblWideScreen = new JLabel("(Wide screen)");
		lblWideScreen.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblWideScreen.setForeground(new Color(255, 0, 0));
		lblWideScreen.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel_2.add(lblWideScreen);
		
	}
	
	public void setImagePanelImage(BufferedImage image) {
    	BufferedImage scaledImage = new BufferedImage(258, 150
    			, BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g2d = scaledImage.createGraphics();
    	g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    	g2d.drawImage(image,0,0,258,150, null);
    	g2d.dispose();
		JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
		this.panel.add(picLabel);
	}
	
	public void setImageProperties(String size, double aspectRatio, String date, double imgTransp) {
		this.lblsizeResult.setText(size);
		this.lblAspectResult.setText(aspectRatio + "");
		this.lblCreationResult.setText(date);
		this.lblTransparencyResult.setText(imgTransp + "");
	}
	
	public void setColoredChoosed(String colorType) {
		if(colorType.equalsIgnoreCase("Colored")) {
			colored.setSelected(true);
			blackWhite.setSelected(false);
		}else {
			colored.setSelected(false);
			blackWhite.setSelected(true);
		}
	}
	
	public double getAspectRatio() {
		return this.AspectRatio;
		}
	
	}
