package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.Port;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PortCrossBuilder extends LevelElementBuilder {
    public PortCrossBuilder(Size elementSize) {
        super(elementSize);
    }

    @Override
    public Port createElement(Position position) throws IOException {
        List<Port.Direction> directions = new ArrayList<>();
        directions.add(Port.Direction.DOWN);
        directions.add(Port.Direction.UP);
        directions.add(Port.Direction.LEFT);
        directions.add(Port.Direction.RIGHT);
        return new Port(position, getTextImage(), directions);
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("crossport").createTextImage();
    }
}

