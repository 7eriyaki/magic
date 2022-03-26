package com.teriyaki.magicworld.events;

import com.teriyaki.magicworld.block.CornerBlock;
import com.teriyaki.magicworld.block.CornerContainerBlock;
import com.teriyaki.magicworld.item.ModItems;
import com.teriyaki.magicworld.util.Config;
import com.teriyaki.magicworld.util.CropBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class ModEvents {
    @SubscribeEvent
    public void onCopperedSheep(AttackEntityEvent event) {
        // Check if player is holding a Coppered Apple
        if (event.getPlayer().getHeldItemMainhand().getItem() == ModItems.COPPERED_APPLE.get()) {
            // Check if the target is a mob (alive)
            if (event.getTarget().isAlive()) {
                // Store target entity (mob)
                LivingEntity target = (LivingEntity)event.getTarget();
                // Check if the target is a Sheep
                if (target instanceof SheepEntity) {
                    // Store the player reference
                    PlayerEntity player = event.getPlayer();
                    // Delete (consume) one item in main hand (coppered apple)
                    player.getHeldItemMainhand().shrink(1);
                    // apply effect to target (sheep)
                    target.addPotionEffect(new EffectInstance(Effects.GLOWING, Config.COPPERED_GLOW_DURATION.get()));

                    if (!player.world.isRemote()) {
                        String msg = TextFormatting.YELLOW + "Sheep is now glowing!";
                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onCopperedSheepDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof SheepEntity) {
            World world = entity.getEntityWorld();
            Collection<ItemEntity> drops = event.getDrops();

            LogManager.getLogger().debug(entity.getActivePotionEffects());

            if (entity.isPotionActive(Effects.GLOWING)) {
                drops.add(new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(),
                        new ItemStack(ModItems.COPPER_INGOT.get())));
            }
        }
    }

    @SubscribeEvent
    public void onCropHarvestWithCopperHoe(BlockEvent.BlockToolInteractEvent event) {
        // Check that player is holding a copper hoe
        if (event.getPlayer().getHeldItemMainhand().getItem() == ModItems.COPPER_HOE.get()) {
            // Store the block that was clicked
            BlockState clickedBlockState = event.getState();
            Block clickedBlock = clickedBlockState.getBlock();

            // Check that the block is a crop
            if (CropBlocks.isCropsBlock(clickedBlock)) {
                CropBlocks cropEnum = CropBlocks.getBlockEnum(clickedBlock);
                // Check that the crop is fully grown
                if (clickedBlockState.get(cropEnum.getAgeProperty()) == cropEnum.getMaxAge()) {
                    // Reset the blockstate Age to default (0)
                    event.getWorld().setBlockState(event.getPos(), clickedBlockState.with(cropEnum.getAgeProperty(), 0), 2);

                    // Play block break sound
                    event.getWorld().playSound(null, event.getPos(), clickedBlockState.getSoundType().getBreakSound(), SoundCategory.BLOCKS, 1f, 1f);

                    // Reduce durability of tool and exhaust player
                    PlayerEntity player = event.getPlayer();
                    useToolExhaustPlayer(event.getPlayer());

                    // Drop the crop harvest items, -1 seed (auto replanted)
                    if (player.world instanceof ServerWorld) {
                        // Get Crop drops
                        List<ItemStack> drops = Block.getDrops(clickedBlockState, (ServerWorld) player.world, event.getPos(), null);

                        // Remove 1 seed from stack (always second item in list)
                        for (ItemStack drop : drops) {
                            if (drop.getItem() == cropEnum.getSeedsItem()) {
                                drop.shrink(1);
                                break;
                            }
                        }

                        // Spawn Items in list of drops
                        drops.forEach((itemDrop) -> {
                            Block.spawnAsEntity(player.world, event.getPos(), itemDrop);
                        });
                        // Not sure why this is here... Was in Block.SpawnDrops()
                        clickedBlockState.spawnAdditionalDrops((ServerWorld) player.world, event.getPos(), ItemStack.EMPTY);
                    }
                }
            }
        }
    }
    private void useToolExhaustPlayer(PlayerEntity player) {
        // Check if the player is not in creative
        if (!player.isCreative()) {
            // Add exhaustion to player (same as breaking a block)
            player.addExhaustion(0.005F);
            // Reduce Durability of Tool
            player.getHeldItemMainhand().attemptDamageItem(1, new Random(), null);
        }
    }


    @SubscribeEvent
    public void onPlaceCornerBlock(BlockEvent.EntityPlaceEvent event) {
        if (event.getEntity() instanceof PlayerEntity && event.getPlacedBlock().getBlock() instanceof CornerContainerBlock) {
            event.setCanceled(true);
            if (event.getPlacedAgainst().getBlock() instanceof CornerContainerBlock) {
                // check if the clicked corner face of CornerBlock is Block face (Should you add to corner block or place new container)
                PlayerEntity player = (PlayerEntity) event.getEntity();
                Vector3d lookVec = player.getLookVec();

                RayTraceContext rayContext = new RayTraceContext( player.getEyePosition(0), player.getEyePosition(0).add(lookVec.scale(5D)), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, null);


                BlockRayTraceResult rayTraceResult = event.getWorld().rayTraceBlocks(rayContext);

//                double xCord = rayTraceResult.hitInfo;
//                double yCord = player.getPosY() + player.getLookVec().getCoordinate(Direction.Axis.Y);
//                double zCord = player.getPosZ() + player.getLookVec().getCoordinate(Direction.Axis.Z);

                if (!player.world.isRemote()) {
                    String msg = TextFormatting.YELLOW + rayTraceResult.getPos().getCoordinatesAsString();
                    player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
                }
            }
            else {
                // Check if block adjacent to clicked face is already corner block (CornerBlock VoxelShape is bounded by placed corner/s)
                    // (If adjecent block is already CornerBlock, add new corner to it)
                    // (Otherwise if block is empty place new cornerBlock)
                    // (If block is not empty, cancel place event)
            }
        }
    }


}
