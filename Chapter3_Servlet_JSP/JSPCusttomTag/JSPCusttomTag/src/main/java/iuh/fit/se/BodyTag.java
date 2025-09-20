package iuh.fit.se;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.BodyContent;
import jakarta.servlet.jsp.tagext.BodyTagSupport;

import java.io.IOException;

public class BodyTag extends BodyTagSupport {
    private int times;

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public int doStartTag() throws JspException {
        if (times > 0) {
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doAfterBody() throws JspException {
        if (--times >= 0) {
            try {
                BodyContent bc = getBodyContent();
                bc.writeOut(bc.getEnclosingWriter());
                bc.clearBody();
            } catch (IOException e) {
                throw new JspException("Error in BodyTag", e);
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
