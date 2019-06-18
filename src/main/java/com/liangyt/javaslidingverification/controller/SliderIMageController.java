package com.liangyt.javaslidingverification.controller;

import com.liangyt.javaslidingverification.sliding.SlidingImage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 描述：生成图片
 * 作者：lyt
 * 日期：2019/6/18 4:40 PM
 * 类名：SliderIMageController
 * 版本： version 1.0
 */
@Controller
@RequestMapping(value = "/slider")
public class SliderIMageController {
    // 保存对应的位置，以作对比
    WeakHashMap<String, Localtion> localtion = new WeakHashMap<>();


    @GetMapping
    public String fetchImage(HttpServletRequest request, Model model) throws IOException {
        String sessionId = request.getSession().getId();

        // 位置需要随机生成，并且需要注意最大值 原图大小 - 滑动大小 还有就是估计得尽量靠右一些
        localtion.put(sessionId, new Localtion(30, 40));

        SlidingImage si = new SlidingImage();
        Map<String, String> images = si.create(30, 40);

        model.addAttribute("backImage", images.get("backImage"));
        model.addAttribute("slidingImage", images.get("slidingImage"));

        return "index";
    }

    /**
     * 位置(左上角)
     */
    class Localtion {
        private int x;
        private int y;

        public Localtion() {
        }

        public Localtion(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
