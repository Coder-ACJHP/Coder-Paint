package com.coder.userInterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


public class EditImageBox extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String[] options = {"Edit picture", "Blur image", "Flip image", "Rotate image", "Invert image"};
	protected ImageIcon[] images;
	@SuppressWarnings("rawtypes")
	protected JComboBox editBox;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditImageBox() {
		super(new BorderLayout());
		images = new ImageIcon[options.length];
		Integer[] intArray = new Integer[options.length];
		for (int i = 0; i < options.length; i++) {
			intArray[i] = new Integer(i);
			images[i] = createImageIcon("/com/coder/icons/" + options[i] + ".png");
			if (images[i] != null) {
				images[i].setDescription(options[i]);
			}
		}
		editBox = new JComboBox(intArray);
		editBox.setSelectedIndex(0);
		editBox.setActionCommand("editbox");
		
		ComboBoxRenderer renderer = new ComboBoxRenderer();
		renderer.setPreferredSize(new Dimension(115, 25));
		editBox.setRenderer(renderer);
		editBox.setSelectedIndex(0);
		editBox.setMaximumRowCount(4);
		add(editBox, BorderLayout.PAGE_START);
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ChangeJoinBox.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			return null;
		}
	}

	protected void addActionListener(final ActionListener listener) {
		this.editBox.addActionListener(listener);
	}

	@SuppressWarnings("rawtypes")
	class ComboBoxRenderer extends JLabel implements ListCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Font uhOhFont;

		public ComboBoxRenderer() {
			setOpaque(true);
			setHorizontalAlignment(LEFT);
			setVerticalAlignment(CENTER);
		}

		/*
		 * This method finds the image and text corresponding to the selected
		 * value and returns the label, set up to display the text and image.
		 */
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			int selectedIndex = ((Integer) value).intValue();

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			ImageIcon icon = images[selectedIndex];
			String pet = options[selectedIndex];
			setIcon(icon);
			if (icon != null) {
				setText(pet);
				setFont(new Font("Dialog", Font.PLAIN, 9));
			} else {
				setUhOhText(pet, list.getFont());
			}

			return this;
		}

		// Set the font and text when no image was found.
		protected void setUhOhText(String uhOhText, Font normalFont) {
			if (uhOhFont == null) { // lazily create this font
				uhOhFont = normalFont.deriveFont(Font.ITALIC);
			}
			setFont(uhOhFont);
			setText(uhOhText);
		}
	}
}
