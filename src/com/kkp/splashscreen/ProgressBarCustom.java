package com.kkp.splashscreen;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class ProgressBarCustom extends JProgressBar {

    private Color colorString = new Color(0, 0, 0);
    private final int borderRadius = 10;
    private Color gradientStart = new Color(66, 133, 244);  // Google Blue
    private Color gradientEnd = new Color(15, 157, 88);     // Google Green
    private int animationSpeed = 1;
    private float transparency = 0.8f;

    public ProgressBarCustom() {
        setPreferredSize(new Dimension(200, 8));  // Slightly taller for better visibility
        setBackground(new Color(240, 240, 240));  // Lighter background
        setForeground(gradientStart);
        setBorderPainted(false);
        setOpaque(false);

        // Custom UI
        setUI(new BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                int width = c.getWidth();
                int height = c.getHeight();

                // Draw background with rounded corners
                g2d.setColor(getBackground());
                RoundRectangle2D background = new RoundRectangle2D.Float(0, 0, width, height, borderRadius, borderRadius);
                g2d.fill(background);

                // Calculate progress width
                int progressWidth = (int) ((width * getPercentComplete()));

                if (progressWidth > 0)
                {
                    // Create gradient paint
                    GradientPaint gradient = new GradientPaint(
                            0, 0, gradientStart,
                            progressWidth, height, gradientEnd
                    );
                    g2d.setPaint(gradient);

                    // Draw progress with rounded corners
                    RoundRectangle2D progress = new RoundRectangle2D.Float(
                            0, 0, progressWidth, height, borderRadius, borderRadius
                    );
                    g2d.fill(progress);

                    // Add shine effect
                    AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
                    g2d.setComposite(alpha);
                    GradientPaint shine = new GradientPaint(
                            0, 0, new Color(255, 255, 255, 100),
                            0, height / 2, new Color(255, 255, 255, 0)
                    );
                    g2d.setPaint(shine);
                    g2d.fill(progress);
                }

                // Draw percentage text if string painted is enabled
                if (isStringPainted())
                {
                    paintString(g2d, 0, 0, width, height, progressWidth, getInsets());
                }

                g2d.dispose();
            }

            @Override
            protected void paintIndeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = c.getWidth();
                int height = c.getHeight();

                // Draw background
                g2d.setColor(getBackground());
                RoundRectangle2D background = new RoundRectangle2D.Float(0, 0, width, height, borderRadius, borderRadius);
                g2d.fill(background);

                // Calculate animation position
                int animationWidth = width / 3;
                int pos = getAnimationIndex();
                pos = (pos * animationSpeed) % (width + animationWidth) - animationWidth;

                // Draw animated gradient
                GradientPaint gradient = new GradientPaint(
                        pos, 0, gradientStart,
                        pos + animationWidth, 0, gradientEnd
                );
                g2d.setPaint(gradient);

                RoundRectangle2D progress = new RoundRectangle2D.Float(
                        pos, 0, animationWidth, height, borderRadius, borderRadius
                );
                g2d.fill(progress);

                g2d.dispose();
            }

            @Override
            protected void paintString(Graphics g, int x, int y, int width, int height, int fillStart, Insets insets) {
                if (isStringPainted())
                {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2d.setColor(getColorString());

                    // Format the percentage string
                    String progressString = String.format("%d%%", (int) (getPercentComplete() * 100));

                    g2d.setFont(getFont());
                    FontMetrics fm = g2d.getFontMetrics();
                    int stringWidth = fm.stringWidth(progressString);
                    int stringHeight = fm.getHeight();

                    g2d.drawString(progressString,
                            width / 2 - stringWidth / 2,
                            height / 2 + stringHeight / 4
                    );

                    g2d.dispose();
                }
            }
        });
    }

    // Getter and setter methods
    public Color getColorString() {
        return colorString;
    }

    public void setColorString(Color colorString) {
        this.colorString = colorString;
    }

    public void setGradientColors(Color start, Color end) {
        this.gradientStart = start;
        this.gradientEnd = end;
        repaint();
    }

    public void setAnimationSpeed(int speed) {
        this.animationSpeed = speed;
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
        repaint();
    }
}
