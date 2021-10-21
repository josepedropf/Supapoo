package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.Zonk;

import java.io.IOException;

public class ZonkBuilder extends LevelElementBuilder {
    public ZonkBuilder(Size elementSize) {
        super(elementSize);
    }

    @Override
    public Zonk createElement(Position position) throws IOException {
        return new Zonk(position, getTextImage());
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("zonk").createTextImage();
    }
}