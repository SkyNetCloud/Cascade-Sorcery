package net.mcreator.voltage.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.network.PacketBuffer;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.voltage.gui.BatteryGUIGui;
import net.mcreator.voltage.VoltageModVariables;
import net.mcreator.voltage.VoltageModElements;

import java.util.Map;

import io.netty.buffer.Unpooled;

@VoltageModElements.ModElement.Tag
public class PocketbatteryItemInInventoryTickProcedure extends VoltageModElements.ModElement {
	public PocketbatteryItemInInventoryTickProcedure(VoltageModElements instance) {
		super(instance, 13);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PocketbatteryItemInInventoryTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PocketbatteryItemInInventoryTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PocketbatteryItemInInventoryTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PocketbatteryItemInInventoryTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PocketbatteryItemInInventoryTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((VoltageModVariables.MapVariables.get(world).Tenergy) == (VoltageModVariables.MapVariables.get(world).Tmax))
				&& ((VoltageModVariables.MapVariables.get(world).Tenter) == 1))) {
			{
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					BlockPos _bpos = new BlockPos((int) x, (int) y, (int) z);
					NetworkHooks.openGui((ServerPlayerEntity) _ent, new INamedContainerProvider() {
						@Override
						public ITextComponent getDisplayName() {
							return new StringTextComponent("BatteryGUI");
						}

						@Override
						public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
							return new BatteryGUIGui.GuiContainerMod(id, inventory, new PacketBuffer(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			}
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("voltage:alarm")),
						SoundCategory.NEUTRAL, (float) 0.25, (float) 1);
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("voltage:alarm")),
						SoundCategory.NEUTRAL, (float) 0.25, (float) 1, false);
			}
		}
	}
}
