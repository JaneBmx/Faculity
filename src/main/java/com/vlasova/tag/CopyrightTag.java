package com.vlasova.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CopyrightTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(CopyrightTag.class);
    private static final String COPYRIGHT_TAG = "J :)";

    @Override
    public int doStartTag() {
        try {
            pageContext.getOut().write(COPYRIGHT_TAG);
        } catch (IOException e) {
            LOGGER.warn(e);
        }
        return SKIP_BODY;
    }
}