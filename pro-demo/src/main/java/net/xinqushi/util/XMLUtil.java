package net.xinqushi.util;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

@Component
public class XMLUtil {
    
    private static Logger logger = LoggerFactory.getLogger(XMLUtil.class);

    public String mapToXML(Map<String, String> source) {
        if (source == null || source.isEmpty()) {
            return "";
        }
        List<String> keys = new ArrayList<String>(source.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (String key : keys) {
            sb.append("<").append(key).append(">");
            sb.append(source.get(key));
            sb.append("</").append(key).append(">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    public Map<String, String> XMLToMap(String xml) {
        StringReader stringReader = new StringReader(xml);
        InputSource source = new InputSource(stringReader);
        SAXBuilder saxBuilder = new SAXBuilder();
        Map<String, String> map = new HashMap<String, String>();
        try {
            Document doc = saxBuilder.build(source);
            Element root = doc.getRootElement();
            List<Element> elements = root.getChildren();
            for (Element element : elements) {
                map.put(element.getName(), element.getValue());
            }
        } catch (Exception e) {
            logger.error("Fail to conver XML to map", e);
        }
        return map;
    }
}
