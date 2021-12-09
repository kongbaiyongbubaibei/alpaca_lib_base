package com.alpaca.library_base.event;

public class WxTemplateEvent {

    public String touser;
    public String template_id;
    public String scene;
    public WxTemplateEvent() {
    }

    public WxTemplateEvent(String touser, String template_id, String scene) {
        this.touser = touser;
        this.template_id = template_id;
        this.scene = scene;
    }
}
