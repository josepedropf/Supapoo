package com.g37.supa.model.gui;

import com.g37.supa.model.*;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.MouseCaptureMode;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.EnumSet;

public class LanternaGUI implements GUI {
    private final TerminalScreen screen;
    private final Size size;

    public LanternaGUI(Size size) throws IOException {
        //AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        this.size = size;
        Terminal terminal = createTerminal(size.getWidth(), size.getHeight());
        screen = createScreen(terminal);
    }

    @Override
    public Size getSize() {
        return this.size;
    }

    private TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen screen;
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setTerminalEmulatorTitle("Supa");
        terminalFactory.setMouseCaptureMode(MouseCaptureMode.CLICK_RELEASE_DRAG_MOVE);
        //terminalFactory.setForceAWTOverSwing(true);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    @Override
    public KEYACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return KEYACTION.NONE;
        if (keyStroke.getKeyType() == KeyType.EOF) return KEYACTION.CLOSE;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return KEYACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'p') return KEYACTION.PAUSE;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'r') return KEYACTION.RESTART;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'h') return KEYACTION.SKIP;
        if (keyStroke.isCtrlDown()) {
            if (keyStroke.getKeyType() == KeyType.ArrowUp) return KEYACTION.SLURP_UP;
            if (keyStroke.getKeyType() == KeyType.ArrowRight) return KEYACTION.SLURP_RIGHT;
            if (keyStroke.getKeyType() == KeyType.ArrowDown) return KEYACTION.SLURP_DOWN;
            if (keyStroke.getKeyType() == KeyType.ArrowLeft) return KEYACTION.SLURP_LEFT;
        } else {
            if (keyStroke.getKeyType() == KeyType.ArrowUp) return KEYACTION.UP;
            if (keyStroke.getKeyType() == KeyType.ArrowRight) return KEYACTION.RIGHT;
            if (keyStroke.getKeyType() == KeyType.ArrowDown) return KEYACTION.DOWN;
            if (keyStroke.getKeyType() == KeyType.ArrowLeft) return KEYACTION.LEFT;
        }
        if (keyStroke.getKeyType() == KeyType.Enter) return KEYACTION.SELECT;
        return KEYACTION.NONE;
    }

    @Override
    public void drawCharacter(Position position, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), "" + c);
    }

    @Override
    public void drawText(Position position, Text text) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(text.getForegroundColor()));
        tg.setBackgroundColor(TextColor.Factory.fromString(text.getBackgroundColor()));
        EnumSet<SGR> modifiers = EnumSet.noneOf(SGR.class);
        for (Text.TEXTMODIFIER modifier : text.getModifiers()) {
            switch (modifier) {
                case BLINK:
                    modifiers.add(SGR.BLINK);
                    break;
                case BOLD:
                    modifiers.add(SGR.BOLD);
                    break;
                case BORDERED:
                    modifiers.add(SGR.BORDERED);
                    break;
                case CIRCLED:
                    modifiers.add(SGR.CIRCLED);
                    break;
                case CROSSED_OUT:
                    modifiers.add(SGR.CROSSED_OUT);
                    break;
                case FRAKTUR:
                    modifiers.add(SGR.FRAKTUR);
                    break;
                case ITALIC:
                    modifiers.add(SGR.ITALIC);
                    break;
                case REVERSE:
                    modifiers.add(SGR.REVERSE);
                    break;
                case UNDERLINE:
                    modifiers.add(SGR.UNDERLINE);
                    break;
                default:
                    break;
            }
        }
        tg.putString(position.getX(), position.getY(), text.getString(), modifiers);
    }

    @Override
    public void drawTextImage(Position position, TextImage textImage) {
        TerminalSize size = new TerminalSize(textImage.getSize().getWidth(), textImage.getSize().getHeight());
        BasicTextImage lanternaTextImage = new BasicTextImage(size);
        Appearance appearance;
        TextColor foregroundColor, backgroundColor;
        for (int row = 0; row < size.getRows(); row++) {
            for (int column = 0; column < size.getColumns(); column++) {
                appearance = textImage.getAppearanceAt(new Position(column, row));
                foregroundColor = TextColor.Factory.fromString(appearance.getForegroundColor());
                backgroundColor = TextColor.Factory.fromString(appearance.getBackgroundColor());
                lanternaTextImage.setCharacterAt(column, row, TextCharacter.fromCharacter(appearance.getForm(), foregroundColor, backgroundColor)[0]);
            }
        }
        TextGraphics tg = screen.newTextGraphics();
        tg.drawImage(new TerminalPosition(position.getX(), position.getY()), lanternaTextImage);
    }

    @Override
    public void drawRectangle(Position position, Size size, Appearance appearance) {
        TextGraphics tg = screen.newTextGraphics();
        TerminalPosition tp = new TerminalPosition(position.getX(), position.getY());
        TerminalSize ts = new TerminalSize(size.getWidth(), size.getHeight());
        TextColor foregroundColor, backgroundColor;
        foregroundColor = TextColor.Factory.fromString(appearance.getForegroundColor());
        backgroundColor = TextColor.Factory.fromString(appearance.getBackgroundColor());
        tg.fillRectangle(tp, ts, TextCharacter.fromCharacter(appearance.getForm(), foregroundColor, backgroundColor)[0]);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}