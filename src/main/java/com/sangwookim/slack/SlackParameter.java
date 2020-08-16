package com.sangwookim.slack;

import java.util.List;

public class SlackParameter {
    private String channel;
    private String username;
    private String text;
    private List<SlackAttachment> attachments;

    public String getChannel() {
        return channel;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public List<SlackAttachment> getAttachments() {
        return attachments;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAttachments(List<SlackAttachment> attachments) {
        this.attachments = attachments;
    }
}
