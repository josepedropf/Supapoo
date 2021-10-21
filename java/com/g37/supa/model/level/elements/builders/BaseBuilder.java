package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.Base;

import java.io.IOException;

public class BaseBuilder extends LevelElementBuilder<Base> {

    public BaseBuilder(Size elementSize) {
        super(elementSize);
    }

    @Override
    public Base createElement(Position position) throws IOException {
        Base base = new Base(position, getTextImage());
        return base;
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("base").createTextImage();
    }
}
