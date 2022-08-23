package com.freecoder.enlearn.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freecoder.enlearn.model.Word;
import org.jsoup.internal.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank
 * @date 2022/4/28 13:16
 */
@Component
public class FileUtils {
    ObjectMapper objectMapper = new ObjectMapper();

    public List<String> loadData() throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("COCA_20000.txt"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    list.add(line.trim());
                }
            }
        }
        return list;
    }

    public void persistWord(Word word) throws IOException {
        if (word == null || StringUtil.isBlank(word.getWord())) {
            return;
        }
        String json = objectMapper.writeValueAsString(word);

        if(!new File("dict").exists()) {
            new File("dict").mkdir();
        }
        File file = new File("dict/" + word.getWord() + ".json");
        if (file.exists()){
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("dict/" + word.getWord() + ".json"));) {
            bw.write(json);
            bw.newLine();
        }
    }

    public void persistUKAudio(String word, byte[] bts) throws IOException {
        if(!new File("mp3").exists()) {
            new File("mp3").mkdir();
        }
        File mp3File = new File("mp3/" + word + "_uk.mp3");
        if (mp3File.exists()) {
            return;
        }
        try(FileOutputStream out = new FileOutputStream(mp3File)){
            out.write(bts);
        }
    }
    public void persistUSAudio(String word, byte[] bts) throws IOException {
        if(!new File("mp3").exists()) {
            new File("mp3").mkdir();
        }
        File mp3File = new File("mp3/" + word + "_us.mp3");
        if (mp3File.exists()) {
            return;
        }
        try(FileOutputStream out = new FileOutputStream(mp3File)){
            out.write(bts);
        }
    }

}
