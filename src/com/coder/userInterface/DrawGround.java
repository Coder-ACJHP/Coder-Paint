package com.coder.userInterface;

/*
 * This class creating drawing area and all shapes
 * it works like double buffered but not it( see CoderPaint class)
 * First time creating buffered image and after that getting this
 * image and drawing shapes on this buffered image .When needed to change
 * this image there is method called set image ,it initialize sub image 
 * on master image because the master image cannot change from main
 * method 'paint method' . 
 */

import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.RasterFormatException;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.coder.image.ImageProps;
import com.coder.image.MyObserver;
import com.coder.image.TransferableImage;
import com.coder.shapes.EnumRope;
import com.coder.shapes.HeartShape;
import com.coder.shapes.RegularPolygon;
import com.coder.shapes.StarPolygon;
import com.coder.stack.SizedStack;

public final class DrawGround extends JPanel{

	/*
	 * 
	 * @author Coder ACJHP
	 * 
	 */


	private int dist = 6;
    private Graphics2D g2D;
    private int pressNo = 0;
    protected Rectangle rect;
    private GradientPaint gp;
    boolean clearFlag = false;
    private int dragFlag1 = -1;
    private int dragFlag2 = -1;
    private final JLabel label;
    protected EnumRope figures;
    float dashes[] = { 5f, 5f };
    private Line2D.Float line2D;
    private double HEART_RADIUS;
    private Line2D.Float line2d;
    private final Image hornIcon;
    private StarPolygon star, bahai;
    private int width, height, x, y;
    private final double zoom = 200;
    private boolean isFilled = false;
    protected boolean isCopied= false;
    private BufferedImage croppedCopy;
    private Ellipse2D.Float ellipse2d;
    private Rectangle2D.Float ErasRect;
    private QuadCurve2D.Float quadCurve;
    private CubicCurve2D.Float cubicCurve; 
    private boolean isRandomColor = false;
    static JTextArea t = new JTextArea();
    private boolean isGredientColor = false;
    private BufferedImage image, scaledImage;
    private RegularPolygon triangle, rahimbus;
    private Rectangle rectangle, movedRectangle;
    private final HeartShape heart = new HeartShape();
    protected final ImageProps imageProps = new ImageProps();
    private static final long serialVersionUID = 1L;
    private Font myFont = new Font("Dialog", Font.PLAIN, 13);
    private final List<List<Point>> points = new ArrayList<>(25);
    private static int AREA_WIDTH = 1000, AREA_HEIGHT = 625;
    private final static MyObserver observer = new MyObserver();
    private int thickness = 3, oldX, oldY, currentX, currentY, X, Y;
    protected final MyMouseListener mouseListener = new MyMouseListener();
    private final SizedStack<BufferedImage> undoStack = new SizedStack<>(12);
    private final Color selectionTrans = UIManager.getColor("List.selectionBackground");
    private Color Gredient1, Gredient2, currentColor = Color.BLACK, randomColor = Color.WHITE;
    private BasicStroke basicStroke = new BasicStroke(thickness, BasicStroke.CAP_ROUND,
    		BasicStroke.JOIN_ROUND);
    private float x1, y1, xc1cur, yc1cur, xc1new, yc1new, xc2cur, yc2cur, xc2new,
    		yc2new, x4cur, y4cur, x4new, y4new;
    private final BasicStroke stroke = new BasicStroke(1f, BasicStroke.CAP_BUTT,
    		BasicStroke.JOIN_BEVEL, 10f, dashes, 0f);
    private final Color selectionColor = new Color(selectionTrans.getRed(), 
    		selectionTrans.getGreen(), selectionTrans.getBlue(), 128);
    private final  int locations[] = {
       SwingConstants.NORTH, SwingConstants.SOUTH, SwingConstants.WEST,
       SwingConstants.EAST, SwingConstants.NORTH_WEST,
       SwingConstants.NORTH_EAST, SwingConstants.SOUTH_WEST,
       SwingConstants.SOUTH_EAST
    };
    private boolean release = false;
    
    public DrawGround() {
        super();

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
        addMouseListener(mouseListener);
        addMouseWheelListener(mouseListener);
        addMouseMotionListener(mouseListener);
        label = new JLabel();
        label.addMouseListener(mouseListener);
        label.addMouseMotionListener(mouseListener);
        t.setBackground(new Color(0,0,0,0));
        t.setColumns(0);
        t.setLineWrap(true);
        t.setEditable(false);
        this.add(t);

        
//INITIALIZE THE ICONS-------------------------------------------        
        URL urll = this.getClass().getResource("/com/coder/icons/Horn.gif");
        hornIcon = new ImageIcon(urll).getImage();
//---------------------------------------------------------------        
        image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        setVisible(true);
    }

    public void setStroke(JSlider slider) {
        this.thickness = slider.getValue();
        setBasic();
    }

