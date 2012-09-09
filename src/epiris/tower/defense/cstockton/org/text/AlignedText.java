package epiris.tower.defense.cstockton.org.text;

import java.util.Enumeration;
import java.util.Vector;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;

public class AlignedText extends ChangeableText {

    HorizontalAlign mAlignment;
    float mInitialX;

    public static String wrapText(final String text, final int len) {

        // return empty array for null text
        if (text == null) {
            return new String();
        }

        // return text if len is zero or less
        if (len <= 0) {
            return text;
        }

        // return text if less than length
        if (text.length() <= len) {
            return text;
        }

        char[] chars = text.toCharArray();
        final Vector<String> lines = new Vector<String>();
        final StringBuffer line = new StringBuffer();
        final StringBuffer word = new StringBuffer();

        for (int i = 0; i < chars.length; i++) {
            word.append(chars[i]);

            if (chars[i] == ' ') {
                if ((line.length() + word.length()) > len) {
                    lines.add(line.toString());
                    line.delete(0, line.length());
                }

                line.append(word);
                word.delete(0, word.length());
            }
        }

        // handle any extra chars in current word
        if (word.length() > 0) {
            if ((line.length() + word.length()) > len) {
                lines.add(line.toString());
                line.delete(0, line.length());
            }
            line.append(word);
        }

        // handle extra line
        if (line.length() > 0) {
            lines.add(line.toString());
        }

        String ret = "";
        for (final Enumeration<String> e = lines.elements(); e.hasMoreElements();) {
            ret += "\n" + e.nextElement();
        }

        return ret;
    }

    public AlignedText(float pX, float pY, Font pFont, String pText, HorizontalAlign pHorizontalAlign) {
        super(pX, pY, pFont, pText, pHorizontalAlign, pText.length());
        mAlignment = pHorizontalAlign;
        mInitialX = pX;
        alignText();
    }

    public AlignedText(float pX, float pY, Font pFont, String pText, HorizontalAlign pHorizontalAlign, final int pLength) {
        super(pX, pY, pFont, pText, pHorizontalAlign, pLength);
        mAlignment = pHorizontalAlign;
        mInitialX = pX;
        alignText();
    }

    private void alignText() {
        float textwidth = getWidth();
        float x = 0;
        if (mAlignment == HorizontalAlign.CENTER)
            x = mInitialX - textwidth / 2;
        if (mAlignment == HorizontalAlign.RIGHT)
            x = mInitialX - textwidth;
        setPosition(x, mY);
    }

    @Override
    public void setText(String pText) {
        super.setText(pText);
        alignText();
    }
}