package ru.dv.mailhelper.beans;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.dv.mailhelper.entities.Mailing;
import ru.dv.mailhelper.entities.MessageItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class MsgBuild {
    private List<MessageItem> items;
    private Integer totalQuantity;

    public MsgBuild() {
        items = new ArrayList<>();
        totalQuantity = 0;
    }

    public void add(Mailing m, String subject, String body) {
        MessageItem messageItem = findMessageFromMailing(m);
        if (messageItem == null) {
            messageItem = new MessageItem();
            messageItem.setMailing(m);
            messageItem.setSubject(subject);
            messageItem.setBody(body);
            messageItem.setId(0L);
            items.add(messageItem);
        }
        recalculate();
    }

    public void remove(Long mailingId) {
        Iterator<MessageItem> iter = items.iterator();
        while (iter.hasNext()) {
            MessageItem o = iter.next();
            if (o.getMailing().getId().equals(mailingId)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        totalQuantity = 0;
        for (MessageItem o : items) {
            totalQuantity++;
        }
    }

    private MessageItem findMessageFromMailing(Mailing m) {
        return items.stream().filter(o -> o.getMailing().getId().equals(m.getId())).findFirst().orElse(null);
    }

}