    public void changeImageSizeDynmcally(int x, int y) {

        AREA_WIDTH = x;
        AREA_HEIGHT = y;
        repaint();

        scaledImage = new BufferedImage(AREA_WIDTH, AREA_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
        g2d.drawImage(image, 0, 0, observer);

        setImage(scaledImage);
    }
//---------------------------------------------------------------- 

    public void convertToRGB() {
        convertToARGB(image);
    }

    public void flipImage() {
        image = createFlipped(image);

    }

    public void RotateImage() {
        image = createRotated(image);
    }

    public void InvertImage() {
        image = createInverted(image);
    }

    public void BlurImage() {
        image = createBlured(image);
    }

    //--------------MERGE TWO IMAGES-------------------------
    public void mergeTwoImages() {
        JFileChooser sec = new JFileChooser();
        FileNameExtensionFilter JpegFilter = new FileNameExtensionFilter("JPEG Files(*.jpeg)", "jpg", "jpeg");
        FileNameExtensionFilter PngFilter = new FileNameExtensionFilter("PNG Files(*.png)", "png", "png");
        FileNameExtensionFilter GifFilter = new FileNameExtensionFilter("GIF Files(*.gif)", "gif", "gif");
        sec.setFileFilter(GifFilter);
        sec.setFileFilter(PngFilter);
        sec.setFileFilter(JpegFilter);
        sec.setMultiSelectionEnabled(true);
        sec.setAcceptAllFileFilterUsed(false);
        int result = sec.showOpenDialog(sec);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File[] file = sec.getSelectedFiles();
                BufferedImage img1 = ImageIO.read(file[0]);
                BufferedImage img2 = ImageIO.read(file[1]);
                BufferedImage mergedImage = joinBufferedImage(img1, img2);
                ImageIO.write(mergedImage, "jpg", new File(System.getProperty("user.home") + File.separator + "MergedImage.jpg"));
                Notifyuser("<html>" + "Saved at : " + System.getProperty("user.home") + File.separator + "<br>" + "Desktop\\" + "MergedImage.jpg" + "</html>");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static BufferedImage joinBufferedImage(BufferedImage img1,
            BufferedImage img2) {
        int offset = 2;
        int width = img1.getWidth() + img2.getWidth() + offset;
        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();
        return newImage;
    }

    //------------------EDIT IMAGE------------------------------
    
    private static BufferedImage convertToARGB(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, observer);
        g.dispose();
        return newImage;
    }

    private static BufferedImage createFlipped(BufferedImage image) {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1, -1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
        return createTransformed(image, at);
    }

    private static BufferedImage createRotated(BufferedImage image) {
        AffineTransform at = AffineTransform.getRotateInstance(
                Math.PI, image.getWidth() / 2, image.getHeight() / 2.0);
        return createTransformed(image, at);
    }

    private static BufferedImage createTransformed(BufferedImage image, AffineTransform at) {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    private static BufferedImage createInverted(BufferedImage image) {
        if (image.getType() != BufferedImage.TYPE_INT_ARGB) {
            image = convertToARGB(image);
        }
        LookupTable lookup = new LookupTable(0, 4) {
            @Override
            public int[] lookupPixel(int[] src, int[] dest) {
                dest[0] = (int) (255 - src[0]);
                dest[1] = (int) (255 - src[1]);
                dest[2] = (int) (255 - src[2]);
                return dest;
            }
        };
        LookupOp op = new LookupOp(lookup, new RenderingHints(null));
        return op.filter(image, null);
    }

    private static BufferedImage createBlured(BufferedImage image) {
        Kernel kernel = new Kernel(3, 3, new float[]{1f / 9f, 1f / 9f, 1f / 9f,
            1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f});
        BufferedImageOp op = new ConvolveOp(kernel);
        return image = op.filter(image, null);
    }

//------------------EDIT COLORS & STROKES-----------------------------------
    public void setAllFilled() {
        this.isFilled = true;
    }

    public void setUnFilled() {
        this.isFilled = false;
    }

    public void setDashed(JButton pen, JButton text, JSlider slider) {

        pen.setEnabled(false);
        text.setEnabled(false);
        slider.setEnabled(false);
        basicStroke = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1, new float[]{4}, 0);
    }

    public void unDashed(JButton pen, JButton text, JSlider slider) {

        pen.setEnabled(true);
        text.setEnabled(true);
        slider.setEnabled(true);
        setBasic();
    }

    public void setBeveled() {

        this.basicStroke = new BasicStroke(thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        repaint();
    }

    public void setMittered() {

        this.basicStroke = new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        repaint();
    }

    public void setRounded() {

        this.basicStroke = new BasicStroke(thickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
        repaint();
    }

    public void setBasic() {

        this.basicStroke = new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        repaint();
    }
    
    public void setTextStroke() {
    	this.basicStroke = stroke;
    	repaint();
    }
//----------FOR CHOOSING COLOR FROM COLOR CHOOSER

    public void ChooseColor() {
        isRandomColor = false;
        isGredientColor = false;
        Color color = JColorChooser.showDialog(this, "Choose Your Color", Color.BLACK);
        if (color != null) {
            currentColor = color;
        } else {
            currentColor = Color.BLACK;
        }
    }

    public void setRandomColor() {
        isGredientColor = false;
        currentColor = null;
        isRandomColor = true;
    }

    public void setUnRandomColor() {
        isRandomColor = false;
    }

    public void setGredient() {
        currentColor = null;
        isRandomColor = false;
        isGredientColor = true;
        Gredient1 = JColorChooser.showDialog(this, "Choose First Color", this.getBackground());
        Gredient2 = JColorChooser.showDialog(this, "Choose Second Color", this.getBackground());

        if (Gredient1 == null || Gredient2 == null) {
            currentColor = Color.BLACK;
        } else {
            gp = new GradientPaint(0, 0, Gredient1, 0, AREA_HEIGHT, Gredient2);
        }
    }

    public void changePanelColor(JPanel panel) {
        if (currentColor.getRGB() == 0) {
            currentColor = Color.WHITE;
        } else {
            panel.setBackground(currentColor);
        }
    }

    public void setColor(Color color) {
        this.currentColor = color;
    }

//-----------------CHOOSING FONT---------------------------
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void makeFontStrk() {
        Map attributes = myFont.getAttributes();
        attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        Font font = new Font(attributes);
        this.myFont = font;
    }

    public void setFont(String fontName, int fontStyle, int fontSize) {
        Font newFont = new Font(fontName, fontStyle, fontSize);
        this.myFont = newFont;
    }

//-------------SET ZOOM PICTURE-------------------------
    public void setZoomIn() {
        AREA_WIDTH += zoom;
        AREA_HEIGHT += zoom;
        repaint();
        figures = EnumRope.ZOOMIN;
    }

    public void setZoomOut() {
        AREA_WIDTH -= zoom;
        AREA_HEIGHT -= zoom;
        repaint();
        figures = EnumRope.ZOOMOUT;
    }

//------------------MASTER DRAWING METHOD----------------------
    public void update(Graphics g) {
        paint(g);
      }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//--------AT FIRST INITIALIZE THE ALL GRAPHICS ON BUFFERED IMAGE-------------
        this.g2D = (Graphics2D) image.getGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
        g2D.setBackground(Color.white);
        if (basicStroke != null || rectangle != null || movedRectangle != null || triangle != null || 
        		ellipse2d != null || quadCurve != null || star != null) {
        	g2D.setStroke(basicStroke);
        	 if (isRandomColor == true) {
                 currentColor = null;
                 gp = null;
                 int rR = (int) Math.round((Math.random() * 255));
                 int gG = (int) Math.round((Math.random() * 255));
                 int bB = (int) Math.round((Math.random() * 255));
                 randomColor = new Color(rR, gG, bB);
                 g2D.setColor(randomColor);
             } else if (currentColor != null) {
                 g2D.setPaint(currentColor);
             } else {
                 g2D.setPaint(gp);
             }
        }
//---------------------DRAW ALL SHAPES----------------------------------
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
        if (image != null) {
            g2d.drawImage(image, 0, 0, AREA_WIDTH, AREA_HEIGHT, observer);
        }


        if (basicStroke != null || rectangle != null || movedRectangle != null || triangle != null || 
        		ellipse2d != null || quadCurve != null || star != null) {
        	g2d.setStroke(basicStroke);
        	g2d.setPaintMode();
        }
        
        release = true;
        
        if (isRandomColor == true) {
            currentColor = null;
            isGredientColor = false;
            int rR = (int) Math.round((Math.random() * 255));
            int gG = (int) Math.round((Math.random() * 255));
            int bB = (int) Math.round((Math.random() * 255));
            randomColor = new Color(rR, gG, bB);
            g2d.setColor(randomColor);
        } else if (currentColor != null) {
            isGredientColor = false;
            isRandomColor = false;
            g2d.setPaint(currentColor);
        } else if (isGredientColor) {
            currentColor = null;
            isRandomColor = false;
            g2d.setPaint(gp);
        }

        if (g2d != null) {

            if (figures == EnumRope.RECT && rectangle != null) {
                if (isFilled == true) {
                    g2d.fill(rectangle);
                } else {
                    g2d.draw(rectangle);
                }
            } else if (figures == EnumRope.OVAL && ellipse2d != null) {
                if (isFilled == true) {
                    g2d.fill(ellipse2d);
                } else {
                    g2d.draw(ellipse2d);
                }
            } else if (figures == EnumRope.LINE && line2d != null) {

                g2d.draw(line2d);

            } else if (figures == EnumRope.PENCIL) {
                g2d.drawLine(oldX, oldY, currentX, currentY);
                repaint();
                
            } else if (figures == EnumRope.ERASER && ErasRect != null) {

                g2d.setPaint(Color.WHITE);
                g2d.fill(ErasRect);

            } else if (figures == EnumRope.CURVE) {
				if (pressNo == 1) {
					g2d.setColor(Color.black);
					g2d.setStroke(stroke);

					line2D = new Line2D.Float(x1, y1, x4cur, y4cur);
					g2d.draw(line2D);

					line2D = new Line2D.Float(x1, y1, x4new, y4new);
					g2d.draw(line2D);

					x4cur = x4new;
					y4cur = y4new;
				} else if (pressNo == 2) {
					g2d.setColor(Color.black);
					g2d.setStroke(stroke);

					if (dragFlag1 != -1) {
						quadCurve = new QuadCurve2D.Float(x1, y1, xc1cur, yc1cur, x4new, y4new);
						g2d.draw(quadCurve);
					}
					dragFlag1++; // Reset the drag-flag

					quadCurve = new QuadCurve2D.Float(x1, y1, xc1new, yc1new, x4new, y4new);
					g2d.draw(quadCurve);

					xc1cur = xc1new;
					yc1cur = yc1new;
				} else if (pressNo == 3) {
					g2d.setColor(Color.black);
					g2d.setStroke(stroke);

					if (dragFlag2 != -1) {
						cubicCurve = new CubicCurve2D.Float(x1, y1, xc1new, yc1new, xc2cur, yc2cur, x4new, y4new);
						g2d.draw(cubicCurve);
					}
					dragFlag2++;
					cubicCurve = new CubicCurve2D.Float(x1, y1, xc1new, yc1new, xc2new, yc2new, x4new, y4new);
					g2d.draw(cubicCurve);
					xc2cur = xc2new;
					yc2cur = yc2new;
				}
				if (clearFlag) {

					g2d.setColor(currentColor);
					g2d.setStroke(basicStroke);

					line2D = new Line2D.Float(x1, y1, x4new, y4new);
					g2d.draw(line2D);
					quadCurve = new QuadCurve2D.Float(x1, y1, xc1new, yc1new, x4new, y4new);
					g2d.draw(quadCurve);
					repaint();
					clearFlag = false;
				}
            } else if (figures == EnumRope.SELECTION && rectangle != null) {
            	
                g2d.setColor(selectionTrans);
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(selectionColor);
                g2d.fill(movedRectangle);
                g2d.draw(movedRectangle);

            } else if (figures == EnumRope.PASTE) {
            	release = false;
                label.setLocation(X, Y);
                if (X > 0 && X < AREA_WIDTH && Y > 0 && Y < AREA_HEIGHT) {
                    label.setLocation(X, Y);
                } else {
                    label.setLocation(image.getWidth() / 2, image.getHeight() / 2);
                }

            } else if (figures == EnumRope.DOIT && isCopied) {
            	Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    BufferedImage clippedImage = null;
        			try {
        				g2d = (Graphics2D) image.getGraphics();
        				clippedImage = (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
        				g2d.drawImage(clippedImage, label.getLocation().x, label.getLocation().y, label.getWidth(), label.getHeight(), observer);
        			} catch (UnsupportedFlavorException | IOException e) {
        				e.printStackTrace();
        			}
        			label.setIcon(null);
        			label.setVisible(false);
        			this.remove(label);
        			clearClipBoard();
        			isCopied = false;
                }

            } else if (figures == EnumRope.TRIANGLE && triangle != null) {
                if (isFilled == true) {
                    g2d.fill(triangle);
                } else {
                    g2d.drawPolygon(triangle);
                }

            } else if (figures == EnumRope.HEART && heart != null) {

            	if (isFilled == true) {
            		heart.drawFilledHeart(g2d);
                } else {
                	heart.drawHeart(g2d);
                }

            } else if (figures == EnumRope.RAHIMBUS && rahimbus != null) {
                if (isFilled == true) {
                    g2d.fill(rahimbus);
                } else {
                    g2d.draw(rahimbus);
                }
            }else if(figures == EnumRope.BAHAI && bahai != null) {
                if (isFilled == true) {
                    g2d.fill(bahai);
                } else {
                    g2d.draw(bahai);
                }
            
            } else if (figures == EnumRope.TEXT && rect != null) {
            	release = false;
                t.setFont(myFont);
                g2d.setPaint(selectionTrans);
                g2d.draw(rect);
                for (int i = 0; i < locations.length; i++) {
                    Rectangle rect = getRectangle(x, y, width, height, locations[i]);
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.setColor(selectionTrans);
                    g2d.drawRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
                }
                t.setForeground(currentColor);
                t.setBounds(setTextAreaSize());
                t.setEditable(true);
                t.setVisible(true);
            } else if (figures == EnumRope.GUIDELINES) {
                for (int x = 2; x <= AREA_WIDTH - 2; x += 10) {
                    for (int y = 2; y <= AREA_HEIGHT - 2; y += 10) {
                        g2d.setStroke(new BasicStroke(1));
                        g2d.setColor(Color.GRAY.brighter());
                        g2d.drawRect(x, y, 10, 10);
                    }
                }
            } else if(figures == EnumRope.HITME) {
                if(hornIcon != null) {
                     g2d.drawImage(hornIcon,0,0, AREA_WIDTH, AREA_HEIGHT, null);
                     repaint();
                }
               
            }else if (figures == EnumRope.STAR && star != null) {
                if (isFilled == true) {
                    g2d.fill(star);
                } else {
                    g2d.draw(star);
                }
            }
           if(release) {
        	   g2d.finalize();
               g2d.dispose();
           }else {
        	   repaint();
           }
        }
    }
//----------------GET RECT BOUNDS FOR MOVABLE TEXTAREA--------------
    private Rectangle setTextAreaSize() {
    	double locX = 0, locY = 0, Width = 0, Height = 0;
      if(rect.getBounds() != null) {	
    	 locX = rect.getLocation().getX()+5;
    	 locY = rect.getLocation().getY()+5;
    	 Width = rect.getWidth() -10;
    	 Height = rect.getHeight() -10;
      }
    	return new Rectangle((int)locX, (int)locY, (int)Width, (int)Height);
    }
    
//----------------REFRESH THE SRAWING AREA-------------------   
    public void redraw() {
    	x = 0;
    	y = 0;
    	width = 0;
    	height = 0;
        oldX = 0;
        oldY = 0;
        currentX = 0;
        currentY = 0;
        repaint(100, image.getMinX(), image.getMinY(), AREA_WIDTH, AREA_HEIGHT);
        this.repaint();
    }

    public void clearArea() {
        label.setIcon(null);
        points.clear();
        currentX = 0;
        currentY = 0;
        oldX = 0;
        oldY = 0;
        image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        repaint();
    }

	public static void clearClipBoard() {
		try {
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new Transferable() {
				public DataFlavor[] getTransferDataFlavors() {
					return new DataFlavor[0];
				}

				public boolean isDataFlavorSupported(DataFlavor flavor) {
					return false;
				}

				public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
					throw new UnsupportedFlavorException(flavor);
				}
			}, null);
		} catch (IllegalStateException e) {
		}
	}
    
//--------------DO UNDO (ADD TO STACK)------------------
    public void undo() {
        if (undoStack.size() > 0) {
            setImage(undoStack.pop());
            label.setIcon(null);
        }
    }

