package com.g37.supa.model.level.elements.builders;

import com.g37.supa.model.LoaderTextImageBuilder;
import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.TextImage;
import com.g37.supa.model.level.elements.Explosion;

import java.io.IOException;

public class ExplosionBuilder extends LevelElementBuilder {
    Explosion.EXPLOSIONSTATE explosionState;

    public ExplosionBuilder(Size elementSize, Explosion.EXPLOSIONSTATE explosionState) {
        super(elementSize);
        this.explosionState = explosionState;
    }

    @Override
    public Explosion createElement(Position position) throws IOException {
        return new Explosion(position, getTextImage(), explosionState);
    }

    @Override
    protected TextImage getTextImage() throws IOException {
        return new LoaderTextImageBuilder("explosion").createTextImage();
    }
}

