package by.epam.litvinko.beautysalon.tag;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The type Title image tag.
 */
public class TitleImageTag extends TagSupport {

    private static final Logger logger = LogManager.getLogger(TitleImageTag.class);

    @Override
    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<link rel=\"shortcut icon\" href=\"../static/core/img/ico.png\" type=\"image/png\">");
        } catch (IOException e) {
            logger.error("Error while writing to out stream for tag", e);
        }
        return SKIP_BODY;
    }
}
