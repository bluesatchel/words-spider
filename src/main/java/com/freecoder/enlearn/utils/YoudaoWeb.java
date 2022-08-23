package com.freecoder.enlearn.utils;

import com.freecoder.enlearn.model.Word;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Frank
 * @date 2022/4/28 13:34
 */
@Component
@Slf4j
public class YoudaoWeb {
    static String url = "https://youdao.com/w/";

    public Word analyse(String word) throws IOException {
        Document doc = Jsoup.connect(url + word).get();
        String keyWord = doc.select(".keyword").text();

        if (!Objects.equals(word, keyWord)) {
            log.error("{} 在词典查不到", word);
            return null;
        }
        Elements phoneticEles = doc.select(".baav .phonetic");
        //获取英式音标
        String uk_phonetic = phoneticEles == null || phoneticEles.size() == 0?"":(phoneticEles.size() == 1? phoneticEles.get(0).text() : phoneticEles.get(0).text());
        // 获取美式音标
        String us_phonetic = phoneticEles == null || phoneticEles.size() == 0?"":(phoneticEles.size() == 1? phoneticEles.get(0).text() : phoneticEles.get(1).text());

        // 单词解释
        List<String> explains = new ArrayList<>();
        Elements explainsEles = doc.select("#phrsListTab .trans-container li");
        if (explainsEles != null) {
            for (Element element : explainsEles) {
                explains.add(element.text());
            }
        }

        // 词组短语
        List<String> wordGroup = new ArrayList<>();
        Elements wordGroupEles = doc.select("#wordGroup2 p");
        if (wordGroupEles != null) {
            for (Element wordGroupEle : wordGroupEles) {
                wordGroup.add(wordGroupEle.text());
            }
        }

        // 例句
        List<Pair<String, String>> sentences = new ArrayList<>();
        Elements sentenceEles = doc.select("#bilingual li p[class!=example-via]");
        if (sentenceEles != null) {
            for (int i=0; i<sentenceEles.size()/2; i++) {
                sentences.add(new Pair<>(sentenceEles.get(2*i).text(), sentenceEles.get(2*i+1).text().trim()));
            }
        }
        return new Word(word, uk_phonetic,us_phonetic, explains, wordGroup, sentences);
    }
}
