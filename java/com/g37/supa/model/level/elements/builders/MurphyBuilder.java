package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.Murphy;

import java.io.IOException;

public class MurphyBuilder extends LevelElementBuilder {
    public MurphyBuilder(Size elementSize) {
        super(elementSize);
    }

    @Override
    public Murphy createElement(Position position) throws IOException {
        return new Murphy(position, getTextImage(), 1);
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("murphy").createTextImage();
    }
}