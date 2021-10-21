package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.Port;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PortUpBuilder extends LevelElementBuilder {
    public PortUpBuilder(Size elementSize) {
        super(elementSize);
    }

    @Override
    public Port createElement(Position position) throws IOException {
        List<Port.Direction> directions = new ArrayList<>();
        directions.add(Port.Direction.UP);
        return new Port(position, getTextImage(), directions);
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("upport").createTextImage();
    }
}

