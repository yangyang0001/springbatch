package com.deepblue.lark;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class LarkCardBuilder {

    /**
     * 构建带多个下载按钮的飞书卡片 JSON
     */
    public static String buildCardMessage(List<String> downloadUrls) {
        JSONObject card = new JSONObject();
        card.put("msg_type", "interactive");

        JSONObject cardObj = new JSONObject();
        card.put("card", cardObj);

        // header
        JSONObject header = new JSONObject();
        header.put("title", new JSONObject() {{
            put("tag", "plain_text");
            put("content", "📢 BP营销短信重复发送用户通知");
        }});
        cardObj.put("header", header);

        // elements
        JSONArray elements = new JSONArray();

        elements.add(markdown("**任务开始时间：** 2025-11-11 03:00:00"));
        elements.add(markdown("**任务结束时间：** 2025-11-11 03:10:00"));
        elements.add(markdown("**业务系统：** 星灵"));
        elements.add(markdown("**任务ID：** 12345"));
        elements.add(markdown("**任务名称：** TEST_AAAA"));
        elements.add(markdown("**任务用户总数：** 1000"));
        elements.add(markdown("**重复用户数：** 200"));

        // 添加多个下载按钮
        elements.add(markdown("重复用户明细："));

        int index = 1;
        for (String url : downloadUrls) {
            elements.add(makeDownloadButton("下载明细 " + index, url));
            index++;
        }

        elements.add(separator());
        elements.add(markdown("请及时处理，避免再次误发送。"));

        cardObj.put("elements", elements);

        return card.toJSONString();
    }

    private static JSONObject markdown(String text) {
        return new JSONObject() {{
            put("tag", "markdown");
            put("content", text);
        }};
    }

    private static JSONObject separator() {
        return new JSONObject() {{
            put("tag", "hr");
        }};
    }

    private static JSONObject makeDownloadButton(String text, String url) {
        // div + button 结构
        JSONObject div = new JSONObject();
        div.put("tag", "div");

        JSONObject extra = new JSONObject();
        extra.put("tag", "button");
        extra.put("text", new JSONObject() {{
            put("tag", "plain_text");
            put("content", text);
        }});
        extra.put("type", "default");
        extra.put("url", url);

        div.put("extra", extra);
        return div;
    }
}