    private void saveToStack(BufferedImage img2) {
        BufferedImage imageForStack = new BufferedImage(AREA_WIDTH, AREA_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imageForStack.createGraphics();
        g2d.drawImage(img2, 0, 0, Color.WHITE, observer);
        undoStack.push(imageForStack);
    }

//-----------WHEN YOU CHANGE IMAGE SET IT MASTER-------------------
    public void setImage(BufferedImage img) {
        if (img != null) {
            g2D = (Graphics2D) img.getGraphics();
            g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
            image = img;
            repaint();
        }
    }

    private BufferedImage copyImage(BufferedImage img) {
        BufferedImage copyOfImage = new BufferedImage(AREA_WIDTH, AREA_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, AREA_WIDTH, AREA_HEIGHT, observer);
        return copyOfImage;
    }

    public void cropImage() {

        if (movedRectangle != null) {
            BufferedImage cropedImage = image.getSubimage(movedRectangle.x, movedRectangle.y, movedRectangle.width, movedRectangle.height);
            croppedCopy = new BufferedImage(cropedImage.getWidth(), cropedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = croppedCopy.getGraphics();
            g.drawImage(cropedImage, 0, 0, observer);
            setImage(croppedCopy);
            repaint();
        }
    }

    public Dimension getCroppedSize() {
        if (croppedCopy != null) {
            return new Dimension(croppedCopy.getWidth(), croppedCopy.getHeight());
        }
        return null;

    }

    public void addToClipboard() {
        if (movedRectangle != null) {
            try {
                BufferedImage copyOfImage = image.getSubimage(movedRectangle.x, movedRectangle.y, movedRectangle.width,
                        movedRectangle.height);
                TransferableImage transferableImage = new TransferableImage(copyOfImage);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(transferableImage, transferableImage);
                Notifyuser("Selected area copied to clipboard.");
                isCopied = true;
            } catch (RasterFormatException ex) {
                Notifyuser(ex.getMessage());
            }
        }
    }

    public BufferedImage getFromClipboard(){

        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            BufferedImage clippedImage = null;
			try {
				clippedImage = (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
			this.add(label);
            label.setIcon(new ImageIcon(clippedImage));
            label.setSize(clippedImage.getWidth(), clippedImage.getHeight());
            label.setVisible(true);
            figures = EnumRope.PASTE;
            Notifyuser("Move it via mouse! To paste just double click on screen.");
            return clippedImage;
        }
        Notifyuser("At first u need to copy something!");
        return null;
    }
//------------------ERASER CURSOR IMAGE---------------------------

    protected BufferedImage getCursorImage() {
        int w = 27, h = 27,
                type = BufferedImage.TYPE_INT_ARGB_PRE;
        BufferedImage cursorImage = new BufferedImage(w, h, type);
        Graphics2D g2 = cursorImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.drawRect(9, 9, 9, 9);
        g2.setColor(Color.BLACK);
        g2.drawRect(10, 10, 10, 10);
        g2.dispose();
        return cursorImage;
    }
    
//-----------------CAHANGE COLOR OF TOOLBAR PANEL-----------------

    public void changeColor(JPanel panel) {
        panel.setBackground(currentColor);
    }

//-----------------IMAGE PROPERTIES---------------------------
    public static void floodFillImage(BufferedImage image,int x, int y, Color color) 
    {
        int srcColor = image.getRGB(x, y);
        boolean[][] hits = new boolean[image.getHeight()][image.getWidth()];

        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) 
        {
            Point p = queue.remove();

            if(floodFillImageDo(image,hits,p.x,p.y, srcColor, color.getRGB()))
            {     
                queue.add(new Point(p.x,p.y - 1)); 
                queue.add(new Point(p.x,p.y + 1)); 
                queue.add(new Point(p.x - 1,p.y)); 
                queue.add(new Point(p.x + 1,p.y)); 
            }
        }
    }

    private static boolean floodFillImageDo(BufferedImage image, boolean[][] hits,int x, int y, int srcColor, int tgtColor) 
    {
        if (y < 0) return false;
        if (x < 0) return false;
        if (y > image.getHeight()-1) return false;
        if (x > image.getWidth()-1) return false;
        if (hits[y][x]) return false;
        if (image.getRGB(x, y)!=srcColor)
            return false;

        image.setRGB(x, y, tgtColor);
        hits[y][x] = true;
        return true;
    }
	
    public BufferedImage getImageProp() {
        String imgSize = image.getWidth() + " X " + image.getHeight() + " px";
        double imgFileSize = (double) image.getTransparency();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date today = new Date();
        String creationDate = sdf.format(today);
        String imgType;
        if (BufferedImage.TYPE_BYTE_BINARY == image.getType()) {
            imgType = "Black&White";
        } else {
            imgType = "Colored";
        }

        imageProps.setVisible(true);
        imageProps.setLocationRelativeTo(null);
        imageProps.setImageProperties(imgSize, imageProps.getAspectRatio(), creationDate, imgFileSize);
        imageProps.setColoredChoosed(imgType);
        imageProps.setImagePanelImage(image);
        return this.image;
    }

    public void getScreenCapture() {
        Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenCapture = null;
        try {
            screenCapture = new Robot().createScreenCapture(screen);
        } catch (AWTException e) {
        }
        int w = screenCapture.getWidth();
        int h = screenCapture.getHeight();
        if (w > AREA_WIDTH && h > AREA_HEIGHT) {
			w = AREA_WIDTH;
			h = AREA_HEIGHT;
			repaint();
		}
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        g2D.drawImage(screenCapture, 0, 0, w, h, observer);
        g2D.dispose();
        repaint();
    }

//--------------ALERT(NOTFYING)----------------------------
    public void Notifyuser(String note) {
        NotifyMe notifyMe = new NotifyMe();
        notifyMe.setNotifiyNote(note);
        notifyMe.setVisible(true);
        Timer swingTimer = new Timer(3000, (ActionEvent e) -> {
            notifyMe.setVisible(false);
        });
        swingTimer.start();
    }

//----------------------IMPORT IMAGE----------------------------
    public void importImage() {
        JFileChooser sec = new JFileChooser();
        FileNameExtensionFilter JpegFilter = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png");
        sec.setFileFilter(JpegFilter);
        sec.setAcceptAllFileFilterUsed(false);
        sec.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = sec.showOpenDialog(sec);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage newImage = ImageIO.read(sec.getSelectedFile());
                if (newImage != null) {
                    int w = newImage.getWidth();
                    int h = newImage.getHeight();

					if (w > AREA_WIDTH && h > AREA_HEIGHT) {
						w = AREA_WIDTH;
						h = AREA_HEIGHT;
						repaint();
					}

                    g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
                    g2D.drawImage(newImage, 0, 0, w, h, observer);
                    g2D.dispose();
                    repaint();
                } else {

                    Notifyuser("Image cannot be null!");
                }
            } catch (IOException e1) {
                Notifyuser("Error! cannot import image...");
            }
        }
    }

//-----------------------SAVE IMAGE AS--------------------------------
    public boolean SaveImage() {
        boolean status = false;
        try {
            final JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter JpegFilter = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png");
            chooser.setFileFilter(JpegFilter);
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setSelectedFile(new File("Untitled.png"));
            chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
            chooser.setDialogTitle("Specify a directory to save the file.");
            int result = chooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File fileToSave = chooser.getSelectedFile();
                String path = fileToSave.getAbsolutePath();

                if (path != null && path.length() > 0) {
                    String type = path.substring(path.length() - 3);
                    if (type.equalsIgnoreCase("jpg")) {
                        BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
                        convertedImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, observer);
                        ImageIO.write(convertedImage, type, new File(path));
                    } else {
                        ImageIO.write(image, type, new File(path));
                    }
                    Notifyuser("Your image saved successfully.");
                    status = true;
                } else {
                    Notifyuser("Your directory is not correct!");
                    status = false;
                }
            }

        } catch (IOException e2) {
            System.out.println("Cannot save the image!");
        }
        return status;
    }
