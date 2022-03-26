package com.teriyaki.magicworld.util.cornerblocktools;

import net.minecraft.block.Block;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.Collection;

public enum CornerPosition implements IStringSerializable {
    CORNER_POS0("corner_pos0",0, Block.makeCuboidShape(0, 0, 0, 8, 8, 8)),
    CORNER_POS1("corner_pos1",1, Block.makeCuboidShape(8, 0, 0, 16, 8, 8)),
    CORNER_POS2("corner_pos2",2, Block.makeCuboidShape(8, 0, 8, 16, 8, 16)),
    CORNER_POS3("corner_pos3",3, Block.makeCuboidShape(0, 0, 8, 8, 8, 16)),
    CORNER_POS4("corner_pos4",4, Block.makeCuboidShape(0, 8, 0, 8, 16, 8)),
    CORNER_POS5("corner_pos5",5, Block.makeCuboidShape(8, 8, 0, 16, 16, 8)),
    CORNER_POS6("corner_pos6",6, Block.makeCuboidShape(8, 8, 8, 16, 16, 16)),
    CORNER_POS7("corner_pos7",7, Block.makeCuboidShape(0, 8, 8, 8, 16, 16));

    private static final ArrayList<CornerPosition> POSITIONS_LIST = new ArrayList<>();
    static {
        POSITIONS_LIST.add(CORNER_POS0);
        POSITIONS_LIST.add(CORNER_POS1);
        POSITIONS_LIST.add(CORNER_POS2);
        POSITIONS_LIST.add(CORNER_POS3);
        POSITIONS_LIST.add(CORNER_POS4);
        POSITIONS_LIST.add(CORNER_POS5);
        POSITIONS_LIST.add(CORNER_POS6);
        POSITIONS_LIST.add(CORNER_POS7);
    }

    private final String name;
    private final int position;
    private final VoxelShape shape;

    CornerPosition(String name, int position, VoxelShape shape) {
        this.name = name;
        this.position = position;
        this.shape = shape;
    }

    public int getPosition() {
        return this.position;
    }
    public VoxelShape getShape() {
        return this.shape;
    }
    @Override
    public String getString() {
        return this.name;
    }

    public static VoxelShape getShapeByPos(int position) {
        return getCornerByPos(position).getShape();
    }
    public static Collection<CornerPosition> getPosCollection() {
        return POSITIONS_LIST;
    }

    public static CornerPosition getCornerByName(String name) {
        switch(name) {
            case "corner_pos0":
                return CORNER_POS0;
            case "corner_pos1":
                return CORNER_POS1;
            case "corner_pos2":
                return CORNER_POS2;
            case "corner_pos3":
                return CORNER_POS3;
            case "corner_pos4":
                return CORNER_POS4;
            case "corner_pos5":
                return CORNER_POS5;
            case "corner_pos6":
                return CORNER_POS6;
            case "corner_pos7":
            default:
                return CORNER_POS7;
        }
    }

    public static CornerPosition getCornerByPos(int pos) {
        for (CornerPosition cornerPos : POSITIONS_LIST){
            if (cornerPos.position == pos) return cornerPos;
        }
        throw new NullPointerException();
    }

    public static int getPosByName(String name) {
        return getCornerByName(name).position;
    }
}
