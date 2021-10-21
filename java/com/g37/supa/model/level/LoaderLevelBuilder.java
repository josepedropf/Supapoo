package com.g37.supa.model.level;

import com.g37.supa.model.Position;
import com.g37.supa.model.Size;
import com.g37.supa.model.level.elements.*;
import com.g37.supa.model.level.elements.builders.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoaderLevelBuilder extends LevelBuilder {
    private final int level;
    private final List<String> lines;

    /*
    private final Appearance wallAppearance = new Appearance('W', "#0000ff");
    private final Appearance spikedWallAppearance = new Appearance('#', "#ff0000");
    private final Appearance ramWallAppearance = new Appearance('0', "#00ff00");
    private final Appearance ramSpikedWallAppearance = new Appearance('#', "#00ff00");
    private final Appearance enemyAppearance = new Appearance('S', "#ff2222");
    private final Appearance infotronAppearance = new Appearance('F', "#ff0000");
    private final Appearance baseAppearance = new Appearance((char) 2588, "#00ff00");
    private final Appearance zonkAppearance = new Appearance('O', "#666666");
    private final Appearance xEnemyAppearance = new Appearance('X', "#ffff00");
    private final Appearance endBlockAppearance = new Appearance('E', "#ff6600");
    private final Appearance murphyAppearance = new Appearance('O', "#ff0000");
    */

    public LoaderLevelBuilder(int level) throws IOException {
        this.level = level;

        URL resource = LoaderLevelBuilder.class.getResource("/levels/level" + level + ".lvl");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    @Override
    protected Size getSize() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        int height = lines.size() - 1;
        return new Size(width, height);
    }

    @Override
    protected Size getElementSize() {
        return new Size(10, 5);
    }

    @Override
    protected boolean getGravity() {
        if (lines.get(0).length() == 0) return false;
        return lines.get(0).charAt(0) == 'G';
    }

    @Override
    protected List<Wall> createWalls() throws IOException {
        List<Wall> walls = new ArrayList<>();

        for (int y = 1; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'W')
                    walls.add(new WallBuilder(getElementSize()).createElement(new Position(x, y - 1)));
                //if (line.charAt(x) == 'W') walls.add(new Wall(new Position(x, y-1), wallAppearance, 0, false)if (line.charAt(x) == 'W') walls.add(new WallBuilder().createElement(new Position(x, y-1)));
                /*
                if (line.charAt(x) == '#') walls.add(new Wall(new Position(x, y-1), spikedWallAppearance, 1, false));
                if (line.charAt(x) == '0') walls.add(new Wall(new Position(x, y-1), ramWallAppearance, 0, true));
                if (line.charAt(x) == 'w') walls.add(new Wall(new Position(x, y-1), ramSpikedWallAppearance, 1, true));
                */
            }
        }

        return walls;
    }

    @Override
    protected List<Infotron> createInfotrons() throws IOException {
        List<Infotron> infotrons = new ArrayList<>();

        for (int y = 1; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                //if (line.charAt(x) == 'I') infotrons.add(new Infotron(new Position(x, y-1), infotronAppearance));
                if (line.charAt(x) == 'I')
                    infotrons.add(new InfotronBuilder(getElementSize()).createElement(new Position(x, y - 1)));
            }
        }
        return infotrons;
    }

    @Override
    protected List<Joker> createJokers() throws IOException {
        List<Joker> jokers = new ArrayList<>();

        for (int y = 1; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'J')
                    jokers.add(new JokerBuilder(getElementSize()).createElement(new Position(x, y - 1)));
            }
        }
        return jokers;
    }

    @Override
    protected List<Port> createPorts() throws IOException {
        List<Port> ports = new ArrayList<>();

        for (int y = 1; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                switch (line.charAt(x)) {
                    case 'D':
                        ports.add(new PortDownBuilder(getElementSize()).createElement(new Position(x, y - 1)));
                        break;
                    case 'U':
                        ports.add(new PortUpBuilder(getElementSize()).createElement(new Position(x, y - 1)));
                        break;
                    case 'R':
                        ports.add(new PortRightBuilder(getElementSize()).createElement(new Position(x, y - 1)));
                        break;
                    case 'L':
                        ports.add(new PortLeftBuilder(getElementSize()).createElement(new Position(x, y - 1)));
                        break;
                    case 'H':
                        ports.add(new PortHorizontalBuilder(getElementSize()).createElement(new Position(x, y - 1)));
                        break;
                    case 'V':
                        ports.add(new PortVerticalBuilder(getElementSize()).createElement(new Position(x, y - 1)));
                        break;
                    case 'C':
                        ports.add(new PortCrossBuilder(getElementSize()).createElement(new Position(x, y - 1)));
                        break;
                    default:
                        break;
                }

            }
        }
        return ports;
    }

    @Override
    protected List<Base> createBases() throws IOException {
        List<Base> bases = new ArrayList<>();

        for (int y = 1; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                //if (line.charAt(x) == 'B') bases.add(new Base(new Position(x, y-1), baseAppearance));
                if (line.charAt(x) == 'B')
                    bases.add(new BaseBuilder(getElementSize()).createElement(new Position(x, y - 1)));
            }
        }

        return bases;
    }

    @Override
    protected List<Zonk> createZonks() throws IOException {
        List<Zonk> zonks = new ArrayList<>();

        for (int y = 1; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                //if (line.charAt(x) == 'Z') zonks.add(new Zonk(new Position(x, y-1), zonkAppearance));
                if (line.charAt(x) == 'Z')
                    zonks.add(new ZonkBuilder(getElementSize()).createElement(new Position(x, y - 1)));
            }
        }

        return zonks;
    }

    @Override
    protected List<XEnemy> createXEnemies() throws IOException {
        List<XEnemy> xenemies = new ArrayList<>();

        for (int y = 1; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                //if (line.charAt(x) == 'X') xenemies.add(new XEnemy(new Position(x, y-1), xEnemyAppearance));
                if (line.charAt(x) == 'X')
                    xenemies.add(new XEnemyBuilder(getElementSize()).createElement(new Position(x, y - 1)));
            }
        }

        return xenemies;
    }

    @Override
    protected List<EndBlock> createEndBlocks() throws IOException {
        List<EndBlock> endBlocks = new ArrayList<>();

        for (int y = 1; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                //if (line.charAt(x) == 'E') endBlocks.add(new EndBlock(new Position(x, y-1), endBlockAppearance));
                if (line.charAt(x) == 'E')
                    endBlocks.add(new EndBlockBuilder(getElementSize()).createElement(new Position(x, y - 1)));
            }
        }

        return endBlocks;
    }

    @Override
    protected Murphy createMurphy() throws IOException {
        for (int y = 1; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                //if (line.charAt(x) == 'M') return new Murphy(new Position(x, y-1), murphyAppearance, 5);
                if (line.charAt(x) == 'M')
                    return new MurphyBuilder(getElementSize()).createElement(new Position(x, y - 1));
            }
        }
        return null;
    }


}
