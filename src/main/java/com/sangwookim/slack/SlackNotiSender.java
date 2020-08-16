package com.sangwookim.slack;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
@Log4j
public class SlackNotiSender {
    @Value("https://hooks.slack.com/services/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")
    private String url;
    private RestTemplate restTemplate;
    private SlackParameter slackParameter;

    public SlackNotiSender() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        slackParameter = new SlackParameter();
    }

    public void noticePost(SlackAttachment slackAttachment)
    {
        slackParameter.setText("noticePost");
        List<SlackAttachment> list = new ArrayList<>();
        list.add(slackAttachment);
        slackParameter.setAttachments(list);

        log.info(slackParameter.getAttachments().get(0).getAuthor_name());

        restTemplate.postForObject(url, slackParameter, String.class);
    }
}
