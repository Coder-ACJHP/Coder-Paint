package com.coder.userInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

public class TabbedPane extends JTabbedPane {

    /**
     *
     */
    protected JPanel panel;
    protected JLabel emptylabel;
    protected JSlider slider;
    protected JSpinner spinner;
    private final String[] fontNames;
    protected JComboBox<String> fontBox;
    protected EditImageBox editImageBox;
    protected ChangeJoinBox changeJoinBox;
    private final JPanel toolPanel = new JPanel();
    protected final JPanel extrasPanel = new JPanel();
    private final JSeparator separator, separator1, separator2, separator3, separator4, separator5;
    protected JCheckBox chckbxDashed, chckbxGredient, chckbxRandomColor, fillAllShapes, chkBold, chkItalic, chkStrkTrh;
    protected JLabel lblCurrentColor, lblDemision, lblCurrentX, lblCurrentY, lblSize, lblSetFont;
    protected JButton btnPencil, btnEraser, btnColorChooser, btnTriangle, btnLine, btnClear, btnRectangel, btnOval, btnHitMe,
    btnBucket, btnText, btnSelection, btnAutofill, colorPicker, btnQuadcurve, btnStar, btnCrop, btnHeart, btnRahimbus, btnBahai;
    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public TabbedPane() {
        
    	Dimension minimumSize = new Dimension(110, 20);
    	Dimension maximumSize = new Dimension(110, 35);
    	Dimension preferredSize = new Dimension(110, 30);
    	extrasPanel.setPreferredSize(new Dimension(115, 450));
    	toolPanel.setPreferredSize(new Dimension(115, 450));
    	
        btnPencil = new JButton("Pencil");
        btnPencil.setActionCommand("Pencil");
        btnPencil.setOpaque(true);
        btnPencil.setBorder(BorderFactory.createRaisedBevelBorder());
        btnPencil.setToolTipText("First choose color , change 'Stroke' enabled.");
        btnPencil.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnPencil.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/rsz_quill.png")));
        btnPencil.setMinimumSize(minimumSize);
        btnPencil.setMaximumSize(maximumSize);
        btnPencil.setPreferredSize(preferredSize);
        btnPencil.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.setBackground(new Color(173, 216, 230));
        toolPanel.add(btnPencil);

        btnEraser = new JButton("Eraser");
        btnEraser.setActionCommand("Eraser");
        btnEraser.setOpaque(true);
        btnEraser.setBorder(BorderFactory.createRaisedBevelBorder());
        btnEraser.setToolTipText("Change 'Stroke' enabled.");
        btnEraser.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnEraser.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/eraser.png")));
        btnEraser.setMinimumSize(minimumSize);
        btnEraser.setMaximumSize(maximumSize);
        btnEraser.setPreferredSize(preferredSize);
        btnEraser.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(btnEraser);

        btnLine = new JButton();
        btnLine.setActionCommand("  Line");
        btnLine.setOpaque(true);
        btnLine.setBorder(BorderFactory.createRaisedBevelBorder());
        btnLine.setToolTipText("First choose color , change 'Stroke' enabled.");
        btnLine.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnLine.setForeground(new Color(91, 4, 145));
        btnLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLine.setMinimumSize(new Dimension(45, 20));
        btnLine.setMaximumSize(maximumSize);
        btnLine.setPreferredSize(new Dimension(45, 30));
        btnLine.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Line.png")));
        extrasPanel.setBackground(new Color(173, 216, 230));
        extrasPanel.add(btnLine);

        btnStar = new JButton();
        btnStar.setActionCommand("    Star");
        btnStar.setOpaque(true);
        btnStar.setBorder(BorderFactory.createRaisedBevelBorder());
        btnStar.setToolTipText("First choose color , change 'Stroke' enabled.");
        btnStar.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnStar.setForeground(new Color(91, 4, 145));
        btnStar.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Star.png")));
        btnStar.setMinimumSize(new Dimension(45, 20));
        btnStar.setMaximumSize(maximumSize);
        btnStar.setPreferredSize(new Dimension(45, 30));
        btnStar.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(btnStar);

        btnOval = new JButton();
        btnOval.setActionCommand("  Circle");
        btnOval.setOpaque(true);
        btnOval.setBorder(BorderFactory.createRaisedBevelBorder());
        btnOval.setToolTipText("First choose color , change 'Stroke' enabled.");
        btnOval.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnOval.setForeground(new Color(91, 4, 145));
        btnOval.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Circle.png")));
        btnOval.setMinimumSize(new Dimension(45, 20));
        btnOval.setMaximumSize(maximumSize);
        btnOval.setPreferredSize(new Dimension(45, 30));
        btnOval.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(btnOval);

        btnClear = new JButton("  Clear");
        btnClear.setActionCommand("  Clear");
        btnClear.setOpaque(true);
        btnClear.setBorder(BorderFactory.createRaisedBevelBorder());
        btnClear.setToolTipText("Clearing all shapes on the 'Draw Area'");
        btnClear.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnClear.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/edit-clear.png")));
        btnClear.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnClear.setMinimumSize(minimumSize);
        btnClear.setMaximumSize(maximumSize);
        btnClear.setPreferredSize(preferredSize);
        toolPanel.add(btnClear);

        btnQuadcurve = new JButton();
        btnQuadcurve.setActionCommand("Curved");
        btnQuadcurve.setOpaque(true);
        btnQuadcurve.setBorder(BorderFactory.createRaisedBevelBorder());
        btnQuadcurve.setToolTipText("First choose color , change 'Stroke' enabled.");
        btnQuadcurve.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnQuadcurve.setForeground(new Color(91, 4, 145));
        btnQuadcurve.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Curved.png")));
        btnQuadcurve.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuadcurve.setMinimumSize(new Dimension(45, 20));
        btnQuadcurve.setMaximumSize(maximumSize);
        btnQuadcurve.setPreferredSize(new Dimension(45, 30));
        extrasPanel.add(btnQuadcurve);

        btnRectangel = new JButton();
        btnRectangel.setActionCommand("Rectan.");
        btnRectangel.setOpaque(true);
        btnRectangel.setBorder(BorderFactory.createRaisedBevelBorder());
        btnRectangel.setToolTipText("First choose color , change 'Stroke' enabled.");
        btnRectangel.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnRectangel.setForeground(new Color(91, 4, 145));
        btnRectangel.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Rectangle.png")));
        btnRectangel.setMinimumSize(new Dimension(45, 20));
        btnRectangel.setPreferredSize(new Dimension(45, 30));
        btnRectangel.setMaximumSize(maximumSize);
        btnRectangel.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(btnRectangel);

        btnTriangle = new JButton();
        btnTriangle.setOpaque(true);
        btnTriangle.setActionCommand("Polygon");
        btnTriangle.setBorder(BorderFactory.createRaisedBevelBorder());
        btnTriangle.setToolTipText("First choose color , change 'Stroke' enabled.");
        btnTriangle.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnTriangle.setForeground(new Color(91, 4, 145));
        btnTriangle.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Triangle.png")));
        btnTriangle.setMinimumSize(new Dimension(45, 20));
        btnTriangle.setMaximumSize(maximumSize);
        btnTriangle.setPreferredSize(new Dimension(45, 30));
        btnTriangle.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(btnTriangle);

        btnHeart = new JButton();
        btnHeart.setActionCommand("Heart");
        btnHeart.setOpaque(true);
        btnHeart.setToolTipText("Draw it to bottom right corner.");
        btnHeart.setBorder(BorderFactory.createRaisedBevelBorder());
        btnHeart.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnHeart.setForeground(new Color(91, 4, 145));
        btnHeart.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/heart.png")));
        btnHeart.setMinimumSize(new Dimension(45, 20));
        btnHeart.setMaximumSize(maximumSize);
        btnHeart.setPreferredSize(new Dimension(45, 30));
        btnHeart.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(btnHeart);

        btnRahimbus = new JButton();
        btnRahimbus.setActionCommand("Rahimbus");
        btnRahimbus.setOpaque(true);
        btnRahimbus.setBorder(BorderFactory.createRaisedBevelBorder());
        btnRahimbus.setToolTipText("Draw rahimbus shape.");
        btnRahimbus.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnRahimbus.setForeground(new Color(91, 4, 145));
        btnRahimbus.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Rahimbus.png")));
        btnRahimbus.setMinimumSize(new Dimension(45, 20));
        btnRahimbus.setMaximumSize(maximumSize);
        btnRahimbus.setPreferredSize(new Dimension(45, 30));
        btnRahimbus.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(btnRahimbus);

        
        btnBahai = new JButton();
        btnBahai.setActionCommand("Bahai");
        btnBahai.setOpaque(true);
        btnBahai.setBorder(BorderFactory.createRaisedBevelBorder());
        btnBahai.setToolTipText("Draw bahai shape.");
        btnBahai.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnBahai.setForeground(new Color(91, 4, 145));
        btnBahai.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Bahai.png")));
        btnBahai.setMinimumSize(new Dimension(45, 20));
        btnBahai.setMaximumSize(maximumSize);
        btnBahai.setPreferredSize(new Dimension(45, 30));
        btnBahai.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(btnBahai);
        
        btnAutofill = new JButton();
        btnAutofill.setActionCommand("AutoFill");
        btnAutofill.setOpaque(true);
        btnAutofill.setBorder(BorderFactory.createRaisedBevelBorder());
        btnAutofill.setToolTipText("First choose color , change 'Stroke' enabled.");
        btnAutofill.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnAutofill.setForeground(new Color(91, 4, 145));
        btnAutofill.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/filler.png")));
        btnAutofill.setMinimumSize(new Dimension(45, 20));
        btnAutofill.setMaximumSize(maximumSize);
        btnAutofill.setPreferredSize(new Dimension(45, 30));
        btnAutofill.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(btnAutofill);

        
        btnHitMe = new JButton("Hit Me!");
        btnHitMe.setActionCommand("Hitme");
        btnHitMe.setToolTipText("Hit and drag the mouse into drawing area!");
        btnHitMe.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnHitMe.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Hitme.png")));
        btnHitMe.setMinimumSize(minimumSize);
        btnHitMe.setMaximumSize(maximumSize);
        btnHitMe.setPreferredSize(new Dimension(130, 30));
        btnHitMe.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(btnHitMe);
        
        btnSelection = new JButton("Select");
        btnSelection.setActionCommand("Select");
        btnSelection.setOpaque(true);
        btnSelection.setBorder(BorderFactory.createRaisedBevelBorder());
        btnSelection.setFont(new Font("Dialog", Font.PLAIN, 12));
        btnSelection.setToolTipText("Select an area to be able to copy or crop it.\n Resize your rectangle with mouse wheel");
        btnSelection.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/selection.png")));
        btnSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSelection.setMinimumSize(minimumSize);
        btnSelection.setMaximumSize(maximumSize);
        btnSelection.setPreferredSize(preferredSize);
        toolPanel.add(btnSelection);

        btnColorChooser = new JButton("Colors");
        btnColorChooser.setActionCommand("Colors");
        btnColorChooser.setOpaque(true);
        btnColorChooser.setBorder(BorderFactory.createRaisedBevelBorder());
        btnColorChooser.setToolTipText("Shows 'Color Chooser'");
        btnColorChooser.setFont(new Font("Dialog", Font.PLAIN, 12));
        btnColorChooser.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/ColorsPalet.png")));
        btnColorChooser.setMinimumSize(minimumSize);
        btnColorChooser.setMaximumSize(maximumSize);
        btnColorChooser.setPreferredSize(preferredSize);
        btnColorChooser.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(btnColorChooser);

        btnBucket = new JButton("Bucket");
        btnBucket.setActionCommand("Bucket");
        btnBucket.setOpaque(true);
        btnBucket.setBorder(BorderFactory.createRaisedBevelBorder());
        btnBucket.setToolTipText("First choose color , change 'Stroke' enabled.");
        btnBucket.setFont(new Font("Dialog", Font.PLAIN, 12));
        btnBucket.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/filler.png")));
        btnBucket.setMinimumSize(minimumSize);
        btnBucket.setMaximumSize(maximumSize);
        btnBucket.setPreferredSize(preferredSize);
        btnBucket.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(btnBucket);
        
        colorPicker = new JButton("Color Picker");
        colorPicker.setActionCommand("Color Picker");
        colorPicker.setOpaque(true);
        colorPicker.setBorder(BorderFactory.createRaisedBevelBorder());
        colorPicker.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/colorpicker_1.png")));
        colorPicker.setMinimumSize(minimumSize);
        colorPicker.setMaximumSize(maximumSize);
        colorPicker.setPreferredSize(preferredSize);
        colorPicker.setFont(new Font("Dialog", Font.PLAIN, 12));
        colorPicker.setMaximumSize(new Dimension(130, 50));
        colorPicker.setToolTipText("Color Picker : Move the cursor\n on any color and just click.");
        colorPicker.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(colorPicker);

        btnCrop = new JButton("Crop");
        btnCrop.setActionCommand("Crop");
        btnCrop.setOpaque(true);
        btnCrop.setBorder(BorderFactory.createRaisedBevelBorder());
        btnCrop.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Crop.png")));
        btnCrop.setMinimumSize(minimumSize);
        btnCrop.setMaximumSize(maximumSize);
        btnCrop.setPreferredSize(preferredSize);
        btnCrop.setFont(new Font("Dialog", Font.PLAIN, 12));
        btnCrop.setToolTipText("Select an area after that crop it.");
        btnCrop.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(btnCrop);

        btnText = new JButton(" Text");
        btnText.setActionCommand(" Text");
        btnText.setOpaque(true);
        btnText.setBorder(BorderFactory.createRaisedBevelBorder());
        btnText.setToolTipText("First drag the mouse and draw a rectangle after that write into it."
                + "You can resize the rectangle by mouse wheel");
        btnText.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnText.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Text.png")));
        btnText.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnText.setPreferredSize(preferredSize);
        btnText.setMaximumSize(maximumSize);
        btnText.setMinimumSize(minimumSize);
        toolPanel.add(btnText);

        lblSetFont = new JLabel("Choose Font :");
        lblSetFont.setMinimumSize(new Dimension(52, 16));
        lblSetFont.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSetFont.setMinimumSize(minimumSize);
        lblSetFont.setPreferredSize(preferredSize);
        lblSetFont.setMaximumSize(maximumSize);
        lblSetFont.setForeground(new Color(0, 0, 0));
        lblSetFont.setFont(new Font("Dialog", Font.BOLD, 11));
        toolPanel.add(lblSetFont);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final Font[] localFonts = ge.getAllFonts();
        fontNames = new String[localFonts.length];
        for (int i = 0; i < localFonts.length; i++) {
            fontNames[i] = localFonts[i].getFontName();
        }
        fontBox = new JComboBox<>(fontNames);
        fontBox.setActionCommand("FontBox");
        fontBox.setFont(new Font("Dialog", Font.PLAIN, 9));
        fontBox.setPreferredSize(new Dimension(125, 25));
        fontBox.setSelectedIndex(0);
        toolPanel.add(fontBox);

        chkBold = new JCheckBox();
        chkBold.setActionCommand("Bold");
        chkBold.setOpaque(true);
        chkBold.setBackground(Color.WHITE);
        chkBold.setBorder(BorderFactory.createRaisedBevelBorder());
        chkBold.setToolTipText("Make your font BOLD");
        chkBold.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Bold.png")));
        chkBold.setMinimumSize(new Dimension(20, 20));
        chkBold.setMaximumSize(maximumSize);
        chkBold.setPreferredSize(new Dimension(20, 20));
        chkBold.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(chkBold);

        chkItalic = new JCheckBox();
        chkItalic.setActionCommand("Italic");
        chkItalic.setOpaque(true);
        chkItalic.setBackground(Color.WHITE);
        chkItalic.setBorder(BorderFactory.createRaisedBevelBorder());
        chkItalic.setToolTipText("Make your font ITALIC");
        chkItalic.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Italic.png")));
        chkItalic.setMinimumSize(new Dimension(20, 20));
        chkItalic.setMaximumSize(maximumSize);
        chkItalic.setPreferredSize(new Dimension(20, 20));
        chkItalic.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(chkItalic);

        chkStrkTrh = new JCheckBox();
        chkStrkTrh.setActionCommand("StrokeThrough");
        chkStrkTrh.setOpaque(true);
        chkStrkTrh.setBackground(Color.WHITE);
        chkStrkTrh.setBorder(BorderFactory.createRaisedBevelBorder());
        chkStrkTrh.setToolTipText("Make your font STRIKETHROUGH");
        chkStrkTrh.setIcon(new ImageIcon(getClass().getResource("/com/coder/icons/Strikethrough.png")));
        chkStrkTrh.setMinimumSize(new Dimension(20, 20));
        chkStrkTrh.setMaximumSize(maximumSize);
        chkStrkTrh.setPreferredSize(new Dimension(20, 20));
        chkStrkTrh.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(chkStrkTrh);

        spinner = new JSpinner();
        spinner.setOpaque(true);
        spinner.setValue(8);
        spinner.setName("Spinner");
        spinner.setMinimumSize(new Dimension(35, 20));
        spinner.setPreferredSize(new Dimension(40, 20));
        spinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        JComponent comp = spinner.getEditor();
        JFormattedTextField textField = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) textField.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        toolPanel.add(spinner);

        separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(125, 3));
        separator2.setForeground(Color.GRAY);
        separator2.setAutoscrolls(true);
        separator2.setVisible(true);
        toolPanel.add(separator2);

        lblCurrentColor = new JLabel("Current Color :");
        lblCurrentColor.setMinimumSize(new Dimension(52, 16));
        lblCurrentColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCurrentColor.setMinimumSize(minimumSize);
        lblCurrentColor.setPreferredSize(preferredSize);
        lblCurrentColor.setMaximumSize(maximumSize);
        lblCurrentColor.setForeground(new Color(0, 0, 0));
        lblCurrentColor.setFont(new Font("Dialog", Font.BOLD, 11));
        toolPanel.add(lblCurrentColor);

        panel = new JPanel();
        panel.setToolTipText("Choosed color.");
        panel.setBackground(Color.BLACK);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setMaximumSize(new Dimension(110, 50));
        panel.setPreferredSize(new Dimension(110, 40));
        panel.setMinimumSize(new Dimension(110, 20));
        toolPanel.add(panel);

        separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setPreferredSize(new Dimension(125, 3));
        separator3.setForeground(Color.GRAY);
        separator3.setAutoscrolls(true);
        separator3.setVisible(true);
        toolPanel.add(separator3);

        lblDemision = new JLabel("Set Stroke : ");
        lblDemision.setMinimumSize(new Dimension(110, 25));
        lblDemision.setPreferredSize(new Dimension(110, 25));
        lblDemision.setMaximumSize(new Dimension(110, 25));
        lblDemision.setFont(new Font("Lucida Grande", Font.BOLD, 11));
        lblDemision.setForeground(new Color(0, 0, 0));
        lblDemision.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(lblDemision);

        slider = new JSlider(JSlider.HORIZONTAL, 1, 50, 2);
        slider.setPreferredSize(new Dimension(120, 29));
        slider.setMaximumSize(new Dimension(150, 29));
        slider.setMinimumSize(new Dimension(36, 25));
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setName("Slider");
        slider.setOpaque(true);
        toolPanel.add(slider);

        separator5 = new JSeparator(SwingConstants.HORIZONTAL);
        separator5.setPreferredSize(new Dimension(125, 3));
        separator5.setForeground(Color.GRAY);
        separator5.setAutoscrolls(true);
        separator5.setVisible(true);
        toolPanel.add(separator5);

        lblCurrentX = new JLabel("Current X :");
        lblCurrentX.setToolTipText("Shows 'X' point on the screen");
        lblCurrentX.setFont(new Font("Dialog", Font.PLAIN, 10));
        lblCurrentX.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        lblCurrentX.setBackground(new Color(60, 179, 113));
        lblCurrentX.setOpaque(true);
        lblCurrentX.setMinimumSize(new Dimension(110, 25));
        lblCurrentX.setPreferredSize(new Dimension(110, 25));
        lblCurrentX.setMaximumSize(new Dimension(110, 25));
        lblCurrentX.setAutoscrolls(true);
        lblCurrentX.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(lblCurrentX);

        lblCurrentY = new JLabel("Current Y :");
        lblCurrentY.setToolTipText("Shows 'Y' point on the screen");
        lblCurrentY.setMinimumSize(new Dimension(110, 25));
        lblCurrentY.setPreferredSize(new Dimension(110, 25));
        lblCurrentY.setMaximumSize(new Dimension(110, 25));
        lblCurrentY.setFont(new Font("Dialog", Font.PLAIN, 10));
        lblCurrentY.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        lblCurrentY.setBackground(new Color(173, 255, 47));
        lblCurrentY.setOpaque(true);
        lblCurrentY.setAlignmentX(Component.CENTER_ALIGNMENT);
        toolPanel.add(lblCurrentY);

        PointerInfo taskk = new PointerInfo();
        taskk.getPoints(lblCurrentX, lblCurrentY);

        lblSize = new JLabel();
        lblSize.setMinimumSize(new Dimension(110, 25));
        lblSize.setPreferredSize(new Dimension(110, 25));
        lblSize.setMaximumSize(new Dimension(110, 25));
        lblSize.setFont(new Font("Dialog", Font.PLAIN, 10));
        lblSize.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        lblSize.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSize.setBackground(Color.YELLOW);
        lblSize.setOpaque(true);
        toolPanel.add(lblSize);
        
        emptylabel = new JLabel();
        emptylabel.setPreferredSize(maximumSize);
        toolPanel.add(emptylabel);
        
        extrasPanel.add(Box.createHorizontalStrut(10));
        separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setPreferredSize(new Dimension(125, 3));
        separator1.setForeground(Color.GRAY);
        separator1.setAutoscrolls(true);
        separator1.setVisible(true);
        extrasPanel.add(separator1);

        changeJoinBox = new ChangeJoinBox();
        changeJoinBox.setFont(new Font("Dialog", Font.PLAIN, 9));
        changeJoinBox.setPreferredSize(new Dimension(125, 25));
        changeJoinBox.strokeBox.setSelectedItem(0);
        changeJoinBox.strokeBox.setToolTipText("Change the stroke (if you want) before choosing edges");
        changeJoinBox.setOpaque(false);
        extrasPanel.add(changeJoinBox);

        editImageBox = new EditImageBox();
        editImageBox.setFont(new Font("Dialog", Font.PLAIN, 9));
        editImageBox.setPreferredSize(new Dimension(125, 25));
        editImageBox.setToolTipText("Play with your image.");
        editImageBox.setOpaque(false);
        extrasPanel.add(editImageBox);

        extrasPanel.add(Box.createHorizontalStrut(10));
        separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(125, 3));
        separator.setForeground(Color.GRAY);
        separator.setAutoscrolls(true);
        separator.setVisible(true);
        extrasPanel.add(separator);

        chckbxDashed = new JCheckBox("Dashed");
        chckbxDashed.setAutoscrolls(true);
        chckbxDashed.setOpaque(false);
        chckbxDashed.setToolTipText("Dashed allowed for empty Rectangle, Circle, Line only.");
        chckbxDashed.setFont(new Font("Dialog", Font.BOLD, 13));
        chckbxDashed.setForeground(new Color(0, 0, 0));
        chckbxDashed.setMaximumSize(new Dimension(125, 23));
        chckbxDashed.setPreferredSize(new Dimension(125, 25));
        chckbxDashed.setAlignmentX(Component.CENTER_ALIGNMENT);
        chckbxDashed.setMinimumSize(new Dimension(92, 23));
        extrasPanel.add(chckbxDashed);

        chckbxGredient = new JCheckBox("Greadiant");
        chckbxGredient.setAutoscrolls(true);
        chckbxGredient.setFont(new Font("Dialog", Font.BOLD, 13));
        chckbxGredient.setOpaque(false);
        chckbxGredient.setPreferredSize(new Dimension(125, 25));
        chckbxGredient.setToolTipText("Pick two colors to gradient.");
        chckbxGredient.setForeground(new Color(0, 0, 0));
        chckbxGredient.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(chckbxGredient);

        chckbxRandomColor = new JCheckBox("Mix colors");
        chckbxRandomColor.setAutoscrolls(true);
        chckbxRandomColor.setFont(new Font("Dialog", Font.BOLD, 13));
        chckbxRandomColor.setOpaque(false);
        chckbxRandomColor.setPreferredSize(new Dimension(125, 25));
        chckbxRandomColor.setToolTipText("Auto Randomize colors");
        chckbxRandomColor.setForeground(new Color(0, 0, 0));
        chckbxRandomColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(chckbxRandomColor);

        fillAllShapes = new JCheckBox("Fill in shape");
        fillAllShapes.setAutoscrolls(true);
        fillAllShapes.setToolTipText("Check it for drawing filled shape with choosed color.");
        fillAllShapes.setFont(new Font("Dialog", Font.BOLD, 13));
        fillAllShapes.setPreferredSize(new Dimension(125, 25));
        fillAllShapes.setForeground(new Color(0, 0, 0));
        fillAllShapes.setBackground(new Color(173, 216, 230));
        fillAllShapes.setAlignmentX(Component.CENTER_ALIGNMENT);
        extrasPanel.add(fillAllShapes);

        toolPanel.add(Box.createHorizontalStrut(10));
        separator4 = new JSeparator(SwingConstants.HORIZONTAL);
        separator4.setPreferredSize(new Dimension(125, 3));
        separator4.setForeground(Color.GRAY);
        separator4.setAutoscrolls(true);
        extrasPanel.add(separator4);

        addTab("Tool", toolPanel);
        setIconAt(0, new ImageIcon(TabbedPane.class.getResource("/com/coder/icons/Tools.png")));
        setBackgroundAt(0, new Color(224, 255, 255));
        setForegroundAt(0, new Color(0, 0, 0));
        addTab("Shape", extrasPanel);
        setBackgroundAt(1, new Color(224, 255, 255));
        setIconAt(1, new ImageIcon(TabbedPane.class.getResource("/com/coder/icons/Shapes.png")));
        setAutoscrolls(true);
    }

    public void addActionToAllComponents(ActionListener listener) {
        this.btnPencil.addActionListener(listener);
        this.btnEraser.addActionListener(listener);
        this.btnLine.addActionListener(listener);
        this.btnClear.addActionListener(listener);
        this.btnQuadcurve.addActionListener(listener);
        this.btnOval.addActionListener(listener);
        this.btnRectangel.addActionListener(listener);
        this.btnTriangle.addActionListener(listener);
        this.btnSelection.addActionListener(listener);
        this.btnAutofill.addActionListener(listener);
        this.btnText.addActionListener(listener);
        this.btnColorChooser.addActionListener(listener);
        this.colorPicker.addActionListener(listener);
        this.editImageBox.addActionListener(listener);
        this.changeJoinBox.addActionListener(listener);
        this.btnStar.addActionListener(listener);
        this.btnCrop.addActionListener(listener);
        this.btnHeart.addActionListener(listener);
        this.btnRahimbus.addActionListener(listener);
        this.chkItalic.addActionListener(listener);
        this.chkBold.addActionListener(listener);
        this.chkStrkTrh.addActionListener(listener);
        this.fontBox.addActionListener(listener);
        this.btnBahai.addActionListener(listener);
        this.btnHitMe.addActionListener(listener);
        this.btnBucket.addActionListener(listener);
    }

    public void addItemListeners(ItemListener itemListener) {
        this.chckbxDashed.addItemListener(itemListener);
        this.chckbxRandomColor.addItemListener(itemListener);
        this.chckbxGredient.addItemListener(itemListener);
        this.fillAllShapes.addItemListener(itemListener);

    }

    public void addChangeListeners(ChangeListener changeListener) {
        this.spinner.addChangeListener(changeListener);
    }
}
