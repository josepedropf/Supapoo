package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.XEnemy;

import java.io.IOException;

public class XEnemyBuilder extends LevelElementBuilder {
    public XEnemyBuilder(Size elementSize) {
        super(elementSize);
    }

    @Override
    public XEnemy createElement(Position position) throws IOException {
        return new XEnemy(position, getTextImage());
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("xenemy").createTextImage();
    }
}
