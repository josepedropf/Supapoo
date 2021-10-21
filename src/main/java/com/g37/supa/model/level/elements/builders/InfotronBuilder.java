package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.Infotron;

import java.io.IOException;

public class InfotronBuilder extends LevelElementBuilder {
    public InfotronBuilder(Size elementSize) {
        super(elementSize);
    }

    @Override
    public Infotron createElement(Position position) throws IOException {
        return new Infotron(position, getTextImage());
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("infotron").createTextImage();
    }
}
