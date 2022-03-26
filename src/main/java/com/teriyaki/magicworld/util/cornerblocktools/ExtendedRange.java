package com.teriyaki.magicworld.util.cornerblocktools;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.chunk.AbstractChunkProvider;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class ExtendedRange // SOURCE: IntegratedHen On StackOverflow: https://stackoverflow.com/questions/61717481/how-can-i-get-the-coordinates-of-a-block-the-player-is-looking-at
{

    private Minecraft mc;
    public BlockRayTraceResult mcObjectMouseOver;
    private final double range;
    private final boolean fluidMode;
    private final Entity requestingEntity;
    private Float partialTicks;
    public boolean airTargeted;
    public Direction blockSideHit;

    private AbstractChunkProvider chunkProvider;

    public ExtendedRange(@Nullable Float partialTicks, double range, boolean fluidMode, Minecraft mc, Entity entity)
    {
        this.mc = mc;
        if (partialTicks == null)
        {
            this.partialTicks = 1.0F;
        }
        this.range = range;
        this.fluidMode = fluidMode;
        this.requestingEntity = entity;
    }

    public void getMouseOver()
    {
        if (requestingEntity != null)
        {
            if (this.mc.world != null)
            {
                this.mc.getProfiler().startSection("pick");
                Entity pointedEntity = null;
                double d0 = range; // block reach distance. default 5.0D, max 1024.0D;

                this.mcObjectMouseOver = (BlockRayTraceResult) requestingEntity.pick(d0, partialTicks, fluidMode);
                BlockPos blockPos = this.mcObjectMouseOver.getPos();
                BlockState state = mc.world.getBlockState(blockPos);
                this.blockSideHit = mcObjectMouseOver.getFace();

                this.airTargeted = state.isAir(mc.world, blockPos);

                Vector3d vector3dEyePos = requestingEntity.getEyePosition(partialTicks);
                boolean flag = false;
                int i = 3;
                double d1 = d0;
                if (d0 > 3.0D) // if range is larger than 3 blocks
                {
                    flag = true;
                }

                if (this.mcObjectMouseOver != null)
                {
                    d1 = this.mcObjectMouseOver.getHitVec().distanceTo(vector3dEyePos); // distance between ray trace and eye position
                }

                Vector3d vector3d1 = requestingEntity.getLook(1.0F); // get vector from angle of look
                Vector3d vector3d2 = vector3dEyePos.add(vector3d1.x * d0, vector3d1.y * d0, vector3d1.z * d0); // add range multiplied by where entity is looking
                Vector3d vector3d3 = null;
                float f = 1.0F;
                List<Entity> list = this.mc.world.getEntitiesInAABBexcluding(requestingEntity, requestingEntity.getBoundingBox().expand(vector3d1.x * d0, vector3d1.y * d0, vector3d1.z * d0).grow(1.0D, 1.0D, 1.0D), EntityPredicates.NOT_SPECTATING.and(Entity::canBeCollidedWith));
                double d2 = d1; // d1 is either range or distance between ray trace and eye position

                for (Entity entity1 : list)
                {
                    AxisAlignedBB axisalignedbb = entity1.getBoundingBox().grow((double) entity1.getCollisionBorderSize());

                    BlockRayTraceResult raytraceresult = null;
                    if (axisalignedbb.intersects(vector3dEyePos, vector3d2)) { // vector between eye position and range location
                        Vector3d hitVectorPos = axisalignedbb.rayTrace(vector3dEyePos, vector3d2).get();

                        double dx = vector3d2.x - vector3dEyePos.x;
                        double dy = vector3d2.y - vector3dEyePos.y;
                        double dz = vector3d2.z - vector3dEyePos.z;
                        raytraceresult = new BlockRayTraceResult(hitVectorPos, Objects.requireNonNull(calcSideHit(axisalignedbb, vector3dEyePos, new double[]{1.0D}, (Direction) null, dx, dy, dz)), entity1.getPosition(), false);
                    }

                    if (axisalignedbb.contains(vector3dEyePos)) // if entity is intersected, set entity as intersected
                    {
                        if (d2 >= 0.0D)
                        {
                            pointedEntity = entity1;
                            this.airTargeted = false;

                            vector3d3 = raytraceresult == null ? vector3dEyePos : raytraceresult.getHitVec();
                            d2 = 0.0D;
                        }
                    }
                    else if (raytraceresult != null) // run when entity is targeted...
                    {
                        double d3 = vector3dEyePos.distanceTo(raytraceresult.getHitVec()); // distance between eye position and range location
                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity1.getLowestRidingEntity() == requestingEntity.getLowestRidingEntity() && !entity1.canRiderInteract()) // if the entity found is what the requesting entity is riding
                            {
                                if (d2 == 0.0D)
                                {
                                    pointedEntity = entity1;
                                    this.airTargeted = false;

                                    vector3d3 = raytraceresult.getHitVec();
                                }
                            }
                            else
                            {
                                pointedEntity = entity1;
                                this.airTargeted = false;

                                vector3d3 = raytraceresult.getHitVec();
                                d2 = d3;
                            }
                        }
                    }

                }

                if (pointedEntity != null && flag && vector3dEyePos.distanceTo(vector3d3) > 3.0D) // if * AND reach is greater than 3 blocks AND
                {
                    this.mcObjectMouseOver = new BlockRayTraceResult(vector3d3, (Direction) null, new BlockPos(vector3d3), false);
                }

                if (pointedEntity != null && (d2 < d1 || this.mcObjectMouseOver == null))
                {
                    this.mcObjectMouseOver = new BlockRayTraceResult(vector3d3, null,pointedEntity.getPosition(), false);
                }

                this.mc.getProfiler().endSection();
            }
        }
    }

    @Nullable
    private static Direction calcSideHit(AxisAlignedBB aabb, Vector3d start, double[] minDistance, @Nullable Direction facing, double deltaX, double deltaY, double deltaZ) {
        if (deltaX > 1.0E-7D) {
            facing = checkSideForHit(minDistance, facing, deltaX, deltaY, deltaZ, aabb.minX, aabb.minY, aabb.maxY, aabb.minZ, aabb.maxZ, Direction.WEST, start.x, start.y, start.z);
        } else if (deltaX < -1.0E-7D) {
            facing = checkSideForHit(minDistance, facing, deltaX, deltaY, deltaZ, aabb.maxX, aabb.minY, aabb.maxY, aabb.minZ, aabb.maxZ, Direction.EAST, start.x, start.y, start.z);
        }

        if (deltaY > 1.0E-7D) {
            facing = checkSideForHit(minDistance, facing, deltaY, deltaZ, deltaX, aabb.minY, aabb.minZ, aabb.maxZ, aabb.minX, aabb.maxX, Direction.DOWN, start.y, start.z, start.x);
        } else if (deltaY < -1.0E-7D) {
            facing = checkSideForHit(minDistance, facing, deltaY, deltaZ, deltaX, aabb.maxY, aabb.minZ, aabb.maxZ, aabb.minX, aabb.maxX, Direction.UP, start.y, start.z, start.x);
        }

        if (deltaZ > 1.0E-7D) {
            facing = checkSideForHit(minDistance, facing, deltaZ, deltaX, deltaY, aabb.minZ, aabb.minX, aabb.maxX, aabb.minY, aabb.maxY, Direction.NORTH, start.z, start.x, start.y);
        } else if (deltaZ < -1.0E-7D) {
            facing = checkSideForHit(minDistance, facing, deltaZ, deltaX, deltaY, aabb.maxZ, aabb.minX, aabb.maxX, aabb.minY, aabb.maxY, Direction.SOUTH, start.z, start.x, start.y);
        }

        return facing;
    }
    @Nullable
    private static Direction checkSideForHit(double[] minDistance, @Nullable Direction prevDirection, double distanceSide, double distanceOtherA, double distanceOtherB, double minSide, double minOtherA, double maxOtherA, double minOtherB, double maxOtherB, Direction hitSide, double startSide, double startOtherA, double startOtherB) {
        double d0 = (minSide - startSide) / distanceSide;
        double d1 = startOtherA + d0 * distanceOtherA;
        double d2 = startOtherB + d0 * distanceOtherB;
        if (0.0D < d0 && d0 < minDistance[0] && minOtherA - 1.0E-7D < d1 && d1 < maxOtherA + 1.0E-7D && minOtherB - 1.0E-7D < d2 && d2 < maxOtherB + 1.0E-7D) {
            minDistance[0] = d0;
            return hitSide;
        } else {
            return prevDirection;
        }
    }
}
