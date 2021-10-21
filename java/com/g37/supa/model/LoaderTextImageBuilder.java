package com.g37.supa.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoaderTextImageBuilder {
    private final String filename;
    private final List<String> lines;

    public LoaderTextImageBuilder(String filename) throws IOException {
        this.filename = filename;

        URL resource = LoaderTextImageBuilder.class.getResource("/textimages/" + filename + ".ti");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        lines = readLines(br);
    }

    public Size getSize() {
        int width = lines.get(lines.size() - 1).length();
        int height = lines.size() - 1;
        return new Size(width, height);
    }

    public TextImage createTextImage() {
        Map<Character, Appearance> encoding = new HashMap<>();
        int ncolors = Integer.parseInt(lines.get(0));
        TextImage ti = new TextImage(new Size(lines.get(lines.size() - 1).length(), lines.size() - (ncolors + 2)), new Appearance(' ', "#000000", "#000000"));
        for (int i = 1; i <= ncolors; i++) {
            String color = lines.get(i).substring(2, 9);
            encoding.put(lines.get(i).charAt(0), new Appearance(' ', color, color));
        }
        for (int row = ncolors + 2; row < lines.size(); row++) {
            for (int column = 0; column < lines.get(row).length(); column++) {
                ti.setAppearanceAt(new Position(column, row - (ncolors + 2)), encoding.get(lines.get(row).charAt(column)));
            }
        }
        return ti;
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }
}