//---------------------PRINT THE DRAWING AREA-------------------------- 

    public void printImage() {

        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable((Graphics graphics, PageFormat pageFormat, int pageIndex) -> {
            if (pageIndex != 0) {
                return NO_SUCH_PAGE;
            }
            graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), observer);
            return PAGE_EXISTS;
        });
        try {
            boolean OK = printJob.printDialog();
            if (OK) {
                printJob.print();
            } else {
                Notifyuser("Print job cancelled!");
            }
        } catch (PrinterException e1) {
        }
    }

//---------------THE FOLLOWING SHAPES JUST DRAWIN ON IMAGE------------
    
    public void addRectangle(Rectangle rectangle) {

        if (isFilled) {
            g2D.fill(rectangle);
        } else {
            g2D.draw(rectangle);
        }
        repaint();
    }

    public void addEllipse(Ellipse2D.Float ellipse2D) {

        if (isFilled) {
            g2D.fill(ellipse2D);
        } else {
            g2D.draw(ellipse2D);
        }
        repaint();
    }

    public void addStar(StarPolygon star) {

        if (isFilled) {
            g2D.fill(star);
        } else {
            g2D.draw(star);
        }
        repaint();
    }

     public void addBahai(StarPolygon bahai) {

        if (isFilled) {
            g2D.fill(bahai);
        } else {
            g2D.draw(bahai);
        }
        repaint();
    }

    public void addRegularPolygon(RegularPolygon polyRect) {

        if (isFilled) {
            g2D.fill(polyRect);
        } else {
            g2D.draw(polyRect);
        }
        repaint();
    }
    
    public void addTriangle(RegularPolygon poly) {

        if (isFilled) {
            g2D.fillPolygon(poly);
        } else {
            g2D.drawPolygon(poly);
        }
        repaint();
    }

    public void addLine(Line2D.Float line2D) {

        g2D.draw(line2D);
        repaint();
    }

    public void addEraser(Rectangle2D.Float erasRect) {
        g2D.setStroke(basicStroke);
        g2D.setPaint(Color.WHITE);
        g2D.fill(erasRect);
        repaint();
    }

    public void addPencil(Color color, int tickness) {

        g2D.setStroke(new BasicStroke(tickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g2D.drawLine(oldX, oldY, currentX, currentY);
        oldX = currentX;
        oldY = currentY;
        repaint();
    }

	public void addCurve(CubicCurve2D.Float cubicCurve) {
		g2D.draw(cubicCurve);
		repaint();
		
	}


    public void addHeart(HeartShape heart) {

        if(isFilled) {
        	heart.drawFilledHeart(g2D);
        	repaint();
        }else {
        	heart.drawHeart(g2D);
        	repaint();
        }
        
    }

    private Rectangle getRectangle(int x, int y, int w, int h, int location) {

        switch (location) {
            case SwingConstants.NORTH:
                return new Rectangle(x + w / 2 - dist / 2, y, dist, dist);
            case SwingConstants.SOUTH:
                return new Rectangle(x + w / 2 - dist / 2, y + h - dist, dist,
                        dist);
            case SwingConstants.WEST:
                return new Rectangle(x, y + h / 2 - dist / 2, dist, dist);
            case SwingConstants.EAST:
                return new Rectangle(x + w - dist, y + h / 2 - dist / 2, dist,
                        dist);
            case SwingConstants.NORTH_WEST:
                return new Rectangle(x, y, dist, dist);
            case SwingConstants.NORTH_EAST:
                return new Rectangle(x + w - dist, y, dist, dist);
            case SwingConstants.SOUTH_WEST:
                return new Rectangle(x, y + h - dist, dist, dist);
            case SwingConstants.SOUTH_EAST:
                return new Rectangle(x + w - dist, y + h - dist, dist, dist);
        }
        return null;
    }
    
    class MyMouseListener extends MouseInputAdapter {

        private Point startpoint;
        private List<Point> newShape;

        @Override
        public void mousePressed(MouseEvent e) {
            List<Point> newShape = new ArrayList<>(25);
            newShape.add(e.getPoint());
            points.add(newShape);

            oldX = e.getX();
            oldY = e.getY();
            repaint();

//---------------CUARDIC CURVE----------------------------
            if (pressNo == 0) { 
                pressNo++;
                x1 = x4cur = e.getX();
                y1 = y4cur = e.getY();
                repaint();
              } else if (pressNo == 1) {
                pressNo++;
                xc1cur = e.getX();
                yc1cur = e.getY();
                repaint();
              } else if (pressNo == 2) {
                pressNo++;
                xc2cur = e.getX();
                yc2cur = e.getY();
                repaint();
              }
            
//-------------------TEXT RECTANGLE-----------------------
            startpoint = e.getPoint();
            rectangle = new Rectangle();
            movedRectangle = new Rectangle();
            saveToStack(copyImage(image));

            if (e.getClickCount() == 1 && rect != null && figures == EnumRope.TEXT) {
                if (e.getLocationOnScreen() != rect.getLocation()) {
                    if (t.getText() != null && t.getLocationOnScreen().x > 0 && t.getLocationOnScreen().y > 0) {
                        g2D.setFont(myFont);
                        g2D.setColor(currentColor);
                        g2D.drawString(t.getText(), setTextAreaSize().x + 5, setTextAreaSize().y + myFont.getSize());
                        t.setText("");
                        t.setEditable(false);
                        t.setVisible(false);
                        rect = null;
                        repaint();
                    }
                }
            } else if (figures == EnumRope.AUTOFILLED) {

            	floodFillImage(image, e.getX(), e.getY(), currentColor);
            	repaint();

            }
            
            //-----------------COLOR PICKER LOOKING FOR PIXEL COLOR------------
            if (figures == EnumRope.COLORPICKER) {
                int color = image.getRGB(oldX, oldY);
                currentColor = new Color(color, true);
            }
        }

   @Override
   public void mouseDragged(MouseEvent e) {
            currentX = e.getX();
            currentY = e.getY();
            repaint();

            x = Math.min(startpoint.x, e.getX());
            y = Math.min(startpoint.y, e.getY());
            width = Math.abs(startpoint.x - e.getX());
            height = Math.abs(startpoint.y - e.getY());

//-----------------THIS CODES FOR COORDINATING HEART----------------
            HEART_RADIUS = Math.min(width, height);

//------------THIS CODES FOR DRAWING AUTOFILLED------------------
            newShape = points.get(points.size() - 1);
            newShape.add(e.getPoint());
            repaint();
//--------------------------------------------------------------  
            heart.Heart(x, y, HEART_RADIUS);
            repaint();
            heart.fillHeart(x, y, HEART_RADIUS);
            repaint();
            movedRectangle.setBounds(x, y, width, height);
            repaint();
            rectangle.setBounds(x, y, width, height);
            repaint();
            ellipse2d = new Ellipse2D.Float(x, y, width, height);
            repaint();
            line2d = new Line2D.Float(startpoint.x, startpoint.y, currentX, currentY);
            repaint();
            ErasRect = new Rectangle2D.Float(currentX, currentY, thickness + 5, thickness + 5);
            repaint();
            star = new StarPolygon(currentX, currentY, Math.max(width, height) / 2, Math.max(width, height), 5, Math.PI / 2);
            repaint();
            triangle = new RegularPolygon(currentX, currentY, Math.max(width, height) / 2, 3, Math.PI / 6);
            repaint();
            rahimbus = new RegularPolygon(x, y, Math.max(width, height), 4, 0);
            repaint();
            bahai = new StarPolygon(currentX, currentY, Math.max(width, height) / 2, Math.max(width, height), 9, Math.PI / 2);
            repaint();
            rect = new Rectangle(x + dist / 2, y + dist / 2, width - dist, height - dist);
            repaint();
            
            
            int thisX = label.getLocation().x;
            int thisY = label.getLocation().y;

//-------------DETERMINE THE MOUSE MOVED SINCE THE INITIAL CLICK
            int xMoved = (thisX + e.getX()) - (thisX + startpoint.x);
            int yMoved = (thisY + e.getY()) - (thisY + startpoint.y);

//----------------MOVE THE PICTURE TO THIS---------------------
            X = thisX + xMoved;
            Y = thisY + yMoved;

            label.setLocation(X, Y);
            label.repaint();
//----------------------------CUADRIC CURVE------------------------
            if (pressNo == 1) {
                x4new = e.getX();
                y4new = e.getY();
                repaint();
              } else if (pressNo == 2) {
                xc1new = e.getX();
                yc1new = e.getY();
                repaint();
              } else if (pressNo == 3) {
                xc2new = e.getX();
                yc2new = e.getY();
                repaint();
              }
            
//--------WE NEED ADD THIS TWO SHAPE HERE BECAUSE SHAPE DARWING WITH MOUSEDRAG !
            
            if (figures == EnumRope.ERASER) {
                addEraser(ErasRect);
                ErasRect = null;
                repaint();
            } else if (figures == EnumRope.PENCIL) {
                addPencil(currentColor, thickness);
                repaint();
            }

        }
        
//----------------GETTING RELEASED COORDINATE TO DRAW LINE-------------------------

        @Override
        public void mouseReleased(MouseEvent e) {

            if (null != figures) {
                switch (figures) {
                    case OVAL:
                        addEllipse(ellipse2d);
                        ellipse2d = null;
                        break;
                    case RECT:
                        addRectangle(rectangle);
                        rectangle = null;
                        break;
                    case LINE:
                        addLine(line2d);
                        line2d = null;
                        break;
                    case CURVE:
                    	if (pressNo == 1) {
                            x4new = e.getX();
                            y4new = e.getY();
                            repaint();
                          } else if (pressNo == 2) {
                            xc1new = e.getX();
                            yc1new = e.getY();
                            repaint();
                          } else if (pressNo == 3) {
                            xc2new = e.getX();
                            yc2new = e.getY();
                            repaint();
                            pressNo = 0;
                            dragFlag1 = -1;
                            dragFlag2 = -1;
                            clearFlag = true;
                          }
                    	if(cubicCurve != null) {
                    		addCurve(cubicCurve);
                        	cubicCurve = null;
                    	}
                        break;
                    case TRIANGLE:
                        addTriangle(triangle);
                        triangle = null;
                        break;
                    case STAR:
                        addStar(star);
                        star = null;
                        break;
                    case COPY:
                        if (movedRectangle.x < 0 && movedRectangle.y < 0) {
                            movedRectangle.setLocation(0, 0);
                        } else if (movedRectangle.getWidth() > AREA_WIDTH && movedRectangle.getHeight() > AREA_HEIGHT) {
                            movedRectangle.setSize(AREA_WIDTH, AREA_HEIGHT);
                        } else {
                            addToClipboard();
                            movedRectangle = null;
                        }
                        break;
                    case CROP:
                        if (movedRectangle.x < 0 && movedRectangle.y < 0) {
                            movedRectangle.setLocation(0, 0);
                        } else if (movedRectangle.getWidth() > AREA_WIDTH && movedRectangle.getHeight() > AREA_HEIGHT) {
                            movedRectangle.setSize(AREA_WIDTH, AREA_HEIGHT);
                        } else {
                            cropImage();
                            movedRectangle = null;
                        }
                        break;
                    case AUTOFILLED:
                        repaint();
                        break;
                    case HEART:
                         addHeart(heart);
                         heart.clear();
                         break;
                    case RAHIMBUS:
                        addRegularPolygon(rahimbus);
                        rahimbus = null;
                         break;
                    case BAHAI:
                        addBahai(bahai);
                        bahai = null;
                        break;
                    default:
                        repaint();
					break;
				}
			}
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int x = e.getX();
			int y = e.getY();
			repaint();
			
			if (figures == EnumRope.SELECTION && e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
				float amount = e.getWheelRotation() * 5f;

				if (movedRectangle.getBounds2D().contains(x, y)) {

					movedRectangle.width += amount;
					movedRectangle.height += amount;
					repaint();
				}
			} else {
				removeMouseWheelListener(this);
			}

		}

	}
}
