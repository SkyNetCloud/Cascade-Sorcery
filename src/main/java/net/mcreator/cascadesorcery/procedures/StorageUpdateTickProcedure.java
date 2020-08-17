package net.mcreator.voltage.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Explosion;

import net.mcreator.voltage.VoltageModVariables;
import net.mcreator.voltage.VoltageModElements;

import java.util.Map;

@VoltageModElements.ModElement.Tag
public class StorageUpdateTickProcedure extends VoltageModElements.ModElement {
	public StorageUpdateTickProcedure(VoltageModElements instance) {
		super(instance, 12);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure StorageUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure StorageUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure StorageUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure StorageUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((VoltageModVariables.MapVariables.get(world).Tenergy) > 10) && ((VoltageModVariables.MapVariables.get(world).Tenter) == 1))) {
			if (((VoltageModVariables.MapVariables.get(world).Tenergy) == (VoltageModVariables.MapVariables.get(world).Tmax))) {
				VoltageModVariables.MapVariables.get(world).DangerT = (double) ((VoltageModVariables.MapVariables.get(world).DangerT) + 0.5);
				VoltageModVariables.MapVariables.get(world).syncData(world);
				if (((VoltageModVariables.MapVariables.get(world).DangerT) == 500)) {
					if (world instanceof World && !world.getWorld().isRemote) {
						world.getWorld().createExplosion(null, (int) x, (int) y, (int) z, (float) 4, Explosion.Mode.BREAK);
					}
				}
			}
		}
	}
}
