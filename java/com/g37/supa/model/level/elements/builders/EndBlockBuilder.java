package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.EndBlock;

import java.io.IOException;

public class EndBlockBuilder extends LevelElementBuilder {
    public EndBlockBuilder(Size elementSize) {
        super(elementSize);
    }

    @Override
    public EndBlock createElement(Position position) throws IOException {
        return new EndBlock(position, getTextImage());
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("endblock").createTextImage();
    }
}
